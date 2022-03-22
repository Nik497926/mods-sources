/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.handler;

import cpw.mods.fml.common.FMLLog;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.Level;

public class CraftingHandler {
    public static int countCrafting = 0;
    public static int countFurnace = 0;

    public static void RemoveCrafting(Item item) {
        List recipes = CraftingManager.getInstance().getRecipeList();
        Iterator remover = recipes.iterator();
        while (remover.hasNext()) {
            ItemStack itemstack = ((IRecipe)remover.next()).getRecipeOutput();
            if (itemstack == null || itemstack.getItem() != item) continue;
            remover.remove();
            ++countCrafting;
            FMLLog.log(Level.INFO, "Removed crafting recipes of " + StatCollector.translateToLocal(itemstack.getUnlocalizedName() + ".name") + ".");
        }
    }

    public static void RemoveFurnace(ItemStack itemstack) {
        Map recipe = FurnaceRecipes.smelting().getSmeltingList();
        Iterator entries = recipe.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            ItemStack result = (ItemStack)entry.getValue();
            if (!ItemStack.areItemStacksEqual(result, itemstack)) continue;
            entries.remove();
            ++countFurnace;
            FMLLog.log(Level.INFO, "Removed furnace recipes of " + StatCollector.translateToLocal(itemstack.getUnlocalizedName() + ".name") + ".");
        }
    }
}

