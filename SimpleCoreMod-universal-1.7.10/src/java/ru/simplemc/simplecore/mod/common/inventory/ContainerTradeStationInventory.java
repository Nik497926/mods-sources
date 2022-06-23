/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory;

import javax.annotation.Nonnull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.simplecore.mod.common.inventory.ContainerTradeStation;
import ru.simplemc.simplecore.mod.common.inventory.InventoryTileEntityTradeStation;
import ru.simplemc.simplecore.mod.common.inventory.slot.SlotBlocked;

public class ContainerTradeStationInventory
extends ContainerTradeStation {
    public ContainerTradeStationInventory(InventoryTileEntityTradeStation inventory, InventoryPlayer inventoryPlayer) {
        super(inventory, inventoryPlayer);
        this.addSlots();
    }

    private void addSlots() {
        int i;
        for (i = 0; i < 104; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventoryTradeStation, i, 12 + i % 13 * 18, 19 + i / 13 * 18));
        }
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)this.inventoryPlayer, j + i * 9 + 9, 48 + j * 18, 174 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventoryPlayer, i, 48 + i * 18, 232));
        }
    }

    public ItemStack transferStackInSlot(@Nonnull EntityPlayer player, int index) {
        Slot slot = (Slot)this.inventorySlots.get(index);
        ItemStack transferredItemStack = null;
        if (slot instanceof SlotBlocked) {
            return null;
        }
        if (slot != null && slot.getHasStack()) {
            ItemStack slotItemStack = slot.getStack();
            transferredItemStack = slotItemStack.copy();
            if (index < 104 ? !this.mergeItemStack(slotItemStack, 104, this.inventorySlots.size(), true) : !this.mergeItemStack(slotItemStack, 0, 104, false)) {
                return null;
            }
            if (slotItemStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            slot.onPickupFromSlot(player, slotItemStack);
        }
        return transferredItemStack;
    }
}

