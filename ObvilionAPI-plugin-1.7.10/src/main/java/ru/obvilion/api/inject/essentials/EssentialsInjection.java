package ru.obvilion.api.inject.essentials;

import com.earth2me.essentials.Essentials;
import ru.obvilion.api.inject.essentials.api.EssKit;
import ru.obvilion.api.inject.essentials.api.IKit;
import java.util.List;
import java.util.stream.Collectors;

public class EssentialsInjection implements IEssentialsInjection {
    public static Essentials essentials;

    @Override
    public List<IKit> getKits() {
        return getEssentials()
                .getSettings().getKits()
                .getKeys(false)
                .stream()
                .map(EssKit::new)
                .collect(Collectors.toList());
    }

    @Override
    public IKit getKit(String kit) {
        return new EssKit(kit);
    }

    public static Essentials getEssentials() {
        if (essentials == null) {
            essentials = (Essentials) Essentials.getProvidingPlugin(Essentials.class);
        }

        return essentials;
    }
}
