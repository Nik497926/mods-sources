/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process.smart;

import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.tileentity.component.process.AbstractProcessHolder;
import ru.simplemc.senergetics.common.tileentity.component.process.smart.SmartMachineProcess;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMachine;
import ru.simplemc.senergetics.util.InventoryUtils;

public class SmartMachineProcessHolder<T extends TileEntitySmartMachine<T>>
extends AbstractProcessHolder<T, SmartMachineProcess<T>> {
    public SmartMachineProcessHolder(T machine) {
        super(machine, SmartMachineProcess::new, () -> new SmartMachineProcess[8]);
    }

    @Override
    public void removeProcess(SmartMachineProcess<T> process) {
        super.removeProcess(process);
        if (this.activeProcesses == 0) {
            this.sortNotProcessedItems();
        }
    }

    private void sortNotProcessedItems() {
        for (int i = 0; i < 8; ++i) {
            ItemStack itemStackInSlot = ((TileEntitySmartMachine)this.machine).getStackInSlot(i);
            for (int j = i + 1; j < 8; ++j) {
                ItemStack itemStackInNextSlot = ((TileEntitySmartMachine)this.machine).getStackInSlot(j);
                if (itemStackInSlot == null && itemStackInNextSlot != null) {
                    itemStackInSlot = itemStackInNextSlot;
                    ((TileEntitySmartMachine)this.machine).setInventorySlotContents(i, itemStackInSlot);
                    ((TileEntitySmartMachine)this.machine).setInventorySlotContents(j, null);
                    continue;
                }
                if (itemStackInSlot == null || itemStackInNextSlot == null || !InventoryUtils.isStackEqualStrict(itemStackInSlot, itemStackInNextSlot) || itemStackInSlot.stackSize >= itemStackInSlot.getMaxStackSize()) continue;
                int transfer = Math.min(itemStackInNextSlot.stackSize, itemStackInSlot.getMaxStackSize() - itemStackInSlot.stackSize);
                itemStackInSlot.stackSize += transfer;
                itemStackInNextSlot.stackSize -= transfer;
                if (itemStackInNextSlot.stackSize > 0) continue;
                ((TileEntitySmartMachine)this.machine).setInventorySlotContents(j, null);
            }
        }
    }
}

