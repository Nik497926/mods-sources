/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.machine.smart;

import ic2.api.recipe.IMachineRecipeManager;
import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import ru.simplemc.senergetics.common.tileentity.component.process.smart.SmartMachineProcessOutput;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMachine;

public class TileEntitySmartFurnace
extends TileEntitySmartMachine<TileEntitySmartFurnace> {
    @Override
    protected IMachineRecipeManager getMachineRecipeManager() {
        return null;
    }

    @Override
    @Nullable
    public SmartMachineProcessOutput getMachineProcessOutput(ItemStack itemStackInput) {
        ItemStack itemStackOutput = FurnaceRecipes.smelting().getSmeltingResult(itemStackInput);
        if (itemStackOutput != null) {
            ItemStack itemStackInputProcessing = itemStackInput.copy();
            int outputMultiplier = itemStackInput.stackSize;
            itemStackInput.stackSize = 0;
            return new SmartMachineProcessOutput(itemStackOutput.copy(), itemStackInputProcessing, outputMultiplier);
        }
        return null;
    }
}

