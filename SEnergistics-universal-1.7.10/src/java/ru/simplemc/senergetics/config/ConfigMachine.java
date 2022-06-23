/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.config;

public class ConfigMachine {
    private int ticksForProcess;
    private double energyUsage;
    private double energyCapacity;

    public int getTicksForProcess() {
        return this.ticksForProcess;
    }

    public double getEnergyUsage() {
        return this.energyUsage;
    }

    public double getEnergyCapacity() {
        return this.energyCapacity;
    }

    public ConfigMachine(int ticksForProcess, double energyUsage, double energyCapacity) {
        this.ticksForProcess = ticksForProcess;
        this.energyUsage = energyUsage;
        this.energyCapacity = energyCapacity;
    }
}

