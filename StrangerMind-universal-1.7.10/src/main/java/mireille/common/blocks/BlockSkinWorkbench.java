package mireille.common.blocks;

import java.util.*;
import net.minecraft.block.material.*;
import mireille.*;
import net.minecraft.block.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.entity.player.*;
import net.minecraft.tileentity.*;
import mireille.common.tileentity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.item.*;
import net.minecraft.entity.item.*;
import net.minecraft.nbt.*;
import net.minecraft.entity.*;

public class BlockSkinWorkbench extends BlockContainer implements ITileEntityProvider
{
    private final Random random;
    
    public BlockSkinWorkbench(final String name) {
        super(Material.iron);
        this.random = new Random();
        this.setBlockName(name);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(3.0f);
        this.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerBlock((Block)this, name);
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int side, final float hitX, final float hitY, final float hitZ) {
        if (!world.isRemote) {
            player.openGui((Object)StrangerMind.instance, 3, world, x, y, z);
        }
        return true;
    }
    
    public TileEntity createNewTileEntity(final World world, final int meta) {
        return new TileSkinWorkbench();
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public boolean isNormalCube() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public int getRenderType() {
        return -1;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(final IBlockAccess blockAccess, final int x, final int y, final int z, final int side) {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister icon) {
        super.blockIcon = icon.registerIcon("mireille:stone/stone_0");
    }
    
    public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int par1) {
        final TileSkinWorkbench tile = (TileSkinWorkbench)world.getTileEntity(x, y, z);
        if (tile != null) {
            for (int i1 = 0; i1 < tile.getSizeInventory(); ++i1) {
                final ItemStack itemstack = tile.getStackInSlot(i1);
                if (itemstack != null) {
                    final float f = this.random.nextFloat() * 0.8f + 0.1f;
                    final float f2 = this.random.nextFloat() * 0.8f + 0.1f;
                    final float f3 = this.random.nextFloat() * 0.8f + 0.1f;
                    while (itemstack.stackSize > 0) {
                        int j1 = this.random.nextInt(21) + 10;
                        if (j1 > itemstack.stackSize) {
                            j1 = itemstack.stackSize;
                        }
                        final ItemStack itemStack = itemstack;
                        itemStack.stackSize -= j1;
                        final EntityItem entityitem = new EntityItem(world, (double)(x + f), (double)(y + f2), (double)(z + f3), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                        final float f4 = 0.05f;
                        entityitem.motionX = (float)this.random.nextGaussian() * f4;
                        entityitem.motionY = (float)this.random.nextGaussian() * f4 + 0.2f;
                        entityitem.motionZ = (float)this.random.nextGaussian() * f4;
                        world.spawnEntityInWorld((Entity)entityitem);
                    }
                }
            }
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, par1);
    }
}
