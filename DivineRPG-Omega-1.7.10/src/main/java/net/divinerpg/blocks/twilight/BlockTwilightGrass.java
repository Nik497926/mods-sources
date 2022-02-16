/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package net.divinerpg.blocks.twilight;

import java.util.ArrayList;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockTwilightGrass
extends BlockMod
implements IShearable {
    private Block grass;

    public BlockTwilightGrass(String name, Block grass) {
        super(EnumBlockType.PLANT, name, 0.0f);
        float var4 = 0.2f;
        this.setBlockBounds(0.5f - var4, 0.0f, 0.5f - var4, 0.5f + var4, var4 * 3.0f, 0.5f + var4);
        this.grass = grass;
    }

    public boolean canPlaceBlockAt(World w, int x, int y, int z) {
        return w.getBlock(x, y - 1, z) == this.grass;
    }

    public boolean canBlockStay(World w, int x, int y, int z) {
        return w.getBlock(x, y - 1, z) == this.grass;
    }

    public void onNeighborBlockChange(World w, int x, int y, int z, Block b) {
        if (!this.canBlockStay(w, x, y, z)) {
            w.setBlockToAir(x, y, z);
            this.dropBlockAsItem(w, x, y, z, new ItemStack((Block)this));
        }
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        return null;
    }

    public int getRenderType() {
        return 1;
    }

    @Override
    public Item getItemDropped(int par1, Random rand, int par3) {
        return null;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack((Block)this, 1));
        return ret;
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }
}

