/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 */
package net.divinerpg.blocks.arcana;

import net.divinerpg.blocks.arcana.BlockArcanaCrop;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockEucalyptusRoot
extends BlockArcanaCrop {
    public BlockEucalyptusRoot(String name) {
        super(name, 3, "eucalyptusRoot");
    }

    @Override
    public Item getSeeds() {
        return ArcanaItems.eucalyptusSeeds;
    }

    @Override
    public Item getDropItem() {
        return Item.getItemFromBlock((Block)ArcanaBlocks.eucalyptusWood);
    }
}

