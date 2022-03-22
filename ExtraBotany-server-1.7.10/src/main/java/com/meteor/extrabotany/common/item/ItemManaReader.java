/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item;

import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.item.ItemMods;
import com.meteor.extrabotany.common.item.ModItems;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemManaReader
extends ItemMods {
    private static final String COUNT = "useCount";

    public ItemManaReader(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        ItemManaReader.addStringToTooltip(StatCollector.translateToLocal("botaniamisc.usecount").replaceAll("%s%", String.valueOf(this.getCount(stack))), list);
    }

    static void addStringToTooltip(String s, List tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    private void writeCoord(ItemStack stack, int x, int y, int z, int DIM) {
        ItemNBTHelper.setInt(stack, "x", x);
        ItemNBTHelper.setInt(stack, "y", y);
        ItemNBTHelper.setInt(stack, "z", z);
        ItemNBTHelper.setInt(stack, "DIM", DIM);
    }

    public static boolean getSeeTransformater(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (!(stack.getItem() instanceof ItemManaReader)) {
            return false;
        }
        return ItemNBTHelper.getBoolean(stack, "transf", false);
    }

    public static boolean getSeeSomething(ItemStack stack) {
        return ItemManaReader.getSeeTransformater(stack) || ItemManaReader.getSeeEAltar(stack);
    }

    public static boolean getSeeEAltar(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (!(stack.getItem() instanceof ItemManaReader)) {
            return false;
        }
        return ItemNBTHelper.getBoolean(stack, "altar", false);
    }

    public static ChunkCoordinates getBoundTile(ItemStack stack, World world) {
        if (world.provider.dimensionId == ItemNBTHelper.getInt(stack, "DIM", -999999)) {
            int x = ItemNBTHelper.getInt(stack, "x", 0);
            int y = ItemNBTHelper.getInt(stack, "y", -1);
            int z = ItemNBTHelper.getInt(stack, "z", 0);
            return new ChunkCoordinates(x, y, z);
        }
        return new ChunkCoordinates(0, -1, 0);
    }

    public void thousandUse(EntityPlayer player, int count) {
        if (count == 1000) {
            player.inventory.addItemStackToInventory(new ItemStack(ModItems.material, 1, 4 + player.worldObj.rand.nextInt(3)));
            player.addStat(ModAchievement.thousandUse, 1);
            if (!player.worldObj.isRemote) {
                player.addChatMessage(new ChatComponentTranslation("botaniamisc.thousandUse").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
            }
        }
    }

    public int getCount(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, COUNT, 0);
    }

    public void setCount(ItemStack stack, int i) {
        ItemNBTHelper.setInt(stack, COUNT, i);
    }
}

