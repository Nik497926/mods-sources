/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.potion;

import com.meteor.extrabotany.common.lib.LibReference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionMods
extends Potion {
    private static final ResourceLocation resource = LibReference.POTION_GUI;

    public PotionMods(int id, String name, boolean badEffect, int color, int iconIndex) {
        super(id, badEffect, color);
        this.setPotionName("extrabotania.potion." + name);
        this.setIconIndex(iconIndex % 8, iconIndex / 8);
    }

    @SideOnly(value=Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(resource);
        return super.getStatusIconIndex();
    }

    public boolean hasEffect(EntityLivingBase entity) {
        return this.hasEffect(entity, this);
    }

    public boolean hasEffect(EntityLivingBase entity, Potion potion) {
        return entity.getActivePotionEffect(potion) != null;
    }

    public void performEffect(EntityLivingBase entity, int level) {
    }

    public boolean isReady(int duration, int level) {
        return true;
    }
}

