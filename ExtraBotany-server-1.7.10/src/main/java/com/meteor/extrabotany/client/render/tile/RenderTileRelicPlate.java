/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.common.block.BlockRelicPlate;
import com.meteor.extrabotany.common.block.tile.TileRelicPlate;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.helper.ShaderHelper;

@SideOnly(value= Side.CLIENT)
public class RenderTileRelicPlate
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
        TileRelicPlate plate = (TileRelicPlate)tileentity;
        float max = 50000.0f;
        float alphaMod = Math.min(max, (float)plate.getCurrentMana()) / max;
        GL11.glPushMatrix();
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        GL11.glRotated((double)90.0, (double)1.0, (double)0.0, (double)0.0);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.1885f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)2.0f, (float)2.0f, (float)0.2f, (float)1.0f);
        GL11.glDisable((int)3008);
        float alpha = (float)((Math.sin((double)((float)ClientTickHandler.ticksInGame + f) / 8.0) + 1.0) / 5.0 + 0.6) * alphaMod;
        if (ShaderHelper.useShaders()) {
            GL11.glColor4f((float)2.0f, (float)2.0f, (float)0.2f, (float)alpha);
        } else {
            int light = 0xF000F0;
            int lightmapX = light % 65536;
            int lightmapY = light / 65536;
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)lightmapX, (float)lightmapY);
            GL11.glColor4f((float)(1.6f + (float)((Math.cos((double)((float)ClientTickHandler.ticksInGame + f) / 6.0) + 1.0) / 5.0)), (float)2.0f, (float)0.1f, (float)alpha);
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        ShaderHelper.useShader((int)ShaderHelper.terraPlateRune);
        this.renderIcon(0, 0, BlockRelicPlate.overlay, 1, 1, 240);
        ShaderHelper.releaseShader();
        GL11.glEnable((int)3008);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)2.0f, (float)2.0f, (float)0.2f, (float)1.0f);
        GL11.glPopMatrix();
    }

    public void renderIcon(int par1, int par2, IIcon par3Icon, int par4, int par5, int brightness) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setBrightness(brightness);
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par5), 0.0, (double)par3Icon.getMinU(), (double)par3Icon.getMaxV());
        tessellator.addVertexWithUV((double)(par1 + par4), (double)(par2 + par5), 0.0, (double)par3Icon.getMaxU(), (double)par3Icon.getMaxV());
        tessellator.addVertexWithUV((double)(par1 + par4), (double)(par2 + 0), 0.0, (double)par3Icon.getMaxU(), (double)par3Icon.getMinV());
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), 0.0, (double)par3Icon.getMinU(), (double)par3Icon.getMinV());
        tessellator.draw();
    }
}

