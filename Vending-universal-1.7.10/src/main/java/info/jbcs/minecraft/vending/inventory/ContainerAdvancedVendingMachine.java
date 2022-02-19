/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package info.jbcs.minecraft.vending.inventory;

import info.jbcs.minecraft.vending.inventory.ContainerTileEntity;
import info.jbcs.minecraft.vending.inventory.SlotAdvancedVendingMachine;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerAdvancedVendingMachine
extends ContainerTileEntity<TileEntityVendingMachine> {
    SlotAdvancedVendingMachine advSlot;

    public ContainerAdvancedVendingMachine(IInventory playerInv, TileEntityVendingMachine machine) {
        super(playerInv, machine, 8, 84);
        this.addSlotToContainer(new Slot((IInventory)machine, 9, 120, 48));
        this.advSlot = new SlotAdvancedVendingMachine(machine, 10, 42, 48);
        this.addSlotToContainer(this.advSlot);
    }
}

