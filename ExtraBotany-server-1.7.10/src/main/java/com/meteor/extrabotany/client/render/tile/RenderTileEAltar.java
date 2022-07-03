/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.client.model.TileEntity.ModelTileEAltar;
import com.meteor.extrabotany.common.block.tile.TileEAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTileEAltar
extends TileEntitySpecialRenderer {
    private static final ResourceLocation textures = new ResourceLocation("extrabotania", "textures/blocks/eAltar.png");
    ModelTileEAltar model = new ModelTileEAltar();
    public static int forceMeta = -1;

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
        for (int i = 0; i < 5; ++i) {
            ItemStack _stack = ((TileEAltar)tileentity).getStackInSlot(i);
            if (_stack == null) continue;
            this.renderItem(_stack, d0, d1, d2, pticks, tileentity, i);
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslated((double)(d0 + 0.5), (double)(d1 + 1.5), (double)(d2 + 0.5));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        this.model.render();
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
        forceMeta = -1;
    }

    private void renderItem(ItemStack stack, double d0, double d1, double d2, float pticks, TileEntity tileEntity, int index) {
        EntityItem entityitem = null;
        float ticks = (float)Minecraft.getMinecraft().renderViewEntity.ticksExisted + pticks;
        GL11.glPushMatrix();
        float h = MathHelper.sin((float)(ticks % 32767.0f / 16.0f)) * 0.05f;
        switch (index) {
            case 0: {
                GL11.glTranslatef((float)((float)d0 + 0.5f), (float)((float)d1 + 1.2f + h), (float)((float)d2 - 0.25f));
                break;
            }
            case 1: {
                GL11.glTranslatef((float)((float)d0 + 0.5f), (float)((float)d1 + 1.2f + h), (float)((float)d2 + 1.25f));
                break;
            }
            case 2: {
                GL11.glTranslatef((float)((float)d0 - 0.25f), (float)((float)d1 + 1.2f + h), (float)((float)d2 + 0.5f));
                break;
            }
            case 3: {
                GL11.glTranslatef((float)((float)d0 + 1.25f), (float)((float)d1 + 1.2f + h), (float)((float)d2 + 0.5f));
                break;
            }
            case 4: {
                GL11.glTranslatef((float)((float)d0 + 0.5f), (float)((float)d1 + 0.15f + h), (float)((float)d2 + 0.5f));
            }
        }
        GL11.glRotatef((float)(ticks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        float size = stack.getItem() instanceof ItemBlock ? (index != 4 ? 1.0f : 2.0f) : (index != 4 ? 0.5f : 1.0f);
        GL11.glScalef((float)size, (float)size, (float)size);
        stack.stackSize = 1;
        entityitem = new EntityItem(tileEntity.getWorldObj(), 0.0, 0.0, 0.0, stack);
        entityitem.hoverStart = 0.0f;
        RenderManager.instance.renderEntityWithPosYaw((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
        if (!Minecraft.isFancyGraphicsEnabled()) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            RenderManager.instance.renderEntityWithPosYaw((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
        }
        GL11.glPopMatrix();
    }
}

