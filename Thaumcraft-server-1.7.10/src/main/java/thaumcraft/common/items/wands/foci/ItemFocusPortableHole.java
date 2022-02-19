package thaumcraft.common.items.wands.foci;

import com.gamerforea.eventhelper.util.EventUtils;
import com.gamerforea.thaumcraft.ModUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileHole;

import javax.annotation.Nullable;

public class ItemFocusPortableHole extends ItemFocusBasic
{
	IIcon depthIcon = null;
	private static final AspectList cost;

	public ItemFocusPortableHole()
	{
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	public String getSortingHelper(ItemStack itemstack)
	{
		return "BPH" + super.getSortingHelper(itemstack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.depthIcon = ir.registerIcon("thaumcraft:focus_portablehole_depth");
		this.icon = ir.registerIcon("thaumcraft:focus_portablehole");
	}

	@Override
	public IIcon getFocusDepthLayerIcon(ItemStack itemstack)
	{
		return this.depthIcon;
	}

	@Override
	public int getFocusColor(ItemStack itemstack)
	{
		return 594985;
	}

	@Override
	public AspectList getVisCost(ItemStack itemstack)
	{
		return cost.copy();
	}

	// TODO gamerforEA code start
	public static boolean createHole(World world, int ii, int jj, int kk, int side, byte count, int max)
	{
		return createHole(null, world, ii, jj, kk, side, count, max);
	}
	// TODO gamerforEA code end

	// TODO gamerforEA add EntityPlayer parameter
	public static boolean createHole(
			@Nullable EntityPlayer player, World world, int x, int y, int z, int side, byte count, int max)
	{
		Block block = world.getBlock(x, y, z);
		if (world.getTileEntity(x, y, z) == null && !ThaumcraftApi.portableHoleBlackList.contains(block) && block != Blocks.bedrock && block != ConfigBlocks.blockHole && !block.isAir(world, x, y, z) && !block.canPlaceBlockAt(world, x, y, z) && block.getBlockHardness(world, x, y, z) != -1.0F)
		{
			// TODO gamerforEA code start
			if (EventUtils.cantBreak(player == null ? ModUtils.getModFake(world) : player, x, y, z))
				return false;
			// TODO gamerforEA code end

			TileHole ts = new TileHole(block, world.getBlockMetadata(x, y, z), (short) max, count, (byte) side, null);

			// TODO gamerforEA code start
			if (player != null)
				ts.fake.setRealPlayer(player);
			// TODO gamerforEA code end

			world.setBlock(x, y, z, Blocks.air, 0, 0);
			if (world.setBlock(x, y, z, ConfigBlocks.blockHole, 0, 0))
				world.setTileEntity(x, y, z, ts);

			world.markBlockForUpdate(x, y, z);
			Thaumcraft.proxy.blockSparkle(world, x, y, z, 4194368, 1);
			return true;
		}
		else
			return false;
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop)
	{
		ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
		if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK)
		{
			if (world.provider.dimensionId == Config.dimensionOuterId)
			{
				if (!world.isRemote)
					world.playSoundEffect(mop.blockX + 0.5D, mop.blockY + 0.5D, mop.blockZ + 0.5D, "thaumcraft:wandfail", 1.0F, 1.0F);

				player.swingItem();
				return itemstack;
			}

			int ii = mop.blockX;
			int jj = mop.blockY;
			int kk = mop.blockZ;
			int enlarge = wand.getFocusEnlarge(itemstack);
			boolean distance = false;
			int maxdis = 33 + enlarge * 8;

			int var17;
			for (var17 = 0; var17 < maxdis; ++var17)
			{
				Block c = world.getBlock(ii, jj, kk);
				if (ThaumcraftApi.portableHoleBlackList.contains(c) || c == Blocks.bedrock || c == ConfigBlocks.blockHole || c.isAir(world, ii, jj, kk) || c.getBlockHardness(world, ii, jj, kk) == -1.0F)
					break;

				switch (mop.sideHit)
				{
					case 0:
						++jj;
						break;
					case 1:
						--jj;
						break;
					case 2:
						++kk;
						break;
					case 3:
						--kk;
						break;
					case 4:
						++ii;
						break;
					case 5:
						--ii;
				}
			}

			AspectList var18 = this.getVisCost(itemstack);
			Aspect[] di = var18.getAspects();
			int dur = di.length;

			for (int i$ = 0; i$ < dur; ++i$)
			{
				Aspect a = di[i$];
				var18.merge(a, var18.getAmount(a) * var17);
			}

			if (wand.consumeAllVis(itemstack, player, var18, true, false))
			{
				int var19 = this.getUpgradeLevel(wand.getFocusItem(itemstack), FocusUpgradeType.extend);
				short var20 = (short) (120 + 60 * var19);

				// TODO gamerforEA add EntityPlayer parameter
				createHole(player, world, mop.blockX, mop.blockY, mop.blockZ, mop.sideHit, (byte) (var17 + 1), var20);
			}

			player.swingItem();
			if (!world.isRemote)
				world.playSoundEffect(mop.blockX + 0.5D, mop.blockY + 0.5D, mop.blockZ + 0.5D, "mob.endermen.portal", 1.0F, 1.0F);
		}

		return itemstack;
	}

	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
	{
		switch (rank)
		{
			case 1:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
			case 2:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
			case 3:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
			case 4:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
			case 5:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
			default:
				return null;
		}
	}

	static
	{
		cost = new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.AIR, 10);
	}
}
