/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.tile.TileBlockBoost;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBoost
extends Block
implements ITileEntityProvider {
    protected BlockBoost() {
        super(Material.rock);
        this.setBlockName("manaboost");
        this.setHardness(1.0f);
        this.setBlockBounds(0.0f, 0.0f, 0.0625f, 1.0f, 0.5f, 1.0f);
        GameRegistry.registerBlock(this, "manaboost");
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileBlockBoost();
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        if (par7Entity != null && !(par7Entity instanceof EntityItem)) {
            super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, arraylist, par7Entity);
        }
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public int getMixedBrightnessForBlock(IBlockAccess world, int x, int y, int z) {
        return world.getLightBrightnessForSkyBlocks(x, y, z, world.getBlock(x, y, z).getLightValue(world, x, y - 1, z));
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        return 0;
    }
}

