/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiTextField
 */
package info.jbcs.minecraft.vending.gui;

import info.jbcs.minecraft.vending.gui.GuiElement;
import info.jbcs.minecraft.vending.gui.InputKeyboardEvent;
import info.jbcs.minecraft.vending.gui.InputMouseEvent;
import net.minecraft.client.gui.GuiTextField;

public class GuiEdit
extends GuiElement {
    GuiTextField field;
    String tempString = "";

    public GuiEdit(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void onAdded() {
        this.field = new GuiTextField(this.gui.fontRenderer(), this.x, this.y, this.w, this.h);
        this.setText(this.tempString);
    }

    public String getText() {
        if (this.field == null) {
            return this.tempString;
        }
        return this.field.getText();
    }

    public void setText(String text) {
        if (this.field == null) {
            this.tempString = text;
        } else {
            this.field.setText(text);
        }
    }

    @Override
    public void render() {
        this.field.drawTextBox();
    }

    @Override
    public void mouseDown(InputMouseEvent ev) {
        this.field.mouseClicked(ev.x, ev.y, ev.button);
        if (this.isMouseOver(ev)) {
            ev.handled = true;
        }
    }

    @Override
    public void keyPressed(InputKeyboardEvent ev) {
        this.field.textboxKeyTyped(ev.character, ev.key);
        if (this.field.isFocused()) {
            ev.handled = true;
        }
    }
}

