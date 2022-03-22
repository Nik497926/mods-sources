/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.recipe;

import com.meteor.extrabotany.common.core.handler.CraftingHandler;
import com.meteor.extrabotany.common.recipe.ModEAltarRecipe;
import com.meteor.extrabotany.common.recipe.ModManaInfusionRecipe;
import com.meteor.extrabotany.common.recipe.ModPetalRecipe;
import com.meteor.extrabotany.common.recipe.ModRuneRecipe;
import com.meteor.extrabotany.common.recipe.subtile.ModInfernoidisyRecipe;
import com.meteor.extrabotany.common.recipe.subtile.ModStonesiaRecipe;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.Level;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

public class ModRecipe {
    public static IRecipe baubleDog0;
    public static IRecipe baubleDog1;
    public static IRecipe baubleDog2;
    public static IRecipe baubleDog3;
    public static IRecipe elvenQuartz;
    public static IRecipe gaiaQuartz;
    public static IRecipe goldString;
    public static IRecipe teleportpearl;
    public static IRecipe gaiawise;
    public static IRecipe bladered;
    public static IRecipe bladepurple;
    public static IRecipe angelwand;
    public static IRecipe manapotato;
    public static IRecipe gaiatablet;
    public static IRecipe manareader;
    public static IRecipe oghelm;
    public static IRecipe ogchest;
    public static IRecipe oglegs;
    public static IRecipe ogboots;
    public static IRecipe sgmini;
    public static IRecipe sgquick;
    public static IRecipe sgenhanced;
    public static IRecipe sgbee;
    public static IRecipe eternalsilence;
    public static IRecipe excaliber;
    public static IRecipe hermes;
    public static IRecipe valkyie;
    public static IRecipe dagger;
    public static IRecipe maxwell;
    public static IRecipe emptysoulsteel;
    public static IRecipe soulsteel;
    public static IRecipe elfirium;
    public static IRecipe blockelfirium;
    public static IRecipe auracontroler;
    public static IRecipe elfpool;
    public static IRecipe elfupdater;
    public static IRecipe invreader;
    public static IRecipe maininvreader;
    public static IRecipe maininvchest;
    public static IRecipe manatrade;
    public static IRecipe crystaleye;
    public static IRecipe memory0;
    public static IRecipe memory1;
    public static IRecipe memory2;
    public static IRecipe memory3;
    public static IRecipe summon;
    public static IRecipe efirpool;
    public static IRecipe transformater;
    public static IRecipe eAltar;
    public static IRecipe boost;
    public static IRecipe relicTest;
    public static IRecipe elfiriumFragment;
    public static IRecipe elfTablet;
    public static IRecipe relicPanel;

    public static void initSubtile() {
        ModStonesiaRecipe.init();
        ModInfernoidisyRecipe.init();
    }

