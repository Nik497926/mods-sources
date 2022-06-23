/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.slot.SlotSpawnerAttacked;
import ru.simplemc.senergetics.common.inventory.slot.SlotSpawnerInventory;
import ru.simplemc.senergetics.common.inventory.slot.SlotSpawnerMobScanner;
import ru.simplemc.senergetics.common.inventory.slot.SlotSpawnerUpgrade;
import ru.simplemc.senergetics.common.item.tool.ItemMobScanner;
import ru.simplemc.senergetics.common.item.tool.spawner.ItemSpawnerUpgrade;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntitySpawner;

public class ContainerSpawner
extends Container {
    public final TileEntitySpawner spawner;

    public ContainerSpawner(InventoryPlayer inventoryPlayer, TileEntitySpawner spawner) {
        this.spawner = spawner;
        this.bindSlots(inventoryPlayer);
    }

    private void bindSlots(InventoryPlayer inventoryPlayer) {
        int i;
        for (i = 0; i < 46; ++i) {
            if (i < 40) {
                this.addSlotToContainer(new SlotSpawnerInventory(this.spawner.inventory, i, 26 + i % 8 * 18, 19 + i / 8 * 18));
            }
            if (i == 40) {
                this.addSlotToContainer(new SlotSpawnerMobScanner(this.spawner.inventory, i, 9, 135));
            }
            if (i == 41) {
                this.addSlotToContainer(new SlotSpawnerAttacked(this.spawner.inventory, i, 9 + 18 * (i - 40), 135));
            }
            if (i == 42) {
                this.addSlotToContainer(new SlotSpawnerUpgrade(this.spawner.inventory, i, 18 + 18 * (i - 40), 135));
            }
            if (i == 43) {
                this.addSlotToContainer(new SlotSpawnerUpgrade(this.spawner.inventory, i, 18 + 18 * (i - 40), 135));
            }
            if (i == 44) {
                this.addSlotToContainer(new SlotSpawnerUpgrade(this.spawner.inventory, i, 18 + 18 * (i - 40), 135));
            }
            if (i != 45) continue;
            this.addSlotToContainer(new SlotSpawnerUpgrade(this.spawner.inventory, i, 18 + 18 * (i - 40), 135));
        }
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 161 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 219));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
        Slot slot = (Slot)this.inventorySlots.get(slotId);
        ItemStack itemStack = null;
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStackInSlot = slot.getStack();
            itemStack = itemStackInSlot.copy();
            int inventoryRange = this.inventorySlots.size() - 36;
            if (itemStack.getItem() instanceof ItemSpawnerUpgrade ? (slotId < inventoryRange ? !this.mergeItemStack(itemStackInSlot, inventoryRange, this.inventorySlots.size(), true) : !this.mergeItemStack(itemStackInSlot, 42, inventoryRange, false)) : (itemStack.getItem() instanceof ItemMobScanner ? (slotId < inventoryRange ? !this.mergeItemStack(itemStackInSlot, inventoryRange, this.inventorySlots.size(), true) : !this.mergeItemStack(itemStackInSlot, 40, inventoryRange - 5, false)) : (slotId < inventoryRange ? !this.mergeItemStack(itemStackInSlot, inventoryRange, this.inventorySlots.size(), true) : !this.mergeItemStack(itemStackInSlot, 41, inventoryRange - 4, false)))) {
                return null;
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

    public boolean canInteractWith(EntityPlayer player) {
        return this.spawner.inventory.isUseableByPlayer(player);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (Object o : this.crafters) {
            ICrafting crafter = (ICrafting)o;
            crafter.sendProgressBarUpdate((Container)this, 0, this.spawner.dataManager.currentEnergyPercent);
            crafter.sendProgressBarUpdate((Container)this, 1, this.spawner.dataManager.currentWorkingPercent);
            crafter.sendProgressBarUpdate((Container)this, 2, this.spawner.dataManager.currentExperiencePercent);
            crafter.sendProgressBarUpdate((Container)this, 3, this.spawner.dataManager.spawnerLevel);
            crafter.sendProgressBarUpdate((Container)this, 4, this.spawner.dataManager.standalone);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.spawner.dataManager.currentEnergyPercent = value;
        }
        if (id == 1) {
            this.spawner.dataManager.currentWorkingPercent = value;
        }
        if (id == 2) {
            this.spawner.dataManager.currentExperiencePercent = value;
        }
        if (id == 3) {
            this.spawner.dataManager.spawnerLevel = value;
        }
        if (id == 4) {
            this.spawner.dataManager.standalone = value;
        }
    }
}

