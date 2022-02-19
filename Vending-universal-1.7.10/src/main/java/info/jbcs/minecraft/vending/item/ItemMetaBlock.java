/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemBlock
 */
package info.jbcs.minecraft.vending.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemMetaBlock
extends ItemBlock {
    public ItemMetaBlock(Block b) {
        super(b);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(int i) {
        return i;
    }
}

