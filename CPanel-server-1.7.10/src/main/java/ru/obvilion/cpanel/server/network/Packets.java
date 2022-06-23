package ru.obvilion.cpanel.server.network;

import java.util.HashMap;

public class Packets {
    public static HashMap<String, IPacketHandler> packets = new HashMap<>();

    static {
        packets.put(NetworkHandler.channel + ":TEST", (is, player) -> {
            
        });
    }
}
