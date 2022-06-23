/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.slot;

import ic2.api.item.IElectricItem;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;

public class SlotElectricItem
extends Slot {
    public SlotElectricItem(InventoryTileEntity<?> inventory, int index, int posX, int posY) {
        super(inventory, index, posX, posY);
    }

    public boolean isItemValid(ItemStack itemStack) {
        return itemStack != null && itemStack.getItem() instanceof IElectricItem;
    }

    public int getSlotStackLimit() {
        return 64;
    }
}

