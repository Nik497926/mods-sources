package ru.obvilion.mserver.utils;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Bytes {
    public static String toString(byte[] bytes, int size) {
        byte[] n = Arrays.copyOf(bytes, size);

        return Arrays.toString(n);
    }

    public static void putString(String s, ByteBuffer buffer) {
        buffer.put((byte) s.getBytes().length);
        buffer.put(s.getBytes());
    }
}
