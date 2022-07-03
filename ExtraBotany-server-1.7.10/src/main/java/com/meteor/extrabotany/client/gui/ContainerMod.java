/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public abstract class ContainerMod
extends Container {
    protected void addPlayerSlots(InventoryPlayer playerInventory, int x, int y) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)playerInventory, j + i * 9 + 9, x + j * 18, y + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)playerInventory, i, x + i * 18, y + 58));
        }
    }
}

