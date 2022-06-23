/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.config;

public class ConfigLavaGeneratorRecipe {
    private String inputItemName;
    private int inputItemCount;
    private int lavaGeneration;

    public String getInputItemName() {
        return this.inputItemName;
    }

    public int getInputItemCount() {
        return this.inputItemCount;
    }

    public int getLavaGeneration() {
        return this.lavaGeneration;
    }

    public ConfigLavaGeneratorRecipe(String inputItemName, int inputItemCount, int lavaGeneration) {
        this.inputItemName = inputItemName;
        this.inputItemCount = inputItemCount;
        this.lavaGeneration = lavaGeneration;
    }
}

