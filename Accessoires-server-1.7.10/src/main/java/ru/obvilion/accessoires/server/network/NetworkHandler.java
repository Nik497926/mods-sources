/*
 * Decompiled with CFR 0.152.
 */
package ru.obvilion.accessoires.server.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.Unpooled;
import ru.obvilion.accessoires.AccessoriesMod;
import ru.obvilion.accessoires.data.AccessoryItem;
import ru.obvilion.accessoires.server.ServerProxy;
import ru.obvilion.accessoires.server.network.packet.CPacketBalance;
import ru.obvilion.accessoires.server.network.packet.CPacketItemsEquip;
import ru.obvilion.accessoires.server.network.packet.CPacketItemsInfo;
import ru.obvilion.accessoires.server.network.packet.IPacket;
import ru.obvilion.accessoires.utils.Logger;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import ru.obvilion.api.inject.InjectionManager;
import ru.obvilion.api.inject.permissions.api.IUser;

public class NetworkHandler {
    private static String channel = "ACS";

    public static void sendPacket(IPacket packet, EntityPlayerMP player) {
        ((ServerProxy) AccessoriesMod.proxy).channel
                .sendTo(new FMLProxyPacket(Unpooled.copiedBuffer(packet.getBytes()), channel), player);
    }

    public static void sendPacket(IPacket packet) {
        ((ServerProxy) AccessoriesMod.proxy).channel
                .sendToAll(new FMLProxyPacket(Unpooled.copiedBuffer(packet.getBytes()), channel));
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
                        sendItemsTo(player);
                    }

                    sendBalanceTo(player);
                    break;
                case "ACS:GET_BALANCE":
                    Logger.debug(player.getDisplayName() + " отправил пакет ACS:GET_BALANCE");

                    sendBalanceTo(player);
                    break;
                case "ACS:PURCHASE":
                    String item = dis.readUTF();
                    AccessoryItem it = AccessoryItem.get(item);

                    Logger.debug(player.getDisplayName() + " отправил пакет ACS:PURCHASE [" + item + "]");

                    if (it == null) {
                        Logger.debug("Ошибка! Предмет не найден в закэшированном массиве.");
                        return;
                    }

                    ArrayList<AccessoryItem> result = new ArrayList<>();
                    AccessoryItem i = AccessoryItem.copy(it); i.has = true;
                    result.add(i);

                    sendItemsTo(player, result);
                    sendBalanceTo(player);
                    buyItem(player, i);

                    break;
                case "ACS:EQUIP":
                    String item1 = dis.readUTF();
                    AccessoryItem it1 = AccessoryItem.get(item1);

                    Logger.debug(player.getDisplayName() + " отправил пакет ACS:EQUIP [" + item1 + "]");

                    if (it1 == null) {
                        Logger.debug("Ошибка! Предмет не найден в закэшированном массиве.");
                        return;
                    }

                    equip(player, it1);

                    break;
                case "ACS:TAKEOFF":
                    String type1 = dis.readUTF();
                    Logger.debug(player.getDisplayName() + " отправил пакет ACS:TAKEOFF [" + type1 + "]");

                    takeoff(player, type1);

                    break;
                default:
                    Logger.debug("Неверный тип пакета аксессуара! Тип: " + type);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendBalanceTo(EntityPlayerMP player) {
        // TODO: Get balance
        int balance = 100;

        Logger.debug("Отправляю пакет ACS:BALANCE [" + balance + "] для " + player.getDisplayName());

        CPacketBalance packet = new CPacketBalance(balance);
        sendPacket(packet, player);
    }

