/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventoryChunkLoader;
import ru.simplemc.senergetics.common.inventory.slot.SlotChunkLoaderTicket;

public class ContainerChunkLoader
extends Container {
    private final InventoryChunkLoader inventoryChunkLoader;

    public ContainerChunkLoader(InventoryPlayer inventoryPlayer, InventoryChunkLoader inventory) {
        int i;
        this.inventoryChunkLoader = inventory;
        this.addSlotToContainer(new SlotChunkLoaderTicket(this.inventoryChunkLoader, 0, 8, 22));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 57 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 115));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
        Slot slot = (Slot)this.inventorySlots.get(slotId);
        ItemStack itemStack = null;
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStackInSlot = slot.getStack();
            itemStack = itemStackInSlot.copy();
            int inventoryRange = this.inventorySlots.size() - 36;
            if (slotId < inventoryRange ? !this.mergeItemStack(itemStackInSlot, inventoryRange, this.inventorySlots.size(), true) : !this.mergeItemStack(itemStackInSlot, 0, inventoryRange - 1, false)) {
                return null;
            }
            if (itemStackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            if (itemStackInSlot.stackSize == itemStack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, itemStackInSlot);
        }
        return itemStack;
    }

    public boolean canInteractWith(EntityPlayer player) {
        return this.inventoryChunkLoader.isUseableByPlayer(player);
    }

    public InventoryChunkLoader getInventoryChunkLoader() {
        return this.inventoryChunkLoader;
    }
}

