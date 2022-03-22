/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network;

import com.meteor.extrabotany.common.core.network.PacketProcessorServer;
import com.meteor.extrabotany.common.core.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;

public class NetworkHandler {
    private static final String ChannelLabel = "ExtraBotaniaDataTunnel";
    private static FMLEventChannel Channel;
    private boolean registeredChannels = false;
    public static final NetworkHandler INSTANCE;

    public void init() {
        Channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(ChannelLabel);
    }

    public void registerChannels(PacketProcessorServer proc) {
        if (!this.registeredChannels) {
            this.registeredChannels = true;
            Channel.register(proc);
            FMLCommonHandler.instance().bus().register(proc);
        } else {
            LogHelper.info("Redundant call to register channels.");
        }
    }

    public void sendPacketToClientPlayer(EntityPlayerMP player, byte packetID, byte[] data) {
        byte[] pkt_data = new byte[data.length + 1];
        pkt_data[0] = packetID;
        for (int packet = 0; packet < data.length; ++packet) {
            pkt_data[packet + 1] = data[packet];
        }
        FMLProxyPacket var6 = new FMLProxyPacket(Unpooled.copiedBuffer(pkt_data), ChannelLabel);
        var6.setTarget(Side.CLIENT);
        Channel.sendTo(var6, player);
    }

    public void sendPacketToServer(byte packetID, byte[] data) {
        byte[] pkt_data = new byte[data.length + 1];
        pkt_data[0] = packetID;
        for (int packet = 0; packet < data.length; ++packet) {
            pkt_data[packet + 1] = data[packet];
        }
        FMLProxyPacket var5 = new FMLProxyPacket(Unpooled.copiedBuffer(pkt_data), ChannelLabel);
        var5.setTarget(Side.SERVER);
        Channel.sendToServer(var5);
    }

    public static void sendToServer(IMessage message) {
        NetworkHandler.sendToServer(message);
    }

    public void sendPacketToAllClientsNear(int dimension, double ox, double oy, double oz, double radius, byte packetID, byte[] data) {
        byte[] pkt_data = new byte[data.length + 1];
        pkt_data[0] = packetID;
        for (int packet = 0; packet < data.length; ++packet) {
            pkt_data[packet + 1] = data[packet];
        }
        FMLProxyPacket var14 = new FMLProxyPacket(Unpooled.copiedBuffer(pkt_data), ChannelLabel);
        var14.setTarget(Side.CLIENT);
        Channel.sendToAllAround(var14, new NetworkRegistry.TargetPoint(dimension, ox, oy, oz, radius));
    }

    static {
        INSTANCE = new NetworkHandler();
    }
}

