/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.inventory.Container
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package info.jbcs.minecraft.vending.gui;

import info.jbcs.minecraft.vending.GeneralClient;
import info.jbcs.minecraft.vending.gui.GuiElement;
import info.jbcs.minecraft.vending.gui.InputKeyboardEvent;
import info.jbcs.minecraft.vending.gui.InputMouseEvent;
import info.jbcs.minecraft.vending.inventory.DummyContainer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.Container;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiScreenPlus
extends GuiContainer {
    public int screenW;
    public int screenH;
    public int screenX;
    public int screenY;
    public GuiElement root;
    String backgroundTexture;
    InputMouseEvent mouseEvent = new InputMouseEvent();
    int oldX = -1;
    int oldY = -1;
    boolean[] downButtons = new boolean[12];
    InputKeyboardEvent keyboardEvent = new InputKeyboardEvent();

    public GuiScreenPlus(Container container, int w, int h, String backgroundTexture) {
        super(container);
        this.root = new GuiElement(0, 0, w, h);
        this.root.gui = this;
        this.screenW = w;
        this.screenH = h;
        this.backgroundTexture = backgroundTexture;
    }

    public GuiScreenPlus(int w, int h, String backgroundTexture) {
        this(new DummyContainer(), w, h, backgroundTexture);
    }

    public void initGui() {
        this.xSize = this.screenW;
        this.ySize = this.screenH;
        super.initGui();
        this.screenX = this.guiLeft;
        this.screenY = this.guiTop;
        this.root.onAdded();
        Keyboard.enableRepeatEvents((boolean)true);
    }

    public void handleInput() {
        while (Mouse.next()) {
            this.handleMouseInput();
        }
        while (Keyboard.next()) {
            this.handleKeyboardInput();
        }
    }

    public void handleMouseInput() {
        this.mouseEvent.handled = false;
        this.mouseEvent.x = Mouse.getEventX() * this.width / this.mc.displayWidth - this.screenX;
        this.mouseEvent.y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1 - this.screenY;
        if (this.oldX == -1) {
            this.oldX = this.mouseEvent.x;
            this.oldY = this.mouseEvent.y;
        }
        this.mouseEvent.dx = this.mouseEvent.x - this.oldX;
        this.mouseEvent.dy = this.mouseEvent.y - this.oldY;
        this.oldX = this.mouseEvent.x;
        this.oldY = this.mouseEvent.y;
        this.mouseEvent.down = Mouse.getEventButtonState();
        this.mouseEvent.button = Mouse.getEventButton();
        this.mouseEvent.wheel = Mouse.getEventDWheel();
        if (this.mouseEvent.wheel != 0) {
            this.mouseEvent.wheel = this.mouseEvent.wheel < 0 ? -1 : 1;
            this.root.mouseWheel(this.mouseEvent);
        } else if (this.mouseEvent.button >= 0 && this.mouseEvent.button < this.downButtons.length) {
            if (this.downButtons[this.mouseEvent.button] != this.mouseEvent.down) {
                this.downButtons[this.mouseEvent.button] = this.mouseEvent.down;
                if (this.mouseEvent.down) {
                    this.root.mouseDown(this.mouseEvent);
                } else {
                    this.root.mouseUp(this.mouseEvent);
                }
            } else if (this.mouseEvent.dx != 0 || this.mouseEvent.dy != 0) {
                this.root.mouseMove(this.mouseEvent);
            }
        } else if (this.mouseEvent.dx != 0 || this.mouseEvent.dy != 0) {
            this.root.mouseMove(this.mouseEvent);
        }
        if (!this.mouseEvent.handled) {
            super.handleMouseInput();
        }
    }

    public void handleKeyboardInput() {
        this.keyboardEvent.handled = false;
        if (Keyboard.getEventKeyState()) {
            this.keyboardEvent.key = Keyboard.getEventKey();
            this.keyboardEvent.character = Keyboard.getEventCharacter();
            switch (this.keyboardEvent.key) {
                case 1: {
                    break;
                }
                default: {
                    this.root.keyPressed(this.keyboardEvent);
                }
            }
        }
        if (!this.keyboardEvent.handled) {
            super.handleKeyboardInput();
        }
    }

    public void close() {
        this.mc.displayGuiScreen((GuiScreen)null);
        this.mc.setIngameFocus();
    }

    protected void addChild(GuiElement e) {
        this.root.addChild(e);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int bx, int by) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GeneralClient.bind(this.backgroundTexture);
        this.drawTexturedModalRect(this.screenX, this.screenY, 0, 0, this.screenW, this.screenH);
    }

    protected void drawGuiContainerForegroundLayer(int fx, int fy) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.root.render();
    }

    public void drawString(String text, int sx, int sy, int color) {
        FontRenderer fontRenderer = this.fontRendererObj;
        fontRenderer.drawString(text, sx, sy, color);
    }

    public void drawCenteredString(String text, int sx, int sy, int color) {
        FontRenderer fontRenderer = this.fontRendererObj;
        fontRenderer.drawString(text, sx - fontRenderer.getStringWidth(text) / 2, sy - fontRenderer.FONT_HEIGHT / 2, color);
    }

    public void drawStringWithShadow(String text, int sx, int sy, int color) {
        FontRenderer fontRenderer = this.fontRendererObj;
        fontRenderer.drawStringWithShadow(text, sx, sy, color);
    }

    public void drawCenteredStringWithShadow(String text, int sx, int sy, int color) {
        FontRenderer fontRenderer = this.fontRendererObj;
        fontRenderer.drawStringWithShadow(text, sx - fontRenderer.getStringWidth(text) / 2, sy - fontRenderer.FONT_HEIGHT / 2, color);
    }

    public FontRenderer fontRenderer() {
        return this.mc.fontRenderer;
    }

    protected void drawRect(int gx, int gy, int gw, int gh, int c1, int c2) {
        this.drawGradientRect(gx, gy, gx + gw, gy + gh, c1, c2);
    }

    public void drawTiledRect(int rx, int ry, int rw, int rh, int u, int v, int tw, int th) {
        if (rw == 0 || rh == 0 || tw == 0 || th == 0) {
            return;
        }
        float pixel = 0.00390625f;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        for (int y = 0; y < rh; y += th) {
            for (int x = 0; x < rw; x += tw) {
                int qh;
                int qw = tw;
                if (x + qw > rw) {
                    qw = rw - x;
                }
                if (y + (qh = th) > rh) {
                    qh = rh - y;
                }
                double x1 = rx + x;
                double x2 = rx + x + qw;
                double y1 = ry + y;
                double y2 = ry + y + qh;
                double u1 = pixel * (float)u;
                double u2 = pixel * (float)(u + tw);
                double v1 = pixel * (float)v;
                double v2 = pixel * (float)(v + th);
                tessellator.addVertexWithUV(x1, y2, (double)this.zLevel, u1, v2);
                tessellator.addVertexWithUV(x2, y2, (double)this.zLevel, u2, v2);
                tessellator.addVertexWithUV(x2, y1, (double)this.zLevel, u2, v1);
                tessellator.addVertexWithUV(x1, y1, (double)this.zLevel, u1, v1);
            }
        }
        tessellator.draw();
    }

    public void bindTexture(String tex) {
        GeneralClient.bind(tex);
    }
}

