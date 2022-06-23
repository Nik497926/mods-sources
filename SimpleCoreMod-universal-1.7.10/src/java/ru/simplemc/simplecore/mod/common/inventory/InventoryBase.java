/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ru.simplemc.simplecore.mod.util.ItemStackHelper;

public abstract class InventoryBase
implements IInventory {
    private final ItemStack[] inventoryContents;

    public InventoryBase(int slotsCount) {
        this.inventoryContents = new ItemStack[slotsCount];
    }

    public ItemStack getStackInSlot(int index) {
        return index >= 0 && index < this.inventoryContents.length ? this.inventoryContents[index] : null;
    }

    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemStack = this.getStackInSlot(index);
        if (itemStack != null) {
            if (this.inventoryContents[index].stackSize <= count) {
                itemStack = this.inventoryContents[index];
                this.inventoryContents[index] = null;
            } else {
                itemStack = this.inventoryContents[index].splitStack(count);
                if (this.inventoryContents[index].stackSize == 0) {
                    this.inventoryContents[index] = null;
                }
            }
            this.markDirty();
        }
        return itemStack;
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    public ItemStack addItem(ItemStack itemStack, int slotsRange) {
        ItemStack itemStackToAdd = itemStack.copy();
        for (int i = 0; i < slotsRange; ++i) {
            int j;
            int k;
            ItemStack itemStackInSlot = this.getStackInSlot(i);
            if (itemStackInSlot == null) {
                this.setInventorySlotContents(i, itemStackToAdd);
                this.markDirty();
                return null;
            }
            if (!ItemStack.areItemStacksEqual((ItemStack)itemStackInSlot, (ItemStack)itemStackToAdd) || (k = Math.min(itemStackToAdd.stackSize, (j = Math.min(this.getInventoryStackLimit(), itemStackInSlot.getMaxStackSize())) - itemStackInSlot.stackSize)) <= 0) continue;
            itemStackInSlot.stackSize += k;
            itemStackToAdd.stackSize -= k;
            if (itemStackToAdd.stackSize != 0) continue;
            this.markDirty();
            return null;
        }
        return itemStackToAdd;
    }

    public ItemStack addItem(ItemStack itemStack) {
        return this.addItem(itemStack, this.inventoryContents.length);
    }

    public boolean canAddItem(ItemStack itemStack) {
        return this.canAddItem(itemStack, this.inventoryContents.length);
    }

    public boolean canAddItem(ItemStack itemStack, int slotsRange) {
        ItemStack itemStackToAdd = itemStack.copy();
        for (int i = 0; i < slotsRange; ++i) {
            int j;
            int k;
            ItemStack itemStackInSlot = this.getStackInSlot(i);
            if (itemStackInSlot == null) {
                return true;
            }
            if (!ItemStack.areItemStacksEqual((ItemStack)itemStackInSlot, (ItemStack)itemStackToAdd) || (k = Math.min(itemStackToAdd.stackSize, (j = Math.min(this.getInventoryStackLimit(), itemStackInSlot.getMaxStackSize())) - itemStackInSlot.stackSize)) <= 0) continue;
            return true;
        }
        return false;
    }

    public ItemStack removeAndGetFromSlot(int index) {
        ItemStack itemStackInSlot = this.inventoryContents[index];
        if (itemStackInSlot != null) {
            this.inventoryContents[index] = null;
        }
        return itemStackInSlot;
    }

    public void setInventorySlotContents(int index, ItemStack itemStack) {
        this.inventoryContents[index] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    public String getInventoryName() {
        return null;
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getSizeInventory() {
        return this.inventoryContents.length;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isItemValidForSlot(int index, ItemStack itemStack) {
        return true;
    }

    public void readFromNBT(NBTTagCompound compound) {
        ItemStackHelper.loadAllItems(compound, this.inventoryContents);
    }

    public void writeToNBT(NBTTagCompound compound) {
        ItemStackHelper.saveAllItems(compound, this.inventoryContents);
    }
}

