/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="accessories", version="1.0")
public class AccessoriesCore {
    public static final String MODID = "accessories";
    public static final String VERSION = "1.0";
    public static final boolean DEBUG = true;

    @SidedProxy(serverSide="net.frozor.accessories.server.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(value="accessories")
    public static AccessoriesCore instance;

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}

