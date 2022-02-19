package thaumcraft.api.visnet;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;

import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public abstract class TileVisNode extends TileThaumcraft
{
	WeakReference<TileVisNode> parent = null;
	ArrayList<WeakReference<TileVisNode>> children = new ArrayList();
	protected int nodeCounter = 0;
	private boolean nodeRegged = false;
	public boolean nodeRefresh = false;

	public WorldCoordinates getLocation()
	{
		return new WorldCoordinates(this);
	}

	public abstract int getRange();

	public abstract boolean isSource();

	/* TODO gamerforEA code replace, old code:
	public int consumeVis(Aspect aspect, int vis)
	{
		if (VisNetHandler.isNodeValid(this.getParent()))
		{
			int out = this.getParent().get().consumeVis(aspect, vis);
			if (out > 0)
				this.triggerConsumeEffect(aspect);

			return out;
		}
		else
			return 0;
	} */
	private static int nextNodeId;
	private final boolean consumeVisOverride = consumeVisOverride(this);
	private final int nodeId = nextNodeId++;

	private static final ThreadLocal<TIntSet> CONSUMING_NODES = new ThreadLocal<>();
	private static final ThreadLocal<TIntSet> REMOVING_NODES = new ThreadLocal<>();

	public int consumeVis(Aspect aspect, int vis)
	{
		int out = 0;

		TIntSet consumingNodes = CONSUMING_NODES.get();
		if (consumingNodes == null)
			CONSUMING_NODES.set(consumingNodes = new TIntHashSet());
		if (consumingNodes.add(this.nodeId))
		{
			TIntList visitedNodes = new TIntArrayList();
			try
			{
				Deque<TileVisNode> stack = new ArrayDeque<>();
				stack.push(this);

				while (true)
				{
					TileVisNode node = stack.peek();
					if (VisNetHandler.isNodeValid(node.getParent()))
					{
						TileVisNode parent = node.getParent().get();
						if (parent.consumeVisOverride)
						{
							out = parent.consumeVis(aspect, vis);
							break;
						}
						if (!consumingNodes.add(parent.nodeId))
							break;
						visitedNodes.add(parent.nodeId);
						stack.push(parent);
					}
					else
						break;
				}

				if (out > 0)
					while (!stack.isEmpty())
					{
						TileVisNode node = stack.pop();
						node.triggerConsumeEffect(aspect);
					}

			}
			finally
			{
				consumingNodes.removeAll(visitedNodes);
				consumingNodes.remove(this.nodeId);
			}
		}

		return out;
	}

	private static boolean consumeVisOverride(TileVisNode node)
	{
		try
		{
			return node.getClass().getMethod("consumeVis", Aspect.class, int.class).getDeclaringClass() != TileVisNode.class;
		}
		catch (Throwable ignored)
		{
		}

		return true;
	}
	// TODO gamerforEA code end

	public void removeThisNode()
	{
		// TODO gamerforEA code start
		TIntSet removingNodes = REMOVING_NODES.get();
		if (removingNodes == null)
			CONSUMING_NODES.set(removingNodes = new TIntHashSet());
		if (!removingNodes.add(this.nodeId))
			return;
		// TODO gamerforEA code end

		try
		{
			for (WeakReference<TileVisNode> n : this.getChildren())
			{
				if (n != null && n.get() != null)
					n.get().removeThisNode();
			}

			this.children = new ArrayList();
			if (VisNetHandler.isNodeValid(this.getParent()))
				this.getParent().get().nodeRefresh = true;

			this.setParent(null);
			this.parentChanged();
			if (this.isSource())
			{
				HashMap<WorldCoordinates, WeakReference<TileVisNode>> sourcelist = VisNetHandler.sources.get(this.worldObj.provider.dimensionId);
				if (sourcelist == null)
					sourcelist = new HashMap<>();

				sourcelist.remove(this.getLocation());
				VisNetHandler.sources.put(this.worldObj.provider.dimensionId, sourcelist);
			}

			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		}
		finally
		{
			// TODO gamerforEA code start
			removingNodes.remove(this.nodeId);
			// TODO gamerforEA code end
		}
	}

	@Override
	public void invalidate()
	{
		this.removeThisNode();
		super.invalidate();
	}

	public void triggerConsumeEffect(Aspect aspect)
	{
	}

	public WeakReference<TileVisNode> getParent()
	{
		return this.parent;
	}

	public WeakReference<TileVisNode> getRootSource()
	{
		return VisNetHandler.isNodeValid(this.getParent()) ? this.getParent().get().getRootSource() : this.isSource() ? new WeakReference<>(this) : null;
	}

	public void setParent(WeakReference<TileVisNode> parent)
	{
		this.parent = parent;
	}

	public ArrayList<WeakReference<TileVisNode>> getChildren()
	{
		return this.children;
	}

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote && (this.nodeCounter++ % 40 == 0 || this.nodeRefresh))
		{
			if (!this.nodeRefresh && this.children.size() > 0)
				for (WeakReference<TileVisNode> n : this.children)
				{
					if (n == null || n.get() == null || !VisNetHandler.canNodeBeSeen(this, n.get()))
					{
						this.nodeRefresh = true;
						break;
					}
				}

			if (this.nodeRefresh)
			{
				for (WeakReference<TileVisNode> n : this.children)
				{
					if (n.get() != null)
						n.get().nodeRefresh = true;
				}

				this.children.clear();
				this.parent = null;
			}

			if (this.isSource() && !this.nodeRegged)
			{
				VisNetHandler.addSource(this.getWorldObj(), this);
				this.nodeRegged = true;
			}
			else if (!this.isSource() && !VisNetHandler.isNodeValid(this.getParent()))
			{
				this.setParent(VisNetHandler.addNode(this.getWorldObj(), this));
				this.nodeRefresh = true;
			}

			if (this.nodeRefresh)
			{
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
				this.parentChanged();
			}

			this.nodeRefresh = false;
		}

	}

	public void parentChanged()
	{
	}

	public byte getAttunement()
	{
		return (byte) -1;
	}
}
