package ru.obvilion.api.utils;

import com.earth2me.essentials.Essentials;
import org.bukkit.configuration.ConfigurationSection;
import ru.obvilion.api.essentials.Kit;

import java.util.ArrayList;
import java.util.List;

public class EssentialsUtils {
    private static Essentials essentials;

    public static boolean supported() {
        try {
            Class.forName("com.earth2me.essentials.Essentials");
        } catch (ClassNotFoundException e) { return false; }

        return true;
    }

    public static List<Kit> getKits() {
        List<Kit> result = new ArrayList<>();

        ConfigurationSection cs = essentials.getSettings().getKits();
        for (String kit : cs.getKeys(false)) {
            result.add(new Kit(essentials.getSettings().getKit(kit)));
        }

        return result;
    }

    public static Essentials getEssentials() {
        if (essentials == null) {
            essentials = (Essentials) Essentials.getProvidingPlugin(Essentials.class);
        }

        return essentials;
    }
}
