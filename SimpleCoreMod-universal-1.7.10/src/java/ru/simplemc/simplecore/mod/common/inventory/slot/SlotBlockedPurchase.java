/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import ru.simplemc.simplecore.mod.common.inventory.slot.SlotBlocked;

public class SlotBlockedPurchase
extends SlotBlocked {
    public SlotBlockedPurchase(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack itemStack) {
        return false;
    }
}

