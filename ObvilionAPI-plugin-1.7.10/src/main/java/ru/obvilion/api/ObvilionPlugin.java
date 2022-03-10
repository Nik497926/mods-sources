package ru.obvilion.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ru.obvilion.api.inject.essentials.EssentialsInjection;
import ru.obvilion.api.inject.essentials.IEssentialsInjection;
import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.inject.permissions.luckperms.LPInjection;
import ru.obvilion.api.inject.permissions.pex.PexInjection;
import ru.obvilion.api.inject.vault.IVaultInjection;
import ru.obvilion.api.inject.vault.VaultInjection;

public class ObvilionPlugin extends JavaPlugin {
    private static IPermissionsInjection pexInjection;
    private static IEssentialsInjection essentialsInjection;
    private static IVaultInjection vaultInjection;

    public static String getVersion() {
        return "1.0.7";
    }

    @Override
    public void onLoad() {
        if (Bukkit.getPluginManager().getPlugin("PermissionsEx") != null) {
            pexInjection = new PexInjection();
        }
        else if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            pexInjection = new LPInjection();
        }

        if (Bukkit.getPluginManager().getPlugin("Essentials") != null) {
            essentialsInjection = new EssentialsInjection();
        }

        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            vaultInjection = new VaultInjection();
        }
    }

    @Override
    public void onEnable() {
        System.out.println("Obvilion API Plugin - enabled");
        System.out.println("- Permissions " + (pexInjection == null ? "not " : "") + "supported");
        System.out.println("- Essentials " + (essentialsInjection == null ? "not " : "") + "supported");
        System.out.println("- Vault " + (vaultInjection == null ? "not " : "") + "supported");
    }

    @Override
    public void onDisable() {

    }

    public static IPermissionsInjection getPermissionsInjection() {
        return pexInjection;
    }

    public static IEssentialsInjection getEssentialsInjection() {
        return essentialsInjection;
    }

    public static IVaultInjection getVaultInjection() {
        return vaultInjection;
    }
}
