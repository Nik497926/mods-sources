/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.block;

import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAyeracoSpawn;
import net.divinerpg.utils.events.ClientTicker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderAyeracoSpawn
extends TileEntitySpecialRenderer {
    private static ResourceLocation greenTexture = new ResourceLocation("divinerpg:textures/model/ayeracoSymbolGreen.png");
    private static ResourceLocation redTexture = new ResourceLocation("divinerpg:textures/model/ayeracoSymbolRed.png");
    private static ResourceLocation yellowTexture = new ResourceLocation("divinerpg:textures/model/ayeracoSymbolYellow.png");
    private static ResourceLocation blueTexture = new ResourceLocation("divinerpg:textures/model/ayeracoSymbolBlue.png");
    private static ResourceLocation purpleTexture = new ResourceLocation("divinerpg:textures/model/ayeracoSymbolPurple.png");

    public void renderTileEntity(TileEntityAyeracoSpawn te, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        if (te.spawnTick > 430) {
            Minecraft.getMinecraft().renderEngine.bindTexture(greenTexture);
        } else if (te.spawnTick > 300) {
            Minecraft.getMinecraft().renderEngine.bindTexture(blueTexture);
        } else if (te.spawnTick > 210) {
            Minecraft.getMinecraft().renderEngine.bindTexture(redTexture);
        } else if (te.spawnTick > 145) {
            Minecraft.getMinecraft().renderEngine.bindTexture(yellowTexture);
        } else if (te.spawnTick > 0) {
            Minecraft.getMinecraft().renderEngine.bindTexture(purpleTexture);
        }
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.01f), (float)((float)z + 1.0625f));
        GL11.glEnable((int)32826);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        Tessellator tessellator = Tessellator.instance;
        float minU = 0.0f;
        float maxU = 1.0f;
        float minV = 0.0f;
        float maxV = 1.0f;
        float f7 = 1.0f;
        float f8 = 0.5f;
        float f9 = 0.25f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.28125f);
        GL11.glRotatef((float)(ClientTicker.tick * 4), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.28125f);
        GL11.glRotatef((float)270.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)0.0f, (float)65536.0f);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        tessellator.addVertexWithUV((double)(0.0f - f8), (double)(0.0f - f9), 0.0, (double)minU, (double)maxV);
        tessellator.addVertexWithUV((double)(f7 - f8), (double)(0.0f - f9), 0.0, (double)maxU, (double)maxV);
        tessellator.addVertexWithUV((double)(f7 - f8), (double)(1.0f - f9), 0.0, (double)maxU, (double)minV);
        tessellator.addVertexWithUV((double)(0.0f - f8), (double)(1.0f - f9), 0.0, (double)minU, (double)minV);
        tessellator.draw();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.001f, (float)-0.28125f);
        GL11.glRotatef((float)(-ClientTicker.tick * 4), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.28125f);
        GL11.glRotatef((float)270.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)0.0f, (float)65536.0f);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        tessellator.addVertexWithUV((double)(0.0f - f8), (double)(0.0f - f9), 0.0, (double)minU, (double)maxV);
        tessellator.addVertexWithUV((double)(f7 - f8), (double)(0.0f - f9), 0.0, (double)maxU, (double)maxV);
        tessellator.addVertexWithUV((double)(f7 - f8), (double)(1.0f - f9), 0.0, (double)maxU, (double)minV);
        tessellator.addVertexWithUV((double)(0.0f - f8), (double)(1.0f - f9), 0.0, (double)minU, (double)minV);
        tessellator.draw();
        GL11.glPopMatrix();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity t, double x, double y, double z, float f) {
        this.renderTileEntity((TileEntityAyeracoSpawn)t, x, y, z, f);
    }
}

