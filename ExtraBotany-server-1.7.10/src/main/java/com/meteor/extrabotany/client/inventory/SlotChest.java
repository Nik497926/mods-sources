/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotChest
extends Slot {
    private Boolean valid;

    public SlotChest(IInventory inventory, int inventoryIndex, int x, int y, Boolean valid) {
        super(inventory, inventoryIndex, x, y);
        this.valid = valid;
    }

    public boolean isItemValid(ItemStack stack) {
        return this.valid;
    }
}

