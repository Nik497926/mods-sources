/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import javax.annotation.Nonnull;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;
import ru.simplemc.senergetics.client.gui.tabs.GuiCreativeTabs;
import ru.simplemc.senergetics.handler.ChunkLoadingCallbackHandler;
import ru.simplemc.senergetics.handler.NetworkHandler;
import ru.simplemc.senergetics.handler.ServerEventHandler;

@Mod(modid="senergetics", name="SEnergetics", version="2.0.2")
public class SEnergetics {
    public static final String MODID = "senergetics";
    public static final String VERSION = "2.0.2";
    public static final GuiCreativeTabs CREATIVE_TAB = new GuiCreativeTabs();
    private static SEnergetics instance;
    private static SEnergeticsConfig config;
    private static Logger logger;

    public SEnergetics() {
        instance = this;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        config = SEnergeticsConfig.init(event);
        //SEnergetics.registerSpecialRenderers(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        SEnergeticsRegistry.init();
        NetworkHandler.init(this);
        SEnergetics.registerEventHandler(new ServerEventHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ForgeChunkManager.setForcedChunkLoadingCallback((Object)instance, (ForgeChunkManager.LoadingCallback)new ChunkLoadingCallbackHandler());
    }

//    private static void registerSpecialRenderers(@Nonnull FMLPreInitializationEvent event) {
//        if (FMLCommonHandler.instance().getSide().isServer()) {
//            return;
//        }
//
//        NEIConfigurer neiConfigurer = new NEIConfigurer();
//        neiConfigurer.loadConfig();
//        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawner.class, (TileEntitySpecialRenderer)new TileEntitySilentSpawnerRenderer());
//        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChunkLoader.class, (TileEntitySpecialRenderer)new TileEntityChunkLoaderRenderer());
//    }

    private static void registerEventHandler(@Nonnull Object eventHandler) {
        MinecraftForge.EVENT_BUS.register(eventHandler);
        FMLCommonHandler.instance().bus().register(eventHandler);
    }

    public static SEnergetics getInstance() {
        return instance;
    }

    public static SEnergeticsConfig getConfig() {
        return config;
    }

    public static Logger getLogger() {
        return logger;
    }
}

