/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;
import ru.simplemc.senergetics.common.tileentity.component.process.AbstractProcess;
import ru.simplemc.senergetics.common.tileentity.component.process.IProcessFactory;
import ru.simplemc.senergetics.common.tileentity.component.process.IProcessStorageFactory;

public class AbstractProcessHolder<T extends TileEntityMachine<T>, P extends AbstractProcess<T>> {
    protected final P[] processes;
    protected final T machine;
    protected final IProcessFactory<P> processFactory;
    protected int activeProcesses = 0;

    public AbstractProcessHolder(T machine, IProcessFactory<P> processFactory, IProcessStorageFactory<P> processStorageFactory) {
        this.machine = machine;
        this.processFactory = processFactory;
        this.processes = (P[]) processStorageFactory.create();
    }

    public void processCommonTick() {
        if (this.activeProcesses > 0) {
            for (P process : this.processes) {
                if (process == null || !((AbstractProcess)process).isFinished()) continue;
                this.removeProcess(process);
            }
        }
    }

    public void addProcess(P process) {
        this.processes[((AbstractProcess)process).getSlotInput()] = process;
        ++this.activeProcesses;
        ((TileEntityMachine)((Object)this.machine)).setChanged(true);
    }

    public void removeProcess(P process) {
        this.processes[((AbstractProcess)process).getSlotInput()] = null;
        --this.activeProcesses;
        ((TileEntityMachine)((Object)this.machine)).setChanged(true);
    }

    public P getProcessForSlot(int slot) {
        return this.processes[slot];
    }

    public int getProcessProgressPercent(int slot) {
        P process = this.getProcessForSlot(slot);
        if (process != null) {
            return ((AbstractProcess)process).getProcessPercent();
        }
        return 0;
    }

    public boolean isProcessSlotIsEmpty(int slot) {
        return this.getProcessForSlot(slot) == null;
    }

    public boolean hasActiveProcesses() {
        return this.activeProcesses > 0;
    }

    public void writeToNBT(NBTTagCompound compound) {
        NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < this.processes.length; ++i) {
            P process = this.processes[i];
            if (process == null) continue;
            NBTTagCompound compoundProcess = new NBTTagCompound();
            compoundProcess.setInteger("ProcessId", i);
            ((AbstractProcess)process).writeToNBT(compoundProcess);
            tagList.appendTag((NBTBase)compoundProcess);
        }
        compound.setTag("Processes", (NBTBase)tagList);
    }

    public void readFromNBT(NBTTagCompound compound) {
        if (compound.hasKey("Processes")) {
            NBTTagList tagList = compound.getTagList("Processes", 10);
            for (int i = 0; i < tagList.tagCount(); ++i) {
                NBTTagCompound compoundProcess = tagList.getCompoundTagAt(i);
                AbstractProcess process = (AbstractProcess)this.processFactory.create();
                process.setMachine(this.machine);
                process.readFromNBT(compoundProcess);
                this.processes[compoundProcess.getInteger((String)"ProcessId")] = (P) process;
                ++this.activeProcesses;
            }
        }
    }

    public P[] getProcesses() {
        return this.processes;
    }

    public int getActiveProcesses() {
        return this.activeProcesses;
    }
}

