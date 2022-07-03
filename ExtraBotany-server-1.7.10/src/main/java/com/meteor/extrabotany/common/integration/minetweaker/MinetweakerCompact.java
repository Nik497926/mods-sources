/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.integration.minetweaker;

import com.meteor.extrabotany.common.integration.minetweaker.MTInfernoidisy;
import com.meteor.extrabotany.common.integration.minetweaker.MTStonesia;
import minetweaker.MineTweakerAPI;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

@ZenClass(value="mods.ExtraBotany")
public class MinetweakerCompact {
    public static void init() {
        MineTweakerAPI.registerClass(MTInfernoidisy.class);
        MineTweakerAPI.registerClass(MTStonesia.class);
    }

    public static boolean isSubtile(ItemStack stack) {
        return stack.getItem() instanceof ItemBlockSpecialFlower;
    }

    public static boolean subtileMatches(ItemStack stack, ItemStack stack2) {
        return ItemBlockSpecialFlower.getType((ItemStack)stack2).equals(ItemBlockSpecialFlower.getType((ItemStack)stack));
    }
}

