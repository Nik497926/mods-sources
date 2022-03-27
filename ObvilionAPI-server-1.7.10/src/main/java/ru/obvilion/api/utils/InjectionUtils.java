package ru.obvilion.api.utils;

import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.InputStream;
import java.lang.reflect.Method;

public final class InjectionUtils {
    private static final Method defineClass;

    public static Class<?> getClass(String pluginName, String path) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin == null)
            return null;

        try {
           return plugin.getClass().getClassLoader().loadClass(path);
        } catch (ClassNotFoundException e) {
            System.err.println("Failed loading " + path + " class!");
            e.printStackTrace();
            return null;
        }
    }

    static {
        try {
            defineClass = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            defineClass.setAccessible(true);
        } catch (Throwable throwable) {
            throw new RuntimeException("Failed hooking ClassLoader.defineClass(String, byte[], int, int) method!", throwable);
        }
    }
}
