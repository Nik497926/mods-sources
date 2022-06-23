/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.config;

public class ConfigMolecularCollectorRecipe {
    private String inputItemName;
    private int inputItemCount;
    private String outputItemName;
    private int outputItemCount;
    private double energyUsage;

    public String getInputItemName() {
        return this.inputItemName;
    }

    public int getInputItemCount() {
        return this.inputItemCount;
    }

    public String getOutputItemName() {
        return this.outputItemName;
    }

    public int getOutputItemCount() {
        return this.outputItemCount;
    }

    public double getEnergyUsage() {
        return this.energyUsage;
    }

    public ConfigMolecularCollectorRecipe(String inputItemName, int inputItemCount, String outputItemName, int outputItemCount, double energyUsage) {
        this.inputItemName = inputItemName;
        this.inputItemCount = inputItemCount;
        this.outputItemName = outputItemName;
        this.outputItemCount = outputItemCount;
        this.energyUsage = energyUsage;
    }
}

