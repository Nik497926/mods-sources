package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.crafting.IInfusionStabiliser;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.Utils;

import java.util.List;
import java.util.Random;

public class BlockCandle extends Block implements IInfusionStabiliser
{
	public IIcon icon;
	public IIcon iconStub;

	public BlockCandle()
	{
		super(Material.circuits);
		this.setHardness(0.1F);
		this.setStepSound(soundTypeCloth);
		this.setCreativeTab(Thaumcraft.tabTC);
		this.setLightLevel(0.95F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 < 16; ++var4)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("thaumcraft:candle");
		this.iconStub = ir.registerIcon("thaumcraft:candlestub");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.icon;
	}

	@Override
	// TODO gamerforEA code start
	@SideOnly(Side.CLIENT)
	// TODO gamerforEA code end
	public int getRenderColor(int meta)
	{
		// TODO gamerforEA code start
		meta = MathHelper.clamp_int(meta, 0, Utils.colors.length - 1);
		// TODO gamerforEA code end

		return Utils.colors[meta];
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return World.doesBlockHaveSolidTopSurface(par1World, par2, par3, par4);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
	{
		int var6 = par1World.getBlockMetadata(par2, par3, par4);
		boolean var7 = this.canPlaceBlockAt(par1World, par2, par3 - 1, par4);
		if (!var7)
		{
			this.dropBlockAsItem(par1World, par2, par3, par4, var6, 0);
			par1World.setBlockToAir(par2, par3, par4);
		}

		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
	}

	@Override
	public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
	{
		return this.canPlaceBlockAt(par1World, par2, par3 - 1, par4);
	}

	@Override
	// TODO gamerforEA code start
	@SideOnly(Side.CLIENT)
	// TODO gamerforEA code end
	public int colorMultiplier(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
	{
		int meta = par1iBlockAccess.getBlockMetadata(par2, par3, par4);

		// TODO gamerforEA code start
		meta = MathHelper.clamp_int(meta, 0, Utils.colors.length - 1);
		// TODO gamerforEA code end

		return Utils.colors[meta];
	}

	@Override
	public int damageDropped(int par1)
	{
		return par1;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
	{
		this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.5F, 0.625F);
		super.setBlockBoundsBasedOnState(par1iBlockAccess, par2, par3, par4);
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	@Override
	public int getRenderType()
	{
		return ConfigBlocks.blockCandleRI;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	// TODO gamerforEA code start
	@SideOnly(Side.CLIENT)
	// TODO gamerforEA code end
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		double var7 = (double) ((float) par2 + 0.5F);
		double var9 = (double) ((float) par3 + 0.7F);
		double var11 = (double) ((float) par4 + 0.5F);
		par1World.spawnParticle("smoke", var7, var9, var11, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", var7, var9, var11, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public boolean canStabaliseInfusion(World world, int x, int y, int z)
	{
		return true;
	}
}
