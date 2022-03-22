/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class PacketProcessorServer {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SubscribeEvent
    public void onServerPacketData(FMLNetworkEvent.ServerCustomPacketEvent event) {
        ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
        int packetID = -1;
        try {
            if (event.packet.getTarget() == Side.SERVER) {
                packetID = bbis.readByte();
                NetHandlerPlayServer t = (NetHandlerPlayServer)event.packet.handler();
                EntityPlayerMP player = t.playerEntity;
                byte[] remaining = new byte[bbis.available()];
                bbis.readFully(remaining);
                return;
            }
        }
        catch (Throwable var16) {
            var16.printStackTrace();
            return;
        }
        finally {
            try {
                if (bbis != null) {
                    bbis.close();
                }
            }
            catch (Throwable var15) {
                var15.printStackTrace();
            }
        }
    }
}

