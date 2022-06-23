package ru.obvilion.mserver.net.packets.out;

import ru.obvilion.mserver.utils.Bytes;

import java.nio.ByteBuffer;

public class ServerInfo {
    public static byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(500);

        Bytes.putString("Example", buffer);
        Bytes.putString("Map", buffer);
        buffer.putInt(10);
        buffer.putInt(0);
        buffer.putInt(131);
        Bytes.putString("Hub", buffer);
        buffer.put((byte)1);
        buffer.putInt(100);
        Bytes.putString("Your description", buffer);

        return buffer.array();
    }
}
