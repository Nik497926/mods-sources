/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process.smart;

import ic2.api.recipe.RecipeOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.item.ItemStack;

public class SmartMachineProcessOutput {
    private final List<ItemStack> itemStacksOutput;
    private final ItemStack itemStackInput;
    private final int outputMultiplier;

    public SmartMachineProcessOutput(RecipeOutput recipeOutput, ItemStack itemStackInput, int outputMultiplier) {
        this(SmartMachineProcessOutput.prepareItemStacksOutput(recipeOutput, outputMultiplier), itemStackInput, outputMultiplier);
    }

    public SmartMachineProcessOutput(ItemStack itemStackOutput, ItemStack itemStackInput, int outputMultiplier) {
        this(SmartMachineProcessOutput.prepareItemStacksOutput(itemStackOutput, outputMultiplier), itemStackInput, outputMultiplier);
    }

    public SmartMachineProcessOutput(List<ItemStack> itemStacksOutput, ItemStack itemStackInput, int outputMultiplier) {
        this.itemStacksOutput = itemStacksOutput;
        this.itemStackInput = itemStackInput;
        this.outputMultiplier = outputMultiplier;
    }

    private static List<ItemStack> prepareItemStacksOutput(ItemStack itemStackOutput, int outputMultiplier) {
        itemStackOutput.stackSize *= outputMultiplier;
        ArrayList<ItemStack> itemStacksOutput = new ArrayList<ItemStack>(1);
        itemStacksOutput.add(itemStackOutput);
        return itemStacksOutput;
    }

    private static List<ItemStack> prepareItemStacksOutput(RecipeOutput recipeOutput, int outputMultiplier) {
        if (recipeOutput != null && recipeOutput.items != null && !recipeOutput.items.isEmpty()) {
            ArrayList<ItemStack> itemStacksOutput = new ArrayList<ItemStack>(1);
            for (ItemStack itemStackOutput : recipeOutput.items) {
                itemStackOutput = itemStackOutput.copy();
                itemStackOutput.stackSize *= outputMultiplier;
                itemStacksOutput.add(itemStackOutput);
            }
            return itemStacksOutput;
        }
        return Collections.emptyList();
    }

    public List<ItemStack> getItemStacksOutput() {
        return this.itemStacksOutput;
    }

    public ItemStack getItemStackInput() {
        return this.itemStackInput;
    }

    public int getOutputMultiplier() {
        return this.outputMultiplier;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SmartMachineProcessOutput)) {
            return false;
        }
        SmartMachineProcessOutput other = (SmartMachineProcessOutput)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getOutputMultiplier() != other.getOutputMultiplier()) {
            return false;
        }
        List<ItemStack> this$itemStacksOutput = this.getItemStacksOutput();
        List<ItemStack> other$itemStacksOutput = other.getItemStacksOutput();
        if (this$itemStacksOutput == null ? other$itemStacksOutput != null : !((Object)this$itemStacksOutput).equals(other$itemStacksOutput)) {
            return false;
        }
        ItemStack this$itemStackInput = this.getItemStackInput();
        ItemStack other$itemStackInput = other.getItemStackInput();
        return !(this$itemStackInput == null ? other$itemStackInput != null : !this$itemStackInput.equals(other$itemStackInput));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SmartMachineProcessOutput;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getOutputMultiplier();
        List<ItemStack> $itemStacksOutput = this.getItemStacksOutput();
        result = result * 59 + ($itemStacksOutput == null ? 43 : ((Object)$itemStacksOutput).hashCode());
        ItemStack $itemStackInput = this.getItemStackInput();
        result = result * 59 + ($itemStackInput == null ? 43 : $itemStackInput.hashCode());
        return result;
    }

    public String toString() {
        return "SmartMachineProcessOutput(itemStacksOutput=" + this.getItemStacksOutput() + ", itemStackInput=" + this.getItemStackInput() + ", outputMultiplier=" + this.getOutputMultiplier() + ")";
    }
}

