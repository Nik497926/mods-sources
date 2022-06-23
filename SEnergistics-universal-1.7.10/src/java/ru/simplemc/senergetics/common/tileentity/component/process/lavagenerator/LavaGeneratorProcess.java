/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process.lavagenerator;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import ru.simplemc.senergetics.common.tileentity.component.process.AbstractProcess;
import ru.simplemc.senergetics.common.tileentity.component.process.lavagenerator.LavaGeneratorProcessOutput;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityLavaGenerator;

public class LavaGeneratorProcess
extends AbstractProcess<TileEntityLavaGenerator> {
    private LavaGeneratorProcessOutput output;
    private int tickCounter;

    public LavaGeneratorProcess(TileEntityLavaGenerator machine, LavaGeneratorProcessOutput output, int slotInput) {
        super(slotInput, 0, machine);
        this.output = output;
    }

    private boolean isTicksRemain() {
        return this.tickCounter++ >= this.getProcessWorkingTicks();
    }

    private int getProcessWorkingTicks() {
        return ((TileEntityLavaGenerator)this.machine).getTicksToProcess() * this.output.getOutputMultiplier();
    }

    @Override
    public boolean isPowered() {
        return ((TileEntityLavaGenerator)this.machine).useEnergy(((TileEntityLavaGenerator)this.machine).getEnergyUsagePerTick());
    }

    @Override
    public boolean isFinished() {
        if (this.isTicksRemain() && !((TileEntityLavaGenerator)this.machine).isInvalid()) {
            return this.tryToExportOutputFluidStack(this.output.getFluidStackOutput());
        }
        return false;
    }

    private boolean tryToExportOutputFluidStack(FluidStack fluidStack) {
        if (fluidStack == null || fluidStack.amount == 0) {
            return true;
        }
        return ((TileEntityLavaGenerator)this.machine).getFluidTank().fill(fluidStack, true) == fluidStack.amount;
    }

    @Override
    public int getProcessPercent() {
        int ticksToFinish = this.getProcessWorkingTicks();
        if (this.tickCounter >= ticksToFinish) {
            return 100;
        }
        return Math.max(1, (int)((double)this.tickCounter / (double)ticksToFinish * 100.0));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("itemStackInput", (NBTBase)this.output.getItemStackInput().writeToNBT(new NBTTagCompound()));
        compound.setTag("fluidStackOutput", (NBTBase)this.output.getFluidStackOutput().writeToNBT(new NBTTagCompound()));
        compound.setInteger("outputMultiplier", this.output.getOutputMultiplier());
        compound.setInteger("tickCounter", this.tickCounter);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ItemStack itemStackInput = ItemStack.loadItemStackFromNBT((NBTTagCompound)compound.getCompoundTag("itemStackInput"));
        FluidStack fluidStackOutput = FluidStack.loadFluidStackFromNBT((NBTTagCompound)compound.getCompoundTag("fluidStackOutput"));
        this.output = new LavaGeneratorProcessOutput(itemStackInput, fluidStackOutput, compound.getInteger("outputMultiplier"));
        this.tickCounter = compound.getInteger("tickCounter");
    }

    public LavaGeneratorProcess() {
    }
}

