package ru.obvilion.api;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import ru.obvilion.api.inject.essentials.EssentialsInjection;
import ru.obvilion.api.inject.essentials.IEssentialsInjection;
import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.inject.permissions.luckperms.LPInjection;
import ru.obvilion.api.inject.permissions.pex.PexInjection;
import ru.obvilion.api.inject.vault.IVaultInjection;
import ru.obvilion.api.inject.vault.VaultInjection;

import java.util.ArrayList;
import java.util.List;

public class ObvilionPlugin extends JavaPlugin {
    public static IPermissionsInjection pexInjection;
    public static IEssentialsInjection essentialsInjection;
    public static IVaultInjection vaultInjection;

    public static ObvilionPlugin INSTANCE;

    public static List<Runnable> onLoadListeners = new ArrayList<>();
    public static List<Runnable> onEnableListeners = new ArrayList<>();
    public static List<Runnable> onDisableListeners = new ArrayList<>();
    public static List<Listener> bukkitListeners = new ArrayList<>();

    public ObvilionPlugin() {
        super();
        INSTANCE = this;
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

        for (Runnable r : onLoadListeners) {
            r.run();
        }
    }

    @Override
    public void onEnable() {
        System.out.println("Obvilion API Plugin - enabled");
        System.out.println("- Permissions " + (pexInjection == null ? "not " : "") + "supported");
        System.out.println("- Essentials " + (essentialsInjection == null ? "not " : "") + "supported");
        System.out.println("- Vault " + (vaultInjection == null ? "not " : "") + "supported");

        for (Runnable r : onEnableListeners) {
            r.run();
        }
    }

    @Override
    public void onDisable() {
        for (Runnable r : onDisableListeners) {
            r.run();
        }
    }
}
