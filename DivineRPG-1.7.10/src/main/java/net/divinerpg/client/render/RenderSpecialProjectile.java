/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSpecialProjectile
extends Render {
    public ResourceLocation texture;
    private float scale;

    public RenderSpecialProjectile(ResourceLocation par1) {
        this.texture = par1;
        this.scale = 1.0f;
    }

    public RenderSpecialProjectile(ResourceLocation par1, float scaleFactor) {
        this.texture = par1;
        this.scale = scaleFactor;
    }

    public void doRender(Entity projectile, double x, double y, double z, float par8, float par9) {
        GL11.glPushMatrix();
        this.bindEntityTexture(projectile);
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        GL11.glEnable((int)32826);
        GL11.glScalef((float)(this.scale * 0.5f), (float)(this.scale * 0.5f), (float)(this.scale * 0.5f));
        Tessellator tessellator = Tessellator.instance;
        float minU = 0.0f;
        float maxU = 1.0f;
        float minV = 0.0f;
        float maxV = 1.0f;
        float f7 = 1.0f;
        float f8 = 0.5f;
        float f9 = 0.25f;
        GL11.glRotatef((float)(180.0f - this.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.renderManager.playerViewX), (float)1.0f, (float)0.0f, (float)0.0f);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        tessellator.addVertexWithUV((double)(0.0f - f8), (double)(0.0f - f9), 0.0, (double)minU, (double)maxV);
        tessellator.addVertexWithUV((double)(f7 - f8), (double)(0.0f - f9), 0.0, (double)maxU, (double)maxV);
        tessellator.addVertexWithUV((double)(f7 - f8), (double)(1.0f - f9), 0.0, (double)maxU, (double)minV);
        tessellator.addVertexWithUV((double)(0.0f - f8), (double)(1.0f - f9), 0.0, (double)minU, (double)minV);
        tessellator.draw();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.texture;
    }
}

