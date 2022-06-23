/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.recipe;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.config.ConfigLavaGeneratorRecipe;
import ru.simplemc.senergetics.util.ItemStackUtils;

public class LavaGeneratorRecipe {
    private static final List<LavaGeneratorRecipe> RECIPES = SEnergetics.getConfig().getLavaGeneratorRecipes().stream().map(LavaGeneratorRecipe::build).collect(Collectors.toList());
    private final ItemStack inputItemStack;
    private final FluidStack outputFluidStack;

    public static List<LavaGeneratorRecipe> getRecipes() {
        return RECIPES;
    }

    public static Optional<LavaGeneratorRecipe> getRecipeForItemStack(ItemStack inputItemStack) {
        return RECIPES.stream().filter(recipe -> recipe.isMatches(inputItemStack)).findAny();
    }

    public static LavaGeneratorRecipe build(ConfigLavaGeneratorRecipe config) {
        ItemStack inputItemStack = ItemStackUtils.findItemStackInRegistryUnsafe(config.getInputItemName());
        inputItemStack.stackSize = config.getInputItemCount();
        return new LavaGeneratorRecipe(inputItemStack, config.getLavaGeneration());
    }

    private LavaGeneratorRecipe(ItemStack inputItemStack, int lavaGeneration) {
        this.inputItemStack = inputItemStack;
        this.outputFluidStack = new FluidStack(FluidRegistry.LAVA, lavaGeneration);
    }

    private boolean isMatches(ItemStack itemStack) {
        return itemStack == null && this.inputItemStack == null || itemStack != null && this.inputItemStack != null && itemStack.getItem() == this.inputItemStack.getItem() && (!itemStack.getHasSubtypes() && !itemStack.isItemStackDamageable() || itemStack.getItemDamage() == this.inputItemStack.getItemDamage()) && itemStack.stackSize >= this.inputItemStack.stackSize;
    }

    public FluidStack getOutputFluidStack() {
        return this.outputFluidStack.copy();
    }

    public ItemStack getInputItemStack() {
        return this.inputItemStack;
    }
}

