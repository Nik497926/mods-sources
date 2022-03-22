/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class ItemNBTHelper {
    public static NBTTagCompound getCompound(ItemStack stack) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        return stack.getTagCompound();
    }

    public static ItemStack setByte(ItemStack stack, String tag, byte b) {
        NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
        compound.setByte(tag, b);
        stack.setTagCompound(compound);
        return stack;
    }

    public static ItemStack setBoolean(ItemStack stack, String tag, boolean b) {
        NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
        compound.setBoolean(tag, b);
        stack.setTagCompound(compound);
        return stack;
    }

    public static ItemStack setShort(ItemStack stack, String tag, short s) {
        NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
        compound.setShort(tag, s);
        stack.setTagCompound(compound);
        return stack;
    }

    public static ItemStack setInteger(ItemStack stack, String tag, int i) {
        NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
        compound.setInteger(tag, i);
        stack.setTagCompound(compound);
        return stack;
    }

    public static ItemStack setLong(ItemStack stack, String tag, long i) {
        NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
        compound.setLong(tag, i);
        stack.setTagCompound(compound);
        return stack;
    }

    public static ItemStack setFloat(ItemStack stack, String tag, float f) {
        NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
        compound.setFloat(tag, f);
        stack.setTagCompound(compound);
        return stack;
    }

    public static ItemStack setDouble(ItemStack stack, String tag, double d) {
        NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
        compound.setDouble(tag, d);
        stack.setTagCompound(compound);
        return stack;
    }

    public static ItemStack setString(ItemStack stack, String tag, String s) {
        NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
        compound.setString(tag, s);
        stack.setTagCompound(compound);
        return stack;
    }

    public static boolean verifyExistance(ItemStack stack, String tag) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound == null) {
            return false;
        }
        return stack.getTagCompound().hasKey(tag);
    }

    public static byte getByte(ItemStack stack, String tag, byte defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? stack.getTagCompound().getByte(tag) : defaultExpected;
    }

    public static boolean getBoolean(ItemStack stack, String tag, boolean defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? stack.getTagCompound().getBoolean(tag) : defaultExpected;
    }

    public static short getShort(ItemStack stack, String tag, short defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? stack.getTagCompound().getShort(tag) : defaultExpected;
    }

    public static int getInteger(ItemStack stack, String tag, int defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? stack.getTagCompound().getInteger(tag) : defaultExpected;
    }

    public static long getLong(ItemStack stack, String tag, long defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? stack.getTagCompound().getLong(tag) : defaultExpected;
    }

    public static float getFloat(ItemStack stack, String tag, float defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? stack.getTagCompound().getFloat(tag) : defaultExpected;
    }

    public static double getDouble(ItemStack stack, String tag, double defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? stack.getTagCompound().getDouble(tag) : defaultExpected;
    }

    public static String getString(ItemStack stack, String tag, String defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? stack.getTagCompound().getString(tag) : defaultExpected;
    }
}

