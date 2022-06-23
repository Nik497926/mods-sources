/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.SEnergeticsRegistry;
import ru.simplemc.senergetics.common.item.tool.spawner.ItemSpawnerUpgrade;

public class SlotSpawnerInventory
extends Slot {
    public SlotSpawnerInventory(IInventory inventory, int id, int posX, int posY) {
        super(inventory, id, posX, posY);
    }

    public boolean isItemValid(ItemStack itemStack) {
        return itemStack.getItem() != SEnergeticsRegistry.itemMobScanner && !(itemStack.getItem() instanceof ItemSpawnerUpgrade);
    }

    public int getSlotStackLimit() {
        return 64;
    }
}

