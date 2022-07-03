/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.integration.minetweaker.utils;

import com.meteor.extrabotany.common.integration.minetweaker.MinetweakerCompact;
import com.meteor.extrabotany.common.integration.minetweaker.utils.InputHelper;
import java.util.Iterator;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class StackHelper {
    public static boolean areEqual(ItemStack stack1, ItemStack stack2) {
        return stack1 != null && stack2 != null ? (MinetweakerCompact.isSubtile(stack1) && MinetweakerCompact.isSubtile(stack2) ? MinetweakerCompact.subtileMatches(stack1, stack2) : stack1.isItemEqual(stack2)) : false;
    }

    public static boolean areEqual(FluidStack stack1, FluidStack stack2) {
        return stack1 != null && stack2 != null ? stack1.isFluidEqual(stack2) : false;
    }

    public static boolean areEqualOrNull(ItemStack stack1, ItemStack stack2) {
        return stack1 == stack2 ? true : StackHelper.areEqual(stack1, stack2);
    }

    public static boolean areEqualOrNull(FluidStack stack1, FluidStack stack2) {
        return stack1 == stack2 ? true : StackHelper.areEqual(stack1, stack2);
    }

    public static boolean matches(IIngredient ingredient, IItemStack itemStack) {
        if (ingredient == null) {
            return false;
        }
        if (!ingredient.matches(itemStack)) {
            return false;
        }
        if (ingredient.getItems() != null && MinetweakerCompact.isSubtile(InputHelper.toStack(itemStack))) {
            IItemStack item;
            Iterator i$ = ingredient.getItems().iterator();
            do {
                if (i$.hasNext()) continue;
                return false;
            } while (!StackHelper.areEqual(InputHelper.toStack(item = (IItemStack)i$.next()), InputHelper.toStack(itemStack)));
            return true;
        }
        return true;
    }

    public static boolean matches(IIngredient ingredient, ILiquidStack liquidStack) {
        if (ingredient == null) {
            return false;
        }
        if (ingredient.matches(liquidStack)) {
            return true;
        }
        if (ingredient.getLiquids() != null) {
            for (ILiquidStack liquid : ingredient.getLiquids()) {
                if (!InputHelper.toFluid(liquid).isFluidEqual(InputHelper.toFluid(liquidStack))) continue;
                return true;
            }
        }
        return false;
    }
}

