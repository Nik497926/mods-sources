/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventorySolarPanel;
import ru.simplemc.senergetics.common.item.electicity.panel.ItemPanelParticleCollector;

public class SlotSolarPanelParticleCollector
extends Slot {
    public SlotSolarPanelParticleCollector(InventorySolarPanel inventory) {
        super((IInventory)inventory, 9, 11, 44);
    }

    public boolean isItemValid(ItemStack itemStack) {
        return itemStack == null || itemStack.getItem() instanceof ItemPanelParticleCollector;
    }
}

