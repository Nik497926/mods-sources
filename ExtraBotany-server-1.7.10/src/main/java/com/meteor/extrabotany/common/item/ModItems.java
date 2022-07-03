/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.item.ItemAdvanceSpark;
import com.meteor.extrabotany.common.item.ItemAngelWand;
import com.meteor.extrabotany.common.item.ItemDungeonBox;
import com.meteor.extrabotany.common.item.ItemElfTablet;
import com.meteor.extrabotany.common.item.ItemGaiaTablet;
import com.meteor.extrabotany.common.item.ItemManaPotato;
import com.meteor.extrabotany.common.item.ItemManaReader;
import com.meteor.extrabotany.common.item.ItemMods;
import com.meteor.extrabotany.common.item.ItemSpawnCardLycorisGreen;
import com.meteor.extrabotany.common.item.ItemSpawnCardLycorisPurple;
import com.meteor.extrabotany.common.item.ItemSpawnCardLycorisRandom;
import com.meteor.extrabotany.common.item.ItemSpawnCardLycorisRed;
import com.meteor.extrabotany.common.item.ItemTeleportPearl;
import com.meteor.extrabotany.common.item.ItemTest;
import com.meteor.extrabotany.common.item.basic.InfoItem;
import com.meteor.extrabotany.common.item.basic.ItemBox;
import com.meteor.extrabotany.common.item.basic.ItemDustOvergrowth;
import com.meteor.extrabotany.common.item.basic.ItemEfirFragment;
import com.meteor.extrabotany.common.item.basic.ItemFragmentEfirium;
import com.meteor.extrabotany.common.item.basic.ItemManaThaum;
import com.meteor.extrabotany.common.item.basic.ItemMaterial;
import com.meteor.extrabotany.common.item.basic.ItemModuleExtraAspects;
import com.meteor.extrabotany.common.item.basic.ItemRecordA;
import com.meteor.extrabotany.common.item.basic.ItemRecordB;
import com.meteor.extrabotany.common.item.basic.ItemRecordC;
import com.meteor.extrabotany.common.item.basic.ItemRecordD;
import com.meteor.extrabotany.common.item.basic.ItemRecordE;
import com.meteor.extrabotany.common.item.basic.ItemToolOvergrowth;
import com.meteor.extrabotany.common.item.basic.awakeArmController;
import com.meteor.extrabotany.common.item.basic.castsoulsteel;
import com.meteor.extrabotany.common.item.basic.elfirium;
import com.meteor.extrabotany.common.item.basic.emptysoulsteel;
import com.meteor.extrabotany.common.item.equipment.ItemBaubleDog;
import com.meteor.extrabotany.common.item.equipment.ItemGaiaWise;
import com.meteor.extrabotany.common.item.equipment.awake.ItemAwakePick;
import com.meteor.extrabotany.common.item.equipment.shield.ItemSGBee;
import com.meteor.extrabotany.common.item.equipment.shield.ItemSGEnhanced;
import com.meteor.extrabotany.common.item.equipment.shield.ItemSGGaia;
import com.meteor.extrabotany.common.item.equipment.shield.ItemSGMini;
import com.meteor.extrabotany.common.item.equipment.shield.ItemSGQuick;
import com.meteor.extrabotany.common.item.relic.legendary.ItemDice6;
import com.meteor.extrabotany.common.item.relic.legendary.ItemEternalSlience;
import com.meteor.extrabotany.common.item.relic.legendary.ItemHermesWand;
import com.meteor.extrabotany.common.item.relic.legendary.ItemMaxwellDemon;
import com.meteor.extrabotany.common.item.relic.legendary.ItemValkyrieCombatUniform;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGBoots;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGChest;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGHelm;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGLegs;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGBoots;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGChest;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGHelm;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGLegs;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerBoots;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerChest;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerHelm;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerLegs;
import com.meteor.extrabotany.common.item.relic.legendary.killerTool.ItemKillerAxe;
import com.meteor.extrabotany.common.item.relic.legendary.killerTool.ItemKillerPick;
import com.meteor.extrabotany.common.item.relic.legendary.killerTool.ItemKillerShovel;
import com.meteor.extrabotany.common.item.relic.legendary.killerTool.ItemKillerSword;
import com.meteor.extrabotany.common.item.system.ItemBasicSkill;
import com.meteor.extrabotany.common.item.system.ItemModulePool;
import com.meteor.extrabotany.common.item.weapon.ItemHeliacalClaymore;
import com.meteor.extrabotany.common.item.weapon.ItemMermaidDagger;
import com.meteor.extrabotany.common.item.weapon.ItemScissorBladePurple;
import com.meteor.extrabotany.common.item.weapon.ItemScissorBladeRed;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {
    public static Item manapotato;
    public static Item gaiatablet;
    public static Item excaliber;
    public static Item lycorisgreen;
    public static Item lycorispurple;
    public static Item lycorisred;
    public static Item lycorisrandom;
    public static Item maxwelldemon;
    public static Item teleportpearl;
    public static Item gaianecklacebroken;
    public static Item dog;
    public static Item hermeswand;
    public static Item eternalslience;
    public static Item valkyriecombatuniform;
    public static Item gaiawise;
    public static Item angelwand;
    public static Item heliacalclaymore;
    public static Item itemtest;
    public static Item material;
    public static Item dungeonbox;
    public static Item boxs;
    public static Item scissorred;
    public static Item scissorpurple;
    public static Item recordB;
    public static Item recordA;
    public static Item recordC;
    public static Item recordD;
    public static Item recordE;
    public static Item key;
    public static Item reader;
    public static Item SGBee;
    public static Item SGGaia;
    public static Item SGEnhanced;
    public static Item SGMini;
    public static Item SGQuick;
    public static Item dagger;
    public static Item oghelm;
    public static Item ogchest;
    public static Item oglegs;
    public static Item ogboots;
    public static Item dice6;
    public static Item castsoulsteel;
    public static Item emptysoulsteel;
    public static Item elfirium;
    public static Item awakearmcontrol;
    public static Item awakeogboots;
    public static Item awakeogchest;
    public static Item awakeoglegs;
    public static Item awakeoghelm;
    public static Item awakepick;
    public static Item skill;
    public static Item efirfragment;
    public static Item killerhelm;
    public static Item killerchest;
    public static Item killerlegs;
    public static Item killerboots;
    public static Item killerSword;
    public static Item killerAxe;
    public static Item killerShovel;
    public static Item killerPick;
    public static Item fragmentEfirium;
    public static Item elfTablet;
    public static Item manaThaum;
    public static Item info;
    public static Item advanceSpark;
    public static Item dust;
    public static Item itemModulePool;
    public static Item moduleExtraAspects;
    public static final String[] SKILLS;

    public static void init() {
        awakepick = new ItemAwakePick();
        skill = new ItemBasicSkill();
        awakeogboots = new ItemAwakeOGBoots();
        awakeogchest = new ItemAwakeOGChest();
        awakeoglegs = new ItemAwakeOGLegs();
        awakeoghelm = new ItemAwakeOGHelm();
        dice6 = new ItemDice6();
        oghelm = new ItemOGHelm();
        ogchest = new ItemOGChest();
        oglegs = new ItemOGLegs();
        ogboots = new ItemOGBoots();
        dagger = new ItemMermaidDagger(Item.ToolMaterial.GOLD, "mermaiddagger");
        SGBee = new ItemSGBee();
        SGGaia = new ItemSGGaia();
        SGEnhanced = new ItemSGEnhanced();
        SGMini = new ItemSGMini();
        SGQuick = new ItemSGQuick();
        reader = new ItemManaReader("manareader");
        recordA = new ItemRecordA("A", "recordA");
        recordB = new ItemRecordB("B", "recordB");
        recordC = new ItemRecordC("C", "recordC");
        recordD = new ItemRecordD("D", "recordD");
        recordE = new ItemRecordE("E", "recordE");
        itemtest = new ItemTest("test");
        dungeonbox = new ItemDungeonBox("dungeonbox");
        boxs = new ItemBox("box");
        scissorred = new ItemScissorBladeRed(Item.ToolMaterial.WOOD, "scissorbladered");
        scissorpurple = new ItemScissorBladePurple(Item.ToolMaterial.WOOD, "scissorbladepurple");
        material = new ItemMaterial("material");
        heliacalclaymore = new ItemHeliacalClaymore();
        dog = new ItemBaubleDog();
        gaiawise = new ItemGaiaWise("gaiawise");
        angelwand = new ItemAngelWand("angelwand");
        maxwelldemon = new ItemMaxwellDemon();
        hermeswand = new ItemHermesWand("hermeswand");
        eternalslience = new ItemEternalSlience("eternalslience");
        valkyriecombatuniform = new ItemValkyrieCombatUniform("valkyriecombatuniform");
        manapotato = new ItemManaPotato("manapotato");
        gaiatablet = new ItemGaiaTablet();
        elfTablet = new ItemElfTablet();
        castsoulsteel = new castsoulsteel("castsoulsteel");
        emptysoulsteel = new emptysoulsteel("emptysoulsteel");
        teleportpearl = new ItemTeleportPearl("teleportpearl");
        lycorisgreen = new ItemSpawnCardLycorisGreen("lycorisgreen");
        lycorisred = new ItemSpawnCardLycorisRed("lycorisred");
        lycorispurple = new ItemSpawnCardLycorisPurple("lycorispurple");
        lycorisrandom = new ItemSpawnCardLycorisRandom("lycorisrandom");
        key = new ItemMods("key");
        elfirium = new elfirium("elfirium");
        awakearmcontrol = new awakeArmController("awakearmcontrol");
        efirfragment = new ItemEfirFragment("efirfragment");
        killerhelm = new ItemKillerHelm();
        killerchest = new ItemKillerChest();
        killerlegs = new ItemKillerLegs();
        killerboots = new ItemKillerBoots();
        killerSword = new ItemKillerSword();
        killerAxe = new ItemKillerAxe();
        killerShovel = new ItemKillerShovel();
        killerPick = new ItemKillerPick();
        fragmentEfirium = new ItemFragmentEfirium("fragmentEfirium");
        if (!ExtraBotany.alfheimLoaded) {
            manaThaum = new ItemManaThaum("manaThaum");
        }
        info = new InfoItem();
        advanceSpark = new ItemAdvanceSpark();
        dust = new ItemDustOvergrowth();
        itemModulePool = new ItemModulePool();
        moduleExtraAspects = new ItemModuleExtraAspects();
        new ItemToolOvergrowth();
        OreDictionary.registerOre((String)"dog", (ItemStack)new ItemStack(dog, 1, 0));
        OreDictionary.registerOre((String)"dog", (ItemStack)new ItemStack(dog, 1, 1));
        OreDictionary.registerOre((String)"dog", (ItemStack)new ItemStack(dog, 1, 2));
        OreDictionary.registerOre((String)"dog", (ItemStack)new ItemStack(dog, 1, 3));
        OreDictionary.registerOre((String)"ShardPrismatic", (ItemStack)new ItemStack(material, 1, 0));
        OreDictionary.registerOre((String)"CardBlank", (ItemStack)new ItemStack(material, 1, 1));
        OreDictionary.registerOre((String)"EssenceGaia", (ItemStack)new ItemStack(material, 1, 2));
        OreDictionary.registerOre((String)"LycorisRed", (ItemStack)new ItemStack(material, 1, 3));
        OreDictionary.registerOre((String)"LycorisGreen", (ItemStack)new ItemStack(material, 1, 4));
        OreDictionary.registerOre((String)"LycorisPurple", (ItemStack)new ItemStack(material, 1, 5));
        OreDictionary.registerOre((String)"QuartzGaia", (ItemStack)new ItemStack(material, 1, 6));
        OreDictionary.registerOre((String)"QuartzElementium", (ItemStack)new ItemStack(material, 1, 7));
        OreDictionary.registerOre((String)"StringGold", (ItemStack)new ItemStack(material, 1, 8));
        OreDictionary.registerOre((String)"FragmentResonance", (ItemStack)new ItemStack(material, 1, 9));
        OreDictionary.registerOre((String)"HeartRebel", (ItemStack)new ItemStack(material, 1, 10));
        OreDictionary.registerOre((String)"SteelSoul", (ItemStack)new ItemStack(material, 1, 11));
        OreDictionary.registerOre((String)"oreEfirium", (ItemStack)new ItemStack(ModBlocks.elfOre));
        for (int i = 0; i < 16; ++i) {
            OreDictionary.registerOre((String)SKILLS[i], (ItemStack)new ItemStack(skill, 1, i));
        }
    }

    static {
        SKILLS = new String[]{"skill0", "skill1", "skill2", "skill3", "skill4", "skill5", "skill6", "skill7", "skill8", "skill9", "skill10", "skill11", "skill12", "skill13", "skill14", "skill15", "skill16"};
    }
}

