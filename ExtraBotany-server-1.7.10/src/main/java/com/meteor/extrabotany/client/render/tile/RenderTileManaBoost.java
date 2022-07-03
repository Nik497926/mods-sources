/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.client.model.TileEntity.ModelBoost;
import com.meteor.extrabotany.common.block.tile.TileBlockBoost;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderTileManaBoost
extends TileEntitySpecialRenderer {
    private ModelBoost model = new ModelBoost();
    private static final ResourceLocation textures = new ResourceLocation("botania", "textures/blocks/livingrock0.png");
    RenderItem renderer = new RenderItem();

    public RenderTileManaBoost() {
        this.renderer.setRenderManager(RenderManager.instance);
    }

    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTicks) {
        ItemStack it = ((TileBlockBoost)tile).getSaveItem();
        if (it != null) {
            this.renderItem(it, x, y, z, partialTicks, tile);
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslated((double)(x + 0.5), (double)(y + 1.5), (double)(z + 0.5));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        this.model.render();
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }

    private void renderItem(ItemStack stack, double x, double y, double z, float pticks, TileEntity tileEntity) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.6f), (float)((float)y + 0.35f), (float)((float)z + 0.6f));
        GL11.glEnable((int)32826);
        EntityItem entity = new EntityItem((World)null, x, y, z, stack);
        entity.hoverStart = 0.0f;
        if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null) {
            entity.age = Minecraft.getMinecraft().thePlayer.ticksExisted;
        }
        try {
            this.renderer.doRender(entity, -0.1, 0.0, -0.1, pticks, pticks);
        }
        catch (Exception exception) {
            // empty catch block
        }
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }
}

