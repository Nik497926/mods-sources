/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package net.divinerpg.blocks.arcana;

import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockElevantium
extends BlockMod {
    public BlockElevantium(String name) {
        super(EnumBlockType.IRON, name, 3.0f, DivineRPGTabs.utility);
        this.setResistance(20.0f);
        this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.03125f, 0.9375f);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean canPlaceBlockAt(World w, int x, int y, int z) {
        return w.getBlock(x, y - 1, z).isSideSolid((IBlockAccess)w, x, y, z, ForgeDirection.UP);
    }

    public boolean canBlockStay(World w, int x, int y, int z) {
        return w.getBlock(x, y - 1, z).isSideSolid((IBlockAccess)w, x, y, z, ForgeDirection.UP);
    }

    public void onNeighborBlockChange(World w, int x, int y, int z, Block b) {
        if (!this.canBlockStay(w, x, y, z)) {
            this.dropBlockAsItem(w, x, y, z, new ItemStack((Block)this, 1, 0));
            w.setBlockToAir(x, y, z);
        }
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    }
}

