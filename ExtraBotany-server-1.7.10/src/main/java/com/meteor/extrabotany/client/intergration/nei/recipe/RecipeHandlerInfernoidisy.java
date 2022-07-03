/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.intergration.nei.recipe;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeInfernoidisy;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class RecipeHandlerInfernoidisy
extends TemplateRecipeHandler {
    public String getRecipeName() {
        return StatCollector.translateToLocal((String)"extrabotania.nei.infernoidisy");
    }

    public String getGuiTexture() {
        return "botania:textures/gui/neiBlank.png";
    }

    public int recipiesPerPage() {
        return 2;
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(70, 22, 18, 18), "botania.infernoidisy", new Object[0]));
    }

    public void drawBackground(int recipe) {
        super.drawBackground(recipe);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
        GuiDraw.changeTexture((String)"botania:textures/gui/pureDaisyOverlay.png");
        GuiDraw.drawTexturedModalRect((int)45, (int)10, (int)0, (int)0, (int)65, (int)44);
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("botania.infernoidisy")) {
            for (Object recipe : ExtraBotanyAPI.infernoidisyRecipes) {
                if (recipe == null) continue;
                this.arecipes.add(new CachedInfernoidisyRecipe((RecipeInfernoidisy) recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        for (Object recipe : ExtraBotanyAPI.infernoidisyRecipes) {
            if (recipe == null || !NEIServerUtils.areStacksSameTypeCrafting((ItemStack)new ItemStack(((RecipeInfernoidisy) recipe).getOutput()), (ItemStack)result)) continue;
            this.arecipes.add(new CachedInfernoidisyRecipe((RecipeInfernoidisy) recipe));
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (Object recipe : ExtraBotanyAPI.infernoidisyRecipes) {
            CachedInfernoidisyRecipe crecipe;
            if (recipe == null || !(crecipe = new CachedInfernoidisyRecipe((RecipeInfernoidisy) recipe)).contains(crecipe.getIngredients(), ingredient) && !crecipe.contains(crecipe.getOtherStacks(), ingredient)) continue;
            this.arecipes.add(crecipe);
        }
    }

    public class CachedInfernoidisyRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        public List inputs;
        public PositionedStack output;
        public List otherStacks;

        public CachedInfernoidisyRecipe(RecipeInfernoidisy recipe) {
            super();
            this.inputs = new ArrayList();
            this.otherStacks = new ArrayList();
            if (recipe != null) {
                this.inputs.add(new PositionedStack((Object)ItemBlockSpecialFlower.ofType((String)"infernoidisy"), 71, 23));
                if (recipe.getInput() instanceof String) {
                    this.inputs.add(new PositionedStack((Object)OreDictionary.getOres((String)((String)recipe.getInput())), 42, 23));
                } else {
                    this.inputs.add(new PositionedStack((Object)new ItemStack((Block)recipe.getInput()), 42, 23));
                }
                this.output = new PositionedStack((Object)new ItemStack(recipe.getOutput()), 101, 23);
            }
        }

        public List getIngredients() {
            return this.getCycledIngredients(RecipeHandlerInfernoidisy.this.cycleticks / 20, this.inputs);
        }

        public PositionedStack getResult() {
            return this.output;
        }

        public List getOtherStacks() {
            return this.otherStacks;
        }

        public boolean contains(Collection ingredients, ItemStack ingredient) {
            if (ingredients == this.inputs) {
                for (Object stack : ingredients) {
                    if (!((PositionedStack) stack).contains(ingredient)) continue;
                    return true;
                }
            }
            return super.contains(ingredients, ingredient);
        }
    }
}

