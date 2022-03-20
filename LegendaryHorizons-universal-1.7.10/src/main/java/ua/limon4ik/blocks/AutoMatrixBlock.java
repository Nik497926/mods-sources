package ua.limon4ik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumicenergistics.common.blocks.AbstractBlockAEWrenchable;
import ua.limon4ik.LegendaryHorizons;
import net.minecraft.util.AxisAlignedBB;
import ua.limon4ik.tiles.TileEntityAutoMatrix;

import java.util.ArrayList;

public class AutoMatrixBlock extends AbstractBlockAEWrenchable {
    public AutoMatrixBlock() {
        super(Material.iron);
        setHardness(1.0f);
        setStepSound(Block.soundTypeMetal);
        setCreativeTab(LegendaryHorizons.tab);
        setBlockName("AutoMatrix");
    }
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x , y, z, x+1, y + 1.65, z+1);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return getCollisionBoundingBoxFromPool(world, x, y, z);
    }
    @Override
    protected boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (player.isSneaking())return false;
        if(!world.isRemote) {
            ItemStack stack=player.getHeldItem();
            TileEntityAutoMatrix te = (TileEntityAutoMatrix) world.getTileEntity(x, y, z);
            if (stack == null || stack.stackSize <= 0) {
                if (te.hasItem()) {
                    ItemStack i = te.getItemStack();
                    te.removeItemStack();
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, i);
                }
            } else {
                if (!te.hasItem()) {
                    if (te.setItem(stack)) {
                        stack = stack.copy();
                        stack.stackSize--;
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, stack.stackSize <= 0 ? null : stack);
                    }
                }
            }
           world.markBlockForUpdate(x, y, z);
        }
        return true;
    }

    @Override
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
        if(!p_149749_1_.isRemote){
            TileEntity te=p_149749_1_.getTileEntity(p_149749_2_,p_149749_3_,p_149749_4_);
            if(te instanceof TileEntityAutoMatrix){
                TileEntityAutoMatrix am= (TileEntityAutoMatrix) te;
                ArrayList<ItemStack> drops=new ArrayList<>();
                am.getDrops(p_149749_1_,p_149749_2_,p_149749_3_,p_149749_4_,drops);
                drops.forEach(e->p_149749_1_.spawnEntityInWorld(new EntityItem(p_149749_1_,p_149749_2_,p_149749_3_,p_149749_4_,e)));
            }
        }
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityAutoMatrix();
    }
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    @Override
    public int getRenderType() {
        return -1;
    }
}
