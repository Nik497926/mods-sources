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

public class GuiHalloweenJackOMan
extends GuiDivineMerchant {
    public GuiHalloweenJackOMan(ContainerDivineMerchant container, IMerchant mer) {
        super(container, mer, "Halloween Jack 'O' Man", "hallowenjackOMan");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int var1, int var2) {
        this.fontRendererObj.drawString(this.name, 56, 6, 16772352);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, this.ySize - 96 + 2, 16772352);
    }
}

