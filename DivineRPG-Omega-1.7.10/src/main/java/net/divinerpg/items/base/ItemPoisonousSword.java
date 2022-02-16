/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package net.divinerpg.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.Ticker;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemPoisonousSword
extends ItemModSword {
    private float poisonSeconds;
    private float level;
    private float blindnessSeconds;
    private int cooldown;

    public ItemPoisonousSword(Item.ToolMaterial mat, String name, float seconds) {
        super(mat, name);
        this.poisonSeconds = seconds;
        this.blindnessSeconds = 0.0f;
        this.level = 1.0f;
        this.cooldown = 0;
    }

    public ItemPoisonousSword(Item.ToolMaterial mat, String name, float seconds, float blindnessSeconds, float level, int cooldown) {
        super(mat, name);
        this.poisonSeconds = seconds;
        this.blindnessSeconds = blindnessSeconds;
        this.level = level;
        this.cooldown = cooldown * 20;
    }

    public ItemPoisonousSword(Item.ToolMaterial mat, String name, float seconds, float blindnessSeconds, float level, int cooldown, String skin) {
        super(mat, name);
        this.skinName = skin;
        this.poisonSeconds = seconds;
        this.blindnessSeconds = blindnessSeconds;
        this.level = level;
        this.cooldown = cooldown * 20;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase live, EntityLivingBase player) {
        if (Ticker.timerSword == 0) {
            live.addPotionEffect(new PotionEffect(Potion.poison.id, (int)(this.poisonSeconds * 20.0f), (int)this.level));
            if (this.skinName != null && this.skinName.equalsIgnoreCase("fire")) {
                live.setFire(5);
            }
            if (this.blindnessSeconds != 0.0f) {
                live.addPotionEffect(new PotionEffect(Potion.blindness.id, (int)(this.blindnessSeconds * 20.0f), (int)this.level + 1));
            }
            Ticker.timerSword = this.cooldown;
        }
        stack.damageItem(1, player);
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        if (this.skinName != null && !this.skinName.isEmpty()) {
            list.add(TooltipLocalizer.getSkin(this.skinName));
        }
        double lvl = (double)EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)item) * 1.25;
        if (EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)item) == 0) {
            list.add(TooltipLocalizer.meleeDam(this.mat.getDamageVsEntity() + 5.0f));
        } else if (EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)item) > 0) {
            list.add(TooltipLocalizer.meleeDam((double)(this.mat.getDamageVsEntity() + 5.0f) + lvl));
        }
        list.add(TooltipLocalizer.poison(this.poisonSeconds));
        if (this.blindnessSeconds != 0.0f) {
            list.add(TooltipLocalizer.blindness(this.blindnessSeconds));
            if (this.cooldown != 0) {
                list.add(TooltipLocalizer.cooldown(this.cooldown / 20));
            }
        }
        int dur = item.getMaxDamage() - item.getItemDamage();
        double max = item.getMaxDamage();
        int res = (int)((double)dur / max * 100.0);
        list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
    }
}

