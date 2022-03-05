package ru.obvilion.api.inject;

import org.bukkit.Bukkit;
import ru.obvilion.api.inject.essentials.EssentialsInjection;
import ru.obvilion.api.inject.essentials.IEssentialsInjection;
import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.inject.permissions.luckperms.LPInjection;
import ru.obvilion.api.inject.permissions.pex.PexInjection;

public class InjectionManager {
    public static boolean initialized = false;

    public static IEssentialsInjection essentials;
    public static IPermissionsInjection pex;

    public static void init() {
        if (Bukkit.getPluginManager().getPlugin("Essentials") != null)
            essentials = EssentialsInjection.getInjection();

        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null)
            pex = LPInjection.getInjection();

        if (pex == null && Bukkit.getPluginManager().getPlugin("PermissionsEx") != null)
            pex = PexInjection.getInjection();
    }

    static {
        new Thread(() -> {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            init();
        }).start();
    }
}
