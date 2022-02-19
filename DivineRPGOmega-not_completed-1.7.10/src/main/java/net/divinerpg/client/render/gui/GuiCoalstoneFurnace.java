/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 */
package net.divinerpg.client.render.gui;

import net.divinerpg.blocks.base.tileentity.TileEntityInfiniteFurnace;
import net.divinerpg.client.render.gui.GuiInfiniteFurnace;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCoalstoneFurnace
extends GuiInfiniteFurnace {
    public GuiCoalstoneFurnace(InventoryPlayer par1InventoryPlayer, TileEntityInfiniteFurnace par2TileEntityFurnace) {
        super(par1InventoryPlayer, par2TileEntityFurnace, "Coalstone Furnace", "noFuel");
    }
}

