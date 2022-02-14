/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.item.crafting.ShapedRecipes
 *  net.minecraftforge.oredict.ShapedOreRecipe
 */
package net.divinerpg.utils.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeUtil {
    protected static void addRecipe(ItemStack i, Object ... o) {
        GameRegistry.addRecipe((ItemStack)i, (Object[])o);
    }

    protected static void addRecipe(Item i, Object ... o) {
        GameRegistry.addRecipe((ItemStack)new ItemStack(i, 1), (Object[])o);
    }

    protected static void addRecipe(Block b, Object ... o) {
        GameRegistry.addRecipe((ItemStack)new ItemStack(b, 1), (Object[])o);
    }

    protected static void addShapelessRecipe(ItemStack i, Object ... o) {
        GameRegistry.addShapelessRecipe((ItemStack)i, (Object[])o);
    }

    protected static void addShapelessRecipe(Item i, Object ... o) {
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(i, 1), (Object[])o);
    }

    protected static void addShapelessRecipe(Block b, Object ... o) {
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(b, 1), (Object[])o);
    }

    protected static void addStairRecipe(Block mat, Block stair) {
        GameRegistry.addRecipe((ItemStack)new ItemStack(stair, 4), (Object[])new Object[]{"X  ", "XX ", "XXX", Character.valueOf('X'), mat});
    }

    protected static void addSlabRecipe(Block mat, Block slab) {
        GameRegistry.addRecipe((ItemStack)new ItemStack(slab, 6), (Object[])new Object[]{"XXX", Character.valueOf('X'), mat});
    }

    protected static void addSmelting(ItemStack input, ItemStack output, float XP) {
        GameRegistry.addSmelting((ItemStack)input, (ItemStack)output, (float)XP);
    }

    protected static void addSmelting(Item input, Item output, float XP) {
        GameRegistry.addSmelting((ItemStack)new ItemStack(input, 1), (ItemStack)new ItemStack(output, 1), (float)XP);
    }

    protected static void addSmelting(Block input, Block output, float XP) {
        GameRegistry.addSmelting((ItemStack)new ItemStack(input, 1), (ItemStack)new ItemStack(output, 1), (float)XP);
    }

    protected static void addSmelting(Block input, Item output, float XP) {
        GameRegistry.addSmelting((ItemStack)new ItemStack(input, 1), (ItemStack)new ItemStack(output, 1), (float)XP);
    }

    protected static void addSmelting(Item input, Block output, float XP) {
        GameRegistry.addSmelting((ItemStack)new ItemStack(input, 1), (ItemStack)new ItemStack(output, 1), (float)XP);
    }

    public static void addOredictRecipe(ItemStack result, Object ... materials) {
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(result, materials));
    }

    public static void addOredictRecipe(Item result, Object ... materials) {
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(result, materials));
    }

    public static void addOredictRecipe(Block result, Object ... materials) {
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(result, materials));
    }

    protected static void removeCraftingRecipe(Item removed) {
        ItemStack recipeResult = null;
        ArrayList recipes = (ArrayList)CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < recipes.size(); ++i) {
            IRecipe tmpRecipe = (IRecipe)recipes.get(i);
            if (tmpRecipe instanceof ShapedRecipes) {
                ShapedRecipes recipe = (ShapedRecipes)tmpRecipe;
                recipeResult = recipe.getRecipeOutput();
            }
            if (!ItemStack.areItemStacksEqual((ItemStack)new ItemStack(removed), (ItemStack)recipeResult)) continue;
            recipes.remove(i);
        }
    }
}

