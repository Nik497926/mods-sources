/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.simplecore.mod.common.item.ItemBackpack;

public class SlotBackpack
extends Slot {
    public SlotBackpack(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public boolean isItemValid(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() instanceof ItemBackpack) {
            return false;
        }
        return super.isItemValid(itemStack);
    }
}

