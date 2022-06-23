/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotSpawnerAttacked
extends Slot {
    public SlotSpawnerAttacked(IInventory inventory, int id, int posX, int posY) {
        super(inventory, id, posX, posY);
    }

    public int getSlotStackLimit() {
        return 1;
    }
}

