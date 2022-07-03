/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.integration.minetweaker;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeStonesia;
import com.meteor.extrabotany.common.core.util.LogHelper;
import com.meteor.extrabotany.common.integration.minetweaker.utils.BaseListAddition;
import com.meteor.extrabotany.common.integration.minetweaker.utils.BaseListRemoval;
import com.meteor.extrabotany.common.integration.minetweaker.utils.InputHelper;
import com.meteor.extrabotany.common.integration.minetweaker.utils.StackHelper;
import java.util.LinkedList;
import java.util.List;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass(value="mods.ExtraBotany.Stonesia")
public class MTStonesia {
    public static final String name = "ExtraBotania Stonesia";

    @ZenMethod
    public static void addRecipe(IIngredient blockInput, int burntime) {
        if (blockInput != null && burntime != 0) {
            Object input = InputHelper.toObject(blockInput);
            if (input != null && (!(input instanceof ItemStack) || InputHelper.isABlock((ItemStack)input))) {
                if (input instanceof ItemStack) {
                    input = Block.getBlockFromItem((Item)((ItemStack)input).getItem());
                }
                RecipeStonesia recipe = new RecipeStonesia(input, burntime);
                MineTweakerAPI.apply((IUndoableAction)new Add(recipe));
            } else {
                LogHelper.error(String.format("Input must be a block or an oredict entry.", new Object[0]), new Object[0]);
            }
        } else {
            LogHelper.error(String.format("Required parameters missing for %s Recipe.", name), new Object[0]);
        }
    }

    @ZenMethod
    public static void removeRecipe(IIngredient input) {
        LinkedList<RecipeStonesia> recipes = new LinkedList<RecipeStonesia>();
        for (Object recipe1 : ExtraBotanyAPI.stonesiaRecipes) {
            RecipeStonesia recipe = (RecipeStonesia) recipe1;
            IItemStack in2;
            if (recipe.getInput() instanceof String) {
                for (ItemStack ostack : OreDictionary.getOres((String)((String)recipe.getInput()))) {
                    IItemStack in1 = InputHelper.toIItemStack(ostack);
                    if (!StackHelper.matches(input, in1)) continue;
                    recipes.add(recipe);
                }
                continue;
            }
            if (!(recipe.getInput() instanceof Block) || !StackHelper.matches(input, in2 = InputHelper.toIItemStack(new ItemStack((Block)recipe.getInput())))) continue;
            recipes.add(recipe);
        }
        if (!recipes.isEmpty()) {
            MineTweakerAPI.apply((IUndoableAction)new Remove(recipes));
        }
    }

    private static class Remove
    extends BaseListRemoval {
        public Remove(List recipes) {
            super(MTStonesia.name, ExtraBotanyAPI.stonesiaRecipes, recipes);
        }

        @Override
        protected String getRecipeInfo(Object var1) {
            return null;
        }
    }

    private static class Add
    extends BaseListAddition {
        public Add(RecipeStonesia recipe) {
            super(MTStonesia.name, ExtraBotanyAPI.stonesiaRecipes);
            this.recipes.add(recipe);
        }

        @Override
        protected String getRecipeInfo(Object var1) {
            return null;
        }
    }
}

