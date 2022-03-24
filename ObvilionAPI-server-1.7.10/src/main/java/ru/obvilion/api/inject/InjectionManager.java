package ru.obvilion.api.inject;

import ru.obvilion.api.inject.essentials.IEssentialsInjection;
import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.inject.vault.IVaultInjection;
import ru.obvilion.api.utils.InjectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InjectionManager {
    public static boolean initialized = false;

    private static IEssentialsInjection essentials;
    private static IPermissionsInjection pex;
    private static IVaultInjection vault;

    public static final String VERSION = "1.0.7";

    public static void init() {
        Class<?> plugin = InjectionUtils.getClass("ObvilionAPI", "ru.obvilion.api.ObvilionPlugin");
        if (plugin == null) {
            System.err.println("[ObvilionAPI] Plugin not found!");
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

        try {
            Method m = plugin.getDeclaredMethod("getVaultInjection");
            vault = (IVaultInjection) m.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            Method m = plugin.getDeclaredMethod("getVersion");
            String version = (String) m.invoke(null);

            if (!VERSION.startsWith(version)) {
                System.err.println(
                        "[ObvilionAPI] Warning! Detected unsuitable version of Plugin!" +
                        " Plugin version: " + version +
                        " Mod version: " + VERSION
                );
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        initialized = true;
    }

    public static IEssentialsInjection getEssentials() {
        if (!initialized) init();

        return essentials;
    }

    public static IPermissionsInjection getPex() {
        if (!initialized) init();

        return pex;
    }

    public static IVaultInjection getVault() {
        if (!initialized) init();

        return vault;
    }
}
