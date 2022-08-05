/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.tile;

import com.meteor.extrabotany.common.block.tile.TileGaiaChest;
import cpw.mods.fml.common.FMLLog;
import java.util.Calendar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value= Side.CLIENT)
public class RenderTileGaiaChest
extends TileEntitySpecialRenderer {
    private static final ResourceLocation field_147504_g = new ResourceLocation("extrabotania", "textures/models/chest/normal.png");
    private ModelChest field_147510_h = new ModelChest();
    private boolean field_147509_j;
    private static final String __OBFID = "CL_00000965";

    public RenderTileGaiaChest() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            this.field_147509_j = true;
        }
    }

    public void renderTileEntityAt(TileGaiaChest p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
        int i;
        if (!p_147500_1_.hasWorldObj()) {
            i = 0;
        } else {
            Block modelchest = p_147500_1_.getBlockType();
            i = p_147500_1_.getBlockMetadata();
            if (modelchest instanceof BlockChest && i == 0) {
                try {
                    ((BlockChest)modelchest).func_149954_e(p_147500_1_.getWorldObj(), p_147500_1_.xCoord, p_147500_1_.yCoord, p_147500_1_.zCoord);
                }
                catch (ClassCastException var13) {
                    FMLLog.severe((String)"Attempted to render a chest at %d,  %d, %d that was not a chest", (Object[])new Object[]{p_147500_1_.xCoord, p_147500_1_.yCoord, p_147500_1_.zCoord});
                }
                i = p_147500_1_.getBlockMetadata();
            }
        }
        ModelChest modelchest1 = this.field_147510_h;
        this.bindTexture(field_147504_g);
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)((float)p_147500_2_), (float)((float)p_147500_4_ + 1.0f), (float)((float)p_147500_6_ + 1.0f));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        int short1 = 0;
        if (i == 2) {
            short1 = 180;
        }
        if (i == 3) {
            short1 = 0;
        }
        if (i == 4) {
            short1 = 90;
        }
        if (i == 5) {
            short1 = -90;
        }
        GL11.glRotatef((float)short1, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        float f1 = p_147500_1_.prevLidAngle + (p_147500_1_.lidAngle - p_147500_1_.prevLidAngle) * p_147500_8_;
        f1 = 1.0f - f1;
        f1 = 1.0f - f1 * f1 * f1;
        modelchest1.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0f);
        modelchest1.renderAll();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
        this.renderTileEntityAt((TileGaiaChest)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
    }
}

