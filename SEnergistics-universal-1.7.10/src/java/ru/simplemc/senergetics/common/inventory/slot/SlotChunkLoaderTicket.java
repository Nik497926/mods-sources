/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.item.tool.ItemChunkLoaderTicket;

public class SlotChunkLoaderTicket
extends Slot {
    public SlotChunkLoaderTicket(IInventory inventory, int id, int posX, int posY) {
        super(inventory, id, posX, posY);
    }

    public boolean isItemValid(ItemStack itemStack) {
        return itemStack != null && itemStack.getItem() instanceof ItemChunkLoaderTicket;
    }

    public int getSlotStackLimit() {
        return 1;
    }
}

