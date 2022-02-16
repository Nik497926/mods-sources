/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderStarlight
extends Render {
    private ResourceLocation texture;
    private Random r = new Random();

    public RenderStarlight(ResourceLocation par1) {
        this.texture = par1;
    }

    public void render(EntityThrowable projectile, double x, double y, double z) {
        GL11.glPushMatrix();
        this.bindEntityTexture((Entity)projectile);
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        GL11.glEnable((int)32826);
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
        tessellator.setColorRGBA_F(this.r.nextFloat(), this.r.nextFloat(), this.r.nextFloat(), 255.0f);
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        tessellator.addVertexWithUV((double)(0.0f - f8), (double)(0.0f - f9), 0.0, (double)minU, (double)maxV);
        tessellator.addVertexWithUV((double)(f7 - f8), (double)(0.0f - f9), 0.0, (double)maxU, (double)maxV);
        tessellator.addVertexWithUV((double)(f7 - f8), (double)(1.0f - f9), 0.0, (double)maxU, (double)minV);
        tessellator.addVertexWithUV((double)(0.0f - f8), (double)(1.0f - f9), 0.0, (double)minU, (double)minV);
        tessellator.draw();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity var1) {
        return this.texture;
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.render((EntityThrowable)par1Entity, par2, par4, par6);
    }
}

