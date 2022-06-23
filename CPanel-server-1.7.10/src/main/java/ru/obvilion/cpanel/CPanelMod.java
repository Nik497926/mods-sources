/*
 * Decompiled with CFR 0.152.
 */
package ru.obvilion.cpanel;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid= CPanelMod.MODID, version= CPanelMod.VERSION)
public class CPanelMod {
    public static final String MODID = "cpanel";
    public static final String VERSION = "1.0.0";
    public static final boolean DEBUG = true;

    @SidedProxy(serverSide="ru.obvilion.cpanel.server.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(value= CPanelMod.MODID)
    public static CPanelMod instance;

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

