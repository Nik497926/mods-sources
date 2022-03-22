/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.util;

import cofh.api.energy.IEnergyContainerItem;
import com.meteor.extrabotany.common.core.util.LogHelper;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class InfoHelper {
    public static void addEnergyInfo(ItemStack stack, List list) {
        IEnergyContainerItem item = (IEnergyContainerItem)stack.getItem();
        int energy = item.getEnergyStored(stack);
        int maxEnergy = item.getMaxEnergyStored(stack);
        String eS = "";
        String eM = "";
        eS = energy < 1000 ? String.valueOf(energy) : (energy < 1000000 ? String.valueOf(energy) : (float) Math.round((float) energy / 1000.0f) / 1000.0f + "m");
        eM = maxEnergy < 1000 ? String.valueOf(maxEnergy) : (maxEnergy < 1000000 ? (float) Math.round((float) maxEnergy / 100.0f) / 10.0f + "k" : (float) Math.round((float) maxEnergy / 10000.0f) / 100.0f + "m");
        list.add(StatCollector.translateToLocal("info.de.charge.txt") + ": " + eS + " / " + eM + " RF");
    }

    public static void addLore(ItemStack stack, List list, boolean addLeadingLine) {
        String[] lore = InfoHelper.getLore(stack);
        if (addLeadingLine) {
            list.add("");
        }
        if (lore == null) {
            list.add("" + EnumChatFormatting.ITALIC + "" + EnumChatFormatting.DARK_PURPLE + "Invalid lore localization! (something is broken)");
            return;
        }
        for (String s : lore) {
            list.add("" + EnumChatFormatting.ITALIC + "" + EnumChatFormatting.DARK_PURPLE + s);
        }
    }

    public static void addLore(ItemStack stack, List list) {
        InfoHelper.addLore(stack, list, true);
    }

    public static void addEnergyAndLore(ItemStack stack, List list) {
        if (!InfoHelper.isShiftKeyDown()) {
            list.add(StatCollector.translateToLocal("info.de.hold.txt") + " " + EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("info.de.shift.txt") + EnumChatFormatting.RESET + " " + EnumChatFormatting.GRAY + StatCollector.translateToLocal("info.de.forDetails.txt"));
        } else {
            InfoHelper.addEnergyInfo(stack, list);
            InfoHelper.addLore(stack, list);
        }
    }

    public static String[] getLore(ItemStack stack) {
        String unlocalizeLore = stack.getItem().getUnlocalizedName() + ".lore";
        String rawLore = StatCollector.translateToLocal(unlocalizeLore);
        if (rawLore.contains(unlocalizeLore)) {
            return null;
        }
        String lineCountS = rawLore.substring(0, 1);
        rawLore = rawLore.substring(1);
        int lineCount = 0;
        try {
            lineCount = Integer.parseInt(lineCountS);
        }
        catch (NumberFormatException e) {
            LogHelper.error("Invalid Lore Format! Lore myst start with the number of lines \"3Line 1\\nLine 2\\nLine 3\"");
        }
        String[] loreLines = new String[lineCount];
        for (int i = 0; i < lineCount; ++i) {
            loreLines[i] = rawLore.contains("\\n") ? rawLore.substring(0, rawLore.indexOf("\\n")) : rawLore;
            if (!rawLore.contains("\\n")) continue;
            rawLore = rawLore.substring(rawLore.indexOf("\\n") + 2);
        }
        return loreLines;
    }

    public static boolean isShiftKeyDown() {
        return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
    }

    public static boolean isCtrlKeyDown() {
        return Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
    }

    public static boolean holdShiftForDetails(List list, boolean inverted) {
        if (InfoHelper.isShiftKeyDown() == inverted) {
            list.add(StatCollector.translateToLocal("info.de.hold.txt") + " " + EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("info.de.shift.txt") + EnumChatFormatting.RESET + " " + EnumChatFormatting.GRAY + StatCollector.translateToLocal("info.de.forDetails.txt"));
        }
        return InfoHelper.isShiftKeyDown();
    }

    public static boolean holdShiftForDetails(List list) {
        return InfoHelper.holdShiftForDetails(list, false);
    }

    public static String ITC() {
        return "" + EnumChatFormatting.RESET + "" + EnumChatFormatting.DARK_AQUA;
    }

    public static String HITC() {
        return "" + EnumChatFormatting.RESET + "" + EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GOLD;
    }
}

