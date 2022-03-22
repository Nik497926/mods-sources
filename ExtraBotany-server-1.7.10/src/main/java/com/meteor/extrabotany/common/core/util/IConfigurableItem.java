/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.util;

import com.meteor.extrabotany.common.core.util.ItemConfigField;
import com.meteor.extrabotany.common.core.util.ItemNBTHelper;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public interface IConfigurableItem {
    List<ItemConfigField> getFields(ItemStack var1, int var2);

    boolean hasProfiles();

    class ProfileHelper {
        public static NBTTagCompound getProfileCompound(ItemStack stack) {
            int profile = ItemNBTHelper.getInteger(stack, "ConfigProfile", 0);
            NBTTagCompound stackCompound = ItemNBTHelper.getCompound(stack);
            if (!stackCompound.hasKey("ConfigProfiles") && stackCompound.getTagList("ConfigProfiles", 10).tagCount() < 5) {
                NBTTagList profileList = new NBTTagList();
                for (int i = 0; i < 5; ++i) {
                    profileList.appendTag(new NBTTagCompound());
                }
                stackCompound.setTag("ConfigProfiles", profileList);
            }
            return stackCompound.getTagList("ConfigProfiles", 10).getCompoundTagAt(profile);
        }

        public static void setBoolean(ItemStack stack, String tag, boolean b) {
            ProfileHelper.getProfileCompound(stack).setBoolean(tag, b);
        }

        public static void setInteger(ItemStack stack, String tag, int i) {
            ProfileHelper.getProfileCompound(stack).setInteger(tag, i);
        }

        public static void setFloat(ItemStack stack, String tag, float f) {
            ProfileHelper.getProfileCompound(stack).setFloat(tag, f);
        }

        public static void setDouble(ItemStack stack, String tag, double d) {
            ProfileHelper.getProfileCompound(stack).setDouble(tag, d);
        }

        public static void setString(ItemStack stack, String tag, String s) {
            ProfileHelper.getProfileCompound(stack).setString(tag, s);
        }

        public static boolean getBoolean(ItemStack stack, String tag, boolean defaultExpected) {
            return ProfileHelper.getProfileCompound(stack).hasKey(tag) ? ProfileHelper.getProfileCompound(stack).getBoolean(tag) : defaultExpected;
        }

        public static int getInteger(ItemStack stack, String tag, int defaultExpected) {
            return ProfileHelper.getProfileCompound(stack).hasKey(tag) ? ProfileHelper.getProfileCompound(stack).getInteger(tag) : defaultExpected;
        }

        public static float getFloat(ItemStack stack, String tag, float defaultExpected) {
            return ProfileHelper.getProfileCompound(stack).hasKey(tag) ? ProfileHelper.getProfileCompound(stack).getFloat(tag) : defaultExpected;
        }

        public static double getDouble(ItemStack stack, String tag, double defaultExpected) {
            return ProfileHelper.getProfileCompound(stack).hasKey(tag) ? ProfileHelper.getProfileCompound(stack).getDouble(tag) : defaultExpected;
        }

        public static String getString(ItemStack stack, String tag, String defaultExpected) {
            return ProfileHelper.getProfileCompound(stack).hasKey(tag) ? ProfileHelper.getProfileCompound(stack).getString(tag) : defaultExpected;
        }
    }
}

