/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.deathcry.deathtrade.barter.BarterHolder;
import ru.deathcry.deathtrade.proxy.CommonProxy;

@Mod(modid="deathtrade", version="6.6.6", name="DeathTrade")
public class Main {
    public static final boolean SERVER = true;
    public static final String MODID = "deathtrade";
    public static final String MOD_NAME = "DeathTrade";
    public static final String VERSION = "6.6.6";
    @Mod.Instance(value="deathtrade")
    public static Main instance;
    public static final Logger logger;
    @SidedProxy(clientSide="ru.deathcry.deathtrade.proxy.ClientProxy", serverSide="ru.deathcry.deathtrade.proxy.ServerProxy")
    public static CommonProxy proxy;
    public static Map<EntityPlayer, BarterHolder> trades;

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandHandler());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    static {
        logger = LogManager.getLogger((String)MODID);
        trades = new HashMap<EntityPlayer, BarterHolder>();
    }
}

