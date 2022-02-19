/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package info.jbcs.minecraft.vending.gui;

import info.jbcs.minecraft.vending.gui.GuiScreenPlus;
import info.jbcs.minecraft.vending.gui.InputKeyboardEvent;
import info.jbcs.minecraft.vending.gui.InputMouseEvent;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;

public class GuiElement {
    public int x;
    public int y;
    public int w;
    public int h;
    public GuiScreenPlus gui;
    public GuiElement parent;
    public GuiElement addedParent;
    private ArrayList<GuiElement> children = null;

    public GuiElement(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public GuiElement addChild(GuiElement e) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        if (e.parent != null) {
            e.parent.removeChild(e);
        }
        this.children.add(e);
        e.x += this.x;
        e.y += this.y;
        e.parent = this;
        e.gui = this.gui;
        return e;
    }

    public GuiElement removeChild(GuiElement e) {
        if (this.children == null) {
            return e;
        }
        this.children.remove(e);
        e.x -= this.x;
        e.y -= this.y;
        e.parent = null;
        e.gui = null;
        return e;
    }

    public void onAdded() {
        if (this.children == null) {
            return;
        }
        for (GuiElement e : this.children) {
            if (e.parent != e.addedParent) {
                e.onAdded();
            }
            e.addedParent = e.parent;
        }
    }

    boolean isMouseOver(InputMouseEvent ev) {
        return ev.x >= this.x && ev.x < this.x + this.w && ev.y >= this.y && ev.y < this.y + this.h;
    }

    public void mouseDown(InputMouseEvent ev) {
        if (this.children == null) {
            return;
        }
        for (GuiElement e : this.children) {
            e.mouseDown(ev);
            if (!ev.handled) continue;
            return;
        }
    }

    public void mouseUp(InputMouseEvent ev) {
        if (this.children == null) {
            return;
        }
        for (GuiElement e : this.children) {
            e.mouseUp(ev);
            if (!ev.handled) continue;
            return;
        }
    }

    public void mouseMove(InputMouseEvent ev) {
        if (this.children == null) {
            return;
        }
        for (GuiElement e : this.children) {
            e.mouseMove(ev);
            if (!ev.handled) continue;
            return;
        }
    }

    public void mouseWheel(InputMouseEvent ev) {
        if (this.children == null) {
            return;
        }
        for (GuiElement e : this.children) {
            e.mouseWheel(ev);
            if (!ev.handled) continue;
            return;
        }
    }

    public void render() {
        if (this.children == null) {
            return;
        }
        for (GuiElement e : this.children) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            e.render();
        }
    }

    public void keyPressed(InputKeyboardEvent ev) {
        if (this.children == null) {
            return;
        }
        for (GuiElement e : this.children) {
            e.keyPressed(ev);
            if (!ev.handled) continue;
            return;
        }
    }
}

