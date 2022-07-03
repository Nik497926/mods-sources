/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.entity;

import java.util.Random;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;

public abstract class RenderSparkBase
extends RenderEntity {
    public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        IIcon iicon = this.getBaseIcon(entity);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.05f);
        double time = (float)ClientTickHandler.ticksInGame + par9;
        float a = 0.1f + (float)(1 - entity.getDataWatcher().getWatchableObjectInt(27)) * 0.8f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((0.7f + 0.3f * (float)(Math.sin((time += (double)new Random(entity.getEntityId()).nextInt()) / 5.0) + 0.5) * 2.0f) * a));
        float scale = 0.75f + 0.1f * (float)Math.sin(time / 10.0);
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        this.bindEntityTexture(entity);
        Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        float r = 180.0f - this.renderManager.playerViewY;
        GL11.glRotatef((float)r, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.renderManager.playerViewX), (float)1.0f, (float)0.0f, (float)0.0f);
        this.func_77026_a(tessellator, iicon);
        IIcon spinningIcon = this.getSpinningIcon(entity);
        if (spinningIcon != null) {
            GL11.glTranslatef((float)(-0.02f + (float)Math.sin(time / 20.0) * 0.2f), (float)(0.24f + (float)Math.cos(time / 20.0) * 0.2f), (float)0.005f);
            GL11.glScalef((float)0.2f, (float)0.2f, (float)0.2f);
            this.colorSpinningIcon(entity, a);
            this.func_77026_a(tessellator, spinningIcon);
        }
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.renderCallback(entity, par9);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    public abstract IIcon getBaseIcon(Entity var1);

    public void colorSpinningIcon(Entity entity, float a) {
    }

    public abstract IIcon getSpinningIcon(Entity var1);

    public void renderCallback(Entity entity, float pticks) {
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return TextureMap.locationItemsTexture;
    }

    private void func_77026_a(Tessellator p_77026_1_, IIcon p_77026_2_) {
        float f = p_77026_2_.getMinU();
        float f1 = p_77026_2_.getMaxU();
        float f2 = p_77026_2_.getMinV();
        float f3 = p_77026_2_.getMaxV();
        float f4 = 1.0f;
        float f5 = 0.5f;
        float f6 = 0.25f;
        p_77026_1_.startDrawingQuads();
        p_77026_1_.setNormal(0.0f, 1.0f, 0.0f);
        p_77026_1_.setBrightness(240);
        p_77026_1_.addVertexWithUV((double)(0.0f - f5), (double)(0.0f - f6), 0.0, (double)f, (double)f3);
        p_77026_1_.addVertexWithUV((double)(f4 - f5), (double)(0.0f - f6), 0.0, (double)f1, (double)f3);
        p_77026_1_.addVertexWithUV((double)(f4 - f5), (double)(f4 - f6), 0.0, (double)f1, (double)f2);
        p_77026_1_.addVertexWithUV((double)(0.0f - f5), (double)(f4 - f6), 0.0, (double)f, (double)f2);
        p_77026_1_.draw();
    }
}