    public static void sendStats(EntityPlayerMP player, EntityPlayerMP to) {
        new Thread(() -> {
            try {
                URL url = new URL("https://obvilion.ru/api/game/accessories/profile?player=" + player.getDisplayName());
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.setRequestMethod("GET");
                request.setRequestProperty("Authorization", AccessoriesMod.TOKEN);
                request.connect();

                InputStream in;
                if (request.getResponseCode() >= 400) {
                    in = request.getErrorStream();
                } else {
                    in = request.getInputStream();
                }

                JsonParser jp = new JsonParser();
                JsonObject root = jp.parse(new InputStreamReader(in)).getAsJsonObject();

                if (request.getResponseCode() >= 400) {
                    Logger.debug(root.toString());
                }

                String familiar = root.has("familiarId") && !root.get("familiarId").isJsonNull() ? root.get("familiarId").getAsString() : "NONE";
                String body = root.has("bodyId") && !root.get("bodyId").isJsonNull() ? root.get("bodyId").getAsString() : "NONE";
                String face = root.has("faceId") && !root.get("faceId").isJsonNull() ? root.get("faceId").getAsString() : "NONE";
                String head = root.has("headId") && !root.get("headId").isJsonNull() ? root.get("headId").getAsString() : "NONE";

                CPacketItemsEquip packet = new CPacketItemsEquip(player.getDisplayName(), head, face, body, familiar);

                if (to == null) {
                    Logger.debug("Отправляю пакет всем ACS:ITEMS_EQUIP [head: " + head + ", face: " + face +
                            ", body: " + body + ", familiar: " + familiar + "] игрока " + player.getDisplayName());

                    sendPacket(packet);
                } else {
                    Logger.debug("Отправляю пакет ACS:ITEMS_EQUIP [head: " + head + ", face: " + face +
                            ", body: " + body + ", familiar: " + familiar + "] для игрока " + player.getDisplayName());

                    sendPacket(packet, to);
                }
            } catch (Exception e) {
                Logger.info("Unable to update Accessories profile!");
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        }, "Obvilion.ru API equip Thread").start();
    }

    public static void buyItem(EntityPlayerMP player, AccessoryItem item) {
        new Thread(() -> {
            try {
                URL url = new URL("https://obvilion.ru/api/game/accessories");
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.setRequestMethod("POST");
                request.setRequestProperty("Authorization", AccessoriesMod.TOKEN);
                request.setRequestProperty("Content-Type", "application/json");
                request.setDoOutput(true);

                request.getOutputStream().write(("{\"modelId\":\"" + item.name + "\",\"player\":\"" + player.getDisplayName() + "\"}").getBytes(StandardCharsets.UTF_8));
                request.getOutputStream().close();
                request.connect();

                InputStream in;
                if (request.getResponseCode() >= 400) {
                    in = request.getErrorStream();
                } else {
                    in = request.getInputStream();
                }

                JsonParser jp = new JsonParser();
                JsonObject root = jp.parse(new InputStreamReader(in)).getAsJsonObject();

                if (request.getResponseCode() >= 400) {
                    Logger.debug(root.toString());
                }

                Logger.debug("Покупка [" + item.name + " для " + player.getDisplayName() + "] прошла успешно!");
            } catch (Exception e) {
                Logger.info("Unable to update Accessories profile!");
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        }, "Obvilion.ru API buyItem Thread").start();
    }

    public static void takeoff(EntityPlayerMP player, String type) {
        new Thread(() -> {
            try {
                URL url = new URL("https://obvilion.ru/api/game/accessories/profile?player=" + player.getDisplayName());
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.setRequestMethod("DELETE");
                request.setRequestProperty("Authorization", AccessoriesMod.TOKEN);
                request.setRequestProperty("Content-Type", "application/json");
                request.setDoOutput(true);

                request.getOutputStream().write(("{\"type\":\"" + type + "\"}").getBytes(StandardCharsets.UTF_8));
                request.getOutputStream().close();

                request.connect();

                InputStream in;
                if (request.getResponseCode() >= 400) {
                    in = request.getErrorStream();
                } else {
                    in = request.getInputStream();
                }

                JsonParser jp = new JsonParser();
                JsonObject root = jp.parse(new InputStreamReader(in)).getAsJsonObject();

                if (request.getResponseCode() >= 400) {
                    Logger.debug(root.toString());
                }

                String familiar = root.has("familiarId") ? root.get("familiarId").getAsString() : "NONE";
                String body = root.has("bodyId") ? root.get("bodyId").getAsString() : "NONE";
                String face = root.has("faceId") ? root.get("faceId").getAsString() : "NONE";
                String head = root.has("headId") ? root.get("headId").getAsString() : "NONE";

                Logger.debug("Отправляю пакет всем ACS:ITEMS_EQUIP [head: " + head + ", face: " + face +
                        ", body: " + body + ", familiar: " + familiar + "] от " + player.getDisplayName());

                CPacketItemsEquip packet = new CPacketItemsEquip(player.getDisplayName(), head, face, body, familiar);
                sendPacket(packet);
            } catch (Exception e) {
                Logger.info("Unable to update Accessories profile!");
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        }, "Obvilion.ru API takeoff Thread").start();
    }

    public static void equip(EntityPlayerMP player, AccessoryItem item) {
        new Thread(() -> {
            try {
                URL url = new URL("https://obvilion.ru/api/game/accessories/profile?player=" + player.getDisplayName());
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.setRequestMethod("PUT");
                request.setRequestProperty("Authorization", AccessoriesMod.TOKEN);
                request.setRequestProperty("Content-Type", "application/json");
                request.setDoOutput(true);

                request.getOutputStream().write(("{\"modelId\":\"" + item.name + "\"}").getBytes(StandardCharsets.UTF_8));
                request.getOutputStream().close();

                request.connect();

                InputStream in;
                if (request.getResponseCode() >= 400) {
                    in = request.getErrorStream();
                } else {
                    in = request.getInputStream();
                }

                JsonParser jp = new JsonParser();
                JsonObject root = jp.parse(new InputStreamReader(in)).getAsJsonObject();

                if (request.getResponseCode() >= 400) {
                    Logger.debug(root.toString());
                }

                String familiar = root.has("familiarId") ? root.get("familiarId").getAsString() : "NONE";
                String body = root.has("bodyId") ? root.get("bodyId").getAsString() : "NONE";
                String face = root.has("faceId") ? root.get("faceId").getAsString() : "NONE";
                String head = root.has("headId") ? root.get("headId").getAsString() : "NONE";

                Logger.debug("Отправляю пакет ACS:ITEMS_EQUIP [head: " + head + ", face: " + face +
                        ", body: " + body + ", familiar: " + familiar + "] для " + player.getDisplayName());

                CPacketItemsEquip packet = new CPacketItemsEquip(player.getDisplayName(), head, face, body, familiar);
                sendPacket(packet);
            } catch (Exception e) {
                Logger.info("Unable to update Accessories profile!");
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        }, "Obvilion.ru API equip Thread").start();
    }

    public static void sendItemsTo(EntityPlayerMP player, ArrayList<AccessoryItem> items) {
        try {
            Logger.debug("Отправляю пакет ACS:ITEMS_INFO [" + items.size() + " обьектов] для " + player.getDisplayName());

            CPacketItemsInfo packet = new CPacketItemsInfo(items);
            sendPacket(packet, player);
        } catch (Exception e) {
            Logger.info("Unable to get Accessories list!");
            e.printStackTrace();
        }
    }

    public static void sendItemsTo(EntityPlayerMP player) {
        new Thread(() -> {
            try {
                URL url = new URL("https://obvilion.ru/api/game/accessories?player=" + player.getDisplayName());
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.setRequestMethod("GET");
                request.setRequestProperty("Authorization", AccessoriesMod.TOKEN);
                request.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonArray json = root.getAsJsonArray();

                ArrayList<AccessoryItem> result = new ArrayList<>();
                for (JsonElement o : json) {
                    JsonObject object = o.getAsJsonObject();

                    AccessoryItem item = new AccessoryItem(object.get("modelId").getAsString());
                    if (object.has("author") && !object.get("author").isJsonNull()) {
                        item.author = object.get("author").getAsString();
                    }
                    if (object.has("cost") && !object.get("cost").isJsonNull()) {
                        item.price = object.get("cost").getAsInt();
                    }
                    if (object.has("has") && !object.get("has").isJsonNull()) {
                        item.has = object.get("has").getAsBoolean();
                    }

                    result.add(item);
                }

                Logger.debug("Отправляю пакет ACS:ITEMS_INFO [" + result.size() + " обьектов] для " + player.getDisplayName());

                CPacketItemsInfo packet = new CPacketItemsInfo(result);
                sendPacket(packet, player);
            } catch (Exception e) {
                Logger.info("Unable to get Accessories list!");
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        }, "Obvilion.ru API sendItemsTo Thread").start();
    }
}

