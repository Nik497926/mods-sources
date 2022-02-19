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

public class EnchantmentObilie
extends Enchantment {
    public EnchantmentObilie(String enchantmentName, int enchantmentId, int weight, EnumEnchantmentType enchantmentType) {
        super(enchantmentId, weight, enchantmentType);
        this.setName(enchantmentName);
    }

    public boolean canApplyTogether(Enchantment enchantment) {
        return super.canApplyTogether(enchantment);
    }

    public int getMinLevel() {
        return 1;
    }

    public int getMaxLevel() {
        return 3;
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 5 + 5 * enchantmentLevel;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }
}

