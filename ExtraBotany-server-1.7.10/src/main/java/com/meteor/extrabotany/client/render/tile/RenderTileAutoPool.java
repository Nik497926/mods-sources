/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.common.block.tile.TileAutoPool;
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

public class RenderTileAutoPool
extends TileEntitySpecialRenderer {
    private static final ResourceLocation textures = new ResourceLocation(ExtraBotany.modid, "textures/blocks/models/pool.png");

    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslated((double)(d0 + 0.5), (double)(d1 + 0.5), (double)(d2 + 0.5));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        ClientProxy.model.renderAll();
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
        if (tileentity instanceof TileAutoPool) {
            TileAutoPool te = (TileAutoPool)tileentity;
            if (te.getStackInSlot(1) != null) {
                this.renderItem(te.getStackInSlot(1).copy(), d0, d1, d2, pticks, tileentity, 1.0f);
            }
            double[][] charge = new double[][]{{-0.62, 0.0}, {0.0, 0.62}, {0.62, 0.0}, {0.0, -0.62}};
            for (int i = 6; i < 10; ++i) {
                if (te.getStackInSlot(i) == null) continue;
                this.renderItem(te.getStackInSlot(i).copy(), d0 + charge[i - 6][0], d1 + 0.17, d2 + charge[i - 6][1], pticks, tileentity, 0.5f);
            }
            double[][] discharge = new double[][]{{-0.5, 0.5}, {0.5, 0.5}, {0.5, -0.5}, {-0.5, -0.5}};
            for (int i = 2; i < 6; ++i) {
                if (te.getStackInSlot(i) == null) continue;
                this.renderItem(te.getStackInSlot(i).copy(), d0 + discharge[i - 2][0], d1 + 0.05, d2 + discharge[i - 2][1], pticks, tileentity, 0.25f);
            }
        }
    }

    private void renderItem(ItemStack stack, double d0, double d1, double d2, float pticks, TileEntity tileEntity, float sizeS) {
        EntityItem entityitem = null;
        float ticks = (float)Minecraft.getMinecraft().renderViewEntity.ticksExisted + pticks;
        GL11.glPushMatrix();
        float h = MathHelper.sin((float)(ticks % 32767.0f / 16.0f)) * 0.05f;
        GL11.glTranslatef((float)((float)d0 + 0.5f), (float)((float)d1 + 1.15f + h), (float)((float)d2 + 0.5f));
        GL11.glRotatef((float)(ticks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        float size = stack.getItem() instanceof ItemBlock ? 2.0f * sizeS : sizeS;
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

