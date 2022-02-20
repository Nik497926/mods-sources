/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class RenderHelper {
    public static void drawGradientLeftToRight(int width, int height, int startColor, int endColor) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)height, (float)0.0f);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        RenderHelper.drawGradientRect(0, 0, height, width, startColor, endColor);
        GL11.glPopMatrix();
    }

    public static void drawCustomSizedTexture(int marginLeft, int marginTop, int weight, int height) {
        RenderHelper.drawModalRectWithCustomSizedTexture(marginLeft, marginTop, 0.0f, 0.0f, weight, height, weight, height);
    }

    public static void drawGradientRightToLeft(int width, int height, int startColor, int endColor) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)height, (float)0.0f);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        RenderHelper.drawGradientRect(0, 0, height, width, startColor, endColor);
        GL11.glPopMatrix();
    }

    public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        float f = (float)(startColor >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(startColor >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(startColor >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(startColor & 0xFF) / 255.0f;
        float f4 = (float)(endColor >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(endColor >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(endColor >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(endColor & 0xFF) / 255.0f;
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        GL11.glShadeModel((int)7425);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(f1, f2, f3, f);
        tessellator.addVertex((double)right, (double)top, 0.0);
        tessellator.addVertex((double)left, (double)top, 0.0);
        tessellator.setColorRGBA_F(f5, f6, f7, f4);
        tessellator.addVertex((double)left, (double)bottom, 0.0);
        tessellator.addVertex((double)right, (double)bottom, 0.0);
        tessellator.draw();
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)f, (float)f1, (float)f2, (float)f3);
        tessellator.startDrawingQuads();
        tessellator.addVertex(left, bottom, 0.0);
        tessellator.addVertex(right, bottom, 0.0);
        tessellator.addVertex(right, top, 0.0);
        tessellator.addVertex(left, top, 0.0);
        tessellator.draw();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        float f = 1.0f / textureWidth;
        float f1 = 1.0f / textureHeight;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)x, (double)(y + height), 0.0, (double)(u * f), (double)((v + (float)height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), 0.0, (double)((u + (float)width) * f), (double)((v + (float)height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)y, 0.0, (double)((u + (float)width) * f), (double)(v * f1));
        tessellator.addVertexWithUV((double)x, (double)y, 0.0, (double)(u * f), (double)(v * f1));
        tessellator.draw();
    }
}

