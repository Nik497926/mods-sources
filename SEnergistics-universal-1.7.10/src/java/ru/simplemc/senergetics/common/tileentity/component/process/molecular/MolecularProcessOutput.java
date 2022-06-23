/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process.molecular;

import net.minecraft.item.ItemStack;

public class MolecularProcessOutput {
    private final ItemStack itemStackInput;
    private final ItemStack itemStackOutput;
    private final double energyRequired;

    public MolecularProcessOutput(ItemStack itemStackInput, ItemStack itemStackOutput, double energyRequired) {
        this.itemStackInput = itemStackInput;
        this.itemStackOutput = itemStackOutput;
        this.energyRequired = energyRequired;
    }

    public ItemStack getItemStackInput() {
        return this.itemStackInput;
    }

    public ItemStack getItemStackOutput() {
        return this.itemStackOutput;
    }

    public double getEnergyRequired() {
        return this.energyRequired;
    }
}

