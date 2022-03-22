/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.side.client.proxy;

import com.prototype.extraamulets.common.proxy.Proxy;
import com.prototype.extraamulets.side.client.render.handler.RenderAmuletHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(value=Side.CLIENT)
public final class ClientProxy
extends Proxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register((Object)new RenderAmuletHandler());
    }
}

