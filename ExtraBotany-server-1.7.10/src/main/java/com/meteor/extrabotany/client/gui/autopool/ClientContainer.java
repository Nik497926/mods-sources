/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.autopool;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileAutoPool;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ClientContainer
extends GuiContainer {
    private ResourceLocation guiTexture;
    private final TileAutoPool inventory;
    private FontRenderer fonts = null;

    public ClientContainer(Container container, String guiTextureName, TileAutoPool inventory) {
        super(container);
        this.inventory = inventory;
        this.xSize = 175;
        this.ySize = 190;
        this.guiTexture = new ResourceLocation(ExtraBotany.modid + ":textures/gui/GuiAutoManaPool.png");
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
            this.drawTexturedModalRect(this.guiLeft + 155, this.guiTop + 64 - m, 221, 65, 16, m);
            this.drawTexturedModalRect(this.guiLeft + 155, this.guiTop + 5, 239, 1, 16, 59);
        }
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fonts = this.fontRendererObj;
    }
}

