/*
 * Decompiled with CFR 0.152.
 */
package info.jbcs.minecraft.vending.gui;

import info.jbcs.minecraft.vending.gui.GuiElement;

public class GuiLabel
extends GuiElement {
    String caption;

    public GuiLabel(int x, int y, int w, int h, String caption) {
        super(x, y, w, h);
        this.caption = caption;
    }

    public GuiLabel(int x, int y, String string) {
        this(x, y, 0, 0, string);
    }

    @Override
    public void render() {
        this.gui.drawString(this.caption, this.x, this.y, 0x404040);
    }
}

