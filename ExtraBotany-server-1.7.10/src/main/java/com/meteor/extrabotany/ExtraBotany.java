/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany;

import com.meteor.extrabotany.common.CommonProxy;
import com.meteor.extrabotany.common.core.network.NetworkHandler2;
import com.meteor.extrabotany.common.core.network.NetworkHandlerAwake;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import java.util.LinkedHashSet;
import java.util.Set;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.KnowledgeType;

@Mod(modid="ExtraBotania", name="ExtraBotania", version="r1.0-21", dependencies="required-after:Botania;after:Baubles")
public class ExtraBotany {
    @Mod.Instance(value="ExtraBotania")
    public static ExtraBotany instance;
    @SidedProxy(serverSide="com.meteor.extrabotany.common.CommonProxy")
    public static CommonProxy proxy;
    public static String modid;
    public static boolean arsmagicaLoaded;
    public static boolean candycraftLoaded;
    public static boolean pamLoaded;
    public static boolean buildcraftLoaded;
    public static boolean thaumcraftLoaded;
    public static boolean minetweakerLoaded;
    public static boolean appliedenergistics2;
    public static boolean extracells;
    public static boolean bloodMagicLoaded;
    public static boolean alfheimLoaded;
    public static boolean witcheryLoaded;
    public static KnowledgeType extraKnowledge;
    public static KnowledgeType legendaryKnowledge;
    public static final ExtraBotanyCreativeTab tabExtraBotany;
    public static Set subtilesForCreativeMenu;
    public static NetworkHandlerAwake networkawake;

    public static void addSubTileToCreativeMenu(String key) {
        subtilesForCreativeMenu.add(key);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        minetweakerLoaded = Loader.isModLoaded("MineTweaker3");
        arsmagicaLoaded = Loader.isModLoaded("arsmagica2");
        candycraftLoaded = Loader.isModLoaded("candycraftmod");
        pamLoaded = Loader.isModLoaded("harvestcraft");
        buildcraftLoaded = Loader.isModLoaded("BuildCraft|Energy");
        appliedenergistics2 = Loader.isModLoaded("appliedenergistics2");
        extracells = Loader.isModLoaded("extracells");
        bloodMagicLoaded = Loader.isModLoaded("AWWayofTime");
        thaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
        alfheimLoaded = Loader.isModLoaded("alfheim");
        witcheryLoaded = Loader.isModLoaded("witchery");
        extraKnowledge = BotaniaAPI.registerKnowledgeType("extra", EnumChatFormatting.DARK_AQUA, false);
        legendaryKnowledge = BotaniaAPI.registerKnowledgeType("legendary", EnumChatFormatting.DARK_RED, false);
        proxy.preInit(event);
        NetworkHandler2.init();
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    static {
        modid = "ExtraBotania";
        arsmagicaLoaded = false;
        candycraftLoaded = false;
        pamLoaded = false;
        buildcraftLoaded = false;
        thaumcraftLoaded = false;
        minetweakerLoaded = false;
        appliedenergistics2 = false;
        extracells = false;
        bloodMagicLoaded = false;
        alfheimLoaded = false;
        witcheryLoaded = false;
        tabExtraBotany = new ExtraBotanyCreativeTab();
        subtilesForCreativeMenu = new LinkedHashSet();
    }
}

