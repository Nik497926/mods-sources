/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.entity;

import com.meteor.extrabotany.common.entity.EntitySakuraFall;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value= Side.CLIENT)
public class RenderSakuraFall
extends Render {
    private static double[][] dVec = new double[][]{{0.0, 1.0, -0.5}, {0.0, 0.75, 0.0}, {0.1, 0.6, -0.15}, {0.0, 0.5, -0.25}, {-0.1, 0.6, -0.15}, {0.0, 0.0, 0.25}, {0.25, 0.0, 0.0}, {0.0, 0.0, -0.25}, {-0.25, 0.0, 0.0}, {0.0, -0.75, 0.0}, {0.1, -0.6, -0.15}, {0.0, -0.5, -0.25}, {-0.1, -0.6, -0.15}, {0.0, -1.0, -0.5}};
    private static int[][] nVecPos = new int[][]{{0, 1, 2, 3}, {0, 3, 4, 1}, {1, 5, 6, 2}, {3, 2, 6, 7}, {3, 7, 8, 4}, {1, 4, 8, 5}, {6, 5, 9, 10}, {6, 10, 11, 7}, {8, 7, 11, 12}, {8, 12, 9, 5}, {10, 9, 13, 11}, {12, 11, 13, 9}};

    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
        if (entity instanceof EntitySakuraFall) {
            this.doDriveRender((EntitySakuraFall)entity, d0, d1, d2, f, f1);
        }
    }

    protected ResourceLocation getEntityTexture(Entity var1) {
        return null;
    }

    private void doDriveRender(EntitySakuraFall entityDrive, double dX, double dY, double dZ, float f, float f1) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)dX), (float)((float)dY), (float)((float)dZ));
        GL11.glRotatef((float)entityDrive.rotationYaw, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-entityDrive.rotationPitch), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)0.25f, (float)1.0f, (float)1.0f);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        float ticks = entityDrive.ticksExisted;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(0.96f, 0.58f, 0.66f, 0.95f);
        double dScale = 1.0;
        for (int idx = 0; idx < nVecPos.length; ++idx) {
            tessellator.addVertex(dVec[nVecPos[idx][0]][0] * dScale, dVec[nVecPos[idx][0]][1] * dScale, dVec[nVecPos[idx][0]][2] * dScale);
            tessellator.addVertex(dVec[nVecPos[idx][1]][0] * dScale, dVec[nVecPos[idx][1]][1] * dScale, dVec[nVecPos[idx][1]][2] * dScale);
            tessellator.addVertex(dVec[nVecPos[idx][2]][0] * dScale, dVec[nVecPos[idx][2]][1] * dScale, dVec[nVecPos[idx][2]][2] * dScale);
            tessellator.addVertex(dVec[nVecPos[idx][3]][0] * dScale, dVec[nVecPos[idx][3]][1] * dScale, dVec[nVecPos[idx][3]][2] * dScale);
        }
        tessellator.draw();
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)3553);
    }
}

