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

public class BlockHitchak
extends BlockArcanaCrop {
    public BlockHitchak(String name) {
        super(name, 3, "hitchak");
    }

    @Override
    public Item getSeeds() {
        return ArcanaItems.hitchakSeeds;
    }

    @Override
    public Item getDropItem() {
        return ArcanaItems.hitchak;
    }
}

