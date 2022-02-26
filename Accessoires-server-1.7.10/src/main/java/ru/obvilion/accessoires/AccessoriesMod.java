/*
 * Decompiled with CFR 0.152.
 */
package ru.obvilion.accessoires;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ru.obvilion.accessoires.data.AccessoryItem;

@Mod(modid="accessories", version="1.0")
public class AccessoriesMod {
    public static final String MODID = "accessories";
    public static final String VERSION = "1.0";
    public static final boolean DEBUG = true;
    public static final String TOKEN = "";

    @SidedProxy(serverSide="ru.obvilion.accessoires.server.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(value="accessories")
    public static AccessoriesMod instance;

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        AccessoryItem.load();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}

