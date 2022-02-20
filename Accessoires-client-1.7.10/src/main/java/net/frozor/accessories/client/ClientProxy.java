/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.frozor.accessories.CommonProxy;
import net.frozor.accessories.client.EventHandler;
import net.frozor.accessories.client.data.EquipManager;
import net.frozor.accessories.client.data.ModelLoader;
import net.frozor.accessories.client.network.NetworkHandler;
import net.frozor.accessories.client.ui.UIItem;
import net.frozor.accessories.client.ui.UIScroll;
import net.frozor.accessories.utils.Logger;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy
extends CommonProxy {
    public static EquipManager equipManager;

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Logger.info(UIItem.l("j"));
        Logger.info(UIScroll.l("j\u0004H\u0002X\u0014D\u0015B\u0002XGf\bOGG\bJ\u0003B\tLI\u0005I"));
        Logger.info(UIItem.l("j"));
        EventHandler handler = new EventHandler();
        MinecraftForge.EVENT_BUS.register((Object)handler);
        FMLCommonHandler.instance().bus().register((Object)handler);
        FMLEventChannel ch = NetworkRegistry.INSTANCE.newEventDrivenChannel("ACS");
        ch.register((Object)new NetworkHandler());
        equipManager = new EquipManager();
        ClientProxy.equipManager.items = ModelLoader.initModels();
        Logger.info(UIScroll.l("G"));
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }
}

