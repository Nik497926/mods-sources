/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;
import ru.simplemc.senergetics.util.InventoryUtils;

public abstract class AbstractProcess<T extends TileEntityMachine<?>> {
    protected int slotInput;
    protected int slotOutput;
    protected T machine;

    public AbstractProcess(int slotInput, int slotOutput, T machine) {
        this.slotInput = slotInput;
        this.slotOutput = slotOutput;
        this.machine = machine;
    }

    public abstract boolean isPowered();

    public abstract boolean isFinished();

    public abstract int getProcessPercent();

    protected boolean tryToExportOutputItemStack(ItemStack itemStack) {
        if (itemStack == null) {
            return true;
        }
        ItemStack itemStackInOutputSlot = ((TileEntityMachine)((Object)this.machine)).getStackInSlot(this.slotOutput);
        if (itemStackInOutputSlot == null) {
            itemStackInOutputSlot = itemStack.copy();
            if (itemStack.getMaxStackSize() < itemStackInOutputSlot.stackSize) {
                itemStackInOutputSlot.stackSize = itemStack.getMaxStackSize();
                itemStack.stackSize -= itemStackInOutputSlot.stackSize;
                if (itemStack.stackSize < 1) {
                    itemStack = null;
                }
            } else {
                itemStack = null;
            }
            this.setMachineInventorySlot(this.slotOutput, itemStackInOutputSlot);
        } else if (InventoryUtils.isStackEqualStrict(itemStackInOutputSlot, itemStack) && itemStackInOutputSlot.stackSize < itemStack.getMaxStackSize()) {
            int amountToTransfer = Math.min(itemStack.stackSize, itemStack.getMaxStackSize() - itemStackInOutputSlot.stackSize);
            if (amountToTransfer > 0) {
                itemStack.stackSize -= amountToTransfer;
                itemStackInOutputSlot.stackSize += amountToTransfer;
                this.setMachineInventorySlot(this.slotOutput, itemStackInOutputSlot);
            }
            if (itemStack.stackSize < 1) {
                itemStack = null;
            }
        }
        return itemStack == null;
    }

    protected void setMachineInventorySlot(int slot, ItemStack itemStack) {
        ((TileEntityMachine)((Object)this.machine)).setInventorySlotContents(slot, itemStack);
        ((TileEntityMachine)((Object)this.machine)).setChanged(true);
    }

    public void writeToNBT(NBTTagCompound compound) {
        compound.setInteger("slotInput", this.slotInput);
        compound.setInteger("slotOutput", this.slotOutput);
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.slotInput = compound.getInteger("slotInput");
        this.slotOutput = compound.getInteger("slotOutput");
    }

    public int getSlotInput() {
        return this.slotInput;
    }

    public int getSlotOutput() {
        return this.slotOutput;
    }

    public T getMachine() {
        return this.machine;
    }

    public void setSlotInput(int slotInput) {
        this.slotInput = slotInput;
    }

    public void setSlotOutput(int slotOutput) {
        this.slotOutput = slotOutput;
    }

    public void setMachine(T machine) {
        this.machine = machine;
    }

    public AbstractProcess() {
    }
}

