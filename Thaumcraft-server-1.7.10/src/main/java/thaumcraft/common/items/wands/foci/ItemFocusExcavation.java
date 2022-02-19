package thaumcraft.common.items.wands.foci;

import com.gamerforea.thaumcraft.EventConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.BlockUtils;

import java.util.*;

public class ItemFocusExcavation extends ItemFocusBasic
{
	private static final AspectList cost = new AspectList().add(Aspect.EARTH, 15);
	private static AspectList cost2 = null;
	static HashMap<String, Long> soundDelay = new HashMap();
	static HashMap<String, Object> beam = new HashMap();
	static HashMap<String, Float> breakcount = new HashMap();
	static HashMap<String, Integer> lastX = new HashMap();
	static HashMap<String, Integer> lastY = new HashMap();
	static HashMap<String, Integer> lastZ = new HashMap();
	public static FocusUpgradeType dowsing = new FocusUpgradeType(20, new ResourceLocation("thaumcraft", "textures/foci/dowsing.png"), "focus.upgrade.dowsing.name", "focus.upgrade.dowsing.text", new AspectList().add(Aspect.MINE, 1));

	public ItemFocusExcavation()
	{
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("thaumcraft:focus_excavation");
	}

	@Override
	public String getSortingHelper(ItemStack itemstack)
	{
		return "BE" + super.getSortingHelper(itemstack);
	}

	@Override
	public int getFocusColor(ItemStack itemstack)
	{
		return 409606;
	}

