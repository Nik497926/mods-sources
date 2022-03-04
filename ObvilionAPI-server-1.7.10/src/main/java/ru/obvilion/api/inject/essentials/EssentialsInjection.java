package ru.obvilion.api.inject.essentials;

import com.earth2me.essentials.Essentials;
import org.bukkit.configuration.ConfigurationSection;
import ru.obvilion.api.utils.InjectionUtils;

import java.util.Map;

public class EssentialsInjection {
    public static IEssentialsInjection getInjection() {
        Class<?> clazz = InjectionUtils.injectClass("Essentials", EssentialsInjection.class);
        if (clazz != null)
            try {
                return (IEssentialsInjection) clazz.newInstance();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        return null;
    }

    public static final class Inj implements IEssentialsInjection {
        public Essentials essentials;

        @Override
        public ConfigurationSection getKitsConfig() {
            return getEssentials().getSettings().getKits();
        }

        @Override
        public Map<String, Object> getKitConfig(String kitName) {
            return getEssentials().getSettings().getKit(kitName);
        }

        public Essentials getEssentials() {
            if (essentials == null) {
                essentials = (Essentials) Essentials.getProvidingPlugin(Essentials.class);
            }

            return essentials;
        }
    }
}
