/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.tileentity;

import javax.annotation.Nonnull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.inventory.InventoryTileEntityTradeStation;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityBase;

public class TileEntityTradeStation
extends TileEntityBase
implements ISidedInventory {
    private final InventoryTileEntityTradeStation inventory = new InventoryTileEntityTradeStation(this);
    public final TradeData data = new TradeData();
    private boolean alreadyUsed = false;

    public void updateEntity() {
        if (this.worldObj.isRemote) {
            this.data.updateAtClient(this);
        } else {
            this.data.updateAtServer(this);
        }
    }

    public void readFromNBT(@Nonnull NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory.readFromNBT(compound);
        this.data.readFromNBT(compound);
    }

    public void writeToNBT(@Nonnull NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.inventory.writeToNBT(compound);
        this.data.writeToNBT(compound);
    }

    public int[] getAccessibleSlotsFromSide(int side) {
        return this.inventory.getAccessibleSlotsFromSide(side);
    }

    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return this.inventory.canInsertItem(slot, itemStack, side);
    }

    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return this.inventory.canExtractItem(slot, itemStack, side);
    }

    public int getSizeInventory() {
        return this.inventory.getSizeInventory();
    }

    public ItemStack getStackInSlot(int slot) {
        return this.inventory.getStackInSlot(slot);
    }

    public ItemStack decrStackSize(int slot, int amount) {
        return this.inventory.decrStackSize(slot, amount);
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        return this.inventory.getStackInSlotOnClosing(slot);
    }

    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.inventory.setInventorySlotContents(slot, itemStack);
    }

    public String getInventoryName() {
        return this.inventory.getInventoryName();
    }

    public boolean hasCustomInventoryName() {
        return this.inventory.hasCustomInventoryName();
    }

    public int getInventoryStackLimit() {
        return this.inventory.getInventoryStackLimit();
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.inventory.isUseableByPlayer(player);
    }

    public void openInventory() {
        this.inventory.openInventory();
    }

    public void closeInventory() {
        this.inventory.closeInventory();
    }

    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return this.inventory.isItemValidForSlot(slot, itemStack);
    }

    public InventoryTileEntityTradeStation getInventory() {
        return this.inventory;
    }

    public TradeData getData() {
        return this.data;
    }

    public boolean isAlreadyUsed() {
        return this.alreadyUsed;
    }

    public void setAlreadyUsed(boolean alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }
}

