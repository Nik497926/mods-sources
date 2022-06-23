/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import ru.simplemc.simplecore.mod.proxy.CommonProxy;

@Mod(modid="simplecore", name="SimpleCore", version="1.0.3")
public class SimpleCore {
    public static final String MOD_ID = "simplecore";
    public static final String MOD_NAME = "SimpleCore";
    public static final String VERSION = "1.0.3";
    @Mod.Instance(value="simplecore")
    public static SimpleCore INSTANCE;
    @SidedProxy(clientSide="ru.simplemc.simplecore.mod.proxy.ClientProxy", serverSide="ru.simplemc.simplecore.mod.proxy.ServerProxy")
    public static CommonProxy PROXY;
    public static Logger LOGGER;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }
}

