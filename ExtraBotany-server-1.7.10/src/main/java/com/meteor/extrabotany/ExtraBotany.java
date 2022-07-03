/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany;

import com.meteor.extrabotany.ExtraBotanyCreativeTab;
import com.meteor.extrabotany.client.inventory.GuiChestHandler;
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
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
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
    @SidedProxy(serverSide="com.meteor.extrabotany.common.CommonProxy", clientSide="com.meteor.extrabotany.client.ClientProxy")
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
    public static Set<String> subtilesForCreativeMenu;
    public static NetworkHandlerAwake networkawake;

    public static void addSubTileToCreativeMenu(String key) {
        subtilesForCreativeMenu.add(key);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)instance, (IGuiHandler)new GuiChestHandler());
        minetweakerLoaded = Loader.isModLoaded((String)"MineTweaker3");
        arsmagicaLoaded = Loader.isModLoaded((String)"arsmagica2");
        candycraftLoaded = Loader.isModLoaded((String)"candycraftmod");
        pamLoaded = Loader.isModLoaded((String)"harvestcraft");
        buildcraftLoaded = Loader.isModLoaded((String)"BuildCraft|Energy");
        appliedenergistics2 = Loader.isModLoaded((String)"appliedenergistics2");
        extracells = Loader.isModLoaded((String)"extracells");
        bloodMagicLoaded = Loader.isModLoaded((String)"AWWayofTime");
        thaumcraftLoaded = Loader.isModLoaded((String)"Thaumcraft");
        alfheimLoaded = Loader.isModLoaded((String)"alfheim");
        witcheryLoaded = Loader.isModLoaded((String)"witchery");
        extraKnowledge = BotaniaAPI.registerKnowledgeType((String)"extra", (EnumChatFormatting)EnumChatFormatting.DARK_AQUA, (boolean)false);
        legendaryKnowledge = BotaniaAPI.registerKnowledgeType((String)"legendary", (EnumChatFormatting)EnumChatFormatting.DARK_RED, (boolean)false);
        proxy.preInit(event);
        NetworkHandler2.init();
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    static {
        modid = "extrabotania";
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

