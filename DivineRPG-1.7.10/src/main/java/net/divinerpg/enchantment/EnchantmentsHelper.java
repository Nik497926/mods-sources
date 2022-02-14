/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.enchantment;

import net.divinerpg.enchantment.EnchanmtmentsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class EnchantmentsHelper {
    public static boolean hasSafeFallEnchantment(EntityPlayer player) {
        return EnchantmentHelper.getEnchantmentLevel((int)EnchanmtmentsRegistry.TELEKINESIS.effectId, (ItemStack)player.getHeldItem()) > 0;
    }

    public static boolean hasSmeltingEnchantment(EntityPlayer player) {
        return EnchantmentHelper.getEnchantmentLevel((int)EnchanmtmentsRegistry.AUTO_SMELTING.effectId, (ItemStack)player.getHeldItem()) > 0;
    }

    public static boolean hasObilieEnchantment(EntityPlayer player) {
        return EnchantmentHelper.getEnchantmentLevel((int)EnchanmtmentsRegistry.OBILIE.effectId, (ItemStack)player.getHeldItem()) > 0;
    }
}

