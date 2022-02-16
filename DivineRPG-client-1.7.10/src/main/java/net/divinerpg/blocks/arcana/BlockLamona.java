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

public class BlockLamona
extends BlockArcanaCrop {
    public BlockLamona(String name) {
        super(name, 3, "lamona");
    }

    @Override
    public Item getSeeds() {
        return ArcanaItems.lamonaSeeds;
    }

    @Override
    public Item getDropItem() {
        return ArcanaItems.lamona;
    }
}

