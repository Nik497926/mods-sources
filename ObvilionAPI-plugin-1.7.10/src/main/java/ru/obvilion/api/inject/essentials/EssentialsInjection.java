package ru.obvilion.api.inject.essentials;

import com.earth2me.essentials.Essentials;
import ru.obvilion.api.inject.essentials.api.IKit;
import java.util.List;

public class EssentialsInjection implements IEssentialsInjection {
//    @Override
//    public ConfigurationSection getKitsConfig() {
//        return getEssentials().getSettings().getKits();
//    }
//
//    @Override
//    public Map<String, Object> getKitConfig(String kitName) {
//        return getEssentials().getSettings().getKit(kitName);
//    }
    public static Essentials essentials;

    @Override
    public List<IKit> getKits() {
        return null;
    }

    @Override
    public IKit getKit(String kit) {
        return null;
    }

    public static Essentials getEssentials() {
        if (essentials == null) {
            essentials = (Essentials) Essentials.getProvidingPlugin(Essentials.class);
        }

        return essentials;
    }
}
