/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process.molecular;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import ru.simplemc.senergetics.common.tileentity.component.process.AbstractProcess;
import ru.simplemc.senergetics.common.tileentity.component.process.molecular.MolecularProcessOutput;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityMolecularCollector;

public class MolecularProcess
extends AbstractProcess<TileEntityMolecularCollector> {
    private MolecularProcessOutput output;
    private double energyUsed;

    public MolecularProcess(TileEntityMolecularCollector machine, MolecularProcessOutput output, int slotInput, int slotOutput) {
        super(slotInput, slotOutput, machine);
        this.output = output;
    }

    @Override
    public boolean isPowered() {
        return this.energyUsed >= this.output.getEnergyRequired();
    }

    @Override
    public boolean isFinished() {
        if (!this.isPowered()) {
            double energyForProcess = ((TileEntityMolecularCollector)this.machine).getEnergyForProcess();
            if (((TileEntityMolecularCollector)this.machine).useEnergy(energyForProcess)) {
                this.energyUsed += energyForProcess;
            }
            if (this.isPowered()) {
                ((TileEntityMolecularCollector)this.machine).setEnergyCapacity(((TileEntityMolecularCollector)this.machine).getEnergyCapacity() - this.output.getEnergyRequired());
            }
        } else {
            return this.tryToExportOutputItemStack(this.output.getItemStackOutput());
        }
        return false;
    }

    @Override
    public int getProcessPercent() {
        if (this.energyUsed >= this.output.getEnergyRequired()) {
            return 100;
        }
        return Math.max(1, (int)(this.energyUsed / this.output.getEnergyRequired() * 100.0));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setDouble("energyUsed", this.energyUsed);
        compound.setDouble("energyRequired", this.output.getEnergyRequired());
        compound.setTag("itemStackInput", (NBTBase)this.output.getItemStackInput().writeToNBT(new NBTTagCompound()));
        compound.setTag("itemStackOutput", (NBTBase)this.output.getItemStackOutput().writeToNBT(new NBTTagCompound()));
        compound.setInteger("itemStackOutputStackSize", this.output.getItemStackOutput().stackSize);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.energyUsed = compound.getDouble("energyUsed");
        double energyRequired = compound.getDouble("energyRequired");
        ItemStack itemStackInput = ItemStack.loadItemStackFromNBT((NBTTagCompound)compound.getCompoundTag("itemStackInput"));
        ItemStack itemStackOutput = ItemStack.loadItemStackFromNBT((NBTTagCompound)compound.getCompoundTag("itemStackOutput"));
        itemStackOutput.stackSize = compound.getInteger("itemStackOutputStackSize");
        this.output = new MolecularProcessOutput(itemStackInput, itemStackOutput, energyRequired);
        ((TileEntityMolecularCollector)this.machine).setEnergyCapacity(((TileEntityMolecularCollector)this.machine).getEnergyCapacity() + energyRequired);
    }

    public MolecularProcessOutput getOutput() {
        return this.output;
    }

    public double getEnergyUsed() {
        return this.energyUsed;
    }

    public MolecularProcess() {
    }
}

