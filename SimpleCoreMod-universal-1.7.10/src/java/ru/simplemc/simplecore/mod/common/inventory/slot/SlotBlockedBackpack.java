/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory.slot;

import javax.annotation.Nonnull;
import net.minecraft.inventory.IInventory;
import ru.simplemc.simplecore.mod.common.inventory.slot.SlotBlocked;

public class SlotBlockedBackpack
extends SlotBlocked {
    private final int buttonSlotNumber;

    public SlotBlockedBackpack(@Nonnull IInventory inventory, int id, int posX, int posY) {
        super(inventory, id, posX, posY);
        this.buttonSlotNumber = id;
    }

    public int getButtonSlotNumber() {
        return this.buttonSlotNumber;
    }
}

