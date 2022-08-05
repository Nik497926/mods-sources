/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value= Side.CLIENT)
public class RenderTileAutoDaisy
extends TileEntitySpecialRenderer {
    private static final ResourceLocation textures = new ResourceLocation(ExtraBotany.modid, "textures/blocks/models/Margaritka.png");

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslated((double)(d0 + 0.5), (double)d1, (double)(d2 + 0.5));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        ClientProxy.modelDaisy.renderAll();
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }
}

