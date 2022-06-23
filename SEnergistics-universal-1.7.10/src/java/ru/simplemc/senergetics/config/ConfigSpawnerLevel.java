/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.config;

public class ConfigSpawnerLevel {
    private int processTicks;
    private double energyUsage;

    public int getProcessTicks() {
        return this.processTicks;
    }

    public double getEnergyUsage() {
        return this.energyUsage;
    }

    public ConfigSpawnerLevel(int processTicks, double energyUsage) {
        this.processTicks = processTicks;
        this.energyUsage = energyUsage;
    }
}

