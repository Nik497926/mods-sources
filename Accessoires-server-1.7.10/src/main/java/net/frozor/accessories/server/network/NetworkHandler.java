/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.server.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.Unpooled;
import net.frozor.accessories.AccessoriesCore;
import net.frozor.accessories.data.AccessoryItem;
import net.frozor.accessories.server.ServerProxy;
import net.frozor.accessories.server.network.packet.CPacketBalance;
import net.frozor.accessories.server.network.packet.CPacketItemsInfo;
import net.frozor.accessories.server.network.packet.IPacket;
import net.frozor.accessories.utils.Logger;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class NetworkHandler {
    private static String channel = "ACS";

    public static void sendPacket(IPacket packet, EntityPlayerMP player) {
        ((ServerProxy) AccessoriesCore.proxy).channel
                .sendTo(new FMLProxyPacket(Unpooled.copiedBuffer(packet.getBytes()), channel), player);
    }

    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(event.packet.payload().array());
            DataInputStream dis = new DataInputStream(inputStream);
            String type = dis.readUTF();

            NetHandlerPlayServer theNetHandlerPlayServer = (NetHandlerPlayServer)event.handler;
            EntityPlayerMP player = theNetHandlerPlayServer.playerEntity;

            switch (type) {
                case "ACS:SHOW_CAPE":
                    boolean showCape = dis.readBoolean();
                    boolean firstJoin = dis.readBoolean();

                    Logger.debug(player.getDisplayName() + " отправил пакет ACS:SHOW_CAPE [" + showCape + "," + firstJoin + "]");

                    if (firstJoin) {
                        Logger.debug("Отправляю пакет ACS:ITEMS_INFO [" + AccessoryItem.ALL_INSTANCES.size() + " обьектов] для " + player.getDisplayName());
                        sendPacket(new CPacketItemsInfo(), player);
                    }

                    sendBalanceTo(player);
                    break;
                case "ACS:GET_BALANCE":
                    Logger.debug(player.getDisplayName() + " отправил пакет ACS:GET_BALANCE");

                    sendBalanceTo(player);
                    break;
                case "ACS:PURCHASE":
                    String item = dis.readUTF();

                    Logger.debug(player.getDisplayName() + " отправил пакет ACS:PURCHASE [" + item + "]");

                    sendBalanceTo(player);
                    break;
                default:
                    Logger.debug("Неверный тип пакета аксессуара! Тип: " + type);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendBalanceTo(EntityPlayerMP player) {
        // TODO: Get balance
        int balance = 100;

        Logger.debug("Отправляю пакет ACS:BALANCE [" + balance + "] для " + player.getDisplayName());

        CPacketBalance packet = new CPacketBalance(balance);
        sendPacket(packet, player);
    }
}

