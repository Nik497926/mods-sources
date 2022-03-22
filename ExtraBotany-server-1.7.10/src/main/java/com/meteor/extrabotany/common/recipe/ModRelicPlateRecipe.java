/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.recipe;

import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class ModRelicPlateRecipe {
    public static ItemStack itemA;
    public static ItemStack itemB;
    public static ItemStack itemC;
    public static ItemStack[] recipeItems;

    public static boolean Recipe(List<EntityItem> items) {
        if (items.size() != 3) {
            return false;
        }
        itemA = null;
        itemB = null;
        itemC = null;
        for (EntityItem item : items) {
            ItemStack stack = item.getEntityItem();
            if (stack.stackSize != 1) {
                return false;
            }
            if (stack == recipeItems[0]) {
                itemA = stack;
                continue;
            }
            if (stack == recipeItems[1]) {
                itemB = stack;
                continue;
            }
            if (stack != recipeItems[2]) {
                return false;
            }
            itemC = stack;
        }
        return itemA != null && itemB != null && itemC != null;
    }
}

