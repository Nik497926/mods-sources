/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  org.lwjgl.input.Mouse
 */
package info.jbcs.minecraft.vending.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.input.Mouse;

public abstract class Scrollbar
extends GuiButton {
    public boolean active;
    public float offset = 0.0f;
    public float step = 0.025f;
    boolean dragged;
    int elementHeight = 15;

    public Scrollbar(int id, int x, int y, int w, int h, String string) {
        super(id, x, y, w, h, string);
        this.height = h;
        this.active = true;
        this.dragged = false;
    }

    public boolean mousePressed(Minecraft mc, int x, int y) {
        if (x < this.xPosition || x >= this.xPosition + this.width) {
            return false;
        }
        if (y < this.yPosition || y >= this.yPosition + this.height) {
            return false;
        }
        if (this.active) {
            this.dragged = true;
        }
        return true;
    }

    public void drawButton(Minecraft mc, int x, int y) {
        if (this.dragged) {
            float initialOffset = this.offset;
            int off = y - this.yPosition - this.elementHeight / 2;
            this.offset = 1.0f * (float)off / (float)(this.height - this.elementHeight);
            if (this.offset < 0.0f) {
                this.offset = 0.0f;
            }
            if (this.offset > 1.0f) {
                this.offset = 1.0f;
            }
            if (initialOffset != this.offset) {
                this.onScrolled(this.offset);
            }
        }
        int bottom = this.yPosition + this.height;
        this.drawTexturedModalRect(this.xPosition, this.yPosition + (int)((float)(this.height - this.elementHeight) * this.offset), this.active ? 232 : 244, 0, 12, this.elementHeight);
    }

    public void handleMouseInput() {
        if (Mouse.getEventButton() == 0 && !Mouse.getEventButtonState()) {
            this.dragged = false;
        }
        if (!this.active) {
            return;
        }
        float initialOffset = this.offset;
        int direction = Mouse.getEventDWheel();
        if (direction != 0) {
            this.offset += direction > 0 ? -this.step : this.step;
        }
        if (this.offset < 0.0f) {
            this.offset = 0.0f;
        }
        if (this.offset > 1.0f) {
            this.offset = 1.0f;
        }
        if (initialOffset != this.offset) {
            this.onScrolled(this.offset);
        }
    }

    public abstract void onScrolled(float var1);
}

