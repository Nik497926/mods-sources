/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.barter;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.deathcry.deathtrade.barter.BarterContainer;
import ru.deathcry.deathtrade.barter.Barterer;
import ru.deathcry.deathtrade.packets.PacketDispatcher;
import ru.deathcry.deathtrade.packets.client.ActionMessage;
import ru.deathcry.deathtrade.packets.client.MoneyChangeMessage;

public class BarterGui
extends GuiContainer {
    public String input = "0.00";
    public int position = this.input.length();
    public boolean isActive = false;
    public static ResourceLocation gui = new ResourceLocation("deathtrade".toLowerCase(), "textures/barter_gui.png");
    public int guiLeft;
    public int guiTop;
    protected int xOffset;
    protected int yOffset;
    private Barterer playerBarter;
    private Barterer opponentBarter;

    public BarterGui(BarterContainer container) {
        super((Container)container);
        this.playerBarter = container.holder.exchanger;
        this.opponentBarter = container.holder.exchanger2;
        this.xSize = 362;
        this.ySize = 220;
    }

    public void initGui() {
        super.initGui();
    }

    protected void drawGuiContainerBackgroundLayer(float fl, int x, int y) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        this.xOffset = (this.width - this.xSize) / 2;
        this.yOffset = (this.height - this.ySize) / 2;
        GL11.glEnable((int)3042);
        this.drawTexturedModalRect(this.xOffset, this.yOffset, 0, 0, this.xSize, this.ySize);
    }

    protected void drawGuiContainerForegroundLayer(int x, int y) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        this.mc.getTextureManager().bindTexture(gui);
        this.xOffset = (this.width - this.xSize) / 2;
        this.yOffset = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(27, 130, 370, this.playerBarter.isReady() ? 36 : 10, 128, 24);
        this.drawTexturedModalRect(300, 203, 362, this.opponentBarter.isReady() ? 0 : 5, 58, 5);
        this.drawTexturedModalRect(27, 164, 370, this.playerBarter.isAccept() ? 89 : 63, 128, 24);
        this.fontRendererObj.drawString(this.opponentBarter.getName(), 192, 116, -1, false);
        this.drawRectangle(112, 8, 60, 14, -1);
        this.drawRectangle(113, 9, 58, 12, -1231231);
        this.fontRendererObj.drawString(this.input, 170 - this.fontRendererObj.getStringWidth(this.input), 12, -1, false);
        String playerMoney = this.playerBarter.getMoneyString();
        this.fontRendererObj.drawString(playerMoney, 344 - this.fontRendererObj.getStringWidth(playerMoney), 12, -1, false);
        String opponentMoney = this.opponentBarter.getMoneyString();
        this.fontRendererObj.drawString(opponentMoney, 344 - this.fontRendererObj.getStringWidth(opponentMoney), 115, -1, false);
        if (System.currentTimeMillis() % 1000L < 500L && this.isActive) {
            GL11.glTranslated((double)-0.5, (double)0.0, (double)0.0);
            this.drawRectangle(170 - this.fontRendererObj.getStringWidth(this.input.substring(this.position)), 10, 1, 10, -1);
            GL11.glTranslated((double)0.5, (double)0.0, (double)0.0);
        }
    }

    private void drawRectangle(int x, int y, int width, int height, int color) {
        BarterGui.drawRect((int)x, (int)y, (int)(x + width), (int)(y + height), (int)color);
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height) {
        float a = 0.001953125f;
        float h = 0.00390625f;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + height), (double)this.zLevel, (double)((float)(u + 0) * a), (double)((float)(v + height) * h));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), (double)this.zLevel, (double)((float)(u + width) * a), (double)((float)(v + height) * h));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + 0), (double)this.zLevel, (double)((float)(u + width) * a), (double)((float)(v + 0) * h));
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)this.zLevel, (double)((float)(u + 0) * a), (double)((float)(v + 0) * h));
        tessellator.draw();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        if ((x -= this.xOffset) >= 27 && x <= 155 && (y -= this.yOffset) >= 130 && y <= 151) {
            PacketDispatcher.sendToServer(new ActionMessage(0));
        }
        if (x >= 27 && x <= 155 && y >= 164 && y <= 183) {
            PacketDispatcher.sendToServer(new ActionMessage(1));
        }
        if (x >= 112 && x <= 172 && y >= 8 && y <= 22) {
            this.makeActive();
            int tmp = x - 112 - (60 - this.fontRendererObj.getStringWidth(this.input));
            this.position = 0;
            try {
                if (tmp <= 0) return;
                int i = 0;
                while (i < tmp) {
                    i = this.fontRendererObj.getStringWidth(this.input.substring(0, this.position + 1)) - 2;
                    ++this.position;
                }
                return;
            }
            catch (Exception e) {
                this.position = this.input.length() - 1;
                return;
            }
        } else {
            this.makeInactive();
        }
    }

    public void makeActive() {
        this.isActive = true;
    }

    public void makeInactive() {
        this.isActive = false;
        try {
            this.playerBarter.setMoneyPlaced(Math.min(Double.parseDouble(this.input), this.playerBarter.getBalance()));
            PacketDispatcher.sendToServer(new MoneyChangeMessage(this.playerBarter.getMoneyPlaced()));
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        this.input = this.playerBarter.getMoneyString();
        this.position = this.input.length();
    }

    public void updateScreen() {
        super.updateScreen();
    }

    protected void keyTyped(char par1, int par2) {
        if (this.isActive) {
            if (par1 >= '0' && par1 <= '9' || par1 == '.') {
                if (this.input.length() > 8) {
                    return;
                }
                if (par1 != '.' || !this.input.contains(".")) {
                    this.input = new StringBuilder(this.input).insert(this.position, par1).toString();
                    ++this.position;
                }
            }
            if (par2 == 14 && this.position > 0) {
                this.input = new StringBuilder(this.input).deleteCharAt(this.position - 1).toString();
                --this.position;
            }
            if (par2 == 211 && this.input.length() > this.position) {
                this.input = new StringBuilder(this.input).deleteCharAt(this.position).toString();
            }
            if (par2 == 203 && this.position > 0) {
                --this.position;
            }
            if (par2 == 205 && this.position < this.input.length()) {
                ++this.position;
            }
            if (par2 == 28 || par2 == 1) {
                this.makeInactive();
            }
        } else {
            super.keyTyped(par1, par2);
        }
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        PacketDispatcher.sendToServer(new ActionMessage(3));
    }
}

