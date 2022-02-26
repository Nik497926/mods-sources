/*
 * Decompiled with CFR 0.152.
 */
package ru.obvilion.accessoires.server;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import ru.obvilion.accessoires.server.network.NetworkHandler;

public class EventHandler {
    @SubscribeEvent
    public void onJoin(FMLNetworkEvent.ServerConnectionFromClientEvent event) {
        NetHandlerPlayServer theNetHandlerPlayServer = (NetHandlerPlayServer)event.handler;
        EntityPlayerMP player = theNetHandlerPlayServer.playerEntity;

        for (Object _pl : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            EntityPlayerMP pl = (EntityPlayerMP) _pl;

            NetworkHandler.sendStats(pl, player);
        }

        NetworkHandler.sendStats(player, null);
    }
}

