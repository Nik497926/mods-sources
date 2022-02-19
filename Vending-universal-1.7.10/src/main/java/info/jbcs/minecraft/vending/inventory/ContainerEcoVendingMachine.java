/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package info.jbcs.minecraft.vending.inventory;

import info.jbcs.minecraft.vending.inventory.ContainerTileEntity;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerEcoVendingMachine
extends ContainerTileEntity<TileEntityVendingMachine> {
    public TileEntityVendingMachine tile;

    public ContainerEcoVendingMachine(InventoryPlayer inventoryplayer, TileEntityVendingMachine machine) {
        super((IInventory)inventoryplayer, machine, 8, 84);
        this.tile = machine;
        this.addSlotToContainer(new Slot((IInventory)machine, 9, 120, 48));
    }
}

