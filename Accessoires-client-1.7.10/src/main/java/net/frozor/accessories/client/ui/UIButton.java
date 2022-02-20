/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

import net.frozor.accessories.client.RenderHelper;
import net.frozor.accessories.client.item.CategoryType;
import net.frozor.accessories.client.item.animation.AbstractAnimation;
import net.frozor.accessories.client.ui.EnumAction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

public class UIButton
extends GuiButton {
    private CategoryType categoryType;
    private float scaleFactor;
    private EnumAction action;

    public void setAction(EnumAction action) {
        this.action = action;
    }

    public UIButton(CategoryType categoryType, String text, int x, int y, int width, int height, float scaleFactor) {
        super(-1, x, y, text);
        this.action = EnumAction.NONE;
        this.width = (int)((float)width * scaleFactor);
        this.height = (int)((float)height * scaleFactor);
        this.xPosition = x;
        this.yPosition = y;
        this.scaleFactor = scaleFactor;
        this.categoryType = categoryType;
    }

    public UIButton(EnumAction action, String text, int x, int y, int width, int height, float scaleFactor) {
        super(-1, x, y, text);
        this.action = action;
        this.width = (int)((float)width * scaleFactor);
        this.height = (int)((float)height * scaleFactor);
        this.xPosition = x;
        this.yPosition = y;
        this.scaleFactor = scaleFactor;
    }

    public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
        return this.visible && mouseX >= this.xPosition && mouseY > this.yPosition && mouseX <= this.xPosition + this.width && mouseY <= this.yPosition + this.height;
    }

    public EnumAction getAction() {
        return this.action;
    }

    public CategoryType getCategoryType() {
        return this.categoryType;
    }

    public UIButton(int id, String text, int x, int y, int width, int height, float scaleFactor) {
        super(id, x, y, text);
        this.action = EnumAction.NONE;
        this.width = (int)((float)width * scaleFactor);
        this.height = (int)((float)height * scaleFactor);
        this.xPosition = x;
        this.yPosition = y;
        this.scaleFactor = scaleFactor;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            boolean hovered = mouseX >= this.xPosition && mouseY > this.yPosition && mouseX <= this.xPosition + this.width && mouseY <= this.yPosition + this.height;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)this.xPosition, (float)this.yPosition, (float)0.0f);
            RenderHelper.drawRect(this.width + 1, 0.0, 0.0, this.height, -2144522963);
            RenderHelper.drawRect(this.width, 0.0, 0.0, this.height, -2146233581);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)this.xPosition, (double)this.yPosition, (double)0.0);
            GL11.glScalef((float)this.scaleFactor, (float)this.scaleFactor, (float)this.scaleFactor);
            GL11.glTranslatef((float)((float)this.width / this.scaleFactor / 2.0f), (float)((float)this.height / this.scaleFactor / 3.0f), (float)0.0f);
            GL11.glScalef((float)0.68f, (float)0.68f, (float)0.68f);
            mc.fontRenderer.drawStringWithShadow((hovered ? "§6" : "") + this.displayString, -mc.fontRenderer.getStringWidth(this.displayString) / 2, 0, -1);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }
}

