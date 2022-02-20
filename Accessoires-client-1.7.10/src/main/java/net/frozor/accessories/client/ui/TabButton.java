/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

import net.frozor.accessories.client.ui.UIAccessorySidebar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class TabButton
extends GuiButton {
    private float scaled = 0.687f;

    public TabButton(int xPosition, int yPosition, int width, int height, String text) {
        super(-1, xPosition, yPosition, width, height, text);
    }

    public boolean mousePressed(Minecraft mc, int xMouse, int yMouse) {
        if (this.enabled && this.visible && xMouse >= this.xPosition && yMouse >= this.yPosition && (float)xMouse < (float)this.xPosition + (float)this.width * this.scaled && (float)yMouse < (float)this.yPosition + (float)this.height * this.scaled) {
            mc.displayGuiScreen((GuiScreen)new UIAccessorySidebar());
            return true;
        }
        return false;
    }

    public void drawButton(Minecraft mc, int xMouse, int yMouse) {
        if (this.visible) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)this.xPosition, (float)this.yPosition, (float)0.0f);
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = xMouse >= this.xPosition && yMouse >= this.yPosition && (float)xMouse < (float)this.xPosition + (float)this.width * this.scaled && (float)yMouse < (float)this.yPosition + (float)this.height * this.scaled;
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable((int)3042);
            OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glScalef((float)this.scaled, (float)this.scaled, (float)this.scaled);
            this.drawTexturedModalRect(0, 0, 0, 46 + k * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.width / 2, 0, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
            this.mouseDragged(mc, xMouse, yMouse);
            int l = 0xE0E0E0;
            if (this.packedFGColour != 0) {
                l = this.packedFGColour;
            } else if (!this.enabled) {
                l = 0xA0A0A0;
            } else if (this.field_146123_n) {
                l = 0xFFFFA0;
            }
            this.drawCenteredString(fontrenderer, this.displayString, this.width / 2, (this.height - 8) / 2, l);
            GL11.glPopMatrix();
        }
    }
}

