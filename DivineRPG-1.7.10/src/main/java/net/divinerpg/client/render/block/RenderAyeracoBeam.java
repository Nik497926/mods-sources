/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.block;

import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAyeracoBeam;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderAyeracoBeam
extends TileEntitySpecialRenderer {
    public void renderTileEntity(TileEntityAyeracoBeam entity, double x, double y, double z, float f) {
        if (entity.texture == null) {
            return;
        }
        float f1 = entity.renderBeam();
        GL11.glAlphaFunc((int)516, (float)0.1f);
        if (f1 > 0.0f) {
            Tessellator tessellator = Tessellator.instance;
            this.bindTexture(entity.texture);
            GL11.glTexParameterf((int)3553, (int)10242, (float)10497.0f);
            GL11.glTexParameterf((int)3553, (int)10243, (float)10497.0f);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            OpenGlHelper.glBlendFunc((int)770, (int)1, (int)1, (int)0);
            float f2 = (float)entity.getWorldObj().getTotalWorldTime() + f;
            float f3 = -f2 * 0.2f - (float)MathHelper.floor_float((float)(-f2 * 0.1f));
            boolean b0 = true;
            double d3 = (double)f2 * 0.025 * (1.0 - (double)(b0 & true) * 2.5);
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA(255, 255, 255, 32);
            double d5 = (double)b0 * 0.2;
            double d7 = 0.5 + Math.cos(d3 + 2.356194490192345) * d5;
            double d9 = 0.5 + Math.sin(d3 + 2.356194490192345) * d5;
            double d11 = 0.5 + Math.cos(d3 + 0.7853981633974483) * d5;
            double d13 = 0.5 + Math.sin(d3 + 0.7853981633974483) * d5;
            double d15 = 0.5 + Math.cos(d3 + 3.9269908169872414) * d5;
            double d17 = 0.5 + Math.sin(d3 + 3.9269908169872414) * d5;
            double d19 = 0.5 + Math.cos(d3 + 5.497787143782138) * d5;
            double d21 = 0.5 + Math.sin(d3 + 5.497787143782138) * d5;
            double d23 = 256.0f * f1;
            double d25 = 0.0;
            double d27 = 1.0;
            double d28 = -1.0f + f3;
            double d29 = (double)(256.0f * f1) * (0.5 / d5) + d28;
            tessellator.addVertexWithUV(x + d7, y + d23, z + d9, d27, d29);
            tessellator.addVertexWithUV(x + d7, y, z + d9, d27, d28);
            tessellator.addVertexWithUV(x + d11, y, z + d13, d25, d28);
            tessellator.addVertexWithUV(x + d11, y + d23, z + d13, d25, d29);
            tessellator.addVertexWithUV(x + d19, y + d23, z + d21, d27, d29);
            tessellator.addVertexWithUV(x + d19, y, z + d21, d27, d28);
            tessellator.addVertexWithUV(x + d15, y, z + d17, d25, d28);
            tessellator.addVertexWithUV(x + d15, y + d23, z + d17, d25, d29);
            tessellator.addVertexWithUV(x + d11, y + d23, z + d13, d27, d29);
            tessellator.addVertexWithUV(x + d11, y, z + d13, d27, d28);
            tessellator.addVertexWithUV(x + d19, y, z + d21, d25, d28);
            tessellator.addVertexWithUV(x + d19, y + d23, z + d21, d25, d29);
            tessellator.addVertexWithUV(x + d15, y + d23, z + d17, d27, d29);
            tessellator.addVertexWithUV(x + d15, y, z + d17, d27, d28);
            tessellator.addVertexWithUV(x + d7, y, z + d9, d25, d28);
            tessellator.addVertexWithUV(x + d7, y + d23, z + d9, d25, d29);
            tessellator.draw();
            GL11.glEnable((int)3042);
            OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
            GL11.glDepthMask((boolean)false);
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA(255, 255, 255, 32);
            double d30 = 0.2;
            double d4 = 0.2;
            double d6 = 0.8;
            double d8 = 0.2;
            double d10 = 0.2;
            double d12 = 0.8;
            double d14 = 0.8;
            double d16 = 0.8;
            double d18 = 256.0f * f1;
            double d20 = 0.0;
            double d22 = 1.0;
            double d24 = -1.0f + f3;
            double d26 = (double)(256.0f * f1) + d24;
            tessellator.addVertexWithUV(x + d30, y + d18, z + d4, d22, d26);
            tessellator.addVertexWithUV(x + d30, y, z + d4, d22, d24);
            tessellator.addVertexWithUV(x + d6, y, z + d8, d20, d24);
            tessellator.addVertexWithUV(x + d6, y + d18, z + d8, d20, d26);
            tessellator.addVertexWithUV(x + d14, y + d18, z + d16, d22, d26);
            tessellator.addVertexWithUV(x + d14, y, z + d16, d22, d24);
            tessellator.addVertexWithUV(x + d10, y, z + d12, d20, d24);
            tessellator.addVertexWithUV(x + d10, y + d18, z + d12, d20, d26);
            tessellator.addVertexWithUV(x + d6, y + d18, z + d8, d22, d26);
            tessellator.addVertexWithUV(x + d6, y, z + d8, d22, d24);
            tessellator.addVertexWithUV(x + d14, y, z + d16, d20, d24);
            tessellator.addVertexWithUV(x + d14, y + d18, z + d16, d20, d26);
            tessellator.addVertexWithUV(x + d10, y + d18, z + d12, d22, d26);
            tessellator.addVertexWithUV(x + d10, y, z + d12, d22, d24);
            tessellator.addVertexWithUV(x + d30, y, z + d4, d20, d24);
            tessellator.addVertexWithUV(x + d30, y + d18, z + d4, d20, d26);
            tessellator.draw();
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glDepthMask((boolean)true);
        }
        GL11.glAlphaFunc((int)516, (float)0.5f);
    }

    public void renderTileEntityAt(TileEntity t, double x, double y, double z, float f) {
        this.renderTileEntity((TileEntityAyeracoBeam)t, x, y, z, f);
    }
}

