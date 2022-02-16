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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package net.divinerpg.items.base;

import com.mojang.realmsclient.gui.ChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.utils.TooltipLocalizer;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHeritageSword
extends ItemModSword {
    private static int useDelay = 0;

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean B) {
        if (useDelay != 0) {
            --useDelay;
        }
    }

    private boolean getReady() {
        return useDelay == 0;
    }

    public ItemHeritageSword(String name, Item.ToolMaterial mat) {
        super(mat, name);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (this.getReady()) {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 2));
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 0));
            useDelay = 2000;
        }
        return super.onItemRightClick(stack, world, player);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        double lvl = (double)EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)item) * 1.25;
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.heritage1", (Object[])new Object[0]));
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.heritage2", (Object[])new Object[0]));
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.heritage3", (Object[])new Object[0]));
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.heritage4", (Object[])new Object[0]));
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

