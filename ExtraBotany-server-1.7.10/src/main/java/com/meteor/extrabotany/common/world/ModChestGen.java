/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world;

import com.meteor.extrabotany.common.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class ModChestGen {
    public static void init() {
        WeightedRandomChestContent key = new WeightedRandomChestContent(new ItemStack(ModItems.key), 1, 4, 1);
        ChestGenHooks.addItem("strongholdLibrary", key);
        ModChestGen.addAll(new ItemStack(ModItems.boxs), 1, 1, 1, false);
        ModChestGen.addAll(new ItemStack(ModItems.dungeonbox), 1, 2, 1, false);
    }

    public static void addAll(ItemStack itemstack, int min, int max, int stacksize, boolean lib) {
        WeightedRandomChestContent rand = new WeightedRandomChestContent(itemstack, min, max, stacksize);
        ChestGenHooks.addItem("bonusChest", rand);
        ChestGenHooks.addItem("dungeonChest", rand);
        ChestGenHooks.addItem("mineshaftCorridor", rand);
        ChestGenHooks.addItem("pyramidDesertyChest", rand);
        ChestGenHooks.addItem("pyramidJungleChest", rand);
        ChestGenHooks.addItem("pyramidJungleDispenser", rand);
        ChestGenHooks.addItem("strongholdCorridor", rand);
        ChestGenHooks.addItem("strongholdCorridor", rand);
        ChestGenHooks.addItem("strongholdCrossing", rand);
        ChestGenHooks.addItem("villageBlacksmith", rand);
        if (lib) {
            ChestGenHooks.addItem("strongholdLibrary", rand);
        }
    }
}

