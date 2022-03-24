package mireille.common.items;

import net.minecraft.item.crafting.*;
import net.minecraft.entity.player.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.relauncher.*;
import java.io.*;
import net.minecraftforge.common.config.*;
import mireille.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import mireille.core.*;
import net.minecraft.inventory.*;
import net.minecraft.world.*;
import java.util.*;

public class CraftingManager
{
    private static String configName;
    private static List<IRecipe> recipes;
    public static String[] WorkbenchCrafts;
    private static int recipeWidth;
    private static int recipeHeight;
    
    public static void setDefault(final String[] MirrorCrafts) {
        CraftingManager.recipes.clear();
        CraftingManager.WorkbenchCrafts = MirrorCrafts;
        ConvertRecipes();
    }
    
    public static void registerSunRecipes(final String configDir) {
        registerSunRecipes(configDir, null);
    }
    
    public static void registerSunRecipes(final String configDir, final EntityPlayerMP player) {
        final Side var10000 = FMLCommonHandler.instance().getSide();
        FMLCommonHandler.instance().getSide();
        if (var10000 == Side.SERVER) {
            final Configuration config = new Configuration(new File(configDir + CraftingManager.configName));
            try {
                config.load();
                CraftingManager.recipes.clear();
                final String[] WorkbenchCraftsDefault = { "minecraft:bedrock; 1 = minecraft:bedrock:0" };
                CraftingManager.WorkbenchCrafts = config.getStringList("WorkbenchCrafts", "WorkbenchCrafts", WorkbenchCraftsDefault, "\u041a\u0440\u0430\u0444\u0442\u044b \u0434\u043b\u044f \u041c\u0438\u0444\u0438\u0447\u0435\u0441\u043a\u043e\u0433\u043e \u0432\u0435\u0440\u0441\u0442\u0430\u043a\u0430");
                ConvertRecipes();
                if (player != null) {
                    player.addChatMessage((IChatComponent)new ChatComponentText(StrangerMind.resource("stming.chat.prefix") + CraftingManager.configName + " §a\u0443\u0441\u043f\u0435\u0448\u043d\u043e \u043f\u0435\u0440\u0435\u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d."));
                }
            }
            catch (Exception var10001) {
                if (player == null) {
                    System.out.println("[StrangerMind] \u041e\u0448\u0438\u0431\u043a\u0430 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438 \u043a\u043e\u043d\u0444\u0438\u0433\u0430: " + CraftingManager.configName + ". \u0412\u044b\u0441\u0442\u0430\u0432\u043b\u0435\u043d\u044b \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e \u0443\u043c\u043e\u043b\u0447\u0430\u043d\u0438\u044e.");
                }
                else {
                    player.addChatMessage((IChatComponent)new ChatComponentText(StrangerMind.resource("stming.chat.prefix") + "\u041e\u0448\u0438\u0431\u043a\u0430 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438 \u043a\u043e\u043d\u0444\u0438\u0433\u0430: §c" + CraftingManager.configName + "§a. \u0412\u044b\u0441\u0442\u0430\u0432\u043b\u0435\u043d\u044b \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e \u0443\u043c\u043e\u043b\u0447\u0430\u043d\u0438\u044e."));
                }
            }
            finally {
                config.save();
            }
        }
    }
    
    private static void ConvertRecipes() {
        for (final String workbenchCraft : CraftingManager.WorkbenchCrafts) {
            final ItemStack[] stacks = new ItemStack[CraftingManager.recipeWidth * CraftingManager.recipeHeight];
            final String line = workbenchCraft.replaceAll("\\s+", "");
            final String[] entryData = line.split("=");
            final String[] entryDataResult = entryData[0].split(";");
            ItemStack result;
            if (entryDataResult.length > 1) {
                result = ModItemsImport.getForgeItem(entryDataResult[0]);
                if (result != null) {
                    result.stackSize = Integer.parseInt(entryDataResult[1]);
                }
            }
            else {
                result = ModItemsImport.getForgeItem(entryDataResult[0]);
            }
            final String[] entryDataStacks = entryData[1].split(",");
            for (int j = 0; j < entryDataStacks.length; ++j) {
                stacks[j] = ModItemsImport.getForgeItem(entryDataStacks[j]);
            }
            CraftingManager.recipes.add((IRecipe)new RecipedWorkbench(CraftingManager.recipeWidth, CraftingManager.recipeHeight, stacks, result));
        }
    }
    
    public static ItemStack findMatchingRecipe(final InventoryCrafting inv, final World world) {
        int i = 0;
        for (int j = 0; j < inv.getSizeInventory(); ++j) {
            final ItemStack itemstack2 = inv.getStackInSlot(j);
            if (itemstack2 != null) {
                ++i;
            }
        }
        for (int j = 0; j < CraftingManager.recipes.size(); ++j) {
            final IRecipe irecipe = CraftingManager.recipes.get(j);
            if (irecipe.matches(inv, world)) {
                return irecipe.getCraftingResult(inv);
            }
        }
        return null;
    }
    
    public static List<IRecipe> getRecipeList() {
        return CraftingManager.recipes;
    }
    
    static {
        CraftingManager.configName = "StrangerMind_Crafts.cfg";
        CraftingManager.recipes = new ArrayList<IRecipe>();
        CraftingManager.recipeWidth = 5;
        CraftingManager.recipeHeight = 5;
    }
}
