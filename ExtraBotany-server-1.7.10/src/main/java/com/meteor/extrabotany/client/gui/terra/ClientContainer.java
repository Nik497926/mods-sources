/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.terra;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileAutoPlate;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ClientContainer
extends GuiContainer {
    private ResourceLocation guiTexture;
    private final TileAutoPlate inventory;
    private FontRenderer fonts = null;

    public ClientContainer(Container container, String guiTextureName, TileAutoPlate inventory) {
        super(container);
        this.inventory = inventory;
        this.xSize = 175;
        this.ySize = 194;
        this.guiTexture = new ResourceLocation(ExtraBotany.modid + ":textures/gui/GuiAutoAgglomerationFactory.png");
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
            this.drawTexturedModalRect(this.guiLeft + 134, this.guiTop + 80 - m, 180, 135, 16, m);
            this.drawTexturedModalRect(this.guiLeft + 134, this.guiTop + 21, 198, 72, 16, 59);
            int pr = MathHelper.floor_double((double)((double)this.inventory.getProgress() / 100.0 * 30.0));
            pr = Math.max(0, Math.min(30, pr));
            this.drawTexturedModalRect(this.guiLeft + 73, this.guiTop + 51, 176, 0, pr, 25);
        }
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fonts = this.fontRendererObj;
    }
}

