package ru.obvilion.cpanel.server.network;

import net.minecraft.entity.player.EntityPlayerMP;

import java.io.DataInputStream;

public interface IPacketHandler {
    void handle(DataInputStream is, EntityPlayerMP player);
}
