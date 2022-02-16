/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.potion.Potion
 */
package net.divinerpg.items.base;

import net.minecraft.potion.Potion;

public class ItemPotion
extends Potion {
    public ItemPotion(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }

    public Potion setIconIndex(int par1, int par2) {
        super.setIconIndex(par1, par2);
        return this;
    }

    public boolean isBadEffect() {
        return false;
    }
}

