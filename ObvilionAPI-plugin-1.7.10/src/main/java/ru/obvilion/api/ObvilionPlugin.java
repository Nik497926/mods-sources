package ru.obvilion.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.obvilion.api.inject.essentials.EssentialsInjection;
import ru.obvilion.api.inject.essentials.IEssentialsInjection;
import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.inject.permissions.luckperms.LPInjection;
import ru.obvilion.api.inject.permissions.pex.PexInjection;

public class ObvilionPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("Obvilion API enabled");
    }

    public static IPermissionsInjection getPermissionsInjection() {
        if (Bukkit.getPluginManager().getPlugin("PermissionsEx") != null) {
            return new PexInjection();
        }
        else if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            return new LPInjection();
        }

        return null;
    }

    public static IEssentialsInjection getEssentialsInjection() {
        if (Bukkit.getPluginManager().getPlugin("Essentials") != null) {
            return new EssentialsInjection();
        }

        return null;
    }
}
