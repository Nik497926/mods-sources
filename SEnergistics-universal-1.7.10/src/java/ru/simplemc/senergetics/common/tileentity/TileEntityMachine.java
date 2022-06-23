/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity;

import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.UpgradableProperty;
import java.util.Collections;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.tileentity.TileEntityNetworkingEnergySink;

public abstract class TileEntityMachine<T extends TileEntity>
extends TileEntityNetworkingEnergySink
implements IUpgradableBlock,
ISidedInventory {
    protected final InventoryTileEntity<T> inventory = this.createInventory();
    protected int ticksToProcess;
    protected double energyUsagePerTick;
    protected boolean isActive = false;
    private boolean isChanged = false;

    protected abstract InventoryTileEntity<T> createInventory();

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (this.isChanged) {
            this.markDirty();
            this.isChanged = false;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.inventory.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory.readFromNBT(compound);
    }

    @Override
    protected void writeDescriptionPacket(NBTTagCompound compound) {
        super.writeDescriptionPacket(compound);
        compound.setDouble("energyUsagePerTick", this.energyUsagePerTick);
        compound.setBoolean("isActive", this.isActive);
        this.isActive = false;
    }

    @Override
    protected void readDescriptionPacket(NBTTagCompound compound) {
        super.readDescriptionPacket(compound);
        this.energyUsagePerTick = compound.getDouble("energyUsagePerTick");
        boolean commonIsActive = compound.getBoolean("isActive");
        if (this.isActive != commonIsActive) {
            this.setForceRenderUpdate(true);
        }
        this.isActive = commonIsActive;
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
        return false;
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

    public Set<UpgradableProperty> getUpgradableProperties() {
        return Collections.emptySet();
    }

    public double getEnergy() {
        return super.getEnergyStored();
    }

    @Override
    public boolean useEnergy(double amount) {
        this.isActive = super.useEnergy(amount);
        return this.isActive;
    }

    public InventoryTileEntity<T> getInventory() {
        return this.inventory;
    }

    public int getTicksToProcess() {
        return this.ticksToProcess;
    }

    public double getEnergyUsagePerTick() {
        return this.energyUsagePerTick;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public boolean isChanged() {
        return this.isChanged;
    }

    public void setTicksToProcess(int ticksToProcess) {
        this.ticksToProcess = ticksToProcess;
    }

    public void setEnergyUsagePerTick(double energyUsagePerTick) {
        this.energyUsagePerTick = energyUsagePerTick;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }
}

