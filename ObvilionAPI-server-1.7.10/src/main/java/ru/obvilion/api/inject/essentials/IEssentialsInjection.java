package ru.obvilion.api.inject.essentials;

import org.bukkit.configuration.ConfigurationSection;

import java.util.Map;

public interface IEssentialsInjection {
    ConfigurationSection getKitsConfig();

    Map<String, Object> getKitConfig(String kitName);
}
