/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets;

import com.prototype.extraamulets.common.proxy.Proxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid="extraamulets", name="Extra Amulets", version="1.0", dependencies="required-after:Baubles")
public final class ExtraAmuletsMod {
    @Mod.Instance(value="extraamulets")
    public static ExtraAmuletsMod instance;
    @SidedProxy(clientSide="com.prototype.extraamulets.side.client.proxy.ClientProxy", serverSide="com.prototype.extraamulets.side.server.proxy.ServerProxy")
    public static Proxy proxy;
    private static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    public static Logger getLogger() {
        return logger;
    }
}

