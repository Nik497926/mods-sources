/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package net.divinerpg.blocks.twilight;

import net.divinerpg.blocks.base.BlockModCrop;
import net.minecraft.block.Block;

public class BlockTwilightCrop
extends BlockModCrop {
    private Block placeOn;

    public BlockTwilightCrop(String name, int stages, String crop, Block grass) {
        super(name, stages, crop);
        this.placeOn = grass;
    }

    @Override
    public boolean canPlaceBlockOn(Block block) {
        return block == this.placeOn;
    }
}

