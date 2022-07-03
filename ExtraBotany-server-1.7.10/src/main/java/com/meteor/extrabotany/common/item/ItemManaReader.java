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
        ItemManaReader.addStringToTooltip(StatCollector.translateToLocal((String)"botaniamisc.usecount").replaceAll("%s%", String.valueOf(this.getCount(stack))), list);
    }

    static void addStringToTooltip(String s, List tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    private void writeCoord(ItemStack stack, int x, int y, int z, int DIM) {
        ItemNBTHelper.setInt((ItemStack)stack, (String)"x", (int)x);
        ItemNBTHelper.setInt((ItemStack)stack, (String)"y", (int)y);
        ItemNBTHelper.setInt((ItemStack)stack, (String)"z", (int)z);
        ItemNBTHelper.setInt((ItemStack)stack, (String)"DIM", (int)DIM);
    }

    public static boolean getSeeTransformater(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (!(stack.getItem() instanceof ItemManaReader)) {
            return false;
        }
        return ItemNBTHelper.getBoolean((ItemStack)stack, (String)"transf", (boolean)false);
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
        return ItemNBTHelper.getBoolean((ItemStack)stack, (String)"altar", (boolean)false);
    }

    public static ChunkCoordinates getBoundTile(ItemStack stack, World world) {
        if (world.provider.dimensionId == ItemNBTHelper.getInt((ItemStack)stack, (String)"DIM", (int)-999999)) {
            int x = ItemNBTHelper.getInt((ItemStack)stack, (String)"x", (int)0);
            int y = ItemNBTHelper.getInt((ItemStack)stack, (String)"y", (int)-1);
            int z = ItemNBTHelper.getInt((ItemStack)stack, (String)"z", (int)0);
            return new ChunkCoordinates(x, y, z);
        }
        return new ChunkCoordinates(0, -1, 0);
    }

    public void thousandUse(EntityPlayer player, int count) {
        if (count == 1000) {
            player.inventory.addItemStackToInventory(new ItemStack(ModItems.material, 1, 4 + player.worldObj.rand.nextInt(3)));
            player.addStat((StatBase)ModAchievement.thousandUse, 1);
            if (!player.worldObj.isRemote) {
                player.addChatMessage(new ChatComponentTranslation("botaniamisc.thousandUse", new Object[0]).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
            }
        }
    }

    public int getCount(ItemStack stack) {
        return ItemNBTHelper.getInt((ItemStack)stack, (String)COUNT, (int)0);
    }

    public void setCount(ItemStack stack, int i) {
        ItemNBTHelper.setInt((ItemStack)stack, (String)COUNT, (int)i);
    }
}

