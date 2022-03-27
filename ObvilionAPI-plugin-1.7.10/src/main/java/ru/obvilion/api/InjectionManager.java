package ru.obvilion.api;

import ru.obvilion.api.inject.essentials.IEssentialsInjection;
import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.inject.vault.IVaultInjection;

public class InjectionManager {
    public static IPermissionsInjection getPermissionsInjection() {
        return ObvilionPlugin.pexInjection;
    }

    public static IEssentialsInjection getEssentialsInjection() {
        return ObvilionPlugin.essentialsInjection;
    }

    public static IVaultInjection getVaultInjection() {
        return ObvilionPlugin.vaultInjection;
    }

    public static String getVersion() {
        return "1.0.9";
    }
}
