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
package net.divinerpg.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderInvisProjectile
extends Render {
    private float scale = 1.0f;
    private static ResourceLocation EntityTexture = new ResourceLocation("divinerpg:textures/projectiles/invis.png");

    public void renderProjectile(EntityThrowable projectile, double x, double y, double z) {
        GL11.glPushMatrix();
        this.bindEntityTexture((Entity)projectile);
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
        tessellator.addVertexWithUV(-0.5, -0.25, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV(0.5, -0.25, 0.0, 1.0, 1.0);
        tessellator.addVertexWithUV(0.5, 0.75, 0.0, 1.0, 0.0);
        tessellator.addVertexWithUV(-0.5, 0.75, 0.0, 0.0, 0.0);
        tessellator.draw();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderProjectile((EntityThrowable)par1Entity, par2, par4, par6);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return EntityTexture;
    }
}

