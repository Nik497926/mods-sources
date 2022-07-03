/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.autoTrade;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileAutoTradeElf;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ClientContainer
extends GuiContainer {
    private ResourceLocation guiTexture;
    private final TileAutoTradeElf inventory;
    private FontRenderer fonts = null;

    public ClientContainer(Container container, String guiTextureName, TileAutoTradeElf inventory) {
        super(container);
        this.inventory = inventory;
        this.xSize = 175;
        this.ySize = 165;
        this.guiTexture = new ResourceLocation(ExtraBotany.modid + ":textures/gui/GuiAutoAlfheimMarket.png");
    }

    public FontRenderer getFontRender() {
        return this.fonts;
    }

    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        if (this.guiTexture != null) {
            this.mc.getTextureManager().bindTexture(this.guiTexture);
            this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
            int m = MathHelper.floor_double((double)((double)this.inventory.getCurrentMana() / (double)this.inventory.getMaxMana() * 59.0));
            m = Math.max(0, Math.min(59, m));
            this.drawTexturedModalRect(this.guiLeft + 152, this.guiTop + 72 - m, 201, 66, 16, m);
            this.drawTexturedModalRect(this.guiLeft + 152, this.guiTop + 15, 219, 3, 16, 59);
        }
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fonts = this.fontRendererObj;
    }
}