    public static void init() {
        ModRecipe.remove();
        ModManaInfusionRecipe.init();
        ModPetalRecipe.init();
        ModRuneRecipe.init();
        ModEAltarRecipe.instance.init();
        ModRecipe.initSubtile();
        int recipeListSize = CraftingManager.getInstance().getRecipeList().size();
        FurnaceRecipes.smelting().func_151393_a(com.meteor.extrabotany.common.block.ModBlocks.elfOre, new ItemStack(com.meteor.extrabotany.common.item.ModItems.fragmentEfirium), 1.0f);
        elfiriumFragment = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.elfTablet), "ABA", "BCB", "ABA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 11), Character.valueOf('B'), com.meteor.extrabotany.common.item.ModItems.elfirium, Character.valueOf('C'), com.meteor.extrabotany.common.item.ModItems.gaiatablet);
        elfTablet = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.relicplate), "ABA", "DCD", "ABA", Character.valueOf('A'), com.meteor.extrabotany.common.item.ModItems.fragmentEfirium, Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 9), Character.valueOf('C'), ModBlocks.terraPlate, Character.valueOf('D'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 2));
        relicPanel = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.oghelm), "ABA", "A A", Character.valueOf('A'), "SteelSoul", Character.valueOf('B'), new ItemStack(ModItems.infiniteFruit));
        oghelm = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.ogchest), "A A", "ABA", "AAA", Character.valueOf('A'), "SteelSoul", Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.valkyriecombatuniform));
        ogchest = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.oglegs), "AAA", "ABA", "A A", Character.valueOf('A'), "SteelSoul", Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.eternalslience));
        oglegs = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.ogboots), "A A", "ABA", Character.valueOf('A'), "SteelSoul", Character.valueOf('B'), new ItemStack(ModItems.flightTiara));
        ogboots = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.reader), " AB", " BA", "A  ", Character.valueOf('A'), "livingwoodTwig", Character.valueOf('B'), "ingotManasteel");
        manareader = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.gaiatablet), "AAA", "ABA", "AAA", Character.valueOf('A'), "QuartzGaia", Character.valueOf('B'), ModItems.manaTablet);
        gaiatablet = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addShapelessRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.manapotato), new ItemStack(Items.potato), new ItemStack(Items.redstone), new ItemStack(ModItems.manaResource, 1, 17));
        manapotato = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.angelwand), " AB", " CA", "AD ", Character.valueOf('A'), "dreamwoodTwig", Character.valueOf('B'), "elvenDragonstone", Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.teleportpearl), Character.valueOf('D'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.manapotato));
        angelwand = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.scissorred), "  A", "BA ", "CBD", Character.valueOf('A'), "ingotElvenElementium", Character.valueOf('B'), "nuggetElvenElementium", Character.valueOf('C'), "LycorisRed", Character.valueOf('D'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.manapotato, 1));
        bladered = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.scissorpurple), "  A", "BA ", "CBD", Character.valueOf('A'), "ingotElvenElementium", Character.valueOf('B'), "nuggetElvenElementium", Character.valueOf('C'), "LycorisPurple", Character.valueOf('D'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.manapotato, 1));
        bladepurple = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.gaiawise), "A A", "BCB", "BBB", Character.valueOf('A'), "gaiaIngot", Character.valueOf('B'), "nuggetTerrasteel", Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 10));
        gaiawise = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addShapedRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.teleportpearl, 16), "AAA", "ABA", "AAA", Character.valueOf('A'), new ItemStack(ModItems.manaBottle), Character.valueOf('B'), Items.ender_pearl);
        teleportpearl = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 8, 8), "AAA", "ABA", "AAA", Character.valueOf('A'), "manaString", Character.valueOf('B'), new ItemStack(Items.gold_ingot));
        goldString = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 8, 6), "AAA", "ABA", "AAA", Character.valueOf('A'), "QuartzElementium", Character.valueOf('B'), "EssenceGaia");
        gaiaQuartz = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.gaiaquartz, 1), "AA", "AA", Character.valueOf('A'), "QuartzGaia");
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.gaiaquartzstairs, 4), "A  ", "AA ", "AAA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.gaiaquartz));
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.gaiaquartzstairs, 4), "  A", " AA", "AAA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.gaiaquartz));
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.gaiaquartzslab, 6), "AAA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.gaiaquartz));
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 8, 7), "AAA", "ABA", "AAA", Character.valueOf('A'), new ItemStack(ModItems.quartz, 1, 1), Character.valueOf('B'), "ingotElvenElementium");
        elvenQuartz = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.elvenquartz, 1), "AA", "AA", Character.valueOf('A'), "QuartzElementium");
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.elvenquartzstairs, 4), "A  ", "AA ", "AAA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.elvenquartz));
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.elvenquartzstairs, 4), "  A", " AA", "AAA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.elvenquartz));
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.elvenquartzslab, 6), "AAA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.elvenquartz));
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.dog, 1, 0), "AAA", "ABA", "AAA", Character.valueOf('A'), "StringGold", Character.valueOf('B'), "CardBlank");
        baubleDog0 = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.dog, 1, 1), "AAA", "ABA", "AAA", Character.valueOf('A'), "StringGold", Character.valueOf('B'), "ShardPrismatic");
        baubleDog1 = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.dog, 1, 2), "AAA", "ABA", "AAA", Character.valueOf('A'), "StringGold", Character.valueOf('B'), "QuartzElementium");
        baubleDog2 = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.dog, 1, 3), "AAA", "ABA", "AAA", Character.valueOf('A'), "StringGold", Character.valueOf('B'), "QuartzGaia");
        baubleDog3 = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.SGMini), "ABA", "ACA", "AAA", Character.valueOf('A'), "ingotManasteel", Character.valueOf('B'), "manaDiamond", Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.manapotato));
        sgmini = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.SGEnhanced), "ABA", "ACA", "AAA", Character.valueOf('A'), "ingotTerrasteel", Character.valueOf('B'), "ShardPrismatic", Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.SGMini));
        sgenhanced = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.SGQuick), "ABA", "ACA", "AAA", Character.valueOf('A'), "ingotElvenElementium", Character.valueOf('B'), "ShardPrismatic", Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.SGMini));
        sgquick = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.SGBee), "AAA", "BCB", "AAA", Character.valueOf('A'), "elvenDragonstone", Character.valueOf('B'), "SteelSoul", Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.SGGaia));
        sgbee = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.eternalslience), "AAA", "BCB", "BBB", Character.valueOf('A'), "SteelSoul", Character.valueOf('B'), "manaDiamond", Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.gaiatablet));
        eternalsilence = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.hermeswand), " AB", " CA", "A  ", Character.valueOf('A'), "livingwoodTwig", Character.valueOf('B'), "SteelSoul", Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.teleportpearl));
        hermes = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.valkyriecombatuniform), "A A", "ABA", "AAA", Character.valueOf('A'), "elvenDragonstone", Character.valueOf('B'), "SteelSoul");
        valkyie = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.dagger), "A", "A", "B", Character.valueOf('A'), "manaDiamond", Character.valueOf('B'), "livingwoodTwig");
        dagger = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.maxwelldemon), "ABA", "ACA", "AAA", Character.valueOf('A'), "ingotManasteel", Character.valueOf('B'), "SteelSoul", Character.valueOf('C'), "bEnderAirBottle");
        maxwell = BotaniaAPI.getLatestAddedRecipe();
        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.emptysoulsteel), new ItemStack(com.meteor.extrabotany.common.item.ModItems.castsoulsteel));
        emptysoulsteel = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 11), "A", "B", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.emptysoulsteel), Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 10));
        soulsteel = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.item.ModItems.elfirium, 9, 0), "A", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.blockelfirium));
        elfirium = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.blockelfirium), "AAA", "AAA", "AAA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.elfirium));
        blockelfirium = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.auracontroler), "ABA", "C C", "ABA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.elfirium), Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.awakearmcontrol), Character.valueOf('C'), new ItemStack(Item.getItemFromBlock(Blocks.glass)));
        auracontroler = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.elfpool), " A ", "ABA", " A ", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 11), Character.valueOf('B'), new ItemStack(Item.getItemFromBlock(ModBlocks.pool)));
        elfpool = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.awakeelfupdater), "ABA", "BCB", "ABA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.elfirium), Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 9), Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.awakearmcontrol));
        elfupdater = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.manatrade), "AAA", "ABA", "CDC", Character.valueOf('A'), new ItemStack(Blocks.glass), Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.dog, 1, 2), Character.valueOf('C'), new ItemStack(ModBlocks.pool), Character.valueOf('D'), new ItemStack(Items.redstone));
        manatrade = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.summon), "ABA", "BCB", "ABA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.elfirium), Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.blocksoulsteel), Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 10));
        summon = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.poolefir), "ABA", "BCB", "ABA", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.blockelfirium), Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.blocksoulsteel), Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.elfpool));
        efirpool = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.transformater), " A ", "ABA", "CCC", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 9), Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.efirfragment), Character.valueOf('C'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.blocksoulsteel));
        transformater = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.eAltar), "AAA", "BCB", "DDD", Character.valueOf('A'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.elfirium), Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 9), Character.valueOf('C'), new ItemStack(ModBlocks.altar), Character.valueOf('D'), new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.blocksoulsteel));
        eAltar = BotaniaAPI.getLatestAddedRecipe();
        ModRecipe.addOreDictRecipe(new ItemStack(com.meteor.extrabotany.common.block.ModBlocks.boost), " A ", "ABA", " A ", Character.valueOf('A'), new ItemStack(ModBlocks.livingrock), Character.valueOf('B'), new ItemStack(com.meteor.extrabotany.common.item.ModItems.teleportpearl));
        boost = BotaniaAPI.getLatestAddedRecipe();
    }

    private static void remove() {
        FMLLog.log(Level.INFO, "Removed %d crafting recipes in ALL", CraftingHandler.countCrafting);
        FMLLog.log(Level.INFO, "Removed %d furnace recipes in ALL", CraftingHandler.countFurnace);
    }

    private static void addOreDictRecipe(ItemStack output, Object ... recipe) {
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
    }

    private static void removeCrafting(ItemStack s) {
        CraftingHandler.RemoveCrafting(s.getItem());
    }

    private static void removeFurnace(ItemStack s) {
        CraftingHandler.RemoveFurnace(s);
    }
}

