/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process.smart;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import ru.simplemc.senergetics.common.tileentity.component.process.AbstractProcess;
import ru.simplemc.senergetics.common.tileentity.component.process.smart.SmartMachineProcessOutput;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMachine;

public class SmartMachineProcess<T extends TileEntitySmartMachine<T>>
extends AbstractProcess<T> {
    private SmartMachineProcessOutput processOutput;
    private int tickCounter;

    public SmartMachineProcess(T machine, SmartMachineProcessOutput processOutput, int slotInput, int slotOutput) {
        super(slotInput, slotOutput, machine);
        this.processOutput = processOutput;
    }

    @Override
    public boolean isPowered() {
        return ((TileEntitySmartMachine)this.machine).useEnergy(((TileEntitySmartMachine)this.machine).getEnergyUsagePerTick());
    }

    @Override
    public boolean isFinished() {
        if (this.tickCounter++ >= this.getProcessWorkingTicks() && !((TileEntitySmartMachine)this.machine).isInvalid()) {
            this.processOutput.getItemStacksOutput().removeIf(this::tryToExportOutputItemStack);
        } else if (!this.isPowered()) {
            return false;
        }
        return this.processOutput.getItemStacksOutput().isEmpty();
    }

    private int getProcessWorkingTicks() {
        return ((TileEntitySmartMachine)this.machine).getTicksToProcess() * this.processOutput.getOutputMultiplier();
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
        compound.setInteger("tickCounter", this.tickCounter);
        compound.setInteger("outputMultiplier", this.processOutput.getOutputMultiplier());
        compound.setTag("itemStackInput", (NBTBase)this.processOutput.getItemStackInput().writeToNBT(new NBTTagCompound()));
        List<ItemStack> itemStacksOutput = this.processOutput.getItemStacksOutput();
        NBTTagList itemsList = new NBTTagList();
        for (int i = 0; i < itemStacksOutput.size(); ++i) {
            ItemStack itemStack = itemStacksOutput.get(i);
            if (itemStack == null) continue;
            NBTTagCompound compoundStack = new NBTTagCompound();
            compoundStack.setInteger("index", i);
            itemStack.writeToNBT(compoundStack);
            itemsList.appendTag((NBTBase)compoundStack);
        }
        compound.setTag("itemStacksOutput", (NBTBase)itemsList);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ItemStack itemStackInput = ItemStack.loadItemStackFromNBT((NBTTagCompound)compound.getCompoundTag("itemStackInput"));
        int outputMultiplier = compound.getInteger("outputMultiplier");
        ArrayList<ItemStack> itemStacksOutput = new ArrayList<ItemStack>();
        NBTTagList tagList = compound.getTagList("itemStacksOutput", 10);
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound stackTag = tagList.getCompoundTagAt(i);
            itemStacksOutput.add(stackTag.getInteger("index"), ItemStack.loadItemStackFromNBT((NBTTagCompound)stackTag));
        }
        this.tickCounter = compound.getInteger("tickCounter");
        this.processOutput = new SmartMachineProcessOutput(itemStacksOutput, itemStackInput, outputMultiplier);
    }

    public SmartMachineProcessOutput getProcessOutput() {
        return this.processOutput;
    }

    public int getTickCounter() {
        return this.tickCounter;
    }

    public SmartMachineProcess() {
    }
}

