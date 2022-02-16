/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IMerchant
 */
package net.divinerpg.client.render.gui;

import net.divinerpg.blocks.base.tileentity.container.ContainerDivineMerchant;
import net.divinerpg.client.render.gui.GuiDivineMerchant;
import net.minecraft.entity.IMerchant;

public class GuiWorkshopMerchant
extends GuiDivineMerchant {
    public GuiWorkshopMerchant(ContainerDivineMerchant container, IMerchant mer) {
        super(container, mer, "Merchant", "workshopWorker");
    }
}

