/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.container;

import ic2.api.item.IElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventorySolarPanel;
import ru.simplemc.senergetics.common.inventory.slot.SlotElectricItem;
import ru.simplemc.senergetics.common.inventory.slot.SlotSolarPanelParticleCollector;
import ru.simplemc.senergetics.common.item.electicity.panel.ItemPanelParticleCollector;
import ru.simplemc.senergetics.common.tileentity.electricity.TileEntitySolarPanel;

public class ContainerSolarPanel
extends Container {
    private final TileEntitySolarPanel tileEntity;
    private final InventorySolarPanel inventorySolarPanel;
    private final EntityPlayer player;

    public ContainerSolarPanel(TileEntitySolarPanel tileEntity, EntityPlayer player) {
        this.tileEntity = tileEntity;
        this.inventorySolarPanel = tileEntity.getInventory();
        this.player = player;
        this.bindSlots();
    }

    private void bindSlots() {
        int i;
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new SlotElectricItem(this.inventorySolarPanel, i, 8 + i * 18, 19));
        }
        this.addSlotToContainer(new SlotSolarPanelParticleCollector(this.inventorySolarPanel));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)this.player.inventory, j + i * 9 + 9, 8 + j * 18, 111 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.player.inventory, i, 8 + i * 18, 169));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
        Slot slot = (Slot)this.inventorySlots.get(slotId);
        ItemStack itemStack = null;
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStackInSlot = slot.getStack();
            itemStack = itemStackInSlot.copy();
            int inventoryRange = this.inventorySlots.size() - 36;
            if (slotId < inventoryRange) {
                if (!this.mergeItemStack(itemStackInSlot, inventoryRange, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else {
                if (itemStackInSlot.getItem() instanceof IElectricItem && !this.mergeItemStack(itemStackInSlot, 0, 8, false)) {
                    return null;
                }
                if (itemStackInSlot.getItem() instanceof ItemPanelParticleCollector && !this.mergeItemStack(itemStackInSlot, 9, inventoryRange, false)) {
                    return null;
                }
            }
            if (itemStackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            if (itemStackInSlot.stackSize == itemStack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, itemStackInSlot);
        }
        return itemStack;
    }

    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tileEntity.isUseableByPlayer(entityPlayer);
    }

    public TileEntitySolarPanel getTileEntity() {
        return this.tileEntity;
    }

    public InventorySolarPanel getInventorySolarPanel() {
        return this.inventorySolarPanel;
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }
}

