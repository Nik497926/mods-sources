/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.plugin;

import com.google.common.io.ByteStreams;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public final class InjectionUtils {
    private static final Method defineClass;

    public static Class<?> injectClass(String pluginName, Class<?> clazz) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin == null) {
            return null;
        }
        try {
            Class var6;
            try (InputStream in = clazz.getClassLoader().getResourceAsStream(clazz.getName().replace('.', '/') + "$Inj.class")){
                byte[] bytes = ByteStreams.toByteArray(in);
                var6 = (Class)defineClass.invoke(plugin.getClass().getClassLoader(), null, bytes, 0, bytes.length);
            }
            return var6;
        }
        catch (Throwable var18) {
            var18.printStackTrace();
            return null;
        }
    }

    static {
        try {
            defineClass = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE);
            defineClass.setAccessible(true);
        }
        catch (Throwable var1) {
            throw new RuntimeException("Failed hooking ClassLoader.defineClass(String, byte[], int, int) method!", var1);
        }
    }
}

