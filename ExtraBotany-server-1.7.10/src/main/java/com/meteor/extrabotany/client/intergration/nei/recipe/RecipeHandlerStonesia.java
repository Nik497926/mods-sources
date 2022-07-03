/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.intergration.nei.recipe;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeStonesia;
import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class RecipeHandlerStonesia
extends TemplateRecipeHandler {
    public String getRecipeName() {
        return StatCollector.translateToLocal((String)"extrabotania.nei.stonesia");
    }

    public String getGuiTexture() {
        return "botania:textures/gui/neiBlank.png";
    }

    public int recipiesPerPage() {
        return 2;
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(70, 22, 18, 18), "botania.stonesia", new Object[0]));
    }

    public void drawBackground(int recipe) {
        super.drawBackground(recipe);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
        GuiDraw.changeTexture((String)"extrabotania:textures/gui/generatingOverlay.png");
        GuiDraw.drawTexturedModalRect((int)45, (int)10, (int)0, (int)0, (int)65, (int)44);
        HUDHandler.renderManaBar((int)32, (int)45, (int)255, (float)0.75f, (int)((CachedStonesiaRecipe)((Object)this.arecipes.get((int)recipe))).manaOutput, (int)100000);
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("botania.stonesia")) {
            for (Object recipe : ExtraBotanyAPI.stonesiaRecipes) {
                if (recipe == null) continue;
                this.arecipes.add(new CachedStonesiaRecipe((RecipeStonesia) recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (Object recipe : ExtraBotanyAPI.stonesiaRecipes) {
            CachedStonesiaRecipe crecipe;
            if (recipe == null || !(crecipe = new CachedStonesiaRecipe((RecipeStonesia) recipe)).contains(crecipe.getIngredients(), ingredient) && !crecipe.contains(crecipe.getOtherStacks(), ingredient)) continue;
            this.arecipes.add(crecipe);
        }
    }

    public class CachedStonesiaRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        public List inputs;
        public PositionedStack output;
        public int manaOutput;

        public CachedStonesiaRecipe(RecipeStonesia recipe) {
            super();
            this.inputs = new ArrayList();
            if (recipe != null) {
                this.inputs.add(new PositionedStack((Object)ItemBlockSpecialFlower.ofType((String)"stonesia"), 71, 23));
                if (recipe.getInput() instanceof String) {
                    this.inputs.add(new PositionedStack((Object)OreDictionary.getOres((String)((String)recipe.getInput())), 42, 23));
                } else {
                    this.inputs.add(new PositionedStack((Object)new ItemStack((Block)recipe.getInput()), 42, 23));
                }
                this.manaOutput = recipe.getMana() * ConfigHandler.efficiencyStonesia;
            }
        }

        public List getIngredients() {
            return this.getCycledIngredients(RecipeHandlerStonesia.this.cycleticks / 20, this.inputs);
        }

        public PositionedStack getResult() {
            return this.output;
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

