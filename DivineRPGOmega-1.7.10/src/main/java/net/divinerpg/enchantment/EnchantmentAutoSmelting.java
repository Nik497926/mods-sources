/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.item.ItemHoe
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemSpade
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class EnchantmentAutoSmelting
extends Enchantment {
    public EnchantmentAutoSmelting(String enchantmentName, int enchantmentId, int weight, EnumEnchantmentType enchantmentType) {
        super(enchantmentId, weight, enchantmentType);
        this.setName(enchantmentName);
    }

    public boolean canApplyTogether(Enchantment enchantment) {
        return super.canApplyTogether(enchantment) && enchantment != Enchantment.silkTouch;
    }

    public boolean canApply(ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemPickaxe || itemStack.getItem() instanceof ItemAxe || itemStack.getItem() instanceof ItemSpade || itemStack.getItem() instanceof ItemHoe;
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

