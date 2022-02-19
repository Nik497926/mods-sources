/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.barter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BarterInvHolder
implements IInventory {
    public ItemStack[] inventory = new ItemStack[this.getSizeInventory()];

    public int getSizeInventory() {
        return 36;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.inventory[slot];
    }

    public ItemStack decrStackSize(int slot, int amount) {
        if (this.inventory[slot] != null) {
            if (this.inventory[slot].stackSize <= amount) {
                ItemStack itemstack = this.inventory[slot];
                this.inventory[slot] = null;
                this.markDirty();
                return itemstack;
            }
            ItemStack itemstack = this.inventory[slot].splitStack(amount);
            if (this.inventory[slot].stackSize == 0) {
                this.inventory[slot] = null;
            }
            this.markDirty();
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        if (this.inventory[slot] != null) {
            ItemStack itemstack = this.inventory[slot];
            this.inventory[slot] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int slot, ItemStack iStack) {
        this.inventory[slot] = iStack;
        if (iStack != null && iStack.stackSize > this.getInventoryStackLimit()) {
            iStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInventoryName() {
        return null;
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public void markDirty() {
    }

    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isItemValidForSlot(int index, ItemStack item) {
        return false;
    }
}

