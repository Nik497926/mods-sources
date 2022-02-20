package thaumcraft.common.tiles;

import com.gamerforea.eventhelper.util.EventUtils;
import com.gamerforea.thaumcraft.ModUtils;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.research.ScanResult;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockTaintFibres;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
import thaumcraft.common.items.ItemCompassStone;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TileNode extends TileThaumcraft implements INode, IWandable
{
	long lastActive = 0L;
	AspectList aspects = new AspectList();
	AspectList aspectsBase = new AspectList();
	public static HashMap<String, ArrayList<Integer>> locations = new HashMap();
	private NodeType nodeType = NodeType.NORMAL;
	private NodeModifier nodeModifier = null;
	int count = 0;
	int regeneration = -1;
	int wait = 0;
	String id = null;
	byte nodeLock = 0;
	boolean catchUp = false;
	public Entity drainEntity = null;
	public MovingObjectPosition drainCollision = null;
	public int drainColor = 16777215;
	public Color targetColor = new Color(16777215);
	public Color color = new Color(16777215);

	@Override
	public String getId()
	{
		if (this.id == null)
			this.id = this.generateId();

		return this.id;
	}

	public String generateId()
	{
		this.id = this.worldObj.provider.dimensionId + ":" + this.xCoord + ":" + this.yCoord + ":" + this.zCoord;
		if (this.worldObj != null && locations != null)
		{
			ArrayList<Integer> t = new ArrayList();
			t.add(this.worldObj.provider.dimensionId);
			t.add(this.xCoord);
			t.add(this.yCoord);
			t.add(this.zCoord);
			locations.put(this.id, t);
		}

		return this.id;
	}

	@Override
	public void onChunkUnload()
	{
		if (locations != null)
			locations.remove(this.id);

		super.onChunkUnload();
	}

	@Override
	public void validate()
	{
		super.validate();
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.id == null)
			this.generateId();

		boolean change = false;
		change = this.handleHungryNodeFirst(change);
		++this.count;
		this.checkLock();
		if (!this.worldObj.isRemote)
		{
			change = this.handleDischarge(change);
			change = this.handleRecharge(change);
			change = this.handleTaintNode(change);
			change = this.handleNodeStability(change);
			change = this.handleDarkNode(change);
			change = this.handlePureNode(change);
			change = this.handleHungryNodeSecond(change);
			if (change)
			{
				this.markDirty();
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}
		}
		else if (this.getNodeType() == NodeType.DARK && this.count % 50 == 0)
			ItemCompassStone.sinisterNodes.put(new WorldCoordinates(this), System.currentTimeMillis());

	}

	public void nodeChange()
	{
		this.regeneration = -1;
		this.markDirty();
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	public double getDistanceTo(double par1, double par3, double par5)
	{
		double var7 = this.xCoord + 0.5D - par1;
		double var9 = this.yCoord + 0.5D - par3;
		double var11 = this.zCoord + 0.5D - par5;
		return var7 * var7 + var9 * var9 + var11 * var11;
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
	{
		return -1;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
	{
		player.setItemInUse(wandstack, Integer.MAX_VALUE);
		ItemWandCasting wand = (ItemWandCasting) wandstack.getItem();
		wand.setObjectInUse(wandstack, this.xCoord, this.yCoord, this.zCoord);
		return wandstack;
	}

	@Override
	public AspectList getAspects()
	{
		return this.aspects;
	}

	@Override
	public AspectList getAspectsBase()
	{
		return this.aspectsBase;
	}

	@Override
	public void setAspects(AspectList aspects)
	{
		this.aspects = aspects;
		this.aspectsBase = aspects.copy();
	}

	@Override
	public int addToContainer(Aspect aspect, int amount)
	{
		int left = amount + this.aspects.getAmount(aspect) - this.aspectsBase.getAmount(aspect);
		left = left > 0 ? left : 0;
		this.aspects.add(aspect, amount - left);
		return left;
	}

	@Override
	public boolean takeFromContainer(Aspect aspect, int amount)
	{
		return this.aspects.reduce(aspect, amount);
	}

	public Aspect takeRandomPrimalFromSource()
	{
		Aspect[] primals = this.aspects.getPrimalAspects();
		Aspect asp = primals[this.worldObj.rand.nextInt(primals.length)];
		return asp != null && this.aspects.reduce(asp, 1) ? asp : null;
	}

	public Aspect chooseRandomFilteredFromSource(AspectList filter, boolean preserve)
	{
		int min = preserve ? 1 : 0;
		ArrayList<Aspect> validaspects = new ArrayList();

		for (Aspect prim : this.aspects.getAspects())
		{
			if (filter.getAmount(prim) > 0 && this.aspects.getAmount(prim) > min)
				validaspects.add(prim);
		}

		if (validaspects.size() == 0)
			return null;
		Aspect asp = validaspects.get(this.worldObj.rand.nextInt(validaspects.size()));
		if (asp != null && this.aspects.getAmount(asp) > min)
			return asp;
		return null;
	}

	@Override
	public NodeType getNodeType()
	{
		return this.nodeType;
	}

	@Override
	public void setNodeType(NodeType nodeType)
	{
		this.nodeType = nodeType;
	}

	@Override
	public void setNodeModifier(NodeModifier nodeModifier)
	{
		this.nodeModifier = nodeModifier;
	}

	@Override
	public NodeModifier getNodeModifier()
	{
		return this.nodeModifier;
	}

	@Override
	public int getNodeVisBase(Aspect aspect)
	{
		return this.aspectsBase.getAmount(aspect);
	}

	@Override
	public void setNodeVisBase(Aspect aspect, short nodeVisBase)
	{
		if (this.aspectsBase.getAmount(aspect) < nodeVisBase)
			this.aspectsBase.merge(aspect, nodeVisBase);
		else
			this.aspectsBase.reduce(aspect, this.aspectsBase.getAmount(aspect) - nodeVisBase);

	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		this.lastActive = nbttagcompound.getLong("lastActive");
		AspectList al = new AspectList();
		NBTTagList tlist = nbttagcompound.getTagList("AspectsBase", 10);

		for (int j = 0; j < tlist.tagCount(); ++j)
		{
			NBTTagCompound rs = tlist.getCompoundTagAt(j);
			if (rs.hasKey("key"))
				al.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
		}

		short oldBase = nbttagcompound.getShort("nodeVisBase");
		this.aspectsBase = new AspectList();
		if (oldBase > 0 && al.size() == 0)
			for (Aspect a : this.aspects.getAspects())
			{
				this.aspectsBase.merge(a, oldBase);
			}
		else
			this.aspectsBase = al.copy();

		int regen = 600;
		if (this.getNodeModifier() != null)
			switch (this.getNodeModifier())
			{
				case BRIGHT:
					regen = 400;
					break;
				case PALE:
					regen = 900;
					break;
				case FADING:
					regen = 0;
			}

		long ct = System.currentTimeMillis();
		int inc = regen * 75;
		if (regen > 0 && this.lastActive > 0L && ct > this.lastActive + inc)
			this.catchUp = true;

	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setLong("lastActive", this.lastActive);
		NBTTagList tlist = new NBTTagList();
		nbttagcompound.setTag("AspectsBase", tlist);

		for (Aspect aspect : this.aspectsBase.getAspects())
		{
			if (aspect != null)
			{
				NBTTagCompound f = new NBTTagCompound();
				f.setString("key", aspect.getTag());
				f.setInteger("amount", this.aspectsBase.getAmount(aspect));
				tlist.appendTag(f);
			}
		}

	}

	@Override
	public void readCustomNBT(NBTTagCompound nbttagcompound)
	{
		this.id = nbttagcompound.getString("nodeId");
		if (this.worldObj != null && locations != null)
		{
			ArrayList<Integer> t = new ArrayList();
			t.add(this.worldObj.provider.dimensionId);
			t.add(this.xCoord);
			t.add(this.yCoord);
			t.add(this.zCoord);
			locations.put(this.id, t);
		}

		this.setNodeType(NodeType.values()[nbttagcompound.getByte("type")]);
		byte mod = nbttagcompound.getByte("modifier");
		if (mod >= 0)
			this.setNodeModifier(NodeModifier.values()[mod]);
		else
			this.setNodeModifier(null);

		this.aspects.readFromNBT(nbttagcompound);
		String de = nbttagcompound.getString("drainer");
		if (de != null && de.length() > 0 && this.getWorldObj() != null)
		{
			this.drainEntity = this.getWorldObj().getPlayerEntityByName(de);
			if (this.drainEntity != null)
				this.drainCollision = new MovingObjectPosition(this.xCoord, this.yCoord, this.zCoord, 0, Vec3.createVectorHelper(this.drainEntity.posX, this.drainEntity.posY, this.drainEntity.posZ));
		}

		this.drainColor = nbttagcompound.getInteger("draincolor");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbttagcompound)
	{
		if (this.id == null)
			this.id = this.generateId();

		if (this.worldObj != null && locations != null)
		{
			ArrayList<Integer> t = new ArrayList();
			t.add(this.worldObj.provider.dimensionId);
			t.add(this.xCoord);
			t.add(this.yCoord);
			t.add(this.zCoord);
			locations.put(this.id, t);
		}

		nbttagcompound.setString("nodeId", this.id);
		nbttagcompound.setByte("type", (byte) this.getNodeType().ordinal());
		nbttagcompound.setByte("modifier", this.getNodeModifier() == null ? -1 : (byte) this.getNodeModifier().ordinal());
		this.aspects.writeToNBT(nbttagcompound);
		if (this.drainEntity instanceof EntityPlayer)
			nbttagcompound.setString("drainer", this.drainEntity.getCommandSenderName());

		nbttagcompound.setInteger("draincolor", this.drainColor);
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count)
	{
		boolean mfu = false;
		ItemWandCasting wand = (ItemWandCasting) wandstack.getItem();
		MovingObjectPosition movingobjectposition = EntityUtils.getMovingObjectPositionFromPlayer(this.worldObj, player, true);
		if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
		{
			int i = movingobjectposition.blockX;
			int j = movingobjectposition.blockY;
			int k = movingobjectposition.blockZ;
			if (i != this.xCoord || j != this.yCoord || k != this.zCoord)
				player.stopUsingItem();
		}
		else
			player.stopUsingItem();

		if (count % 5 == 0)
		{
			int tap = 1;
			if (ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODETAPPER1"))
				++tap;

			if (ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODETAPPER2"))
				++tap;

			boolean preserve = !player.isSneaking() && ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODEPRESERVE") && !wand.getRod(wandstack).getTag().equals("wood") && !wand.getCap(wandstack).getTag().equals("iron");
			boolean success = false;
			Aspect aspect = null;
			if ((aspect = this.chooseRandomFilteredFromSource(wand.getAspectsWithRoom(wandstack), preserve)) != null)
			{
				int amt = this.getAspects().getAmount(aspect);
				if (tap > amt)
					tap = amt;

				if (preserve && tap == amt)
					--tap;

				if (tap > 0)
				{
					int rem = wand.addVis(wandstack, aspect, tap, !this.worldObj.isRemote);
					if (rem < tap)
					{
						this.drainColor = aspect.getColor();
						if (!this.worldObj.isRemote)
						{
							this.takeFromContainer(aspect, tap - rem);
							mfu = true;
						}

						success = true;
					}
				}
			}

			if (success)
			{
				this.drainEntity = player;
				this.drainCollision = movingobjectposition;
				this.targetColor = new Color(this.drainColor);
			}
			else
			{
				this.drainEntity = null;
				this.drainCollision = null;
			}

			if (mfu)
			{
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
				this.markDirty();
			}
		}

		if (player.worldObj.isRemote)
		{
			int r = this.targetColor.getRed();
			int g = this.targetColor.getGreen();
			int b = this.targetColor.getBlue();
			int r2 = this.color.getRed() * 4;
			int g2 = this.color.getGreen() * 4;
			int b2 = this.color.getBlue() * 4;
			this.color = new Color((r + r2) / 5, (g + g2) / 5, (b + b2) / 5);
		}

	}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count)
	{
		this.drainEntity = null;
		this.drainCollision = null;
	}

	@Override
	public boolean receiveClientEvent(int i, int j)
	{
		return super.receiveClientEvent(i, j);
	}

	@Override
	public boolean takeFromContainer(AspectList ot)
	{
		return false;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect tag, int amount)
	{
		return false;
	}

	@Override
	public boolean doesContainerContain(AspectList ot)
	{
		return false;
	}

	@Override
	public int containerContains(Aspect tag)
	{
		return 0;
	}

	@Override
	public boolean doesContainerAccept(Aspect tag)
	{
		return true;
	}

	private boolean handleHungryNodeFirst(boolean change)
	{
		if (this.getNodeType() == NodeType.HUNGRY)
		{
			if (this.worldObj.isRemote)
				for (int a = 0; a < Thaumcraft.proxy.particleCount(1); ++a)
				{
					int tx = this.xCoord + this.worldObj.rand.nextInt(16) - this.worldObj.rand.nextInt(16);
					int ty = this.yCoord + this.worldObj.rand.nextInt(16) - this.worldObj.rand.nextInt(16);
					int tz = this.zCoord + this.worldObj.rand.nextInt(16) - this.worldObj.rand.nextInt(16);
					if (ty > this.worldObj.getHeightValue(tx, tz))
						ty = this.worldObj.getHeightValue(tx, tz);

					Vec3 v1 = Vec3.createVectorHelper(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D);
					Vec3 v2 = Vec3.createVectorHelper(tx + 0.5D, ty + 0.5D, tz + 0.5D);
					MovingObjectPosition mop = ThaumcraftApiHelper.rayTraceIgnoringSource(this.worldObj, v1, v2, true, false, false);
					if (mop != null && this.getDistanceFrom(mop.blockX, mop.blockY, mop.blockZ) < 256.0D)
					{
						tx = mop.blockX;
						ty = mop.blockY;
						tz = mop.blockZ;
						Block bi = this.worldObj.getBlock(tx, ty, tz);
						int md = this.worldObj.getBlockMetadata(tx, ty, tz);
						if (!bi.isAir(this.worldObj, tx, ty, tz))
							Thaumcraft.proxy.hungryNodeFX(this.worldObj, tx, ty, tz, this.xCoord, this.yCoord, this.zCoord, bi, md);
					}
				}

			if (Config.hardNode)
			{
				List<Entity> ents = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1).expand(15.0D, 15.0D, 15.0D));
				if (ents != null && ents.size() > 0)
					for (Entity entity : ents)
					{
						if (!(entity instanceof EntityPlayer) || !((EntityPlayer) entity).capabilities.disableDamage)
						{
							// TODO gamerforEA code start
							if (!(entity instanceof EntityItem) && EventUtils.cantDamage(ModUtils.getModFake(this.worldObj), entity))
								continue;
							// TODO gamerforEA code end

							if (entity.isEntityAlive() && !entity.isEntityInvulnerable())
							{
								double d = this.getDistanceTo(entity.posX, entity.posY, entity.posZ);
								if (d < 2.0D)
								{
									entity.attackEntityFrom(DamageSource.outOfWorld, 1.0F);
									if (!entity.isEntityAlive() && !this.worldObj.isRemote)
									{
										ScanResult scan = new ScanResult((byte) 2, 0, 0, entity, "");
										AspectList al = ScanManager.getScanAspects(scan, this.worldObj);
										if (al != null && al.size() > 0)
										{
											al = ResearchManager.reduceToPrimals(al.copy());
											if (al != null && al.size() > 0)
											{
												Aspect a = al.getAspects()[this.worldObj.rand.nextInt(al.size())];
												if (this.getAspects().getAmount(a) < this.getNodeVisBase(a))
												{
													this.addToContainer(a, 1);
													change = true;
												}
												else if (this.worldObj.rand.nextInt(1 + this.getNodeVisBase(a) * 2) < al.getAmount(a))
												{
													this.aspectsBase.add(a, 1);
													change = true;
												}
											}
										}
									}
								}
							}

							double distanceX = (this.xCoord + 0.5D - entity.posX) / 15.0D;
							double distanceY = (this.yCoord + 0.5D - entity.posY) / 15.0D;
							double distanceZ = (this.zCoord + 0.5D - entity.posZ) / 15.0D;
							double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
							double distanceDelta = 1.0D - distance;
							if (distanceDelta > 0.0D)
							{
								distanceDelta = distanceDelta * distanceDelta;
								entity.motionX += distanceX / distance * distanceDelta * 0.15D;
								entity.motionY += distanceY / distance * distanceDelta * 0.25D;
								entity.motionZ += distanceZ / distance * distanceDelta * 0.15D;
							}
						}
					}
			}
		}

		return change;
	}

	private boolean handleDischarge(boolean change)
	{
		World world = this.worldObj;
		if (world.getBlock(this.xCoord, this.yCoord, this.zCoord) == ConfigBlocks.blockAiry && this.getLock() != 1)
		{
			NodeModifier nodeModifier = this.getNodeModifier();
			if (nodeModifier == NodeModifier.FADING)
				return change;

			boolean shiny = this.getNodeType() == NodeType.HUNGRY || nodeModifier == NodeModifier.BRIGHT;
			int inc = nodeModifier == null ? 2 : shiny ? 1 : nodeModifier == NodeModifier.PALE ? 3 : 2;
			if (this.count % inc != 0)
				return change;

			if (nodeModifier == NodeModifier.PALE && world.rand.nextBoolean())
				return change;

			int xOffset = world.rand.nextInt(5) - world.rand.nextInt(5);
			int yOffset = world.rand.nextInt(5) - world.rand.nextInt(5);
			int zOffset = world.rand.nextInt(5) - world.rand.nextInt(5);
			if (xOffset != 0 || yOffset != 0 || zOffset != 0)
			{
				int x = this.xCoord + xOffset;
				int y = this.yCoord + yOffset;
				int z = this.zCoord + zOffset;

				/* TODO gamerforEA code replace, old code:
				TileEntity te = this.worldObj.getTileEntity(x, y, z);
				if (te instanceof INode && this.worldObj.getBlock(x, y, z) == ConfigBlocks.blockAiry) */
				if (world.blockExists(x, y, z) && world.getBlock(x, y, z) == ConfigBlocks.blockAiry)
				// TODO gamerforEA code end
				{
					// TODO gamerforEA code start
					TileEntity te = world.getTileEntity(x, y, z);
					if (!(te instanceof INode))
						return change;
					// TODO gamerforEA code end

					if (te instanceof TileNode && ((TileNode) te).getLock() > 0)
						return change;

					INode nd = (INode) te;
					int ndavg = (nd.getAspects().visSize() + nd.getAspectsBase().visSize()) / 2;
					int thisavg = (this.getAspects().visSize() + this.getAspectsBase().visSize()) / 2;
					if (ndavg < thisavg && nd.getAspects().size() > 0)
					{
						Aspect a = nd.getAspects().getAspects()[world.rand.nextInt(nd.getAspects().size())];
						boolean u = false;
						if (this.getAspects().getAmount(a) < this.getNodeVisBase(a) && nd.takeFromContainer(a, 1))
						{
							this.addToContainer(a, 1);
							u = true;
						}
						else if (nd.takeFromContainer(a, 1))
						{
							if (world.rand.nextInt(1 + (int) (this.getNodeVisBase(a) / (shiny ? 1.5D : 1.0D))) == 0)
							{
								this.aspectsBase.add(a, 1);
								if (nodeModifier == NodeModifier.PALE && world.rand.nextInt(100) == 0)
								{
									this.setNodeModifier(null);
									this.regeneration = -1;
								}

								if (world.rand.nextInt(3) == 0)
									nd.setNodeVisBase(a, (short) (nd.getNodeVisBase(a) - 1));
							}

							u = true;
						}

						if (u)
						{
							// TODO gamerforEA code start
							if (te instanceof TileNode)
								// TODO gamerforEA code end
								((TileNode) te).wait = ((TileNode) te).regeneration / 2;

							world.markBlockForUpdate(x, y, z);
							te.markDirty();
							change = true;
							PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockZap(this.xCoord + xOffset + 0.5F, this.yCoord + yOffset + 0.5F, this.zCoord + zOffset + 0.5F, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F), new TargetPoint(world.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 32.0D));
						}
					}
				}
			}

			return change;
		}

		return change;
	}

	private boolean handleRecharge(boolean change)
	{
		if (this.regeneration < 0)
		{
			this.regeneration = 600;
			if (this.getNodeModifier() != null)
				switch (this.getNodeModifier())
				{
					case BRIGHT:
						this.regeneration = 400;
						break;
					case PALE:
						this.regeneration = 900;
						break;
					case FADING:
						this.regeneration = 0;
				}

			if (this.getLock() == 1)
				this.regeneration *= 2;

			if (this.getLock() == 2)
				this.regeneration *= 20;
		}

		if (this.catchUp)
		{
			this.catchUp = false;
			long ct = System.currentTimeMillis();
			int inc = this.regeneration * 75;
			int amt = inc > 0 ? (int) ((ct - this.lastActive) / inc) : 0;
			if (amt > 0)
				for (int a = 0; a < Math.min(amt, this.aspectsBase.visSize()); ++a)
				{
					AspectList al = new AspectList();

					for (Aspect aspect : this.getAspects().getAspects())
					{
						if (this.getAspects().getAmount(aspect) < this.getNodeVisBase(aspect))
							al.add(aspect, 1);
					}

					if (al.size() > 0)
						this.addToContainer(al.getAspects()[this.worldObj.rand.nextInt(al.size())], 1);
				}
		}

		if (this.count % 1200 == 0)
		{
			for (Aspect aspect : this.getAspects().getAspects())
			{
				if (this.getAspects().getAmount(aspect) <= 0)
				{
					this.setNodeVisBase(aspect, (short) (this.getNodeVisBase(aspect) - 1));
					if (this.worldObj.rand.nextInt(20) == 0 || this.getNodeVisBase(aspect) <= 0)
					{
						this.getAspects().remove(aspect);
						if (this.worldObj.rand.nextInt(5) == 0)
						{
							if (this.getNodeModifier() == NodeModifier.BRIGHT)
								this.setNodeModifier(null);
							else if (this.getNodeModifier() == null)
								this.setNodeModifier(NodeModifier.PALE);

							if (this.getNodeModifier() == NodeModifier.PALE && this.worldObj.rand.nextInt(5) == 0)
								this.setNodeModifier(NodeModifier.FADING);
						}

						this.nodeChange();
						break;
					}

					this.nodeChange();
				}
			}

			if (this.getAspects().size() <= 0)
			{
				this.invalidate();
				if (this.getBlockType() == ConfigBlocks.blockAiry)
					this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
				else if (this.getBlockType() == ConfigBlocks.blockMagicalLog)
					this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) - 1, 3);
			}
		}

		if (this.wait > 0)
			--this.wait;

		if (this.regeneration > 0 && this.wait == 0 && this.count % this.regeneration == 0)
		{
			this.lastActive = System.currentTimeMillis();
			AspectList al = new AspectList();

			for (Aspect aspect : this.getAspects().getAspects())
			{
				if (this.getAspects().getAmount(aspect) < this.getNodeVisBase(aspect))
					al.add(aspect, 1);
			}

			if (al.size() > 0)
			{
				this.addToContainer(al.getAspects()[this.worldObj.rand.nextInt(al.size())], 1);
				change = true;
			}
		}

		return change;
	}

	private boolean handleTaintNode(boolean change)
	{
		if (this.getNodeType() == NodeType.TAINTED && this.count % 50 == 0)
		{
			int x = 0;
			int z = 0;
			int y = 0;
			x = this.xCoord + this.worldObj.rand.nextInt(8) - this.worldObj.rand.nextInt(8);
			z = this.zCoord + this.worldObj.rand.nextInt(8) - this.worldObj.rand.nextInt(8);
			BiomeGenBase bg = this.worldObj.getBiomeGenForCoords(x, z);
			if (bg.biomeID != ThaumcraftWorldGenerator.biomeTaint.biomeID)
				Utils.setBiomeAt(this.worldObj, x, z, ThaumcraftWorldGenerator.biomeTaint);

			if (Config.hardNode && this.worldObj.rand.nextBoolean())
			{
				x = this.xCoord + this.worldObj.rand.nextInt(5) - this.worldObj.rand.nextInt(5);
				z = this.zCoord + this.worldObj.rand.nextInt(5) - this.worldObj.rand.nextInt(5);
				y = this.yCoord + this.worldObj.rand.nextInt(5) - this.worldObj.rand.nextInt(5);
				if (BlockTaintFibres.spreadFibres(this.worldObj, x, y, z))
					;
			}
		}
		else if (this.getNodeType() != NodeType.PURE && this.getNodeType() != NodeType.TAINTED && this.count % 100 == 0)
		{
			BiomeGenBase bg = this.worldObj.getBiomeGenForCoords(this.xCoord, this.zCoord);
			if (bg.biomeID == ThaumcraftWorldGenerator.biomeTaint.biomeID && this.worldObj.rand.nextInt(500) == 0)
			{
				this.setNodeType(NodeType.TAINTED);
				this.nodeChange();
			}
		}

		return change;
	}

	private boolean handleNodeStability(boolean change)
	{
		if (this.count % 100 == 0)
		{
			if (this.getNodeType() == NodeType.UNSTABLE && this.worldObj.rand.nextBoolean())
				if (this.getLock() == 0)
				{
					Aspect aspect = this.takeRandomPrimalFromSource();
					if (aspect != null)
					{
						EntityAspectOrb orb = new EntityAspectOrb(this.worldObj, this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, aspect, 1);
						this.worldObj.spawnEntityInWorld(orb);
						change = true;
					}
				}
				else if (this.worldObj.rand.nextInt(10000 / this.getLock()) == 42)
				{
					this.setNodeType(NodeType.NORMAL);
					change = true;
				}

			if (this.getNodeModifier() == NodeModifier.FADING && this.getLock() > 0 && this.worldObj.rand.nextInt(12500 / this.getLock()) == 69)
			{
				this.setNodeModifier(NodeModifier.PALE);
				change = true;
			}
		}

		return change;
	}

	private boolean handlePureNode(boolean change)
	{
		int dimbl = ThaumcraftWorldGenerator.getDimBlacklist(this.worldObj.provider.dimensionId);
		if (this.worldObj.provider.dimensionId != -1 && this.worldObj.provider.dimensionId != 1 && dimbl != 0 && dimbl != 2 && this.getNodeType() == NodeType.PURE && this.count % 50 == 0)
		{
			int x = this.xCoord + this.worldObj.rand.nextInt(8) - this.worldObj.rand.nextInt(8);
			int z = this.zCoord + this.worldObj.rand.nextInt(8) - this.worldObj.rand.nextInt(8);
			BiomeGenBase bg = this.worldObj.getBiomeGenForCoords(x, z);
			int biobl = ThaumcraftWorldGenerator.getBiomeBlacklist(bg.biomeID);
			if (biobl != 0 && biobl != 2 && bg.biomeID != ThaumcraftWorldGenerator.biomeMagicalForest.biomeID)
				if (bg.biomeID == ThaumcraftWorldGenerator.biomeTaint.biomeID)
					Utils.setBiomeAt(this.worldObj, x, z, ThaumcraftWorldGenerator.biomeMagicalForest);
				else if (this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) == ConfigBlocks.blockMagicalLog)
					Utils.setBiomeAt(this.worldObj, x, z, ThaumcraftWorldGenerator.biomeMagicalForest);
		}

		return change;
	}

	private boolean handleDarkNode(boolean change)
	{
		int dimbl = ThaumcraftWorldGenerator.getDimBlacklist(this.worldObj.provider.dimensionId);
		int biobl = ThaumcraftWorldGenerator.getBiomeBlacklist(this.worldObj.getBiomeGenForCoords(this.xCoord, this.zCoord).biomeID);
		if (biobl != 0 && biobl != 2 && this.worldObj.provider.dimensionId != -1 && this.worldObj.provider.dimensionId != 1 && dimbl != 0 && dimbl != 2 && this.getNodeType() == NodeType.DARK && this.count % 50 == 0)
		{
			int x = this.xCoord + this.worldObj.rand.nextInt(12) - this.worldObj.rand.nextInt(12);
			int z = this.zCoord + this.worldObj.rand.nextInt(12) - this.worldObj.rand.nextInt(12);
			BiomeGenBase bg = this.worldObj.getBiomeGenForCoords(x, z);
			if (bg.biomeID != ThaumcraftWorldGenerator.biomeEerie.biomeID)
				Utils.setBiomeAt(this.worldObj, x, z, ThaumcraftWorldGenerator.biomeEerie);

			if (Config.hardNode && this.worldObj.rand.nextBoolean() && this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, 24.0D) != null)
			{
				EntityGiantBrainyZombie entity = new EntityGiantBrainyZombie(this.worldObj);
				if (entity != null)
				{
					int j = this.worldObj.getEntitiesWithinAABB(entity.getClass(), AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1).expand(10.0D, 6.0D, 10.0D)).size();
					if (j <= 3)
					{
						double d0 = this.xCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 5.0D;
						double d3 = this.yCoord + this.worldObj.rand.nextInt(3) - 1;
						double d4 = this.zCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 5.0D;
						EntityLiving entityliving = entity instanceof EntityLiving ? entity : null;
						entity.setLocationAndAngles(d0, d3, d4, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
						if (entityliving == null || entityliving.getCanSpawnHere())
						{
							this.worldObj.spawnEntityInWorld(entityliving);
							this.worldObj.playAuxSFX(2004, this.xCoord, this.yCoord, this.zCoord, 0);
							if (entityliving != null)
								entityliving.spawnExplosionParticle();
						}
					}
				}
			}
		}

		return change;
	}

	private boolean handleHungryNodeSecond(boolean change) {
		return change;
	}

	public byte getLock()
	{
		return this.nodeLock;
	}

	public void checkLock()
	{
		if ((this.count <= 1 || this.count % 50 == 0) && this.yCoord > 0 && this.getBlockType() == ConfigBlocks.blockAiry)
		{
			byte oldLock = this.nodeLock;
			this.nodeLock = 0;

			/* TODO gamerforEA code replace, old code:
			if (!this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord - 1, this.zCoord) && this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == ConfigBlocks.blockStoneDevice)
				if (this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord) == 9)
					this.nodeLock = 1;
				else if (this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord) == 10)
					this.nodeLock = 2; */
			int x = this.xCoord;
			int y = this.yCoord - 1;
			int z = this.zCoord;
			if (this.worldObj.getBlock(x, y, z) == ConfigBlocks.blockStoneDevice)
			{
				int meta = this.worldObj.getBlockMetadata(x, y, z);
				if ((meta == 9 || meta == 10) && !this.worldObj.isBlockIndirectlyGettingPowered(x, y, z))
					if (meta == 9)
						this.nodeLock = 1;
					else
						this.nodeLock = 2;
			}
			// TODO gamerforEA code end

			if (oldLock != this.nodeLock)
				this.regeneration = -1;
		}

	}
}
