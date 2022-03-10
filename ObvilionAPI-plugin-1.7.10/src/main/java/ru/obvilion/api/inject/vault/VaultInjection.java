package ru.obvilion.api.inject.vault;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import ru.obvilion.api.inject.vault.api.IEcoUser;
import ru.obvilion.api.inject.vault.api.VaultEcoUser;

import java.util.UUID;

public class VaultInjection implements IVaultInjection {
    public static Economy economy;

    @Override
    public IEcoUser getUser(UUID uuid) {
        return new VaultEcoUser(Bukkit.getOfflinePlayer(uuid));
    }

    @Override
    public IEcoUser getUser(String name) {
        return new VaultEcoUser(Bukkit.getOfflinePlayer(name));
    }

    public static Economy getEconomy() {
        if (economy == null) {
            RegisteredServiceProvider<Economy> economyProvider;
            if ((economyProvider = Bukkit.getServicesManager().getRegistration(Economy.class)) != null) {
                economy = (Economy) economyProvider.getProvider();
            }
        }

        return economy;
    }
}
