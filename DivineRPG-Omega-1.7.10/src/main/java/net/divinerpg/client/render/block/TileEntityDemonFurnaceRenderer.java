/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.block;

import net.divinerpg.blocks.base.tileentity.TileEntityInfiniteFurnace;
import net.divinerpg.client.render.block.model.ModelDemonFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityDemonFurnaceRenderer
extends TileEntitySpecialRenderer {
    private static ModelDemonFurnace model = new ModelDemonFurnace();

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float ticks) {
        String tex = "demonFurnace";
        TileEntityInfiniteFurnace te = (TileEntityInfiniteFurnace)entity;
        int rotation = 0;
        if (entity.getWorldObj() != null) {
            rotation = entity.getBlockMetadata();
        }
        if (rotation >= 8) {
            tex = "demonFurnace_on";
            rotation -= 8;
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/model/" + tex + ".png"));
        GL11.glPushMatrix();
        GL11.glDisable((int)2896);
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.5f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)(rotation * 90), (float)0.0f, (float)1.0f, (float)0.0f);
        model.render(0.0625f);
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }
}

