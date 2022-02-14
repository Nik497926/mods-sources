/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render;

import net.divinerpg.client.render.RenderLivingCreature;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSizeable
extends RenderLivingCreature {
    protected float scale;

    public RenderSizeable(ModelBase var1, float shadowsize, float scale, ResourceLocation texture) {
        super(var1, shadowsize * scale, texture);
        this.scale = scale;
    }

    public void preRenderScale(EntityMob var1, float var2) {
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
    }

    protected void preRenderCallback(EntityLivingBase var1, float var2) {
        this.preRenderScale((EntityMob)var1, var2);
    }
}

