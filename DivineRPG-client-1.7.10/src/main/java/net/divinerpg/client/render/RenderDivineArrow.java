/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderArrow
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.base.EntityDivineArrow;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderDivineArrow
extends RenderArrow {
    private String textureName;

    protected ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation("divinerpg:textures/projectiles/" + ((EntityDivineArrow)entity).getTextureName() + ".png");
    }

    public void doRender(Entity entity, double par2, double par3, double par4, float par5, float par6) {
        this.bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2), (float)((float)par3), (float)((float)par4));
        GL11.glRotatef((float)(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par6 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par6), (float)0.0f, (float)0.0f, (float)1.0f);
        Tessellator tessellator = Tessellator.instance;
        int b0 = 0;
        float f2 = 0.0f;
        float f3 = 0.5f;
        float f4 = (float)(0 + b0 * 10) / 32.0f;
        float f5 = (float)(5 + b0 * 10) / 32.0f;
        float f6 = 0.0f;
        float f7 = 0.15625f;
        float f8 = (float)(5 + b0 * 10) / 32.0f;
        float f9 = (float)(10 + b0 * 10) / 32.0f;
        float f10 = 0.05625f;
        GL11.glEnable((int)32826);
        float f11 = (float)((EntityDivineArrow)entity).arrowShake - par6;
        if (f11 > 0.0f) {
            float f12 = -MathHelper.sin((float)(f11 * 3.0f)) * f11;
            GL11.glRotatef((float)f12, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glRotatef((float)45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)f10, (float)f10, (float)f10);
        GL11.glTranslatef((float)-4.0f, (float)0.0f, (float)0.0f);
        GL11.glNormal3f((float)f10, (float)0.0f, (float)0.0f);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0, -2.0, -2.0, (double)f6, (double)f8);
        tessellator.addVertexWithUV(-7.0, -2.0, 2.0, (double)f7, (double)f8);
        tessellator.addVertexWithUV(-7.0, 2.0, 2.0, (double)f7, (double)f9);
        tessellator.addVertexWithUV(-7.0, 2.0, -2.0, (double)f6, (double)f9);
        tessellator.draw();
        GL11.glNormal3f((float)(-f10), (float)0.0f, (float)0.0f);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0, 2.0, -2.0, (double)f6, (double)f8);
        tessellator.addVertexWithUV(-7.0, 2.0, 2.0, (double)f7, (double)f8);
        tessellator.addVertexWithUV(-7.0, -2.0, 2.0, (double)f7, (double)f9);
        tessellator.addVertexWithUV(-7.0, -2.0, -2.0, (double)f6, (double)f9);
        tessellator.draw();
        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)f10);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-8.0, -2.0, 0.0, (double)f2, (double)f4);
            tessellator.addVertexWithUV(8.0, -2.0, 0.0, (double)f3, (double)f4);
            tessellator.addVertexWithUV(8.0, 2.0, 0.0, (double)f3, (double)f5);
            tessellator.addVertexWithUV(-8.0, 2.0, 0.0, (double)f2, (double)f5);
            tessellator.draw();
        }
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }
}

