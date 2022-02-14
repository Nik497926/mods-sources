/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 */
package net.divinerpg.items.base;

import com.mojang.realmsclient.gui.ChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.utils.TooltipLocalizer;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemEnderFlameSword
extends ItemModSword {
    Random r = new Random();

    public ItemEnderFlameSword(String name, Item.ToolMaterial mat) {
        super(mat, name);
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {
        if (this.r.nextInt(99) < 3) {
            entity.setFire(4);
            entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)player), 100.0f);
        }
        stack.damageItem(1, player);
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        double lvl = (double)EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)item) * 1.25;
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.flameSword2", (Object[])new Object[0]));
        if (EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)item) == 0) {
            list.add(TooltipLocalizer.meleeDam(this.mat.getDamageVsEntity() + 5.0f));
        } else if (EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)item) > 0) {
            list.add(TooltipLocalizer.meleeDam((double)(this.mat.getDamageVsEntity() + 5.0f) + lvl));
        }
        int dur = item.getMaxDamage() - item.getItemDamage();
        double max = item.getMaxDamage();
        int res = (int)((double)dur / max * 100.0);
        list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
    }
}

