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

import net.divinerpg.client.render.block.model.ModelDramixAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityDramixAltarRenderer
extends TileEntitySpecialRenderer {
    private ModelDramixAltar model = new ModelDramixAltar();

    public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/model/dramixAltar.png"));
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y - 0.6f), (float)((float)z + 0.5f));
        this.model.render(0.0625f);
        GL11.glPopMatrix();
    }
}

