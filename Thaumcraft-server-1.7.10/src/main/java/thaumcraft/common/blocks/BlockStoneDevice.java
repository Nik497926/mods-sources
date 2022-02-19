package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.*;

import java.util.List;
import java.util.Random;

import static com.gamerforea.thaumcraft.ChatUtils.color;
import static com.gamerforea.thaumcraft.ChatUtils.translate;

public class BlockStoneDevice extends BlockContainer
{
	public IIcon[] iconFurnace = new IIcon[5];
	public IIcon[] iconPedestal = new IIcon[2];
	public IIcon[] iconWandPedestal = new IIcon[2];
	public IIcon[] iconWandPedestalFocus = new IIcon[3];
	public IIcon[] iconSpa = new IIcon[2];

	public BlockStoneDevice()
	{
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(25.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.iconPedestal[0] = ir.registerIcon("thaumcraft:pedestal_side");
		this.iconPedestal[1] = ir.registerIcon("thaumcraft:pedestal_top");
		this.iconWandPedestal[0] = ir.registerIcon("thaumcraft:wandpedestal_side");
		this.iconWandPedestal[1] = ir.registerIcon("thaumcraft:wandpedestal_top");
		this.iconWandPedestalFocus[0] = ir.registerIcon("thaumcraft:wandpedestal_focus_side");
		this.iconWandPedestalFocus[1] = ir.registerIcon("thaumcraft:wandpedestal_focus_top");
		this.iconWandPedestalFocus[2] = ir.registerIcon("thaumcraft:wandpedestal_focus_bot");
		this.iconFurnace[0] = ir.registerIcon("thaumcraft:al_furnace_side");
		this.iconFurnace[1] = ir.registerIcon("thaumcraft:al_furnace_top");
		this.iconFurnace[2] = ir.registerIcon("thaumcraft:al_furnace_front_off");
		this.iconFurnace[3] = ir.registerIcon("thaumcraft:al_furnace_front_on");
		this.iconFurnace[4] = ir.registerIcon("thaumcraft:al_furnace_top_filled");
		this.iconSpa[0] = ir.registerIcon("thaumcraft:spa_side");
		this.iconSpa[1] = ir.registerIcon("thaumcraft:spa_top");
	}

	@Override
	public int getRenderType()
	{
		return ConfigBlocks.blockStoneDeviceRI;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public IIcon getIcon(int side, int md)
	{
		if (md == 0)
		{
			if (side == 1)
				return this.iconFurnace[1];

			if (side > 1)
				return this.iconFurnace[2];
		}
		else if (md == 1)
		{
			if (side <= 1)
				return this.iconPedestal[1];

			if (side > 1)
				return this.iconPedestal[0];
		}
		else if (md == 5)
		{
			if (side == 0)
				return this.iconPedestal[1];

			if (side == 1)
				return this.iconWandPedestal[1];

			if (side > 1)
				return this.iconWandPedestal[0];
		}
		else if (md == 8)
		{
			if (side == 0)
				return this.iconWandPedestalFocus[2];

			if (side == 1)
				return this.iconWandPedestalFocus[1];

			if (side > 1)
				return this.iconWandPedestalFocus[0];
		}
		else if (md == 12)
		{
			if (side == 0)
				return this.iconPedestal[1];

			if (side == 1)
				return this.iconSpa[1];

			if (side > 1)
				return this.iconSpa[0];
		}

		return this.iconPedestal[1];
	}

	@Override
	public IIcon getIcon(IBlockAccess iblockaccess, int i, int j, int k, int side)
	{
		int metadata = iblockaccess.getBlockMetadata(i, j, k);
		if (metadata == 0)
		{
			TileEntity te = iblockaccess.getTileEntity(i, j, k);
			if (side == 1)
			{
				if (te instanceof TileAlchemyFurnace && ((TileAlchemyFurnace) te).vis > 0)
					return this.iconFurnace[4];

				return this.iconFurnace[1];
			}

			if (side > 1)
			{
				if (te instanceof TileAlchemyFurnace && ((TileAlchemyFurnace) te).isBurning())
					return this.iconFurnace[3];

				return this.iconFurnace[2];
			}
		}
		else if (metadata == 1 || metadata == 5 || metadata == 8 || metadata == 12)
			return super.getIcon(iblockaccess, i, j, k, side);

		return this.iconFurnace[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 5));
		par3List.add(new ItemStack(par1, 1, 8));
		par3List.add(new ItemStack(par1, 1, 9));
		par3List.add(new ItemStack(par1, 1, 10));
		par3List.add(new ItemStack(par1, 1, 11));
		par3List.add(new ItemStack(par1, 1, 12));
		par3List.add(new ItemStack(par1, 1, 13));
		par3List.add(new ItemStack(par1, 1, 14));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, int i, int j, int k, Random r)
	{
		TileEntity te = w.getTileEntity(i, j, k);
		if (te instanceof TileAlchemyFurnace && ((TileAlchemyFurnace) te).isBurning())
		{
			float f = i + 0.5F;
			float f1 = j + 0.2F + r.nextFloat() * 5.0F / 16.0F;
			float f2 = k + 0.5F;
			float f3 = 0.52F;
			float f4 = r.nextFloat() * 0.5F - 0.25F;
			w.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			w.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			w.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			w.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			w.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			w.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			w.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			w.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
		}

	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0)
		{
			TileEntity te = world.getTileEntity(x, y, z);
			if (te instanceof TileAlchemyFurnace && ((TileAlchemyFurnace) te).isBurning())
				return 12;
		}
		else if (meta == 2)
			return 10;

		return super.getLightValue(world, x, y, z);
	}

