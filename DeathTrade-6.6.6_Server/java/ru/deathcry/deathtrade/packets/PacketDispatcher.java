/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade.packets;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import ru.deathcry.deathtrade.packets.client.ActionMessage;
import ru.deathcry.deathtrade.packets.client.MoneyChangeMessage;
import ru.deathcry.deathtrade.packets.server.ExitMessage;
import ru.deathcry.deathtrade.packets.server.MoneyNotify;
import ru.deathcry.deathtrade.packets.server.NotifyTrader;
import ru.deathcry.deathtrade.packets.server.SendBalance;
import ru.deathcry.deathtrade.packets.server.SendOpponentName;

public class PacketDispatcher {
    private static byte packetId = 0;
    private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("deathtrade");

    public static final void registerPackets() {
        PacketDispatcher.registerMessage(ActionMessage.class);
        PacketDispatcher.registerMessage(MoneyChangeMessage.class);
        PacketDispatcher.registerMessage(NotifyTrader.class);
        PacketDispatcher.registerMessage(ExitMessage.class);
        PacketDispatcher.registerMessage(MoneyNotify.class);
        PacketDispatcher.registerMessage(SendBalance.class);
        PacketDispatcher.registerMessage(SendOpponentName.class);
    }

    private static final void registerMessage(Class clazz) {
        byte by = packetId;
        packetId = (byte)(by + 1);
        dispatcher.registerMessage(clazz, clazz, (int)by, Side.CLIENT);
        byte by2 = packetId;
        packetId = (byte)(by2 + 1);
        dispatcher.registerMessage(clazz, clazz, (int)by2, Side.SERVER);
    }

    public static final void sendTo(IMessage message, EntityPlayerMP player) {
        dispatcher.sendTo(message, player);
    }

    public static void sendToAll(IMessage message) {
        dispatcher.sendToAll(message);
    }

    public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
        dispatcher.sendToAllAround(message, point);
    }

    public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) {
        PacketDispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
    }

    public static final void sendToAllAround(IMessage message, EntityPlayer player, double range) {
        PacketDispatcher.sendToAllAround(message, player.worldObj.provider.dimensionId, player.posX, player.posY, player.posZ, range);
    }

    public static final void sendToDimension(IMessage message, int dimensionId) {
        dispatcher.sendToDimension(message, dimensionId);
    }

    public static final void sendToServer(IMessage message) {
        dispatcher.sendToServer(message);
    }
}

