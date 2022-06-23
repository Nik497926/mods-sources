/*
 * Decompiled with CFR 0.152.
 */
package ru.obvilion.cpanel.server.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.Unpooled;
import ru.obvilion.cpanel.CPanelMod;
import ru.obvilion.cpanel.server.ServerProxy;
import ru.obvilion.cpanel.server.network.packet.IPacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class NetworkHandler {
    public static final String channel = "CPM";

    public static void sendPacket(IPacket packet, EntityPlayerMP player) {
        ((ServerProxy) CPanelMod.proxy).channel
                .sendTo(new FMLProxyPacket(Unpooled.copiedBuffer(packet.getBytes()), channel), player);
    }

    public static void sendPacket(IPacket packet) {
        ((ServerProxy) CPanelMod.proxy).channel
                .sendToAll(new FMLProxyPacket(Unpooled.copiedBuffer(packet.getBytes()), channel));
    }

    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(event.packet.payload().array());
            DataInputStream dis = new DataInputStream(inputStream);
            String type = dis.readUTF();

            NetHandlerPlayServer theNetHandlerPlayServer = (NetHandlerPlayServer) event.handler;
            EntityPlayerMP player = theNetHandlerPlayServer.playerEntity;

            IPacketHandler packetHandler = Packets.packets.get(type);
            if (packetHandler == null) {
                return;
            }

            packetHandler.handle(dis, player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

