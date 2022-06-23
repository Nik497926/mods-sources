/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.slot;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotWitherSkull
extends Slot {
    public SlotWitherSkull(IInventory inventory, int slotIndex, int posX, int posY) {
        super(inventory, slotIndex, posX, posY);
    }

    public boolean isItemValid(ItemStack itemStack) {
        return SlotWitherSkull.isMatches(itemStack);
    }

    public int getSlotStackLimit() {
        return 64;
    }

    public static boolean isMatches(ItemStack itemStack) {
        return itemStack != null && itemStack.getItem() == Items.skull && itemStack.getItemDamage() == 1;
    }
}

