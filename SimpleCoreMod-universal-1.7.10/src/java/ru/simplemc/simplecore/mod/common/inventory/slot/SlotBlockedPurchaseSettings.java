/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import ru.simplemc.simplecore.mod.common.inventory.InventoryTileEntityTradeStation;
import ru.simplemc.simplecore.mod.common.inventory.slot.SlotBlocked;

public class SlotBlockedPurchaseSettings
extends SlotBlocked {
    public SlotBlockedPurchaseSettings(InventoryTileEntityTradeStation inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public int getSlotStackLimit() {
        return this.inventory.getInventoryStackLimit();
    }

    public ItemStack slotClickBlocked(int clickType, int dragType, EntityPlayer player) {
        if (clickType == 3 && dragType == 2 && this.getHasStack()) {
            this.putStack(null);
        } else if (clickType == 0 && dragType == 0 || dragType == 1) {
            ItemStack itemStack = player.inventory.getItemStack();
            itemStack = itemStack == null ? null : itemStack.copy();
            this.fillItemStack(this.getStack(), itemStack, dragType);
        }
        return null;
    }

    public void fillItemStack(ItemStack slotItemStack, ItemStack cursorItemStack, int mouseButton) {
        if (cursorItemStack != null) {
            cursorItemStack.stackSize = 1;
        }
        ItemStack phantomItemStack = null;
        if (slotItemStack == null && cursorItemStack != null) {
            phantomItemStack = cursorItemStack.copy();
            phantomItemStack.stackSize = cursorItemStack.stackSize;
        } else if (slotItemStack != null && cursorItemStack != null) {
            phantomItemStack = slotItemStack.copy();
            phantomItemStack.stackSize = slotItemStack.stackSize + (mouseButton == 0 ? -1 : 1);
        } else if (slotItemStack != null && cursorItemStack != null) {
            if (slotItemStack.isItemEqual(cursorItemStack)) {
                phantomItemStack = slotItemStack.copy();
                phantomItemStack.stackSize = slotItemStack.stackSize + (mouseButton == 0 ? -1 : 1);
            } else {
                phantomItemStack = cursorItemStack.copy();
            }
        }
        if (phantomItemStack != null) {
            if (phantomItemStack.getMaxStackSize() < phantomItemStack.stackSize) {
                phantomItemStack.stackSize = phantomItemStack.getMaxStackSize();
            }
            if (phantomItemStack.stackSize < 1) {
                phantomItemStack = null;
            }
        }
        this.putStack(phantomItemStack);
    }
}

