package ru.obvilion.api.inject.vault.api;

import org.bukkit.OfflinePlayer;
import ru.obvilion.api.inject.vault.VaultInjection;

public class VaultEcoUser implements IEcoUser {
    public OfflinePlayer player;

    public VaultEcoUser(OfflinePlayer player) {
        this.player = player;
    }

    @Override
    public double getMoney() {
        return VaultInjection.getEconomy().getBalance(player);
    }

    @Override
    public boolean hasMoney(double amount) {
        return VaultInjection.getEconomy().has(player, amount);
    }

    @Override
    public boolean withdraw(double amount) {
        return VaultInjection.getEconomy().withdrawPlayer(player, amount).transactionSuccess();
    }

    @Override
    public boolean deposit(double amount) {
        return VaultInjection.getEconomy().depositPlayer(player, amount).transactionSuccess();
    }
}
