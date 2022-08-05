/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.entity;

import com.meteor.extrabotany.common.entity.EntitySpear;
import com.meteor.extrabotany.common.item.ItemTest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value= Side.CLIENT)
public class RenderSpear
extends Render {
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        EntitySpear weapon = (EntitySpear)par1Entity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
        GL11.glPushMatrix();
        float s = 1.5f;
        GL11.glScalef((float)s, (float)s, (float)s);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        IIcon icon = ItemTest.spear;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float f = icon.getMinU();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMaxV();
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        GL11.glDisable((int)2896);
        ItemRenderer.renderItemIn2D((Tessellator)Tessellator.instance, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.getIconWidth(), (int)icon.getIconHeight(), (float)0.0625f);
        GL11.glPopMatrix();
        GL11.glDisable((int)2884);
        GL11.glShadeModel((int)7425);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)2896);
        GL11.glShadeModel((int)7424);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}

