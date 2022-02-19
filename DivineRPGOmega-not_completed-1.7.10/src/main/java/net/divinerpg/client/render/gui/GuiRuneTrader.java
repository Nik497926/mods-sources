/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IMerchant
 */
package net.divinerpg.client.render.gui;

import net.divinerpg.blocks.base.tileentity.container.ContainerDivineMerchant;
import net.divinerpg.client.render.gui.GuiDivineMerchant;
import net.divinerpg.utils.MessageLocalizer;
import net.minecraft.entity.IMerchant;

public class GuiRuneTrader
extends GuiDivineMerchant {
    public GuiRuneTrader(ContainerDivineMerchant container, IMerchant mer) {
        super(container, mer, MessageLocalizer.normal("entity.runetrader.name"), "runeTrader");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int var1, int var2) {
        this.fontRendererObj.drawString(this.name, 50, 6, 6422695);
    }
}

