/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package net.divinerpg.utils.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.List;
import java.util.Random;
import net.divinerpg.items.base.ItemModSword;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemSpectralSword
extends ItemModSword {
    private float time;
    private float level;

    public ItemSpectralSword(Item.ToolMaterial toolMaterial, String name) {
        super(toolMaterial, name);
        this.time = 50.0f;
        this.level = 1.0f;
    }

    public ItemSpectralSword(Item.ToolMaterial toolMaterial, String name, float time, float level) {
        super(toolMaterial, name);
        this.time = time;
        this.level = level;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        Random rand = new Random();
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, (int)this.time, (int)this.level));
            ((EntityLivingBase)entity).motionY = 0.3;
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, (int)this.time, (int)this.level));
        }
        return false;
    }

    @Override
    public void addAdditionalInformation(List list) {
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.spectralsword1", (Object[])new Object[0]));
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.spectralsword2", (Object[])new Object[0]));
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.spectralsword3", (Object[])new Object[0]));
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.spectralsword4", (Object[])new Object[0]));
    }
}

