package ru.obvilion.api.utils;

import org.bukkit.configuration.ConfigurationSection;
import ru.obvilion.api.essentials.Kit;
import ru.obvilion.api.inject.InjectionManager;

import java.util.ArrayList;
import java.util.List;

public class EssentialsUtils {
    public static boolean supported() {
        if (!InjectionManager.initialized) {
            InjectionManager.init();
        }

        return InjectionManager.essentials != null;
    }

    public static List<Kit> getKits() {
        List<Kit> result = new ArrayList<>();

        ConfigurationSection cs = InjectionManager.essentials.getKitsConfig();
        for (String kit : cs.getKeys(false)) {
            result.add(new Kit(InjectionManager.essentials.getKitConfig(kit)));
        }

        return result;
    }
}
