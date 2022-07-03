/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.client.model.TileEntity.ModelTilePoolEfir;
import com.meteor.extrabotany.common.block.tile.TileBlockPoolEfir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTilePoolEfir
extends TileEntitySpecialRenderer {
    private static final ResourceLocation textures1 = new ResourceLocation("extrabotania", "textures/blocks/poolefir0.png");
    private static final ResourceLocation textures2 = new ResourceLocation("extrabotania", "textures/blocks/poolefir1.png");
    private static final ResourceLocation textures3 = new ResourceLocation("extrabotania", "textures/blocks/poolefir2.png");
    private static final ResourceLocation textures4 = new ResourceLocation("extrabotania", "textures/blocks/poolefir3.png");
    private static final ResourceLocation textures5 = new ResourceLocation("extrabotania", "textures/blocks/poolefir4.png");
    private static final ResourceLocation textures6 = new ResourceLocation("extrabotania", "textures/blocks/poolefir5.png");
    private static final ResourceLocation textures7 = new ResourceLocation("extrabotania", "textures/blocks/poolefir6.png");
    ModelTilePoolEfir model = new ModelTilePoolEfir();
    public static int forceMeta = -1;

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int stage = ((TileBlockPoolEfir)tileentity).getStageCenter();
        if (stage == 0 || stage == 6) {
            Minecraft.getMinecraft().renderEngine.bindTexture(textures4);
        } else if (stage == 1 || stage == 5) {
            Minecraft.getMinecraft().renderEngine.bindTexture(textures5);
        } else if (stage == 2 || stage == 4) {
            Minecraft.getMinecraft().renderEngine.bindTexture(textures6);
        } else if (stage == 3) {
            Minecraft.getMinecraft().renderEngine.bindTexture(textures7);
        } else if (stage == 7 || stage == 11) {
            Minecraft.getMinecraft().renderEngine.bindTexture(textures3);
        } else if (stage == 8 || stage == 10) {
            Minecraft.getMinecraft().renderEngine.bindTexture(textures2);
        } else if (stage == 9) {
            Minecraft.getMinecraft().renderEngine.bindTexture(textures1);
        }
        GL11.glTranslated((double)(d0 + 0.5), (double)(d1 + 1.5), (double)(d2 + 0.5));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        this.model.render();
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
        forceMeta = -1;
    }
}

