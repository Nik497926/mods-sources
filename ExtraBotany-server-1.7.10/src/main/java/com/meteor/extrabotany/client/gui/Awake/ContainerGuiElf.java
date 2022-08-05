/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.Awake;

import com.meteor.extrabotany.client.reference.Reference;
import java.awt.Color;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@SideOnly(value= Side.CLIENT)
public class ContainerGuiElf extends GuiContainer {
    private final ResourceLocation guiTexture = new ResourceLocation(Reference.MOD_ID_LOWER + ":textures/gui/awakeelf.png");
    private final IInventory inventory;
    private FontRenderer fonts = null;

    public ContainerGuiElf(Container container, String guiTextureName, IInventory inventory) {
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
        this.fontRendererObj.drawString(I18n.format((String)StatCollector.translateToLocal((String)"gui.elf.main"), (Object[])new Object[0]), 55, 8, new Color(0xFFFFFF).getRGB());
        this.fonts = this.fontRendererObj;
    }
}

