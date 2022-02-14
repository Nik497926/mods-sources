/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.util.StatCollector
 */
package net.divinerpg.client.render.gui;

import net.divinerpg.blocks.base.tileentity.container.ContainerDivineMerchant;
import net.divinerpg.client.render.gui.GuiDivineMerchant;
import net.minecraft.entity.IMerchant;
import net.minecraft.util.StatCollector;

public class GuiHunger
extends GuiDivineMerchant {
    public GuiHunger(ContainerDivineMerchant container, IMerchant mer) {
        super(container, mer, "The Hunger", "hunger");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int var1, int var2) {
        this.fontRendererObj.drawString(this.name, 56, 6, 0xFF545F);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, this.ySize - 96 + 2, 0xFF545F);
    }
}

