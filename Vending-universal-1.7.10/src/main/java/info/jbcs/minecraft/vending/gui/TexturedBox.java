/*
 * Decompiled with CFR 0.152.
 */
package info.jbcs.minecraft.vending.gui;

import info.jbcs.minecraft.vending.gui.GuiScreenPlus;

public class TexturedBox {
    String texture;
    int u;
    int v;
    int texw;
    int texh;
    int borderTop;
    int borderRight;
    int borderBottom;
    int borderLeft;

    public TexturedBox(String texture, int u, int v, int texw, int texh, int borderTop, int borderRight, int borderBottom, int borderLeft) {
        this.texture = texture;
        this.u = u;
        this.v = v;
        this.texw = texw;
        this.texh = texh;
        this.borderTop = borderTop;
        this.borderRight = borderRight;
        this.borderBottom = borderBottom;
        this.borderLeft = borderLeft;
    }

    public void render(GuiScreenPlus gui, int x, int y, int w, int h) {
        int th;
        int x1 = x;
        int x2 = x + this.borderLeft;
        int x3 = x + w - this.borderRight;
        int y1 = y;
        int y2 = y + this.borderTop;
        int y3 = y + h - this.borderBottom;
        int w1 = this.borderLeft;
        int w2 = w - this.borderLeft - this.borderRight;
        int w3 = this.borderRight;
        int h1 = this.borderTop;
        int h2 = h - this.borderTop - this.borderBottom;
        int h3 = this.borderBottom;
        int u1 = this.u;
        int v1 = this.v;
        int tw = this.texw;
        if (tw > w) {
            tw = w;
        }
        if ((th = this.texh) > h) {
            th = h;
        }
        int u2 = u1 + this.borderLeft;
        int u3 = u1 + this.texw - this.borderRight;
        int v2 = v1 + this.borderTop;
        int v3 = v1 + this.texh - this.borderBottom;
        int texw1 = this.borderLeft;
        int texw2 = tw - this.borderLeft - this.borderRight;
        int texw3 = this.borderRight;
        int texh1 = this.borderTop;
        int texh2 = th - this.borderTop - this.borderBottom;
        int texh3 = this.borderBottom;
        gui.bindTexture(this.texture);
        gui.drawTexturedModalRect(x1, y1, u1, v1, w1, h1);
        gui.drawTiledRect(x2, y1, w2, h1, u2, v1, texw2, texh1);
        gui.drawTexturedModalRect(x3, y1, u3, v1, w3, h1);
        gui.drawTiledRect(x1, y2, w1, h2, u1, v2, w1, texh2);
        gui.drawTiledRect(x2, y2, w2, h2, u2, v2, texw2, texh2);
        gui.drawTiledRect(x3, y2, w3, h2, u3, v2, w3, texh2);
        gui.drawTexturedModalRect(x1, y3, u1, v3, w1, h3);
        gui.drawTiledRect(x2, y3, w2, h3, u2, v3, texw2, texh3);
        gui.drawTexturedModalRect(x3, y3, u3, v3, w3, h3);
    }
}