	@Override
	public AspectList getVisCost(ItemStack itemstack)
	{
		if (this.isUpgradedWith(itemstack, FocusUpgradeType.silktouch))
		{
			if (cost2 == null)
			{
				cost2 = new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1);
				cost2.add(cost);
			}

			return cost2;
		}
		else if (this.isUpgradedWith(itemstack, dowsing))
		{
			if (cost2 == null)
			{
				cost2 = new AspectList().add(Aspect.FIRE, 2).add(Aspect.ORDER, 2);
				cost2.add(cost);
			}

			return cost2;
		}
		else
			return cost;
	}

	@Override
	public boolean isVisCostPerTick(ItemStack itemstack)
	{
		return true;
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mop)
	{
		p.setItemInUse(itemstack, Integer.MAX_VALUE);
		return itemstack;
	}

	@Override
	public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count)
	{
		ItemWandCasting wand = (ItemWandCasting) stack.getItem();
		if (!wand.consumeAllVis(stack, p, this.getVisCost(stack), false, false))
			p.stopUsingItem();
		else
		{
			String pp = "R" + p.getCommandSenderName();
			if (!p.worldObj.isRemote)
				pp = "S" + p.getCommandSenderName();

			soundDelay.putIfAbsent(pp, 0L);
			breakcount.putIfAbsent(pp, 0.0F);
			lastX.putIfAbsent(pp, 0);
			lastY.putIfAbsent(pp, 0);
			lastZ.putIfAbsent(pp, 0);

			MovingObjectPosition mop = BlockUtils.getTargetBlock(p.worldObj, p, false);
			Vec3 v = p.getLookVec();
			double tx = p.posX + v.xCoord * 10.0D;
			double ty = p.posY + v.yCoord * 10.0D;
			double tz = p.posZ + v.zCoord * 10.0D;
			int impact = 0;
			if (mop != null)
			{
				tx = mop.hitVec.xCoord;
				ty = mop.hitVec.yCoord;
				tz = mop.hitVec.zCoord;
				impact = 5;
				if (!p.worldObj.isRemote && soundDelay.get(pp) < System.currentTimeMillis())
				{
					p.worldObj.playSoundEffect(tx, ty, tz, "thaumcraft:rumble", 0.3F, 1.0F);
					soundDelay.put(pp, System.currentTimeMillis() + 1200L);
				}
			}
			else
				soundDelay.put(pp, 0L);

			if (p.worldObj.isRemote)
				beam.put(pp, Thaumcraft.proxy.beamCont(p.worldObj, p, tx, ty, tz, 2, 'ｦ', false, impact > 0 ? 2.0F : 0.0F, beam.get(pp), impact));

			if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK && p.worldObj.canMineBlock(p, mop.blockX, mop.blockY, mop.blockZ))
			{
				Block bi = p.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
				int md = p.worldObj.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ);
				float hardness = bi.getBlockHardness(p.worldObj, mop.blockX, mop.blockY, mop.blockZ);
				if (hardness >= 0.0F)
				{
					int pot = wand.getFocusPotency(stack);
					float speed = 0.05F + pot * 0.1F;
					if (bi.getMaterial() == Material.rock || bi.getMaterial() == Material.grass || bi.getMaterial() == Material.ground || bi.getMaterial() == Material.sand)
						speed = 0.25F + pot * 0.25F;

					if (bi == Blocks.obsidian)
						speed *= 3.0F;

					if (lastX.get(pp) == mop.blockX && lastY.get(pp) == mop.blockY && lastZ.get(pp) == mop.blockZ)
					{
						float bc = breakcount.get(pp);
						if (p.worldObj.isRemote && bc > 0.0F && bi != Blocks.air)
						{
							int progress = (int) (bc / hardness * 9.0F);
							Thaumcraft.proxy.excavateFX(mop.blockX, mop.blockY, mop.blockZ, p, Block.getIdFromBlock(bi), md, progress);
						}

						if (p.worldObj.isRemote)
						{
							if (bc >= hardness)
								breakcount.put(pp, 0.0F);
							else
								breakcount.put(pp, bc + speed);
						}
						else if (bc >= hardness && wand.consumeAllVis(stack, p, this.getVisCost(stack), true, false))
						{
							if (this.excavate(p.worldObj, stack, p, bi, md, mop.blockX, mop.blockY, mop.blockZ))
								for (int a = 0; a < wand.getFocusEnlarge(stack); ++a)
								{
									if (wand.consumeAllVis(stack, p, this.getVisCost(stack), false, false) && this.breakNeighbour(p, mop.blockX, mop.blockY, mop.blockZ, bi, md, stack))
										wand.consumeAllVis(stack, p, this.getVisCost(stack), true, false);
								}

							lastX.put(pp, Integer.MAX_VALUE);
							lastY.put(pp, Integer.MAX_VALUE);
							lastZ.put(pp, Integer.MAX_VALUE);
							breakcount.put(pp, 0.0F);
						}
						else
							breakcount.put(pp, bc + speed);
					}
					else
					{
						lastX.put(pp, mop.blockX);
						lastY.put(pp, mop.blockY);
						lastZ.put(pp, mop.blockZ);
						breakcount.put(pp, 0.0F);
					}
				}
			}
			else
			{
				lastX.put(pp, Integer.MAX_VALUE);
				lastY.put(pp, Integer.MAX_VALUE);
				lastZ.put(pp, Integer.MAX_VALUE);
				breakcount.put(pp, 0.0F);
			}

		}
	}

	private boolean excavate(World world, ItemStack stack, EntityPlayer player, Block block, int md, int x, int y, int z)
	{
		// TODO gamerforEA code start
		if (EventConfig.excavationFocusBlackList.contains(block, md))
			return false;
		// TODO gamerforEA code end

		GameType gt = GameType.SURVIVAL;
		if (player.capabilities.allowEdit)
		{
			if (player.capabilities.isCreativeMode)
				gt = GameType.CREATIVE;
		}
		else
			gt = GameType.ADVENTURE;

		BreakEvent event = ForgeHooks.onBlockBreakEvent(world, gt, (EntityPlayerMP) player, x, y, z);
		if (event.isCanceled())
			return false;
		else
		{
			ItemWandCasting wand = (ItemWandCasting) stack.getItem();
			int fortune = wand.getFocusTreasure(stack);
			boolean silk = this.isUpgradedWith(wand.getFocusItem(stack), FocusUpgradeType.silktouch);
			if (silk && block.canSilkHarvest(player.worldObj, player, x, y, z, md))
			{
				ArrayList<ItemStack> items = new ArrayList();
				ItemStack itemstack = BlockUtils.createStackedBlock(block, md);
				if (itemstack != null)
					items.add(itemstack);

				ForgeEventFactory.fireBlockHarvesting(items, world, block, x, y, z, md, 0, 1.0F, true, player);

				for (ItemStack is : items)
				{
					BlockUtils.dropBlockAsItem(world, x, y, z, is, block);
				}
			}
			else
			{
				BlockUtils.dropBlockAsItemWithChance(world, block, x, y, z, md, 1.0F, fortune, player);
				block.dropXpOnBlockBreak(world, x, y, z, block.getExpDrop(world, md, fortune));
			}

			world.setBlockToAir(x, y, z);
			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (md << 12));
			return true;
		}
	}

	boolean breakNeighbour(EntityPlayer p, int x, int y, int z, Block block, int md, ItemStack stack)
	{
		List<ForgeDirection> directions = Arrays.asList(ForgeDirection.DOWN, ForgeDirection.UP, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);
		Collections.shuffle(directions, p.worldObj.rand);

		for (ForgeDirection dir : directions)
		{
			if (p.worldObj.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == block && p.worldObj.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == md && this.excavate(p.worldObj, stack, p, block, md, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ))
				return true;
		}

		return false;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer p, int count)
	{
		String pp = "R" + p.getCommandSenderName();
		if (!p.worldObj.isRemote)
			pp = "S" + p.getCommandSenderName();

		soundDelay.putIfAbsent(pp, 0L);
		breakcount.putIfAbsent(pp, 0.0F);
		lastX.putIfAbsent(pp, 0);
		lastY.putIfAbsent(pp, 0);
		lastZ.putIfAbsent(pp, 0);

		beam.put(pp, null);
		lastX.put(pp, Integer.MAX_VALUE);
		lastY.put(pp, Integer.MAX_VALUE);
		lastZ.put(pp, Integer.MAX_VALUE);
		breakcount.put(pp, 0.0F);
	}

	@Override
	public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack itemstack)
	{
		return ItemFocusBasic.WandFocusAnimation.CHARGE;
	}

	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
	{
		switch (rank)
		{
			case 1:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure };
			case 2:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge };
			case 3:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure, dowsing };
			case 4:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge };
			case 5:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure, FocusUpgradeType.silktouch };
			default:
				return null;
		}
	}
}
