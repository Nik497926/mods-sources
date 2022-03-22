/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.proxy;

import com.prototype.extraamulets.common.config.Config;
import com.prototype.extraamulets.common.registry.AbilityRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class Proxy {
    public void preInit(FMLPreInitializationEvent event) {
        AbilityRegistry.register();
        Config.load(event.getModLog());
    }
}

