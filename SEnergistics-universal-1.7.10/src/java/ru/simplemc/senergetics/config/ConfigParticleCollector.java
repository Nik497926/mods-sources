/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.config;

public class ConfigParticleCollector {
    private double energyGenerated;
    private double energyCapacity;

    public double getEnergyGenerated() {
        return this.energyGenerated;
    }

    public double getEnergyCapacity() {
        return this.energyCapacity;
    }

    public ConfigParticleCollector(double energyGenerated, double energyCapacity) {
        this.energyGenerated = energyGenerated;
        this.energyCapacity = energyCapacity;
    }
}

