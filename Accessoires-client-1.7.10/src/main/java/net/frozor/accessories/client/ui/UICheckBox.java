/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

import net.frozor.accessories.client.RenderHelper;
import net.frozor.accessories.client.item.animation.AbstractAnimation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UICheckBox
extends GuiButton {
    private ResourceLocation checked;
    private boolean value;
    private float scale;
    private ResourceLocation unchecked = new ResourceLocation("Accessories:textures/ui/unchecked*checkbox.png");

    public UICheckBox(int x, int y, String buttonText, float scaleFactor, boolean defaultValue) {
        super(-1, x, y, buttonText);
        this.checked = new ResourceLocation("Accessories:textures/ui/checked*checkbox.png");
        this.height = 10;
        this.width = 80;
        this.value = defaultValue;
        this.scale = scaleFactor;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            boolean hovered = mouseX >= this.xPosition && mouseY > this.yPosition && (float)mouseX <= (float)this.xPosition + (float)this.width * this.scale && (float)mouseY <= (float)this.yPosition + (float)this.height * this.scale;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)this.xPosition, (float)this.yPosition, (float)0.0f);
            GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
            RenderHelper.drawRect(this.width, 0.0, 0.0, this.height, -2146233581);
            Minecraft.getMinecraft().getTextureManager().bindTexture(this.value ? this.unchecked : this.checked);
            RenderHelper.drawCustomSizedTexture(0, 0, 10, 10);
            GL11.glEnable((int)3042);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            GL11.glTranslatef((float)25.0f, (float)6.0f, (float)0.0f);
            Minecraft.getMinecraft().fontRenderer.drawString((hovered ? "§7" : "§8") + this.displayString, 0, 0, -1);
            GL11.glPopMatrix();
        }
    }

    public void changeValue(boolean value) {
        this.value = value;
    }

    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        return this.enabled && mouseX >= this.xPosition && mouseY > this.yPosition && (float)mouseX <= (float)this.xPosition + (float)this.width * this.scale && (float)mouseY <= (float)this.yPosition + (float)this.height * this.scale;
    }
}

