package thaumcraft.common.tiles;

import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import com.gamerforea.thaumcraft.ModUtils;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRepairableExtended;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.IWandable;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.equipment.ItemElementalPickaxe;
import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.misc.PacketBoreDig;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.lib.utils.TCVec3;
import thaumcraft.common.lib.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TileArcaneBore extends TileThaumcraft implements IInventory, IWandable
{
	public int spiral = 0;
	public float currentRadius = 0.0F;
	public int maxRadius = 2;
	public float vRadX = 0.0F;
	public float vRadZ = 0.0F;
	public float tRadX = 0.0F;
	public float tRadZ = 0.0F;
	public float mRadX = 0.0F;
	public float mRadZ = 0.0F;
	private int count = 0;
	public int topRotation = 0;
	long soundDelay = 0L;
	Object beam1 = null;
	Object beam2 = null;
	int beamlength = 0;
	TileArcaneBoreBase base = null;
	public ItemStack[] contents = new ItemStack[2];
	public int rotX = 0;
	public int rotZ = 0;
	public int tarX = 0;
	public int tarZ = 0;
	public int speedX = 0;
	public int speedZ = 0;
	public boolean hasFocus = false;
	public boolean hasPickaxe = false;
	int lastX = 0;
	int lastZ = 0;
	int lastY = 0;
	boolean toDig = false;
	int digX = 0;
	int digZ = 0;
	int digY = 0;
	Block digBlock;
	int digMd;
	float radInc;
	int paused;
	int maxPause;
	long repairCounter;
	boolean first;
	public ForgeDirection orientation;
	public ForgeDirection baseOrientation;
	FakePlayer fakePlayer;
	private AspectList repairCost;
	private AspectList currentRepairVis;
	public int fortune;
	public int speed;
	public int area;
	int blockCount;
	private float speedyTime;
	private final int itemsPerVis;

	// TODO gamerforEA code start
	public final FakePlayerContainer fake = ModUtils.NEXUS_FACTORY.wrapFake(this);
	// TODO gamerforEA code end

	public TileArcaneBore()
	{
		this.digBlock = Blocks.air;
		this.digMd = 0;
		this.radInc = 0.0F;
		this.paused = 100;
		this.maxPause = 100;
		this.repairCounter = 0L;
		this.first = true;
		this.orientation = ForgeDirection.getOrientation(1);
		this.baseOrientation = ForgeDirection.getOrientation(1);
		this.fakePlayer = null;
		this.repairCost = new AspectList();
		this.currentRepairVis = new AspectList();
		this.fortune = 0;
		this.speed = 0;
		this.area = 0;
		this.blockCount = 0;
		this.itemsPerVis = 20;
	}

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (!this.worldObj.isRemote && this.speedyTime < 20.0F)
		{
			this.speedyTime += VisNetHandler.drainVis(this.worldObj, this.xCoord, this.yCoord, this.zCoord, Aspect.ENTROPY, 100) / 5.0F;
			if (this.speedyTime < 20.0F && this.base != null && this.base.drawEssentia())
			{
				float time = this.speedyTime;
				this.speedyTime = time + 20.0F;
			}
		}

		if (!this.worldObj.isRemote && this.fakePlayer == null)
			this.fakePlayer = FakePlayerFactory.get((WorldServer) this.worldObj, new GameProfile(null, "FakeThaumcraftBore"));

		if (this.worldObj.isRemote && this.first)
		{
			this.setOrientation(this.orientation, true);
			this.first = false;
		}

		if (this.rotX < this.tarX)
		{
			this.rotX += this.speedX;
			if (this.rotX < this.tarX)
				++this.speedX;
			else
				this.speedX = (int) (this.speedX / 3.0F);
		}
		else if (this.rotX > this.tarX)
		{
			this.rotX += this.speedX;
			if (this.rotX > this.tarX)
				--this.speedX;
			else
				this.speedX = (int) (this.speedX / 3.0F);
		}
		else
			this.speedX = 0;

		if (this.rotZ < this.tarZ)
		{
			this.rotZ += this.speedZ;
			if (this.rotZ < this.tarZ)
				++this.speedZ;
			else
				this.speedZ = (int) (this.speedZ / 3.0F);
		}
		else if (this.rotZ > this.tarZ)
		{
			this.rotZ += this.speedZ;
			if (this.rotZ > this.tarZ)
				--this.speedZ;
			else
				this.speedZ = (int) (this.speedZ / 3.0F);
		}
		else
			this.speedZ = 0;

		if (this.gettingPower() && this.areItemsValid())
			this.dig();
		else if (this.worldObj.isRemote)
		{
			if (this.topRotation % 90 != 0)
				this.topRotation += Math.min(10, 90 - this.topRotation % 90);

			this.vRadX *= 0.9F;
			this.vRadZ *= 0.9F;
		}

		if (!this.worldObj.isRemote && this.hasPickaxe && this.getStackInSlot(1) != null)
		{
			if (this.repairCounter++ % 40L == 0L && this.getStackInSlot(1).isItemDamaged())
				this.doRepair(this.getStackInSlot(1), this.fakePlayer);

			if (this.repairCost != null && this.repairCost.size() > 0 && this.repairCounter % 5L == 0L)
			{
				Aspect[] e = this.repairCost.getAspects();
				int length = e.length;

				for (int i = 0; i < length; ++i)
				{
					Aspect a = e[i];
					if (this.currentRepairVis.getAmount(a) < this.repairCost.getAmount(a))
						this.currentRepairVis.add(a, VisNetHandler.drainVis(this.worldObj, this.xCoord, this.yCoord, this.zCoord, a, this.repairCost.getAmount(a)));
				}
			}

			this.fakePlayer.ticksExisted = (int) this.repairCounter;

			try
			{
				this.getStackInSlot(1).updateAnimation(this.worldObj, this.fakePlayer, 0, true);
			}
			catch (Exception ignored)
			{
			}
		}

	}

	private void doRepair(ItemStack is, EntityPlayer player)
	{
		int level = EnchantmentHelper.getEnchantmentLevel(Config.enchRepair.effectId, is);
		if (level > 0)
		{
			if (level > 2)
				level = 2;

			if (is.getItem() instanceof IRepairable)
			{
				AspectList cost = ThaumcraftCraftingManager.getObjectTags(is);
				if (cost == null || cost.size() == 0)
					return;

				cost = ResearchManager.reduceToPrimals(cost);
				Aspect[] doIt = cost.getAspects();

				for (Aspect aspect : doIt)
				{
					if (aspect != null)
						this.repairCost.merge(aspect, (int) Math.sqrt(cost.getAmount(aspect) * 2) * level);
				}

				boolean var10 = true;
				if (is.getItem() instanceof IRepairableExtended)
					var10 = ((IRepairableExtended) is.getItem()).doRepair(is, player, level);

				Aspect a;
				Aspect[] aspects;
				int i;
				if (var10)
				{
					aspects = this.repairCost.getAspects();

					for (i = 0; i < aspects.length; ++i)
					{
						a = aspects[i];
						if (this.currentRepairVis.getAmount(a) < this.repairCost.getAmount(a))
						{
							var10 = false;
							break;
						}
					}
				}

				if (var10)
				{
					aspects = this.repairCost.getAspects();

					for (i = 0; i < aspects.length; ++i)
					{
						a = aspects[i];
						this.currentRepairVis.reduce(a, this.repairCost.getAmount(a));
					}

					is.damageItem(-level, player);
					this.markDirty();
				}
			}
			else
				this.repairCost = new AspectList();
		}
	}

	private boolean areItemsValid()
	{
		boolean notNearBroken = true;
		if (this.hasPickaxe && this.getStackInSlot(1).getItemDamage() + 1 >= this.getStackInSlot(1).getMaxDamage())
			notNearBroken = false;

		return this.hasFocus && this.hasPickaxe && this.getStackInSlot(1).isItemStackDamageable() && notNearBroken;
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		this.fortune = 0;
		this.area = 0;
		this.speed = 0;
		if (this.getStackInSlot(0) != null && this.getStackInSlot(0).getItem() instanceof ItemFocusExcavation)
		{
			this.fortune = ((ItemFocusExcavation) this.getStackInSlot(0).getItem()).getUpgradeLevel(this.getStackInSlot(0), FocusUpgradeType.treasure);
			this.area = ((ItemFocusExcavation) this.getStackInSlot(0).getItem()).getUpgradeLevel(this.getStackInSlot(0), FocusUpgradeType.enlarge);
			this.speed += ((ItemFocusExcavation) this.getStackInSlot(0).getItem()).getUpgradeLevel(this.getStackInSlot(0), FocusUpgradeType.potency);
			this.hasFocus = true;
		}
		else
			this.hasFocus = false;

		if (this.getStackInSlot(1) != null && this.getStackInSlot(1).getItem() instanceof ItemPickaxe)
		{
			this.hasPickaxe = true;
			int f = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, this.getStackInSlot(1));
			if (f > this.fortune)
				this.fortune = f;

			this.speed += EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, this.getStackInSlot(1));
		}
		else
			this.hasPickaxe = false;
	}

	private void dig()
	{
		if (this.rotX == this.tarX && this.rotZ == this.tarZ)
		{
			if (!this.worldObj.isRemote)
			{
				boolean vx = false;
				if (this.base == null)
				{
					TileEntity tile = this.worldObj.getTileEntity(this.xCoord, this.yCoord + this.baseOrientation.getOpposite().offsetY, this.zCoord);
					// TODO gamerforEA code start
					if (tile instanceof TileArcaneBoreBase)
						// TODO gamerforEA code end
						this.base = (TileArcaneBoreBase) tile;
				}

				if (--this.count > 0)
					return;

				if (this.toDig)
				{
					this.toDig = false;

					// TODO gamerforEA code start
					if (this.fake.cantBreak(this.digX, this.digY, this.digZ))
						return;
					// TODO gamerforEA code end

					Block block = this.worldObj.getBlock(this.digX, this.digY, this.digZ);
					int meta = this.worldObj.getBlockMetadata(this.digX, this.digY, this.digZ);
					int fortune;

					if (!block.isAir(this.worldObj, this.digX, this.digY, this.digZ))
					{
						fortune = this.fortune;
						boolean silkTouch = false;
						if (this.getStackInSlot(1) != null && EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, this.getStackInSlot(1)) > 0 && block.canSilkHarvest(this.worldObj, null, this.digX, this.digY, this.digZ, meta))
						{
							silkTouch = true;
							fortune = 0;
						}

						if (!silkTouch && this.getStackInSlot(0) != null && ((ItemFocusExcavation) this.getStackInSlot(0).getItem()).isUpgradedWith(this.getStackInSlot(0), FocusUpgradeType.silktouch) && block.canSilkHarvest(this.worldObj, null, this.digX, this.digY, this.digZ, meta))
						{
							silkTouch = true;
							fortune = 0;
						}

						this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, ConfigBlocks.blockWoodenDevice, 99, Block.getIdFromBlock(block) + (meta << 12));
						ArrayList<ItemStack> dropList = new ArrayList();
						if (silkTouch)
						{
							ItemStack var13 = BlockUtils.createStackedBlock(block, meta);
							if (var13 != null)
								dropList.add(var13);
						}
						else
							dropList = block.getDrops(this.worldObj, this.digX, this.digY, this.digZ, meta, fortune);

						List<EntityItem> entities = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(this.digX, this.digY, this.digZ, this.digX + 1, this.digY + 1, this.digZ + 1).expand(1.0D, 1.0D, 1.0D));
						if (entities.size() > 0)
						{
							for (EntityItem entityItem : entities)
							{
								dropList.add(entityItem.getEntityItem().copy());
								entityItem.setDead();
							}
						}

						if (dropList.size() > 0)
						{
							for (ItemStack stack : dropList)
							{
								ItemStack impact = stack.copy();
								if (!silkTouch && (this.getStackInSlot(1) != null && this.getStackInSlot(1).getItem() instanceof ItemElementalPickaxe || this.getStackInSlot(0) != null && this.getStackInSlot(0).getItem() instanceof ItemFocusBasic && ((ItemFocusBasic) this.getStackInSlot(0).getItem()).isUpgradedWith(this.getStackInSlot(0), ItemFocusExcavation.dowsing)))
									impact = Utils.findSpecialMiningResult(stack, 0.2F + fortune * 0.075F, this.worldObj.rand);

								if (this.base instanceof TileArcaneBoreBase)
								{
									TileEntity length = this.worldObj.getTileEntity(this.base.xCoord + this.base.orientation.offsetX, this.base.yCoord, this.base.zCoord + this.base.orientation.offsetZ);
									if (length instanceof IInventory)
										impact = InventoryUtils.placeItemStackIntoInventory(impact, (IInventory) length, this.base.orientation.getOpposite().ordinal(), true);

									if (impact != null)
									{
										EntityItem bx = new EntityItem(this.worldObj, this.xCoord + 0.5D + this.base.orientation.offsetX * 0.66D, this.yCoord + 0.4D + this.baseOrientation.getOpposite().offsetY, this.zCoord + 0.5D + this.base.orientation.offsetZ * 0.66D, impact.copy());
										bx.motionX = 0.075F * this.base.orientation.offsetX;
										bx.motionY = 0.02500000037252903D;
										bx.motionZ = 0.075F * this.base.orientation.offsetZ;
										this.worldObj.spawnEntityInWorld(bx);
									}
								}
							}
						}
					}

					this.setInventorySlotContents(1, InventoryUtils.damageItem(1, this.getStackInSlot(1), this.worldObj));
					if (this.getStackInSlot(1).stackSize <= 0)
						this.setInventorySlotContents(1, null);

					this.worldObj.setBlockToAir(this.digX, this.digY, this.digZ);
					if (this.base != null)
						for (fortune = 2; fortune < 6; ++fortune)
						{
							ForgeDirection var34 = ForgeDirection.getOrientation(fortune);
							TileEntity tileLamp = this.worldObj.getTileEntity(this.base.xCoord + var34.offsetX, this.base.yCoord, this.base.zCoord + var34.offsetZ);
							if (tileLamp instanceof TileArcaneLamp)
							{
								int var40 = this.worldObj.rand.nextInt(32) * 2;
								int var43 = this.xCoord + this.orientation.offsetX + this.orientation.offsetX * var40;
								int var46 = this.yCoord + this.orientation.offsetY + this.orientation.offsetY * var40;
								int var51 = this.zCoord + this.orientation.offsetZ + this.orientation.offsetZ * var40;
								int var49 = var40 / 2 % 4;
								if (this.orientation.offsetX != 0)
									var51 += var49 == 0 ? 3 : var49 != 1 && var49 != 3 ? -3 : 0;
								else
									var43 += var49 == 0 ? 3 : var49 != 1 && var49 != 3 ? -3 : 0;

								if (var49 == 3 && this.orientation.offsetY == 0)
									var46 -= 2;

								if (this.worldObj.isAirBlock(var43, var46, var51) && this.worldObj.getBlock(var43, var46, var51) != ConfigBlocks.blockAiry && this.worldObj.getBlockLightValue(var43, var46, var51) < 15)
									this.worldObj.setBlock(var43, var46, var51, ConfigBlocks.blockAiry, 3, 3);
								break;
							}
						}

					vx = true;
				}

				this.findNextBlockToDig();
				if (vx && this.speedyTime > 0.0F)
					--this.speedyTime;
			}
			else
			{
				++this.paused;
				if (this.worldObj.isAirBlock(this.xCoord, this.yCoord, this.zCoord))
					this.invalidate();

				if (this.paused < this.maxPause && this.soundDelay < System.currentTimeMillis())
				{
					this.soundDelay = System.currentTimeMillis() + 1200L + this.worldObj.rand.nextInt(100);
					this.worldObj.playSound(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, "thaumcraft:rumble", 0.25F, 0.9F + this.worldObj.rand.nextFloat() * 0.2F, false);
				}

				if (this.beamlength > 0 && this.paused > this.maxPause)
					--this.beamlength;

				float var50;
				if (this.toDig)
				{
					this.paused = 0;
					this.beamlength = 64;
					Block var27 = this.worldObj.getBlock(this.digX, this.digY, this.digZ);
					int var28 = this.worldObj.getBlockMetadata(this.digX, this.digY, this.digZ);
					if (var27 != null)
						this.maxPause = 10 + Math.max(10 - this.speed, (int) (var27.getBlockHardness(this.worldObj, this.digX, this.digY, this.digZ) * 2.0F) - this.speed * 2);
					else
						this.maxPause = 20;

					if (this.speedyTime <= 0.0F)
						this.maxPause *= 4;

					this.toDig = false;
					double var31 = this.xCoord + 0.5D - (this.digX + 0.5D);
					double var35 = this.yCoord + 0.5D - (this.digY + 0.5D);
					double var41 = this.zCoord + 0.5D - (this.digZ + 0.5D);
					double var47 = MathHelper.sqrt_double(var31 * var31 + var41 * var41);
					var50 = (float) (Math.atan2(var41, var31) * 180.0D / 3.141592653589793D);
					float var52 = (float) -(Math.atan2(var35, var47) * 180.0D / 3.141592653589793D) + 90.0F;
					this.tRadX = MathHelper.wrapAngleTo180_float(this.rotX) + var50;
					if (this.orientation.ordinal() == 5)
					{
						if (this.tRadX > 180.0F)
							this.tRadX -= 360.0F;

						if (this.tRadX < -180.0F)
							this.tRadX += 360.0F;
					}

					this.tRadZ = var52 - this.rotZ;
					if (this.orientation.ordinal() <= 1)
					{
						this.tRadZ += 180.0F;
						if (this.vRadX - this.tRadX >= 180.0F)
							this.vRadX -= 360.0F;

						if (this.vRadX - this.tRadX <= -180.0F)
							this.vRadX += 360.0F;
					}

					this.mRadX = Math.abs((this.vRadX - this.tRadX) / 6.0F);
					this.mRadZ = Math.abs((this.vRadZ - this.tRadZ) / 6.0F);
					if (this.speedyTime > 0.0F)
						--this.speedyTime;
				}

				if (this.paused < this.maxPause)
				{
					if (this.vRadX < this.tRadX)
						this.vRadX += this.mRadX;
					else if (this.vRadX > this.tRadX)
						this.vRadX -= this.mRadX;

					if (this.vRadZ < this.tRadZ)
						this.vRadZ += this.mRadZ;
					else if (this.vRadZ > this.tRadZ)
						this.vRadZ -= this.mRadZ;
				}
				else
				{
					this.vRadX *= 0.9F;
					this.vRadZ *= 0.9F;
				}

				this.mRadX *= 0.9F;
				this.mRadZ *= 0.9F;
				float var29 = this.rotX + 90 - this.vRadX;
				float var30 = this.rotZ + 90 - this.vRadZ;
				float var32 = 1.0F;
				float var33 = MathHelper.sin(var29 / 180.0F * 3.1415927F) * MathHelper.cos(var30 / 180.0F * 3.1415927F) * var32;
				float var36 = MathHelper.cos(var29 / 180.0F * 3.1415927F) * MathHelper.cos(var30 / 180.0F * 3.1415927F) * var32;
				float var39 = MathHelper.sin(var30 / 180.0F * 3.1415927F) * var32;
				Vec3 var42 = Vec3.createVectorHelper(this.xCoord + 0.5D + var33, this.yCoord + 0.5D + var39, this.zCoord + 0.5D + var36);
				Vec3 var45 = Vec3.createVectorHelper(this.xCoord + 0.5D + var33 * this.beamlength, this.yCoord + 0.5D + var39 * this.beamlength, this.zCoord + 0.5D + var36 * this.beamlength);
				MovingObjectPosition var48 = this.worldObj.func_147447_a(var42, var45, false, true, false);
				byte var53 = 0;
				var50 = 64.0F;
				double var54 = var45.xCoord;
				double by = var45.yCoord;
				double bz = var45.zCoord;
				if (var48 != null)
				{
					double a = this.xCoord + 0.5D + var33 - var48.hitVec.xCoord;
					double yd = this.yCoord + 0.5D + var39 - var48.hitVec.yCoord;
					double zd = this.zCoord + 0.5D + var36 - var48.hitVec.zCoord;
					var54 = var48.hitVec.xCoord;
					by = var48.hitVec.yCoord;
					bz = var48.hitVec.zCoord;
					var50 = MathHelper.sqrt_double(a * a + yd * yd + zd * zd) + 0.5F;
					var53 = 5;
					int x = MathHelper.floor_double(var54);
					int y = MathHelper.floor_double(by);
					int z = MathHelper.floor_double(bz);
					if (!this.worldObj.isAirBlock(x, y, z))
						Thaumcraft.proxy.boreDigFx(this.worldObj, x, y, z, this.xCoord + this.orientation.offsetX, this.yCoord + this.orientation.offsetY, this.zCoord + this.orientation.offsetZ, this.worldObj.getBlock(x, y, z), this.worldObj.getBlockMetadata(x, y, z) >> 12 & 255);
				}

				this.topRotation += this.beamlength / 6;
				this.beam1 = Thaumcraft.proxy.beamBore(this.worldObj, this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, var54, by, bz, 1, 'ｦ', true, var53 > 0 ? 2.0F : 0.0F, this.beam1, var53);
				this.beam2 = Thaumcraft.proxy.beamBore(this.worldObj, this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, var54, by, bz, 2, 16746581, false, var53 > 0 ? 2.0F : 0.0F, this.beam2, var53);
				if (this.worldObj.isAirBlock(this.digX, this.digY, this.digZ) && this.digBlock != Blocks.air)
				{
					this.worldObj.playSound(this.digX + 0.5F, this.digY + 0.5F, this.digZ + 0.5F, this.digBlock.stepSound.getBreakSound(), (this.digBlock.stepSound.getVolume() + 1.0F) / 2.0F, this.digBlock.stepSound.getPitch() * 0.8F, false);

					for (int var55 = 0; var55 < Thaumcraft.proxy.particleCount(10); ++var55)
					{
						Thaumcraft.proxy.boreDigFx(this.worldObj, this.digX, this.digY, this.digZ, this.xCoord + this.orientation.offsetX, this.yCoord + this.orientation.offsetY, this.zCoord + this.orientation.offsetZ, this.digBlock, this.digMd >> 12 & 255);
					}

					this.digBlock = Blocks.air;
				}
			}
		}
		else if (this.worldObj.isRemote)
		{
			if (this.topRotation % 90 != 0)
				this.topRotation += Math.min(10, 90 - this.topRotation % 90);

			this.vRadX *= 0.9F;
			this.vRadZ *= 0.9F;
		}
	}

	private void findNextBlockToDig()
	{
		if (this.radInc == 0.0F)
			this.radInc = (this.maxRadius + this.area) / 360.0F;

		int x = this.lastX;
		int z = this.lastZ;

		int y;
		TCVec3 md;
		for (y = this.lastY; x == this.lastX && z == this.lastZ && y == this.lastY; z = MathHelper.floor_double(md.zCoord))
		{
			this.spiral += 2;
			if (this.spiral >= 360)
				this.spiral -= 360;

			this.currentRadius += this.radInc;
			if (this.currentRadius > this.maxRadius + this.area || this.currentRadius < -(this.maxRadius + this.area))
				this.radInc *= -1.0F;

			TCVec3 depth = TCVec3.createVectorHelper(this.xCoord + this.orientation.offsetX + 0.5D, this.yCoord + this.orientation.offsetY + 0.5D, this.zCoord + this.orientation.offsetZ + 0.5D);
			TCVec3 block = TCVec3.createVectorHelper(0.0D, this.currentRadius, 0.0D);
			block.rotateAroundZ(this.spiral / 180.0F * 3.1415927F);
			block.rotateAroundY(1.5707964F * this.orientation.offsetX);
			block.rotateAroundX(1.5707964F * this.orientation.offsetY);
			md = depth.addVector(block.xCoord, block.yCoord, block.zCoord);
			x = MathHelper.floor_double(md.xCoord);
			y = MathHelper.floor_double(md.yCoord);
		}

		this.lastX = x;
		this.lastZ = z;
		this.lastY = y;
		x += this.orientation.offsetX;
		y += this.orientation.offsetY;
		z += this.orientation.offsetZ;

		for (int var10 = 0; var10 < 64; ++var10)
		{
			x += this.orientation.offsetX;
			y += this.orientation.offsetY;
			z += this.orientation.offsetZ;
			Block var11 = this.worldObj.getBlock(x, y, z);
			int var12 = this.worldObj.getBlockMetadata(x, y, z);
			if (var11 != null && var11.getBlockHardness(this.worldObj, x, y, z) < 0.0F)
				break;

			if (!this.worldObj.isAirBlock(x, y, z) && var11 != null && var11.canCollideCheck(var12, false) && var11.getCollisionBoundingBoxFromPool(this.worldObj, x, y, z) != null)
			{
				this.digX = x;
				this.digY = y;
				this.digZ = z;
				if (++this.blockCount > 2)
					this.blockCount = 0;

				this.count = Math.max(10 - this.speed, (int) (var11.getBlockHardness(this.worldObj, x, y, z) * 2.0F) - this.speed * 2);
				if (this.speedyTime < 1.0F)
					this.count *= 4;

				this.toDig = true;
				Vec3 var13 = Vec3.createVectorHelper(this.xCoord + 0.5D + this.orientation.offsetX, this.yCoord + 0.5D + this.orientation.offsetY, this.zCoord + 0.5D + this.orientation.offsetZ);
				Vec3 var14 = Vec3.createVectorHelper(this.digX + 0.5D, this.digY + 0.5D, this.digZ + 0.5D);
				MovingObjectPosition mop = this.worldObj.func_147447_a(var13, var14, false, true, false);
				if (mop != null)
				{
					var11 = this.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
					this.worldObj.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ);
					if (var11.getBlockHardness(this.worldObj, mop.blockX, mop.blockY, mop.blockZ) > -1.0F && var11.getCollisionBoundingBoxFromPool(this.worldObj, mop.blockX, mop.blockY, mop.blockZ) != null)
					{
						this.count = Math.max(10 - this.speed, (int) (var11.getBlockHardness(this.worldObj, mop.blockX, mop.blockY, mop.blockZ) * 2.0F) - this.speed * 2);
						if (this.speedyTime < 1.0F)
							this.count *= 4;

						this.digX = mop.blockX;
						this.digY = mop.blockY;
						this.digZ = mop.blockZ;
					}
				}

				this.sendDigEvent();
				break;
			}
		}
	}

	public boolean gettingPower()
	{
		return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) || this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord + this.baseOrientation.getOpposite().offsetY, this.zCoord);
	}

	public void setOrientation(ForgeDirection or, boolean initial)
	{
		this.orientation = or;
		this.lastX = 0;
		this.lastZ = 0;
		switch (or.ordinal())
		{
			case 0:
				this.tarZ = 180;
				this.tarX = 0;
				break;
			case 1:
				this.tarZ = 0;
				this.tarX = 0;
				break;
			case 2:
				this.tarZ = 90;
				this.tarX = 270;
				break;
			case 3:
				this.tarZ = 90;
				this.tarX = 90;
				break;
			case 4:
				this.tarZ = 90;
				this.tarX = 0;
				break;
			case 5:
				this.tarZ = 90;
				this.tarX = 180;
		}

		if (initial)
		{
			this.rotX = this.tarX;
			this.rotZ = this.tarZ;
		}

		this.toDig = false;
		this.radInc = 0.0F;
		this.paused = 100;
		this.tRadX = 0.0F;
		this.tRadZ = 0.0F;
		this.mRadX = 0.0F;
		this.mRadZ = 0.0F;
		this.digX = 0;
		this.digY = 0;
		this.digZ = 0;
		if (this.worldObj != null)
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		this.speedyTime = nbttagcompound.getShort("SpeedyTime");
		this.setOrientation(this.orientation, true);

		// TODO gamerforEA code start
		this.fake.readFromNBT(nbttagcompound);
		// TODO gamerforEA code end
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setShort("SpeedyTime", (short) (int) this.speedyTime);

		// TODO gamerforEA code start
		this.fake.writeToNBT(nbttagcompound);
		// TODO gamerforEA code end
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbttagcompound)
	{
		this.orientation = ForgeDirection.getOrientation(nbttagcompound.getInteger("orientation"));
		this.baseOrientation = ForgeDirection.getOrientation(nbttagcompound.getInteger("baseOrientation"));
		NBTTagList nbtList = nbttagcompound.getTagList("Inventory", 10);
		this.contents = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbtList.tagCount(); ++i)
		{
			NBTTagCompound nbt = nbtList.getCompoundTagAt(i);
			int slot = nbt.getByte("Slot") & 255;
			if (slot >= 0 && slot < this.contents.length)
				this.contents[slot] = ItemStack.loadItemStackFromNBT(nbt);
		}

		this.markDirty();
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setInteger("orientation", this.orientation.ordinal());
		nbttagcompound.setInteger("baseOrientation", this.baseOrientation.ordinal());
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.contents.length; ++var3)
		{
			if (this.contents[var3] != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.contents[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		nbttagcompound.setTag("Inventory", var2);
	}

	@Override
	public boolean receiveClientEvent(int i, int j)
	{
		if (i != 99)
			return super.receiveClientEvent(i, j);
		else
		{
			try
			{
				if (this.worldObj.isRemote && (j & 4095) > 0)
				{
					Block e = Block.getBlockById(j & 4095);
					if (e != null)
					{
						this.worldObj.playSound(this.digX + 0.5F, this.digY + 0.5F, this.digZ + 0.5F, e.stepSound.getBreakSound(), (e.stepSound.getVolume() + 1.0F) / 2.0F, e.stepSound.getPitch() * 0.8F, false);

						for (int a = 0; a < Thaumcraft.proxy.particleCount(10); ++a)
						{
							Thaumcraft.proxy.boreDigFx(this.worldObj, this.digX, this.digY, this.digZ, this.xCoord + this.orientation.offsetX, this.yCoord + this.orientation.offsetY, this.zCoord + this.orientation.offsetZ, e, j >> 12 & 255);
						}
					}
				}
			}
			catch (Exception ignored)
			{
			}

			return true;
		}
	}

	public void getDigEvent(int j)
	{
		int x = (j >> 16 & 255) - 64;
		int y = (j >> 8 & 255) - 64;
		int z = (j & 255) - 64;
		this.digX = this.xCoord + x;
		this.digY = this.yCoord + y;
		this.digZ = this.zCoord + z;
		this.toDig = true;
		this.digBlock = this.worldObj.getBlock(this.digX, this.digY, this.digZ);
		this.digMd = this.worldObj.getBlockMetadata(this.digX, this.digY, this.digZ);
	}

	public void sendDigEvent()
	{
		int x = this.digX - this.xCoord + 64;
		int y = this.digY - this.yCoord + 64;
		int z = this.digZ - this.zCoord + 64;
		int c = (x & 255) << 16 | (y & 255) << 8 | z & 255;
		PacketHandler.INSTANCE.sendToAllAround(new PacketBoreDig(this.xCoord, this.yCoord, this.zCoord, c), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 64.0D));
	}

	@Override
	public int getSizeInventory()
	{
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int var1)
	{
		return this.contents[var1];
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2)
	{
		if (this.contents[var1] != null)
		{
			ItemStack stack;
			if (this.contents[var1].stackSize <= var2)
			{
				stack = this.contents[var1];
				this.contents[var1] = null;
				this.markDirty();
				return stack;
			}
			else
			{
				stack = this.contents[var1].splitStack(var2);
				if (this.contents[var1].stackSize == 0)
					this.contents[var1] = null;

				this.markDirty();
				return stack;
			}
		}
		else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1)
	{
		if (this.contents[var1] != null)
		{
			ItemStack var2 = this.contents[var1];
			this.contents[var1] = null;
			return var2;
		}
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int id, ItemStack stack)
	{
		this.contents[id] = stack;
		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();

		this.markDirty();
	}

	@Override
	public String getInventoryName()
	{
		return "Arcane Bore";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && var1.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
	{
		this.setOrientation(ForgeDirection.getOrientation(side), false);
		player.worldObj.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.worldObj.rand.nextFloat() * 0.2F, false);
		player.swingItem();
		this.markDirty();
		return 0;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
	{
		return null;
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count)
	{
	}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count)
	{
	}
}
