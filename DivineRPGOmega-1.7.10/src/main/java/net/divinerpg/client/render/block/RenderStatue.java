/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.block;

import net.divinerpg.client.render.block.TileEntityStatue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RenderStatue
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tick) {
        if (te instanceof TileEntityStatue) {
            TileEntityStatue tes = (TileEntityStatue)te;
            int rotation = 0;
            if (tes.getWorldObj() != null) {
                rotation = tes.getBlockMetadata();
            }
            Minecraft.getMinecraft().getTextureManager().bindTexture(tes.texture);
            GL11.glPushMatrix();
            GL11.glDisable((int)2896);
            GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.7f), (float)((float)z + 0.5f));
            GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
            GL11.glRotatef((float)(rotation * 90), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            tes.model.render(0.0625f);
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
        }
    }
}

