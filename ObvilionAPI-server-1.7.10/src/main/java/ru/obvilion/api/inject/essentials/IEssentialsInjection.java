package ru.obvilion.api.inject.essentials;

import ru.obvilion.api.inject.essentials.api.IKit;
import java.util.List;

public interface IEssentialsInjection {
    List<IKit> getKits();

    IKit getKit(String kit);
}
