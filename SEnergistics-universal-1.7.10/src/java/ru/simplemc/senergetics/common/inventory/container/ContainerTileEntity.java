/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;

public class ContainerTileEntity<T extends TileEntityMachine<T>>
extends Container {
    protected InventoryTileEntity<T> inventory;
    protected EntityPlayer player;

    public ContainerTileEntity(InventoryTileEntity<T> inventory, EntityPlayer player) {
        this.inventory = inventory;
        this.player = player;
        this.bindMachineInventory();
        this.bindPlayerInventory();
    }

    protected void bindMachineInventory() {
    }

    protected void bindPlayerInventory() {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)this.player.inventory, j + i * 9 + 9, 8 + j * 18, 111 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.player.inventory, i, 8 + i * 18, 169));
        }
    }

    public InventoryTileEntity<T> getInventoryTileEntity() {
        return this.inventory;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        Slot slot = (Slot)this.inventorySlots.get(index);
        ItemStack itemStack = null;
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStackInSlot = slot.getStack();
            itemStack = itemStackInSlot.copy();
            int inventoryRange = this.inventorySlots.size() - 36;
            if (index < inventoryRange ? !this.mergeItemStack(itemStackInSlot, inventoryRange, this.inventorySlots.size(), true) : !this.mergeItemStack(itemStackInSlot, 0, inventoryRange, false)) {
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

    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.inventory.isUseableByPlayer(entityPlayer);
    }
}