	@Override
	public int damageDropped(int metadata)
	{
		return metadata == 3 ? 7 : metadata == 4 ? 6 : metadata;
	}

	@Override
	public Item getItemDropped(int metadata, Random par2Random, int par3)
	{
		return metadata != 3 && metadata != 4 ? super.getItemDropped(metadata, par2Random, par3) : Item.getItemFromBlock(ConfigBlocks.blockCosmeticSolid);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return metadata == 0 ? new TileAlchemyFurnace() : metadata == 1 ? new TilePedestal() : metadata == 2 ? new TileInfusionMatrix() : metadata == 3 ? new TileInfusionPillar() : metadata == 5 ? new TileWandPedestal() : metadata != 9 && metadata != 10 ? metadata == 11 ? new TileNodeConverter() : metadata == 12 ? new TileSpa() : metadata == 13 ? new TileFocalManipulator() : metadata == 14 ? new TileFluxScrubber() : super.createTileEntity(world, metadata) : new TileNodeStabilizer();
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int rs)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te == null || !(te instanceof TilePedestal) && !(te instanceof TileAlchemyFurnace))
		{
			if (te instanceof TileWandPedestal && ((TileWandPedestal) te).getAspects() != null && ((TileWandPedestal) te).getStackInSlot(0) != null && ((TileWandPedestal) te).getStackInSlot(0).getItem() instanceof ItemWandCasting)
			{
				ItemWandCasting wand = (ItemWandCasting) ((TileWandPedestal) te).getStackInSlot(0).getItem();
				float r = wand.getAllVis(((TileWandPedestal) te).getStackInSlot(0)).visSize() / (wand.getMaxVis(((TileWandPedestal) te).getStackInSlot(0)) * 6.0F);
				return MathHelper.floor_float(r * 14.0F) + 1;
			}
			return 0;
		}
		return Container.calcRedstoneFromInventory((IInventory) te);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int md)
	{
		return null;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
	{
		InventoryUtils.dropItems(par1World, par2, par3, par4);
		TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
		if (tileEntity instanceof TileInfusionMatrix && ((TileInfusionMatrix) tileEntity).crafting)
			par1World.createExplosion(null, par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, 2.0F, true);

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block par5)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileAlchemyFurnace)
			((TileAlchemyFurnace) te).getBellows();
		else if (te instanceof TileNodeConverter)
			((TileNodeConverter) te).checkStatus();
		else
		{
			int metadata = world.getBlockMetadata(x, y, z);

			/* TODO gamerforEA code clear:
			if (metadata == 1)
			{
				if (!world.isAirBlock(x, y + 1, z))
					InventoryUtils.dropItems(world, x, y, z);
			}
			else */
			if (metadata == 5)
			{
				if (!world.isAirBlock(x, y + 1, z) && (world.getBlock(x, y + 1, z) != this || world.getBlockMetadata(x, y + 1, z) != 8))
					InventoryUtils.dropItems(world, x, y, z);
			}
			else if (metadata == 3)
			{
				if (world.getBlock(x, y + 1, z) != this || world.getBlockMetadata(x, y + 1, z) != 4)
				{
					this.dropBlockAsItem(world, x, y, z, metadata, 0);
					world.setBlock(x, y, z, Blocks.air, 0, 3);
				}
			}
			else if (metadata == 4 && (world.getBlock(x, y - 1, z) != this || world.getBlockMetadata(x, y - 1, z) != 3))
			{
				this.dropBlockAsItem(world, x, y, z, metadata, 0);
				world.setBlock(x, y, z, Blocks.air, 0, 3);
			}
		}

	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
	{
		if (world.isRemote)
			return true;
		int metadata = world.getBlockMetadata(x, y, z);
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (metadata == 0 && tileEntity instanceof TileAlchemyFurnace && !player.isSneaking())
		{
			player.openGui(Thaumcraft.instance, 9, world, x, y, z);
			return true;
		}
		if (metadata == 1 && tileEntity instanceof TilePedestal)
		{
			TilePedestal pedestal = (TilePedestal) tileEntity;
			ItemStack stackInSlot = pedestal.getStackInSlot(0);

			// TODO gamerforEA code start
			ItemStack stackInHand = player.getCurrentEquippedItem();
			// TODO gamerforEA code end

			// TODO gamerforEA add condition [2]
			if (stackInSlot != null && stackInSlot.stackSize > 0)
			{
				// TODO gamerforEA code start
				if (stackInHand != null && stackInHand.stackSize > 0 && stackInHand.isItemEqual(stackInSlot) && ItemStack.areItemStackTagsEqual(stackInHand, stackInSlot))
					return true;
				// TODO gamerforEA code end

				InventoryUtils.dropItemsAtEntity(world, x, y, z, player);
				world.playSoundEffect(x, y, z, "random.pop", 0.2F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 1.5F);
				return true;
			}

			// TODO gamerforEA code clear:
			// ItemStack stackInHand = player.getCurrentEquippedItem();

			// TODO gamerforEA add condition [2]
			if (stackInHand != null && stackInHand.stackSize > 0)
			{
				ItemStack i = stackInHand.copy();
				i.stackSize = 1;
				pedestal.setInventorySlotContents(0, i);
				--stackInHand.stackSize;
				if (stackInHand.stackSize == 0)
					player.setCurrentItemOrArmor(0, null);

				player.inventory.markDirty();
				world.playSoundEffect(x, y, z, "random.pop", 0.2F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 1.6F);
				return true;
			}
		}

		if (metadata == 8)
		{
			--y;
			metadata = world.getBlockMetadata(x, y, z);
			tileEntity = world.getTileEntity(x, y, z);
		}

		if (metadata == 5 && tileEntity instanceof TileWandPedestal)
		{
			if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().isItemEqual(new ItemStack(this, 1, 8)))
				return false;

			TileWandPedestal pedestal = (TileWandPedestal) tileEntity;
			if (pedestal.getStackInSlot(0) != null)
			{
				InventoryUtils.dropItemsAtEntity(world, x, y, z, player);
				world.markBlockForUpdate(x, y, z);
				pedestal.markDirty();
				world.playSoundEffect(x, y, z, "random.pop", 0.2F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 1.5F);
				return true;
			}

			ItemStack stackInHand = player.getCurrentEquippedItem();

			// TODO gamerforEA add condition [2]
			if (stackInHand != null && stackInHand.stackSize > 0 && (stackInHand.getItem() instanceof ItemWandCasting || stackInHand.getItem() instanceof ItemAmuletVis))
			{
				ItemStack i = stackInHand.copy();
				i.stackSize = 1;
				pedestal.setInventorySlotContents(0, i);
				--stackInHand.stackSize;
				if (stackInHand.stackSize == 0)
					player.setCurrentItemOrArmor(0, null);

				player.inventory.markDirty();
				world.markBlockForUpdate(x, y, z);
				pedestal.markDirty();
				world.playSoundEffect(x, y, z, "random.pop", 0.2F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 1.6F);
				return true;
			}
		}

		if (metadata == 12 && tileEntity instanceof TileSpa && !player.isSneaking())
		{
			FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(player.inventory.getCurrentItem());
			if (fs != null)
			{
				TileSpa tile = (TileSpa) tileEntity;
				if (tile.tank.getFluidAmount() < tile.tank.getCapacity() && (tile.tank.getFluid() == null || tile.tank.getFluid().isFluidEqual(fs)))
				{
					tile.fill(ForgeDirection.UNKNOWN, FluidContainerRegistry.getFluidForFilledItem(player.inventory.getCurrentItem()), true);
					ItemStack emptyContainer = null;
					FluidContainerData[] fcs = FluidContainerRegistry.getRegisteredFluidContainerData();

					for (FluidContainerData fcd : fcs)
					{
						if (fcd.filledContainer.isItemEqual(player.inventory.getCurrentItem()))
							emptyContainer = fcd.emptyContainer.copy();
					}

					player.inventory.decrStackSize(player.inventory.currentItem, 1);
					if (emptyContainer != null && !player.inventory.addItemStackToInventory(emptyContainer))
						player.dropPlayerItemWithRandomChoice(emptyContainer, false);

					player.inventoryContainer.detectAndSendChanges();
					tile.markDirty();
					world.markBlockForUpdate(x, y, z);
					world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "game.neutral.swim", 0.33F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
				}
			}
			else
				player.openGui(Thaumcraft.instance, 19, world, x, y, z);

			return true;
		}
		if (metadata == 13 && tileEntity instanceof TileFocalManipulator && !player.isSneaking())
		{
			if (ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), "FOCALMANIPULATION"))
				player.openGui(Thaumcraft.instance, 20, world, x, y, z);
			else if (!world.isRemote)
				// TODO gamerforEA code replace, old code:
				// player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + StatCollector.translateToLocal("tc.researchmissing")));
				player.addChatComponentMessage(color(translate("tc.researchmissing"), EnumChatFormatting.RED));
			// TODO gamerforEA code end

			return true;
		}
		return super.onBlockActivated(world, x, y, z, player, side, par7, par8, par9);
	}

	@Override
	public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
	{
		int metadata = world.getBlockMetadata(i, j, k);
		if (metadata == 5)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
			this.setBlockBounds(0.25F, 0.5F, 0.25F, 0.75F, 1.0F, 0.75F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
			this.setBlockBounds(0.125F, 0.25F, 0.125F, 0.875F, 0.5F, 0.875F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
		}
		else
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
		}

	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k)
	{
		int metadata = world.getBlockMetadata(i, j, k);
		if (metadata == 1)
			this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.99F, 0.75F);
		else if (metadata == 5)
			this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
		else if (metadata == 3)
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		else if (metadata == 4)
			this.setBlockBounds(0.0F, -1.0F, 0.0F, 1.0F, -0.5F, 1.0F);
		else if (metadata == 8)
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.4375F, 0.9375F);
		else
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		super.setBlockBoundsBasedOnState(world, i, j, k);
	}

	@Override
	public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		if (par5 == 1)
		{
			if (par1World.isRemote)
			{
				Thaumcraft.proxy.blockSparkle(par1World, par2, par3, par4, 11960575, 2);
				par1World.playAuxSFX(2001, par2, par3, par4, Block.getIdFromBlock(Blocks.stonebrick));
			}

			return true;
		}
		return super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 11 && side == ForgeDirection.UP || meta == 12 || super.isSideSolid(world, x, y, z, side);
	}
}
