/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.intergration.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;
import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.client.intergration.nei.recipe.RecipeHandlerInfernoidisy;
import com.meteor.extrabotany.client.intergration.nei.recipe.RecipeHandlerStonesia;
import com.meteor.extrabotany.common.item.ModItems;
import net.minecraft.item.ItemStack;

public class NEIExtraBotanyConfig
implements IConfigureNEI {
    public String getName() {
        return "ExtraBotania";
    }

    public String getVersion() {
        return "r1.0-21";
    }

    public void loadConfig() {
        API.registerRecipeHandler((ICraftingHandler)new RecipeHandlerInfernoidisy());
        API.registerUsageHandler((IUsageHandler)new RecipeHandlerInfernoidisy());
        API.registerRecipeHandler((ICraftingHandler)new RecipeHandlerStonesia());
        API.registerUsageHandler((IUsageHandler)new RecipeHandlerStonesia());
        for (int i = 0; i < 16; ++i) {
            API.hideItem((ItemStack)new ItemStack(ModItems.skill, 1, i));
        }
        API.hideItem((ItemStack)new ItemStack(ModItems.itemtest));
        if (!ExtraBotany.alfheimLoaded) {
            API.hideItem((ItemStack)new ItemStack(ModItems.info));
        }
    }
}

