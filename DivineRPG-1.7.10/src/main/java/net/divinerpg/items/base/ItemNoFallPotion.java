/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 */
package net.divinerpg.items.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

public class ItemNoFallPotion
extends Potion {
    public ItemNoFallPotion(int p_i1573_1_, boolean p_i1573_2_, int p_i1573_3_) {
        super(p_i1573_1_, p_i1573_2_, p_i1573_3_);
    }

    public boolean isReady(int p_76397_1_, int p_76397_2_) {
        return true;
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
        if (this.id == 22) {
            entity.fallDistance = 0.0f;
        }
    }
}

