/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.recipe;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.config.ConfigMolecularCollectorRecipe;
import ru.simplemc.senergetics.util.ItemStackUtils;

public class MolecularCollectorRecipe {
    private static final List<MolecularCollectorRecipe> RECIPES = SEnergetics.getConfig().getMolecularCollectorRecipes().stream().map(MolecularCollectorRecipe::build).collect(Collectors.toList());
    private final ItemStack inputItemStack;
    private final ItemStack outputItemStack;
    private final double energyUsage;

    public static List<MolecularCollectorRecipe> getRecipes() {
        return RECIPES;
    }

    public static Optional<MolecularCollectorRecipe> getRecipeForItemStack(ItemStack inputItemStack) {
        return RECIPES.stream().filter(recipe -> recipe.isMatches(inputItemStack)).findAny();
    }

    public static MolecularCollectorRecipe build(ConfigMolecularCollectorRecipe config) {
        ItemStack inputItemStack = ItemStackUtils.findItemStackInRegistryUnsafe(config.getInputItemName());
        inputItemStack.stackSize = config.getInputItemCount();
        ItemStack outputItemStack = ItemStackUtils.findItemStackInRegistryUnsafe(config.getOutputItemName());
        outputItemStack.stackSize = config.getOutputItemCount();
        return new MolecularCollectorRecipe(inputItemStack, outputItemStack, config.getEnergyUsage());
    }

    private MolecularCollectorRecipe(ItemStack inputItemStack, ItemStack outputItemStack, double energyUsage) {
        this.inputItemStack = inputItemStack;
        this.outputItemStack = outputItemStack;
        this.energyUsage = energyUsage;
    }

    private boolean isMatches(ItemStack itemStack) {
        return itemStack == null && this.inputItemStack == null || itemStack != null && this.inputItemStack != null && itemStack.getItem() == this.inputItemStack.getItem() && (!itemStack.getHasSubtypes() && !itemStack.isItemStackDamageable() || itemStack.getItemDamage() == this.inputItemStack.getItemDamage()) && itemStack.stackSize >= this.inputItemStack.stackSize;
    }

    public ItemStack getOutputItemStack() {
        return this.outputItemStack.copy();
    }

    public ItemStack getInputItemStack() {
        return this.inputItemStack;
    }

    public double getEnergyUsage() {
        return this.energyUsage;
    }
}

