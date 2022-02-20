/*
 * Decompiled with CFR 0.152.
 */
package com.gamerforea.deathtrade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public final class ReadOnlySlot
extends Slot {
    public ReadOnlySlot(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
    }

    public boolean canTakeStack(EntityPlayer player) {
        return false;
    }

    public boolean isItemValid(ItemStack stack) {
        return false;
    }
}

