/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.genDust;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileGenDust;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class ClientContainer
extends GuiContainer {
    private ResourceLocation guiTexture;
    private final TileGenDust inventory;
    private FontRenderer fonts = null;

    public ClientContainer(Container container, String guiTextureName, TileGenDust inventory) {
        super(container);
        this.inventory = inventory;
        this.xSize = 175;
        this.ySize = 165;
        this.guiTexture = new ResourceLocation(ExtraBotany.modid + ":textures/gui/GUI_TileGenDust.png");
    }

    public FontRenderer getFontRender() {
        return this.fonts;
    }

    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        if (this.guiTexture != null) {
            this.mc.getTextureManager().bindTexture(this.guiTexture);
            this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        }
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fonts = this.fontRendererObj;
    }
}

