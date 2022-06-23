/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.machine;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;
import ru.simplemc.senergetics.common.tileentity.component.process.molecular.MolecularProcess;
import ru.simplemc.senergetics.common.tileentity.component.process.molecular.MolecularProcessHolder;
import ru.simplemc.senergetics.common.tileentity.component.process.molecular.MolecularProcessOutput;
import ru.simplemc.senergetics.recipe.MolecularCollectorRecipe;

public class TileEntityMolecularCollector
extends TileEntityMachine<TileEntityMolecularCollector> {
    private static final int[] ACCESSIBLE_SLOTS_FROM_SIDE = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final MolecularProcessHolder processHolder = new MolecularProcessHolder(this);
    private double energyInjected;
    private double energyForProcess;

    @Override
    protected InventoryTileEntity<TileEntityMolecularCollector> createInventory() {
        return new InventoryTileEntity<TileEntityMolecularCollector>(this, 10);
    }

    public int[] getAccessibleSlotsFromSide(int side) {
        return ACCESSIBLE_SLOTS_FROM_SIDE;
    }

    public boolean canInsertItem(int index, ItemStack itemStack, int side) {
        return index < 5 && this.inventory.isItemValidForSlot(index, itemStack);
    }

    public boolean canExtractItem(int index, ItemStack itemStack, int side) {
        return index > 4 && this.inventory.isItemValidForSlot(index, itemStack);
    }

    @Override
    protected void processCommonTick() {
        int processesNotPowered = 0;
        if (this.processHolder.hasActiveProcesses()) {
            for (MolecularProcess process : (MolecularProcess[])this.processHolder.getProcesses()) {
                if (process == null || process.isPowered()) continue;
                ++processesNotPowered;
            }
            this.energyForProcess = processesNotPowered > 0 ? Math.max(0.0, this.getEnergyStored() / (double)processesNotPowered) : 0.0;
            this.processHolder.processCommonTick();
        }
        if (processesNotPowered == 0) {
            this.energyInjected = 0.0;
            this.energyForProcess = 0.0;
        }
        if (this.canTick(10)) {
            for (int slot = 0; slot < 5; ++slot) {
                ItemStack itemStackInSlot;
                if (!this.processHolder.isProcessSlotIsEmpty(slot) || (itemStackInSlot = this.inventory.getStackInSlot(slot)) == null) continue;
                MolecularProcessOutput processOutput = this.getMachineProcessOutput(itemStackInSlot);
                if (itemStackInSlot.stackSize == 0) {
                    this.inventory.setInventorySlotContents(slot, null);
                }
                if (processOutput == null) continue;
                this.setEnergyCapacity(this.getEnergyCapacity() + processOutput.getEnergyRequired());
                this.processHolder.addProcess(new MolecularProcess(this, processOutput, slot, slot + 5));
            }
        }
    }

    @Override
    protected void processClientTick() {
    }

    @Nullable
    protected MolecularProcessOutput getMachineProcessOutput(ItemStack itemStackInSlot) {
        Optional<MolecularCollectorRecipe> optionalRecipe = MolecularCollectorRecipe.getRecipeForItemStack(itemStackInSlot);
        if (optionalRecipe.isPresent()) {
            MolecularCollectorRecipe recipe = optionalRecipe.get();
            int outputMultiplier = itemStackInSlot.stackSize / recipe.getInputItemStack().stackSize;
            ItemStack itemStackInput = itemStackInSlot.copy();
            ItemStack itemStackOutput = recipe.getOutputItemStack();
            itemStackInput.stackSize = recipe.getInputItemStack().stackSize * outputMultiplier;
            itemStackOutput.stackSize *= outputMultiplier;
            itemStackInSlot.stackSize -= itemStackInput.stackSize;
            return new MolecularProcessOutput(itemStackInput, itemStackOutput, recipe.getEnergyUsage() * (double)outputMultiplier);
        }
        return null;
    }

    @Override
    public double injectEnergy(ForgeDirection from, double amount, double voltage) {
        if (this.processHolder.hasActiveProcesses()) {
            this.energyInjected = amount;
            return super.injectEnergy(from, this.energyInjected, voltage);
        }
        return amount;
    }

    @Override
    protected void writeDescriptionPacket(NBTTagCompound compound) {
        super.writeDescriptionPacket(compound);
        compound.setDouble("energyInjected", this.energyInjected);
        compound.setDouble("energyForProcess", this.energyForProcess);
    }

    @Override
    protected void readDescriptionPacket(NBTTagCompound compound) {
        super.readDescriptionPacket(compound);
        this.energyInjected = compound.getDouble("energyInjected");
        this.energyForProcess = compound.getDouble("energyForProcess");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.processHolder.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.processHolder.readFromNBT(compound);
    }

    public MolecularProcessHolder getProcessHolder() {
        return this.processHolder;
    }

    public double getEnergyInjected() {
        return this.energyInjected;
    }

    public double getEnergyForProcess() {
        return this.energyForProcess;
    }
}

