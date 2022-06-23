/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.inventory.container.ContainerTileEntity;
import ru.simplemc.senergetics.common.inventory.slot.SlotMachineUpgrade;
import ru.simplemc.senergetics.common.inventory.slot.SlotSoulSand;
import ru.simplemc.senergetics.common.inventory.slot.SlotWitherSkull;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityNetherStarCollector;

public class ContainerNetherStarCollector
extends ContainerTileEntity<TileEntityNetherStarCollector> {
    private int processingTicksPercent;

    public ContainerNetherStarCollector(InventoryTileEntity<TileEntityNetherStarCollector> inventory, EntityPlayer player) {
        super(inventory, player);
    }

    @Override
    protected void bindMachineInventory() {
        this.addSlotToContainer(new SlotWitherSkull(this.inventory, 0, 26, 21));
        this.addSlotToContainer(new SlotWitherSkull(this.inventory, 1, 44, 21));
        this.addSlotToContainer(new SlotWitherSkull(this.inventory, 2, 62, 21));
        this.addSlotToContainer(new SlotSoulSand(this.inventory, 3, 26, 39));
        this.addSlotToContainer(new SlotSoulSand(this.inventory, 4, 44, 39));
        this.addSlotToContainer(new SlotSoulSand(this.inventory, 5, 62, 39));
        this.addSlotToContainer(new SlotSoulSand(this.inventory, 6, 44, 57));
        this.addSlotToContainer(new Slot((IInventory)this.inventory, 7, 98, 21));
        this.addSlotToContainer(new Slot((IInventory)this.inventory, 8, 116, 21));
        this.addSlotToContainer(new Slot((IInventory)this.inventory, 9, 134, 21));
        this.addSlotToContainer(new Slot((IInventory)this.inventory, 10, 98, 39));
        this.addSlotToContainer(new Slot((IInventory)this.inventory, 11, 116, 39));
        this.addSlotToContainer(new Slot((IInventory)this.inventory, 12, 134, 39));
        this.addSlotToContainer(new Slot((IInventory)this.inventory, 13, 98, 57));
        this.addSlotToContainer(new Slot((IInventory)this.inventory, 14, 116, 57));
        this.addSlotToContainer(new Slot((IInventory)this.inventory, 15, 134, 57));
        this.addSlotToContainer(new SlotMachineUpgrade(this.inventory, 16, 53, 93));
        this.addSlotToContainer(new SlotMachineUpgrade(this.inventory, 17, 71, 93));
        this.addSlotToContainer(new SlotMachineUpgrade(this.inventory, 18, 89, 93));
        this.addSlotToContainer(new SlotMachineUpgrade(this.inventory, 19, 107, 93));
    }

    @Override
    protected void bindPlayerInventory() {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)this.player.inventory, j + i * 9 + 9, 8 + j * 18, 122 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.player.inventory, i, 8 + i * 18, 180));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);
        ItemStack itemStack = null;
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStackInSlot = slot.getStack();
            itemStack = itemStackInSlot.copy();
            int inventoryRange = this.inventorySlots.size() - 36;
            if (slotIndex < inventoryRange ? !this.mergeItemStack(itemStackInSlot, inventoryRange, this.inventorySlots.size(), true) : (SlotWitherSkull.isMatches(itemStack) ? !this.mergeItemStack(itemStackInSlot, 0, 3, false) : SlotSoulSand.isMatches(itemStack) && !this.mergeItemStack(itemStackInSlot, 3, 7, false))) {
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

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        double ticksToProcess = ((TileEntityNetherStarCollector)((Object)this.inventory.getTileEntity())).getTicksToProcess();
        double processingTicks = ((TileEntityNetherStarCollector)((Object)this.inventory.getTileEntity())).getProcessingTicks();
        int processingTicksPercent = Math.min(100, Math.max(0, (int)(processingTicks / ticksToProcess * 100.0)));
        for (Object o : this.crafters) {
            ((ICrafting)o).sendProgressBarUpdate((Container)this, 0, processingTicksPercent);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int index, int value) {
        if (index == 0) {
            this.processingTicksPercent = value;
        }
    }

    public int getProcessingTicksPercent() {
        return this.processingTicksPercent;
    }
}

