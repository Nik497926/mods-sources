/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.integration.minetweaker.utils;

import com.meteor.extrabotany.common.core.util.LogHelper;
import java.lang.reflect.Array;
import java.util.ArrayList;
import minetweaker.api.entity.IEntity;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.oredict.IOreDictEntry;
import minetweaker.mc1710.item.MCItemStack;
import minetweaker.mc1710.liquid.MCLiquidStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class InputHelper {
    public static boolean isABlock(IItemStack block) {
        return InputHelper.isABlock(InputHelper.toStack(block));
    }

    public static Object[][] getMultiDimensionalArray(Class clazz, Object[] array, int height, int width) {
        Object[][] multiDim = (Object[][])Array.newInstance(clazz, height, width);
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                multiDim[y][x] = array[x + y * width];
            }
        }
        return multiDim;
    }

    public static IItemStack[] toStacks(IIngredient[] iIngredient) {
        ArrayList<IItemStack> stacks = new ArrayList<IItemStack>();
        IIngredient[] arr$ = iIngredient;
        int len$ = iIngredient.length;
        for (int i$ = 0; i$ < len$; ++i$) {
            IIngredient ing = arr$[i$];
            for (IItemStack stack : ing.getItems()) {
                stacks.add(stack);
            }
        }
        return stacks.toArray(new IItemStack[stacks.size()]);
    }

    public static boolean isABlock(ItemStack block) {
        return block.getItem() instanceof ItemBlock;
    }

    public static Entity toEntity(IEntity iEntity) {
        return null;
    }

    public static ItemStack toStack(IItemStack iStack) {
        if (iStack == null) {
            return null;
        }
        Object internal = iStack.getInternal();
        if (!(internal instanceof ItemStack)) {
            LogHelper.error("Not a valid item stack: " + iStack, new Object[0]);
        }
        return (ItemStack)internal;
    }

    public static IIngredient toIngredient(ItemStack stack) {
        return InputHelper.toIItemStack(stack);
    }

    public static IIngredient toIngredient(FluidStack stack) {
        return stack == null ? null : new MCLiquidStack(stack);
    }

    public static IItemStack toIItemStack(ItemStack stack) {
        return stack == null ? null : new MCItemStack(stack);
    }

    public static ILiquidStack toILiquidStack(FluidStack stack) {
        return stack == null ? null : new MCLiquidStack(stack);
    }

    public static ItemStack[] toStacks(IItemStack[] iStack) {
        if (iStack == null) {
            return null;
        }
        ItemStack[] output = new ItemStack[iStack.length];
        for (int i = 0; i < iStack.length; ++i) {
            output[i] = InputHelper.toStack(iStack[i]);
        }
        return output;
    }

    public static Object toObject(IIngredient iStack) {
        return iStack == null ? null : (iStack instanceof IOreDictEntry ? InputHelper.toString((IOreDictEntry)iStack) : (iStack instanceof IItemStack ? InputHelper.toStack((IItemStack)iStack) : null));
    }

    public static Object[] toObjects(IIngredient[] ingredient) {
        if (ingredient == null) {
            return null;
        }
        Object[] output = new Object[ingredient.length];
        for (int i = 0; i < ingredient.length; ++i) {
            output[i] = ingredient[i] != null ? InputHelper.toObject(ingredient[i]) : "";
        }
        return output;
    }

    public static Object[] toShapedObjects(IIngredient[][] ingredients) {
        if (ingredients == null) {
            return null;
        }
        ArrayList<Object> prep = new ArrayList<Object>();
        prep.add("abc");
        prep.add("def");
        prep.add("ghi");
        char[][] map = new char[][]{{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}};
        for (int x = 0; x < ingredients.length; ++x) {
            if (ingredients[x] == null) continue;
            for (int y = 0; y < ingredients[x].length; ++y) {
                if (ingredients[x][y] == null || x >= map.length || y >= map[x].length) continue;
                prep.add(Character.valueOf(map[x][y]));
                prep.add(InputHelper.toObject(ingredients[x][y]));
            }
        }
        return prep.toArray();
    }

    public static String toString(IOreDictEntry entry) {
        return entry.getName();
    }

    public static FluidStack toFluid(ILiquidStack iStack) {
        return iStack == null ? null : FluidRegistry.getFluidStack((String)iStack.getName(), (int)iStack.getAmount());
    }

    public static Fluid getFluid(ILiquidStack iStack) {
        return iStack == null ? null : FluidRegistry.getFluid((String)iStack.getName());
    }

    public static FluidStack[] toFluids(IIngredient[] input) {
        return InputHelper.toFluids((IIngredient[])((IItemStack[])input));
    }

    public static FluidStack[] toFluids(ILiquidStack[] iStack) {
        FluidStack[] stack = new FluidStack[iStack.length];
        for (int i = 0; i < stack.length; ++i) {
            stack[i] = InputHelper.toFluid(iStack[i]);
        }
        return stack;
    }
}

