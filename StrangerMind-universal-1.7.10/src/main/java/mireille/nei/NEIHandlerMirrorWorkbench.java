package mireille.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mireille.StrangerMind;
import mireille.client.gui.container.GuiContainerWorkbench;
import mireille.common.items.CraftingManager;
import mireille.common.items.RecipedWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class NEIHandlerMirrorWorkbench extends TemplateRecipeHandler {
   private ResourceLocation MirrorWorkbenchNei = new ResourceLocation("mireille:textures/gui/mythical_work_nei.png");

   public String getOverlayIdentifier() {
      return "craftingStMind";
   }

   public void loadTransferRects() {
   }

   public void drawExtras(int recipe) {
   }

   public Class getGuiClass() {
      return GuiContainerWorkbench.class;
   }

   public String getRecipeName() {
      return StrangerMind.resource("Workbench.name");
   }

   public String getGuiTexture() {
      return this.MirrorWorkbenchNei.toString();
   }

   public void drawBackground(int recipe) {
      GuiDraw.changeTexture(this.getGuiTexture());
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GuiDraw.drawTexturedModalRect(1, 0, 0, 0, 163, 128);
   }

   public void drawForeground(int recipe) {
   }

   public int recipiesPerPage() {
      return 1;
   }

   public void loadAllRecipes() {
      Iterator var1 = CraftingManager.getRecipeList().iterator();

      while(var1.hasNext()) {
         IRecipe iRecipe = (IRecipe)var1.next();
         RecipedWorkbench recipe = (RecipedWorkbench)iRecipe;
         this.arecipes.add(new NEIHandlerMirrorWorkbench.CachedShapedRecipe(recipe));
      }

   }

   public void loadCraftingRecipes(String outputId, Object... results) {
      if (outputId.equals(this.getOverlayIdentifier())) {
         this.loadAllRecipes();
      } else if (outputId.equals("item")) {
         this.loadCraftingRecipes((ItemStack)results[0]);
      }

   }

   public void loadCraftingRecipes(ItemStack result) {
      Iterator var2 = CraftingManager.getRecipeList().iterator();

      while(var2.hasNext()) {
         IRecipe irecipe = (IRecipe)var2.next();
         if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.getRecipeOutput(), result)) {
            NEIHandlerMirrorWorkbench.CachedShapedRecipe recipe = null;
            if (irecipe instanceof RecipedWorkbench) {
               recipe = new NEIHandlerMirrorWorkbench.CachedShapedRecipe((RecipedWorkbench)irecipe);
            }

            if (recipe != null) {
               recipe.computeVisuals();
               this.arecipes.add(recipe);
            }
         }
      }

   }

   public void loadUsageRecipes(ItemStack ingredient) {
      Iterator var2 = CraftingManager.getRecipeList().iterator();

      while(var2.hasNext()) {
         IRecipe irecipe = (IRecipe)var2.next();
         NEIHandlerMirrorWorkbench.CachedShapedRecipe recipe = null;
         if (irecipe instanceof RecipedWorkbench) {
            recipe = new NEIHandlerMirrorWorkbench.CachedShapedRecipe((RecipedWorkbench)irecipe);
         }

         if (recipe != null) {
            recipe.computeVisuals();
            if (recipe.contains(recipe.ingredients, ingredient)) {
               recipe.setIngredientPermutation(recipe.ingredients, ingredient);
               this.arecipes.add(recipe);
            }
         }
      }

   }

   public class CachedShapedRecipe extends CachedRecipe {
      public ArrayList ingredients;
      public PositionedStack result;

      public CachedShapedRecipe(int w, int h, ItemStack[] items, ItemStack out) {
         //super(NEIHandlerMirrorWorkbench.this);
         this.result = new PositionedStack(out, 138, 57);
         this.ingredients = new ArrayList();
         this.setIngredients(w, h, items);
      }

      public CachedShapedRecipe(RecipedWorkbench recipe) {
         this(recipe.recipeWidth, recipe.recipeHeight, recipe.recipeItems, recipe.getRecipeOutput());
      }

      public void setIngredients(int width, int height, ItemStack[] items) {
         for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
               if (items[y * width + x] != null) {
                  PositionedStack stack = new PositionedStack(items[y * width + x], 6 + x * 18, 20 + y * 18, false);
                  stack.setMaxSize(1);
                  this.ingredients.add(stack);
               }
            }
         }

      }

      public List getIngredients() {
         return this.getCycledIngredients(NEIHandlerMirrorWorkbench.this.cycleticks / 20, this.ingredients);
      }

      public PositionedStack getResult() {
         return this.result;
      }

      public void computeVisuals() {
         Iterator var1 = this.ingredients.iterator();

         while(var1.hasNext()) {
            PositionedStack p = (PositionedStack)var1.next();
            p.generatePermutations();
         }

      }
   }
}
