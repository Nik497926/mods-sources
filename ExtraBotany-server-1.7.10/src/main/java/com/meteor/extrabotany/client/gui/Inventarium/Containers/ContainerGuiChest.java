/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.Inventarium.Containers;

import com.meteor.extrabotany.client.reference.Reference;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public abstract class ContainerGuiChest
extends GuiContainer {
    private final ResourceLocation guiTexture = new ResourceLocation(Reference.MOD_ID_LOWER + ":textures/gui/chestexp.png");
    private final IInventory inventory;
    private FontRenderer fonts = null;

    public ContainerGuiChest(Container container, String guiTextureName, IInventory inventory) {
        super(container);
        this.inventory = inventory;
        this.xSize = 256;
        this.ySize = 256;
    }

    public FontRenderer getFontRender() {
        return this.fonts;
    }

    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(this.guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fonts = this.fontRendererObj;
    }

    public void onTextfieldUpdate(int id) {
    }
}

