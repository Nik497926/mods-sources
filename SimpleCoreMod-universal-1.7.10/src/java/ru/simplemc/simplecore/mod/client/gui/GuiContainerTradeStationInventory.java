/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.simplemc.simplecore.mod.common.inventory.ContainerTradeStationInventory;

public class GuiContainerTradeStationInventory
extends GuiContainer {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("simplecore", "textures/gui/trade_station_inventory.png");
    private final String title = I18n.format((String)"gui.trade_station.inventory.name", (Object[])new Object[0]);

    public GuiContainerTradeStationInventory(ContainerTradeStationInventory container) {
        super((Container)container);
        this.xSize = 256;
        this.ySize = 256;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        RenderHelper.disableStandardItemLighting();
        this.fontRendererObj.drawString(this.title, this.xSize / 2 - this.fontRendererObj.getStringWidth(this.title) / 2, 6, 0x404040);
        RenderHelper.enableGUIStandardItemLighting();
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}

