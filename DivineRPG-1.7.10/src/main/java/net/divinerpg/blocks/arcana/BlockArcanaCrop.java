/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.arcana;

import net.divinerpg.blocks.base.BlockModCrop;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlockArcanaCrop
extends BlockModCrop {
    public BlockArcanaCrop(String name, int stages, String crop) {
        super(name, stages, crop);
    }

    @Override
    public boolean canPlaceBlockOn(Block block) {
        return block == ArcanaBlocks.arcanaGrass;
    }

    @Override
    public boolean canPlaceBlockAt(World w, int x, int y, int z) {
        return Util.bordersTar(w, x, y - 1, z);
    }

    @Override
    public boolean canBlockStay(World w, int x, int y, int z) {
        return super.canBlockStay(w, x, y, z) && Util.bordersTar(w, x, y - 1, z);
    }
}

