/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.upgrade.IUpgradeItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventorySmartMachine;
import ru.simplemc.senergetics.common.inventory.container.ContainerTileEntity;
import ru.simplemc.senergetics.common.inventory.slot.SlotMachineUpgrade;
import ru.simplemc.senergetics.common.tileentity.component.process.smart.SmartMachineProcessHolder;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMachine;

public class ContainerSmartMachine<T extends TileEntitySmartMachine<T>>
extends ContainerTileEntity<T> {
    private final int[] processStatuses = new int[8];

    public ContainerSmartMachine(InventorySmartMachine<T> inventory, EntityPlayer player) {
        super(inventory, player);
    }

    @Override
    protected void bindMachineInventory() {
        int i;
        for (i = 0; i < 4; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventory, i, 8, 23 + i * 18));
        }
        for (i = 4; i < 8; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventory, i, 75, 23 + i % 4 * 18));
        }
        for (i = 8; i < 12; ++i) {
            this.addSlotToContainer(new SlotMachineUpgrade((InventorySmartMachine)this.inventory, i, 152, 23 + i % 4 * 18));
        }
        for (i = 12; i < 16; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventory, i, 52, 23 + i % 4 * 18));
        }
        for (i = 16; i < 20; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventory, i, 119, 23 + i % 4 * 18));
        }
    }

    @Override
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
            } else if (itemStackInSlot.getItem() instanceof IUpgradeItem) {
                IUpgradeItem upgradeItem = (IUpgradeItem)itemStackInSlot.getItem();
                if (!upgradeItem.isSuitableFor(itemStackInSlot, ((TileEntitySmartMachine)((Object)this.inventory.getTileEntity())).getUpgradableProperties())) {
                    return null;
                }
                if (!this.mergeItemStack(itemStackInSlot, 8, 12, false)) {
                    return null;
                }
            } else {
                if (((TileEntitySmartMachine)((Object)this.inventory.getTileEntity())).getMachineProcessOutput(itemStack.copy()) == null) {
                    return null;
                }
                int slotEmptyNext = 0;
                for (int processId = 0; processId < 8; ++processId) {
                    if (this.processStatuses[processId] >= 1) continue;
                    slotEmptyNext = processId;
                    break;
                }
                if (!this.mergeItemStack(itemStackInSlot, slotEmptyNext, 8, false)) {
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

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        TileEntitySmartMachine tileEntitySmartMachine = (TileEntitySmartMachine)((Object)this.inventory.getTileEntity());
        SmartMachineProcessHolder processesHolder = tileEntitySmartMachine.getProcessesHolder();
        for (int i = 0; i < this.processStatuses.length; ++i) {
            this.processStatuses[i] = processesHolder.getProcessProgressPercent(i);
        }
        for (Object o : this.crafters) {
            ICrafting crafter = (ICrafting)o;
            for (int i = 0; i < this.processStatuses.length; ++i) {
                crafter.sendProgressBarUpdate((Container)this, i, this.processStatuses[i]);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int index, int value) {
        if (this.processStatuses.length > index) {
            this.processStatuses[index] = value;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.inventory.isUseableByPlayer(this.player);
    }

    public int[] getProcessStatuses() {
        return this.processStatuses;
    }
}

