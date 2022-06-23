package ru.obvilion.mserver.net.packets.out;

public class ConfirmConnect {
    public static byte[] getBytes() {
        return new byte[] {
            0, 6, (byte) 254, 4,
            1, 2, 3, 4 // Random numbers
        };
    }
}
