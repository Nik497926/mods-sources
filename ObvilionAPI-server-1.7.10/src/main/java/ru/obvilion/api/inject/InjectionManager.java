package ru.obvilion.api.inject;

import org.bukkit.Bukkit;
import ru.obvilion.api.inject.essentials.EssentialsInjection;
import ru.obvilion.api.inject.essentials.IEssentialsInjection;
import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.utils.InjectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InjectionManager {
    public static boolean initialized = false;

    public static IEssentialsInjection essentials;
    public static IPermissionsInjection pex;

    public static void init() {
        Class<?> plugin = InjectionUtils.getClass("ObvilionAPI", "ru.obvilion.api.ObvilionPlugin");
        if (plugin == null) {
            System.err.println("[ObvilionAPI] Plugin not found! ");
            return;
        }

        try {
            Method m = plugin.getDeclaredMethod("getEssentialsInjection");
            essentials = (IEssentialsInjection) m.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            Method m = plugin.getDeclaredMethod("getPermissionsInjection");
            pex = (IPermissionsInjection) m.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
