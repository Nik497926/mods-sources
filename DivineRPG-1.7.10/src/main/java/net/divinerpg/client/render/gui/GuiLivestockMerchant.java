/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.util.StatCollector
 */
package net.divinerpg.client.render.gui;

import net.divinerpg.blocks.base.tileentity.container.ContainerDivineMerchant;
import net.divinerpg.client.render.gui.GuiDivineMerchant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.IMerchant;
import net.minecraft.util.StatCollector;

public class GuiLivestockMerchant
extends GuiDivineMerchant {
    public GuiLivestockMerchant(ContainerDivineMerchant container, IMerchant mer) {
        super(container, mer, "Livestock Merchant", "livestockMerchant");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int var1, int var2) {
        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, this.name, 90, 6, 5161493);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, this.ySize - 96 + 2, 5161493);
    }

    public void drawCenteredString(FontRenderer f, String text, int x, int y, int color) {
        f.drawString(text, x - f.getStringWidth(text) / 2, y, color);
    }
}

