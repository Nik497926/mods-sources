package ru.obvilion.api.utils;

public class NumberUtils {
    public static boolean isNullOrZero(final Object obj) {
        return obj == null || (
            obj instanceof Integer ? (int) obj == 0 :
                obj instanceof Long ? (long) obj == 0 :
                    obj instanceof Double ? (double) obj == 0 :
                        obj instanceof Short ? (short) obj == 0 :
                            obj instanceof Float && (float) obj == 0
        );
    }
}
