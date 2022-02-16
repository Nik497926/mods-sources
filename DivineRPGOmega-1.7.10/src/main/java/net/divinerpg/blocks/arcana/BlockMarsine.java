/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package net.divinerpg.blocks.arcana;

import net.divinerpg.blocks.arcana.BlockArcanaCrop;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.item.Item;

public class BlockMarsine
extends BlockArcanaCrop {
    public BlockMarsine(String name) {
        super(name, 2, "marsine");
    }

    @Override
    public Item getSeeds() {
        return ArcanaItems.marsineSeeds;
    }

    @Override
    public Item getDropItem() {
        return ArcanaItems.marsine;
    }
}

