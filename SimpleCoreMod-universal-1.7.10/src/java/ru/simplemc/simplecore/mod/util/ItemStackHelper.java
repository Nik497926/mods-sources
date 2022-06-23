/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemStackHelper {
    public static boolean isStackEqual(ItemStack stack1, ItemStack stack2) {
        return stack1 == null && stack2 == null || stack1 != null && stack2 != null && stack1.getItem() == stack2.getItem() && (!stack1.getHasSubtypes() && !stack1.isItemStackDamageable() || stack1.getItemDamage() == stack2.getItemDamage());
    }

    public static boolean isStackEqualStrict(ItemStack stack1, ItemStack stack2) {
        return ItemStackHelper.isStackEqual(stack1, stack2) && ItemStack.areItemStackTagsEqual((ItemStack)stack1, (ItemStack)stack2);
    }

    public static void saveAllItems(NBTTagCompound compound, ItemStack[] stacks) {
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < stacks.length; ++i) {
            ItemStack itemStack = stacks[i];
            if (itemStack == null) continue;
            NBTTagCompound compoundSlot = new NBTTagCompound();
            compoundSlot.setByte("Slot", (byte)i);
            itemStack.writeToNBT(compoundSlot);
            nbtTagList.appendTag((NBTBase)compoundSlot);
        }
        compound.setTag("Items", (NBTBase)nbtTagList);
    }

    public static void loadAllItems(NBTTagCompound tag, ItemStack[] stacks) {
        NBTTagList nbtTagList = tag.getTagList("Items", 10);
        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            NBTTagCompound compoundTagAt = nbtTagList.getCompoundTagAt(i);
            int j = compoundTagAt.getByte("Slot") & 0xFF;
            if (j >= stacks.length) continue;
            stacks[j] = ItemStack.loadItemStackFromNBT((NBTTagCompound)compoundTagAt);
        }
    }
}

