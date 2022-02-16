/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package net.divinerpg.utils.recipes;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class DivineShapelessRecipes
implements IRecipe {
    private final ItemStack recipeOutput;
    public final List recipeItems;

    public DivineShapelessRecipes(ItemStack par1ItemStack, List par2List) {
        this.recipeOutput = par1ItemStack;
        this.recipeItems = par2List;
    }

    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World) {
        ArrayList<ItemStack> arraylist = new ArrayList(this.recipeItems);
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 3; ++j) {
                ItemStack itemstack = par1InventoryCrafting.getStackInRowAndColumn(j, i);
                if (itemstack == null) continue;
                boolean flag = false;
                for (ItemStack itemstack1 : arraylist) {
                    if (itemstack.getItem() != itemstack1.getItem() || itemstack1.getItemDamage() != Short.MAX_VALUE && itemstack.getItemDamage() != itemstack1.getItemDamage()) continue;
                    flag = true;
                    arraylist.remove(itemstack1);
                    break;
                }
                if (flag) continue;
                return false;
            }
        }
        return arraylist.isEmpty();
    }

    public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting) {
        return this.recipeOutput.copy();
    }

    public int getRecipeSize() {
        return this.recipeItems.size();
    }
}

