/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.util.ResourceLocation
 */
package info.jbcs.minecraft.vending.gui;

import info.jbcs.minecraft.vending.gui.GuiElement;
import info.jbcs.minecraft.vending.gui.InputMouseEvent;
import info.jbcs.minecraft.vending.gui.TexturedBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class GuiExButton
extends GuiElement {
    protected String caption;
    int u;
    int v;
    int texw;
    int texh;
    int borderTop;
    int borderRight;
    int borderBottom;
    int borderLeft;
    boolean over;
    public boolean disabled;
    public TexturedBox boxDisabled;
    public TexturedBox boxNormal;
    public TexturedBox boxOver;

    public GuiExButton(int x, int y, int w, int h, String caption) {
        this(x, y, w, h, caption, "textures/gui/widgets.png");
    }

    public GuiExButton(int x, int y, int w, int h, String caption, String texture) {
        super(x, y, w, h);
        this.caption = caption;
        this.disabled = false;
        this.u = 0;
        this.v = 46;
        this.texw = 200;
        this.texh = 20;
        this.borderTop = 2;
        this.borderRight = 2;
        this.borderBottom = 3;
        this.borderLeft = 2;
        this.boxDisabled = new TexturedBox(texture, 0, 46, 200, 20, 2, 2, 3, 2);
        this.boxNormal = new TexturedBox(texture, 0, 66, 200, 20, 2, 2, 3, 2);
        this.boxOver = new TexturedBox(texture, 0, 86, 200, 20, 2, 2, 3, 2);
    }

    public void onClick() {
    }

    @Override
    public void render() {
        int color = -2039584;
        TexturedBox box = this.boxDisabled;
        if (!this.disabled && !this.over) {
            box = this.boxNormal;
            color = -1;
        } else if (!this.disabled && this.over) {
            box = this.boxOver;
            color = -144;
        }
        FontRenderer fontRenderer = this.gui.fontRenderer();
        box.render(this.gui, this.x, this.y, this.w, this.h);
        this.gui.drawCenteredString(fontRenderer, this.caption, this.x + this.w / 2, this.y + this.h / 4 + 1, color);
    }

    @Override
    public void mouseMove(InputMouseEvent ev) {
        this.over = this.isMouseOver(ev);
    }

    @Override
    public void mouseDown(InputMouseEvent ev) {
        if (!this.isMouseOver(ev)) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        SoundHandler soundHandler = new SoundHandler(mc.getResourceManager(), mc.gameSettings);
        soundHandler.playSound((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("gui.button.press"), (float)1.0f));
        this.onClick();
    }
}

