/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 */
package net.divinerpg.enchantment;

import net.divinerpg.enchantment.EnchantmentAutoSmelting;
import net.divinerpg.enchantment.EnchantmentObilie;
import net.divinerpg.enchantment.EnchantmentSafeFall;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchanmtmentsRegistry {
    public static final Enchantment TELEKINESIS = new EnchantmentSafeFall("telekinesis", 100, 5, EnumEnchantmentType.weapon);
    public static final Enchantment OBILIE = new EnchantmentObilie("obilie", 103, 5, EnumEnchantmentType.weapon);
    public static final Enchantment AUTO_SMELTING = new EnchantmentAutoSmelting("autosmelting", 102, 3, EnumEnchantmentType.digger);

    public static void register() {
    }
}

