package thaumcraft.common.blocks;

import com.gamerforea.eventhelper.util.EventUtils;
import com.gamerforea.thaumcraft.ModUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileChestHungry;

import java.util.Random;

public class BlockChestHungry extends BlockContainer
{
	private Random random = new Random();
	public IIcon icon;

	public BlockChestHungry()
	{
		super(Material.wood);
		this.setHardness(2.5F);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("thaumcraft:woodplain");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.icon;
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
	public int getRenderType()
	{
		return ConfigBlocks.blockChestHungryRI;
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
		return te instanceof IInventory ? Container.calcRedstoneFromInventory((IInventory) te) : 0;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase entity, ItemStack is)
	{
		// TODO gamerforEA code start
		if (entity instanceof EntityPlayer && EventUtils.cantBreak((EntityPlayer) entity, x, y, z))
			return;
		// TODO gamerforEA code end

		int var6 = par1World.getBlockMetadata(x, y, z) & 3;
		int var7 = BlockPistonBase.determineOrientation(par1World, x, y, z, entity);
		par1World.setBlock(x, y, z, this, var7, 3);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileChestHungry tile = (TileChestHungry) world.getTileEntity(x, y, z);
		if (tile != null)
			for (int slot = 0; slot < tile.getSizeInventory(); ++slot)
			{
				ItemStack stack = tile.getStackInSlot(slot);
				if (stack != null)
				{
					float xOffset = this.random.nextFloat() * 0.8F + 0.1F;
					float yOffset = this.random.nextFloat() * 0.8F + 0.1F;
					float zOffset = this.random.nextFloat() * 0.8F + 0.1F;

					while (stack.stackSize > 0)
					{
						int dropStackSize = this.random.nextInt(21) + 10;
						if (dropStackSize > stack.stackSize)
							dropStackSize = stack.stackSize;

						stack.stackSize -= dropStackSize;

						// TODO gamerforEA code replace, old code:
						// EntityItem entityItem = new EntityItem(world, x + xOffset, y + yOffset, z + zOffset, new ItemStack(stack.getItem(), dropStackSize, stack.getItemDamage()));
						ItemStack dropStack = stack.copy();
						dropStack.stackSize = dropStackSize;
						EntityItem entityItem = new EntityItem(world, x + xOffset, y + yOffset, z + zOffset, dropStack);
						// TODO gamerforEA code end

						float factor = 0.05F;
						entityItem.motionX = (float) this.random.nextGaussian() * factor;
						entityItem.motionY = (float) this.random.nextGaussian() * factor + 0.2F;
						entityItem.motionZ = (float) this.random.nextGaussian() * factor;

						/* TODO gamerforEA code clear:
						if (stack.hasTagCompound())
							entityItem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy()); */

						world.spawnEntityInWorld(entityItem);
					}
				}
			}

		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		float var5 = 0.0625F;
		return AxisAlignedBB.getBoundingBox(par2 + var5, par3, par4 + var5, par2 + 1 - var5, par3 + 1 - var5, par4 + 1 - var5);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile instanceof TileChestHungry)
			if (!world.isRemote)
				// TODO gamerforEA add condition [2]
				if (entity instanceof EntityItem && !entity.isDead)
				{
					EntityItem entityItem = (EntityItem) entity;

					// TODO gamerforEA code replace, old code:
					// ItemStack stack = entityItem.getEntityItem();
					ItemStack stack = entityItem.getEntityItem().copy();
					// TODO gamerforEA code end

					ItemStack leftovers = InventoryUtils.placeItemStackIntoInventory(stack, (IInventory) tile, 1, true);
					if (leftovers == null || leftovers.stackSize != stack.stackSize)
					{
						world.playSoundAtEntity(entity, "random.eat", 0.25F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
						world.addBlockEvent(x, y, z, ConfigBlocks.blockChestHungry, 2, 2);
					}

					// TODO gamerforEA code replace, old code:
					// if (leftovers != null)
					if (leftovers != null && leftovers.stackSize > 0)
						// TODO gamerforEA code end
						entityItem.setEntityItemStack(leftovers);
					else
					{
						entity.setDead();

						// TODO gamerforEA code start
						ItemStack newStack = stack.copy();
						newStack.stackSize = 0;
						entityItem.setEntityItemStack(newStack);
						ModUtils.killEntity(entity);
						// TODO gamerforEA code end
					}

					tile.markDirty();
				}
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if (tile == null)
			return true;
		else if (par1World.isRemote)
			return true;
		else
		{
			par5EntityPlayer.displayGUIChest((IInventory) tile);
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World, int m)
	{
		return new TileChestHungry();
	}
}
