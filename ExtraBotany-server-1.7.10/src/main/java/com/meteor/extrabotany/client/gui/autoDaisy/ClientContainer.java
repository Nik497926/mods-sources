/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.autoDaisy;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileAutoDaisy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

@SideOnly(value= Side.CLIENT)
public class ClientContainer extends GuiContainer {
    private ResourceLocation guiTexture;
    private final TileAutoDaisy inventory;
    private FontRenderer fonts = null;

    public ClientContainer(Container container, String guiTextureName, TileAutoDaisy inventory) {
        super(container);
        this.inventory = inventory;
        this.xSize = 175;
        this.ySize = 165;
        this.guiTexture = new ResourceLocation(ExtraBotany.modid + ":textures/gui/GuiAutoDaisy.png");
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

