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
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.inventory.container.ContainerTileEntity;
import ru.simplemc.senergetics.common.inventory.slot.SlotMachineUpgrade;
import ru.simplemc.senergetics.common.tileentity.component.process.lavagenerator.LavaGeneratorProcess;
import ru.simplemc.senergetics.common.tileentity.component.process.lavagenerator.LavaGeneratorProcessHolder;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityLavaGenerator;

public class ContainerLavaGenerator
extends ContainerTileEntity<TileEntityLavaGenerator> {
    private final int[] processStatuses;

    public ContainerLavaGenerator(InventoryTileEntity<TileEntityLavaGenerator> inventory, EntityPlayer player) {
        super(inventory, player);
        this.processStatuses = new int[((LavaGeneratorProcess[])inventory.getTileEntity().getProcessesHolder().getProcesses()).length];
    }

    @Override
    protected void bindMachineInventory() {
        int i;
        for (i = 0; i < 7; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventory, i, 8 + i * 18, 21));
        }
        for (i = 7; i < 11; ++i) {
            this.addSlotToContainer(new SlotMachineUpgrade(this.inventory, i, 152, 21 + i % 4 * 18));
        }
    }

    @Override
    protected void bindPlayerInventory() {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)this.player.inventory, j + i * 9 + 9, 8 + j * 18, 106 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.player.inventory, i, 8 + i * 18, 164));
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
                if (!upgradeItem.isSuitableFor(itemStackInSlot, ((TileEntityLavaGenerator)((Object)this.inventory.getTileEntity())).getUpgradableProperties())) {
                    return null;
                }
                if (!this.mergeItemStack(itemStackInSlot, 7, 11, false)) {
                    return null;
                }
            } else {
                if (((TileEntityLavaGenerator)((Object)this.inventory.getTileEntity())).getMachineProcessOutput(itemStack.copy()) == null) {
                    return null;
                }
                int slotEmptyNext = 0;
                for (int processId = 0; processId < 7; ++processId) {
                    if (this.processStatuses[processId] >= 1) continue;
                    slotEmptyNext = processId;
                    break;
                }
                if (!this.mergeItemStack(itemStackInSlot, slotEmptyNext, 7, false)) {
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
        LavaGeneratorProcessHolder processHolder = ((TileEntityLavaGenerator)((Object)this.inventory.getTileEntity())).getProcessesHolder();
        for (int i = 0; i < this.processStatuses.length; ++i) {
            this.processStatuses[i] = processHolder.getProcessProgressPercent(i);
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

    public int[] getProcessStatuses() {
        return this.processStatuses;
    }
}

