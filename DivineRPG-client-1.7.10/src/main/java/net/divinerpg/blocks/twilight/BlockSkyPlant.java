/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 */
package net.divinerpg.blocks.twilight;

import net.divinerpg.blocks.twilight.BlockTwilightCrop;
import net.divinerpg.utils.items.TwilightItemsCrops;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockSkyPlant
extends BlockTwilightCrop {
    public BlockSkyPlant() {
        super("skyPlant", 3, "skyPlant", (Block)Blocks.grass);
    }

    @Override
    public Item getSeeds() {
        return TwilightItemsCrops.skyPlantSeeds;
    }

    @Override
    public Item getDropItem() {
        return TwilightItemsCrops.skyFlower;
    }
}

