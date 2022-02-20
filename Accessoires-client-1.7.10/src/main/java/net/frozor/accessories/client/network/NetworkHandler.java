/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import net.frozor.accessories.client.ClientProxy;
import net.frozor.accessories.client.network.packet.IPacket;
import net.frozor.accessories.client.ui.UIAccessorySidebar;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class NetworkHandler {
    private static Minecraft minecraft = Minecraft.getMinecraft();
    private static String channel = "ACS";

    public static void sendPacket(IPacket packet) {
        if (minecraft.getNetHandler() != null) {
            minecraft.getNetHandler().addToSendQueue(new C17PacketCustomPayload(channel, packet.getBytes()));
        }
    }

    private void onPacketItemsEquip(DataInputStream dis) throws Exception {
        String playerName = dis.readUTF();
        ClientProxy.equipManager.setHead(playerName, dis.readUTF());
        ClientProxy.equipManager.setFace(playerName, dis.readUTF());
        ClientProxy.equipManager.setBody(playerName, dis.readUTF());
        ClientProxy.equipManager.setFamiliar(playerName, dis.readUTF());
        ClientProxy.equipManager.setShowCape(playerName, dis.readBoolean());
        if (NetworkHandler.minecraft.currentScreen instanceof UIAccessorySidebar) {
            ((UIAccessorySidebar)NetworkHandler.minecraft.currentScreen).updateButtons();
        }
    }
    
    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event) {
        try {
            boolean var10003 = true;
            boolean var10004 = true;
            boolean var10005 = true;
            boolean var10006 = true;
            ByteArrayInputStream inputStream = new ByteArrayInputStream(event.packet.payload().array());
            DataInputStream dis = new DataInputStream(inputStream);
            String var4 = dis.readUTF();
            byte var5 = -1;
            switch(var4.hashCode()) {
            case -1616061144:
                if (var4.equals("ACS:ITEMS_EQUIP")) {
                    var5 = 1;
                }
                break;
            case -883299178:
                if (var4.equals("ACS:ITEMS_INFO")) {
                    var5 = 0;
                }
                break;
            case 244820909:
                if (var4.equals("ACS:REMOVE")) {
                    var5 = 3;
                }
                break;
            case 1863458995:
                if (var4.equals("ACS:BALANCE")) {
                    var5 = 2;
                }
            }

            switch(var5) {
            case 0:
                this.onPacketItemsInfo(dis);
                break;
            case 1:
                this.onPacketItemsEquip(dis);
                break;
            case 2:
                ClientProxy.equipManager.setUserBalance(dis.readInt());
                break;
            case 3:
                String player = dis.readUTF();
                ClientProxy.equipManager.getPlayers().remove(player);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }
    }

    private void onPacketItemsInfo(DataInputStream dis) throws Exception {
        int count = dis.readInt();
        for (int i = 0; i < count; ++i) {
            ClientProxy.equipManager.updateItemPrice(dis.readUTF(), dis.readInt(), dis.readBoolean(), dis.readUTF());
        }
        if (NetworkHandler.minecraft.currentScreen instanceof UIAccessorySidebar) {
            ((UIAccessorySidebar)NetworkHandler.minecraft.currentScreen).updateButtons();
        }
    }
}

