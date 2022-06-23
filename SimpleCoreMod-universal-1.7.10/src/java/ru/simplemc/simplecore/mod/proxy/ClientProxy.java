/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import ru.simplemc.simplecore.mod.client.gui.toast.ToastManager;
import ru.simplemc.simplecore.mod.client.radio.RadioPlayerController;
import ru.simplemc.simplecore.mod.client.renderer.TileEntityTradeStationRenderer;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;
import ru.simplemc.simplecore.mod.handler.KeyHandler;
import ru.simplemc.simplecore.mod.proxy.CommonProxy;

public class ClientProxy
extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        this.registerEventHandler(new RadioPlayerController());
        this.registerEventHandler(new ToastManager());
        this.registerEventHandler(new KeyHandler());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTradeStation.class, (TileEntitySpecialRenderer)new TileEntityTradeStationRenderer());
    }
}

