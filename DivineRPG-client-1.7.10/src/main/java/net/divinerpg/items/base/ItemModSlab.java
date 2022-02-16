/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.item.ItemSlab
 */
package net.divinerpg.items.base;

import net.divinerpg.blocks.base.BlockModSlab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;

public class ItemModSlab
extends ItemSlab {
    public ItemModSlab(Block thisOne, BlockModSlab single, BlockModSlab stack, Boolean stacked) {
        super(thisOne, (BlockSlab)single, (BlockSlab)stack, stacked.booleanValue());
    }
}

