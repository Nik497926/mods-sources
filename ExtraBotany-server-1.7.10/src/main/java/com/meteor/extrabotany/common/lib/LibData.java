/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.lib;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.common.core.util.ODHelper;
import com.meteor.extrabotany.common.lib.LibRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidBlock;

public class LibData {
    public static int getBlockTemperture(Block block) {
        if (block instanceof IFluidBlock) {
            IFluidBlock fluid = (IFluidBlock)block;
            return fluid.getFluid().getTemperature();
        }
        return block == Blocks.lava ? 1300 : (block == Blocks.water ? 300 : 400);
    }

    public static int getCandyBurnTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        }
        Item item = stack.getItem();
        return !ExtraBotanyAPI.sweetTier6.contains(item) && !ODHelper.isODendswith("cake", stack) ? (ExtraBotanyAPI.sweetTier5.contains(item) ? 128 : (!ExtraBotanyAPI.sweetTier4.contains(item) && !ODHelper.isODendswith("pie", stack) ? (!(ExtraBotanyAPI.sweetTier3.contains(item) || ODHelper.isODendswith("yogurt", stack) || ODHelper.isODendswith("soda", stack) || ODHelper.isODendswith("icecream", stack)) ? (!(ExtraBotanyAPI.sweetTier2.contains(item) || ODHelper.isODendswith("juice", stack) || ODHelper.isODendswith("smoothie", stack) || ODHelper.isODendswith("jelly", stack)) ? (ExtraBotanyAPI.sweetTier1.contains(item) ? 8 : 0) : 16) : 32) : 64)) : 256;
    }

    public static int getBookBurnTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        }
        Item item = stack.getItem();
        return item == Items.book ? 25 : (item == Items.written_book ? 35 : LibRegistry.getFuelValue(stack));
    }
}

