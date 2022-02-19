/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package net.divinerpg.blocks.vanilla;

import java.util.Random;
import net.divinerpg.blocks.vanilla.VanillaBlock;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.item.Item;

public class BlockBloodgemOre
extends VanillaBlock {
    public BlockBloodgemOre() {
        super(EnumBlockType.ROCK, "bloodgemOre", 3.0f, 3);
        this.setResistance(2000.0f);
    }

    @Override
    public Item getItemDropped(int i, Random rand, int i2) {
        return VanillaItemsOther.bloodgem;
    }
}

