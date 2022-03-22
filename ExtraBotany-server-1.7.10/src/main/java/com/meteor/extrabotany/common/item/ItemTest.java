/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemTest
extends ItemMods {
    public static IIcon spear;
    int chunkX = 0;
    NBTTagCompound nbtTer = new NBTTagCompound();

    public ItemTest(String name) {
        super(name);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        list.add(StatCollector.translateToLocal("item.test.desc"));
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
        return true;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        return stack;
    }
}

