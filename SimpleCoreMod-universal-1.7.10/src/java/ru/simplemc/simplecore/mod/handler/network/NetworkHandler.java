package ru.simplemc.simplecore.mod.handler.network;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;

import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.handler.GuiHandler;
import ru.simplemc.simplecore.mod.handler.network.message.*;

public class NetworkHandler {
    public static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel("simplecore");

    public static void registerMessages() {
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)SimpleCore.INSTANCE, (IGuiHandler)new GuiHandler());

        if (FMLLaunchHandler.side().isClient()) {
            NETWORK_WRAPPER.registerMessage(TradeSyncMessageHandler.class, TradeSyncMessage.class, 0, Side.CLIENT);
            NETWORK_WRAPPER.registerMessage(TradeGuiNotifyMessageHandler.class, TradeGuiNotifyMessage.class, 4, Side.CLIENT);
        }

        NETWORK_WRAPPER.registerMessage(TradePurchaseMessageHandler.class, TradePurchaseMessage.class, 1, Side.SERVER);
        NETWORK_WRAPPER.registerMessage(TradeSettingsApplyMessageHandler.class, TradeSettingsApplyMessage.class, 2, Side.SERVER);
        NETWORK_WRAPPER.registerMessage(TradeStationInventoryMessageHandler.class, TradeStationInventoryMessage.class, 3, Side.SERVER);
        NETWORK_WRAPPER.registerMessage(BackpackLockedMessageHandler.class, BackpackLockedMessage.class, 5, Side.SERVER);
    }
}

