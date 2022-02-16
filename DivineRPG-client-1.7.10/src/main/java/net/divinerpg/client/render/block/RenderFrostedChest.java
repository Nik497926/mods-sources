/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelChest
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.blocks.iceika.tileentity.TileEntityFrostedChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderFrostedChest
extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("divinerpg:textures/model/iceikaChest.png");
    private ModelChest model = new ModelChest();

    public void render(TileEntityFrostedChest entity, double x, double y, double z, float f) {
        int i = !entity.hasWorldObj() ? 0 : entity.getBlockMetadata();
        this.bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)((float)x), (float)((float)y + 1.0f), (float)((float)z + 1.0f));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        int rotation = 0;
        if (i == 2) {
            rotation = 180;
        }
        if (i == 3) {
            rotation = 0;
        }
        if (i == 4) {
            rotation = 90;
        }
        if (i == 5) {
            rotation = -90;
        }
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        float f1 = entity.prevLidAngle + (entity.lidAngle - entity.prevLidAngle) * f;
        f1 = 1.0f - f1;
        f1 = 1.0f - f1 * f1 * f1;
        this.model.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0f);
        this.model.renderAll();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float f) {
        this.render((TileEntityFrostedChest)var1, x, y, z, f);
    }
}

