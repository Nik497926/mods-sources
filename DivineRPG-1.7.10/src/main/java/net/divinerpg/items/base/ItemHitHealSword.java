/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.EntityLiving
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
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.utils.TooltipLocalizer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHitHealSword
extends ItemModSword {
    public ItemHitHealSword(String name, Item.ToolMaterial mat) {
        super(mat, name);
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase live, EntityLivingBase player) {
        int size = player.worldObj.getEntitiesWithinAABB(EntityLiving.class, player.boundingBox.expand(5.0, 5.0, 5.0)).size();
        if (size >= 10) {
            player.heal(3.0f);
        } else {
            player.heal(0.3f * (float)size);
        }
        stack.damageItem(1, player);
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.meleeDam(this.mat.getDamageVsEntity() + 5.0f));
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.hitheal", (Object[])new Object[0]));
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.hithealsword", (Object[])new Object[0]));
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.hithealsword2", (Object[])new Object[0]));
        int dur = item.getMaxDamage() - item.getItemDamage();
        double max = item.getMaxDamage();
        int res = (int)((double)dur / max * 100.0);
        list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
    }
}

