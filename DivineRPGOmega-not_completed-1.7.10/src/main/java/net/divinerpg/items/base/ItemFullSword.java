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
 */
package net.divinerpg.items.base;

import com.mojang.realmsclient.gui.ChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFullSword
extends ItemModSword {
    private double chance;
    protected int amountToAdd;
    private Random rand = new Random();

    public ItemFullSword(String name, Item.ToolMaterial mat, int amountToAdd, double chance) {
        super(mat, name);
        this.chance = chance;
        this.amountToAdd = amountToAdd;
    }

    public ItemFullSword(String name, Item.ToolMaterial mat, String skinName, int amountToAdd, double chance) {
        super(mat, name);
        this.chance = chance;
        this.amountToAdd = amountToAdd;
        this.skinName = skinName;
        this.setCreativeTab(DivineRPGTabs.skins);
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase live, EntityLivingBase player) {
        if ((double)this.rand.nextInt(999) < this.chance * 10.0) {
            player.heal(20.0f);
            ArcanaHelper.getProperties((EntityPlayer)player).forceRegen(this.amountToAdd);
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
        list.add(ChatFormatting.GOLD + new Double(this.chance).toString() + "% " + I18n.format((String)"items.fullsword", (Object[])new Object[0]));
        int dur = item.getMaxDamage() - item.getItemDamage();
        double max = item.getMaxDamage();
        int res = (int)((double)dur / max * 100.0);
        list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
    }
}

