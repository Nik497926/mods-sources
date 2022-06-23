/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process.lavagenerator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class LavaGeneratorProcessOutput {
    private final ItemStack itemStackInput;
    private final FluidStack fluidStackOutput;
    private final int outputMultiplier;

    public LavaGeneratorProcessOutput(ItemStack itemStackInput, FluidStack fluidStackOutput, int outputMultiplier) {
        this.itemStackInput = itemStackInput;
        this.fluidStackOutput = fluidStackOutput;
        this.outputMultiplier = outputMultiplier;
    }

    public ItemStack getItemStackInput() {
        return this.itemStackInput;
    }

    public FluidStack getFluidStackOutput() {
        return this.fluidStackOutput;
    }

    public int getOutputMultiplier() {
        return this.outputMultiplier;
    }
}

