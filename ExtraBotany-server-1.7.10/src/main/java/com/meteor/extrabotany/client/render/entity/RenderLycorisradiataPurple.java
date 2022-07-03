/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.entity;

import com.meteor.extrabotany.client.model.ModelLycorisradiata;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataPurple;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderLycorisradiataPurple
extends RenderLiving {
    private static final ResourceLocation texture = new ResourceLocation("extrabotania", "textures/models/Lycorisradiata_PURPLE.png");

    public RenderLycorisradiataPurple() {
        super((ModelBase)new ModelLycorisradiata(), 0.5f);
    }

    protected void preRender(EntityLycorisradiataPurple par1EntityFlowerCyan, float par2) {
        this.shadowSize = 0.0f;
    }

    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2) {
        this.preRender((EntityLycorisradiataPurple)par1EntityLiving, par2);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}

