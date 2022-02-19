package thaumcraft.api.visnet;

import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class VisNetHandler
{
	public static HashMap<Integer, HashMap<WorldCoordinates, WeakReference<TileVisNode>>> sources = new HashMap<>();
	static ArrayList<WorldCoordinates> cache = new ArrayList<>();
	private static HashMap<WorldCoordinates, ArrayList<WeakReference<TileVisNode>>> nearbyNodes = new HashMap<>();

	public static int drainVis(World world, int x, int y, int z, Aspect aspect, int amount)
	{
		int drainedAmount = 0;
		WorldCoordinates drainer = new WorldCoordinates(x, y, z, world.provider.dimensionId);
		if (!nearbyNodes.containsKey(drainer))
			calculateNearbyNodes(world, x, y, z);

		ArrayList<WeakReference<TileVisNode>> nodes = nearbyNodes.get(drainer);
		if (nodes != null && nodes.size() > 0)
			for (WeakReference<TileVisNode> noderef : nodes)
			{
				TileVisNode node = noderef.get();
				if (node != null)
				{
					int a = node.consumeVis(aspect, amount);
					drainedAmount += a;
					amount -= a;
					if (a > 0)
					{
						int color = Aspect.getPrimalAspects().indexOf(aspect);
						generateVisEffect(world.provider.dimensionId, x, y, z, node.xCoord, node.yCoord, node.zCoord, color);
					}

					if (amount <= 0)
						break;
				}
			}

		return drainedAmount;
	}

	public static void generateVisEffect(int dim, int x, int y, int z, int x2, int y2, int z2, int color)
	{
		ThaumcraftApi.internalMethods.generateVisEffect(dim, x, y, z, x2, y2, z2, color);
	}

	public static void addSource(World world, TileVisNode vs)
	{
		HashMap<WorldCoordinates, WeakReference<TileVisNode>> sourcelist = sources.get(world.provider.dimensionId);
		if (sourcelist == null)
			sourcelist = new HashMap<>();

		sourcelist.put(vs.getLocation(), new WeakReference<>(vs));
		sources.put(world.provider.dimensionId, sourcelist);
		nearbyNodes.clear();
	}

	public static boolean isNodeValid(WeakReference<TileVisNode> node)
	{
		return node != null && node.get() != null && !node.get().isInvalid();
	}

	public static WeakReference<TileVisNode> addNode(World world, TileVisNode vn)
	{
		WeakReference<TileVisNode> ref = new WeakReference<>(vn);
		HashMap<WorldCoordinates, WeakReference<TileVisNode>> sourcelist = sources.get(world.provider.dimensionId);
		if (sourcelist == null)
		{
			new HashMap();
			return null;
		}
		else
		{
			ArrayList<Object[]> nearby = new ArrayList<>();

			for (WeakReference<TileVisNode> root : sourcelist.values())
			{
				if (isNodeValid(root))
				{
					TileVisNode source = root.get();
					float r = inRange(world, vn.getLocation(), source.getLocation(), vn.getRange());
					if (r > 0.0F)
						nearby.add(new Object[] { source, r - vn.getRange() * 2 });

					nearby = findClosestNodes(vn, source, nearby);
					cache.clear();
				}
			}

			float dist = Float.MAX_VALUE;
			TileVisNode closest = null;
			if (nearby.size() > 0)
				for (Object[] o : nearby)
				{
					if ((Float) o[1] < dist && (vn.getAttunement() == -1 || ((TileVisNode) o[0]).getAttunement() == -1 || vn.getAttunement() == ((TileVisNode) o[0]).getAttunement()) && canNodeBeSeen(vn, (TileVisNode) o[0]))
					{
						dist = (Float) o[1];
						closest = (TileVisNode) o[0];
					}
				}

			if (closest != null)
			{
				closest.getChildren().add(ref);
				nearbyNodes.clear();
				return new WeakReference<>(closest);
			}
			else
				return null;
		}
	}

	public static ArrayList<Object[]> findClosestNodes(TileVisNode target, TileVisNode parent, ArrayList<Object[]> in)
	{
		if (cache.size() <= 512 && !cache.contains(new WorldCoordinates(parent)))
		{
			cache.add(new WorldCoordinates(parent));

			for (WeakReference<TileVisNode> childWR : parent.getChildren())
			{
				TileVisNode child = childWR.get();
				if (child != null && !child.equals(target) && !child.equals(parent))
				{
					float r2 = inRange(child.getWorldObj(), child.getLocation(), target.getLocation(), target.getRange());
					if (r2 > 0.0F)
						in.add(new Object[] { child, r2 });

					in = findClosestNodes(target, child, in);
				}
			}

			return in;
		}
		else
			return in;
	}

	private static float inRange(World world, WorldCoordinates cc1, WorldCoordinates cc2, int range)
	{
		float distance = cc1.getDistanceSquaredToWorldCoordinates(cc2);
		return distance > range * range ? -1.0F : distance;
	}

	private static void calculateNearbyNodes(World world, int x, int y, int z)
	{
		HashMap<WorldCoordinates, WeakReference<TileVisNode>> sourcelist = sources.get(world.provider.dimensionId);
		if (sourcelist == null)
			new HashMap();
		else
		{
			ArrayList<WeakReference<TileVisNode>> cn = new ArrayList<>();
			WorldCoordinates drainer = new WorldCoordinates(x, y, z, world.provider.dimensionId);
			new ArrayList();

			for (WeakReference<TileVisNode> root : sourcelist.values())
			{
				if (isNodeValid(root))
				{
					TileVisNode source = root.get();
					TileVisNode closest = null;
					float range = Float.MAX_VALUE;
					float r = inRange(world, drainer, source.getLocation(), source.getRange());
					if (r > 0.0F)
					{
						range = r;
						closest = source;
					}

					ArrayList<WeakReference<TileVisNode>> children = new ArrayList<>();

					for (WeakReference<TileVisNode> child : getAllChildren(source, children))
					{
						TileVisNode n = child.get();

						// TODO gamerforEA code replace, old code:
						// if (n != null && !n.equals(root))
						if (n != null && !n.equals(source))
						// TODO gamerforEA code end
						{
							float r2 = inRange(n.getWorldObj(), n.getLocation(), drainer, n.getRange());
							if (r2 > 0.0F && r2 < range)
							{
								range = r2;
								closest = n;
							}
						}
					}

					if (closest != null)
						cn.add(new WeakReference<>(closest));
				}
			}

			nearbyNodes.put(drainer, cn);
		}
	}

	/* TODO gamerforEA code replace, old code:
	private static ArrayList<WeakReference<TileVisNode>> getAllChildren(TileVisNode source, ArrayList<WeakReference<TileVisNode>> list)
	{
		for (WeakReference<TileVisNode> child : source.getChildren())
		{
			TileVisNode n = child.get();
			if (n != null && n.getWorldObj() != null && isChunkLoaded(n.getWorldObj(), n.xCoord, n.zCoord))
			{
				list.add(child);
				list = getAllChildren(n, list);
			}
		}
	
		return list;
	} */
	private static ArrayList<WeakReference<TileVisNode>> getAllChildren(TileVisNode source, ArrayList<WeakReference<TileVisNode>> list)
	{
		return getAllChildren(new HashSet<>(), source, list);
	}

	private static ArrayList<WeakReference<TileVisNode>> getAllChildren(Set<TileVisNode> tiles, TileVisNode source, ArrayList<WeakReference<TileVisNode>> list)
	{
		if (!tiles.add(source))
			return list;

		for (WeakReference<TileVisNode> child : source.getChildren())
		{
			TileVisNode n = child.get();
			if (n != null && n.getWorldObj() != null && isChunkLoaded(n.getWorldObj(), n.xCoord, n.zCoord))
			{
				list.add(child);
				list = getAllChildren(tiles, n, list);
			}
		}

		return list;
	}
	// TODO gamerforEA code end

	public static boolean isChunkLoaded(World world, int x, int z)
	{
		int xx = x >> 4;
		int zz = z >> 4;
		return world.getChunkProvider().chunkExists(xx, zz);
	}

	public static boolean canNodeBeSeen(TileVisNode source, TileVisNode target)
	{
		MovingObjectPosition mop = ThaumcraftApiHelper.rayTraceIgnoringSource(source.getWorldObj(), Vec3.createVectorHelper(source.xCoord + 0.5D, source.yCoord + 0.5D, source.zCoord + 0.5D), Vec3.createVectorHelper(target.xCoord + 0.5D, target.yCoord + 0.5D, target.zCoord + 0.5D), false, true, false);
		return mop == null || mop.typeOfHit == MovingObjectType.BLOCK && mop.blockX == target.xCoord && mop.blockY == target.yCoord && mop.blockZ == target.zCoord;
	}
}
