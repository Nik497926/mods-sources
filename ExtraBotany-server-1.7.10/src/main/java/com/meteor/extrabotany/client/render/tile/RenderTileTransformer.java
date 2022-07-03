/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.client.model.TileEntity.ModelTileTransformer;
import com.meteor.extrabotany.common.block.tile.TileTransformater;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTileTransformer
extends TileEntitySpecialRenderer {
    private static final ResourceLocation textures = new ResourceLocation("extrabotania", "textures/blocks/transformer.png");
    private static final ResourceLocation textures1 = new ResourceLocation("extrabotania", "textures/blocks/transformer0.png");
    private static final ResourceLocation textures2 = new ResourceLocation("extrabotania", "textures/blocks/transformer1.png");
    private static final ResourceLocation textures3 = new ResourceLocation("extrabotania", "textures/blocks/transformer2.png");
    private static final ResourceLocation textures4 = new ResourceLocation("extrabotania", "textures/blocks/transformer3.png");
    private static final ResourceLocation texturesClear = new ResourceLocation("extrabotania", "textures/blocks/transformer4.png");
    ModelTileTransformer model = new ModelTileTransformer();
    public static int forceMeta = -1;

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int stage = ((TileTransformater)tileentity).getStageCenter();
        ItemStack _item = ((TileTransformater)tileentity).getStackInSlot(0);
        if (stage == 0 || stage == 4) {
            Minecraft.getMinecraft().renderEngine.bindTexture(_item == null ? texturesClear : textures);
        } else if (stage == 1 || stage == 3) {
            Minecraft.getMinecraft().renderEngine.bindTexture(_item == null ? texturesClear : textures1);
        } else if (stage == 2) {
            Minecraft.getMinecraft().renderEngine.bindTexture(_item == null ? texturesClear : textures2);
        } else if (stage == 5 || stage == 7) {
            Minecraft.getMinecraft().renderEngine.bindTexture(_item == null ? texturesClear : textures3);
        } else if (stage == 6) {
            Minecraft.getMinecraft().renderEngine.bindTexture(_item == null ? texturesClear : textures4);
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

