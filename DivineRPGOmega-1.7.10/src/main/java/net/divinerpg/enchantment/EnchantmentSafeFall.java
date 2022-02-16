/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 */
package net.divinerpg.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentSafeFall
extends Enchantment {
    public EnchantmentSafeFall(String enchantmentName, int enchantmentId, int weight, EnumEnchantmentType enchantmentType) {
        super(enchantmentId, weight, enchantmentType);
        this.setName(enchantmentName);
    }

    public boolean canApplyTogether(Enchantment enchantment) {
        return super.canApplyTogether(enchantment);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 20;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 30;
    }
}

