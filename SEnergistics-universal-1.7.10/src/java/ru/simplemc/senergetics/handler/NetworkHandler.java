/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.handler.GuiHandler;
import ru.simplemc.senergetics.handler.packet.PacketChangeMode;
import ru.simplemc.senergetics.handler.packet.PacketDrawExperience;

public class NetworkHandler {
    private static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel("senergetics");

    public static void init(SEnergetics mod) {
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)mod, (IGuiHandler)new GuiHandler());
        channel.registerMessage(PacketDrawExperience.class, PacketDrawExperience.class, 0, Side.SERVER);
        channel.registerMessage(PacketChangeMode.class, PacketChangeMode.class, 1, Side.SERVER);
    }

    public static SimpleNetworkWrapper getChannel() {
        return channel;
    }
}

