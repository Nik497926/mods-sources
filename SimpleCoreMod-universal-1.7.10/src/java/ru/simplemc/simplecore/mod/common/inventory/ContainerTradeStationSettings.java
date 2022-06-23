/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.simplecore.mod.common.inventory.ContainerTradeStation;
import ru.simplemc.simplecore.mod.common.inventory.InventoryTileEntityTradeStation;
import ru.simplemc.simplecore.mod.common.inventory.slot.SlotBlockedPurchaseSettings;

public class ContainerTradeStationSettings
extends ContainerTradeStation {
    public ContainerTradeStationSettings(InventoryTileEntityTradeStation inventory, InventoryPlayer inventoryPlayer) {
        super(inventory, inventoryPlayer);
        this.addSlots();
    }

    private void addSlots() {
        int i;
        this.addSlotToContainer(new SlotBlockedPurchaseSettings(this.inventoryTradeStation, 104, 9, 21));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)this.inventoryPlayer, j + i * 9 + 9, 9 + j * 18, 139 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventoryPlayer, i, 9 + i * 18, 197));
        }
    }

    public ItemStack slotClick(int slotId, int dragType, int clickTypeIn, EntityPlayer player) {
        Slot slot;
        if (slotId == 0 && (slot = (Slot)this.inventorySlots.get(slotId)) instanceof SlotBlockedPurchaseSettings) {
            return ((SlotBlockedPurchaseSettings)slot).slotClickBlocked(clickTypeIn, dragType, player);
        }
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        return null;
    }
}

