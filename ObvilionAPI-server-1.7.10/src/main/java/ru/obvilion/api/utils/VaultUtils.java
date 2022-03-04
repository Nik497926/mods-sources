package ru.obvilion.api.utils;

import net.milkbowl.vault.economy.Economy;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultUtils {
    private static Economy economy;

    public static boolean supported() {
        try {
            economy = Bukkit.getServicesManager().getRegistration(Economy.class).getProvider();
        } catch (Exception e) { return false; }

        return true;
    }

    public static boolean hasMoney(EntityPlayer player, double amount) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(player.getUniqueID());
        return VaultUtils.getEconomy().has(p, amount);
    }

    public static boolean withdrawPlayer(EntityPlayer player, double amount) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(player.getUniqueID());
        return VaultUtils.getEconomy().withdrawPlayer(p, amount).transactionSuccess();
    }

    public static boolean depositPlayer(EntityPlayer player, double amount) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(player.getUniqueID());
        return VaultUtils.getEconomy().depositPlayer(p, amount).transactionSuccess();
    }

    public static double getMoney(EntityPlayer player) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(player.getUniqueID());
        return VaultUtils.getEconomy().getBalance(p);
    }

    private static Economy getEconomy() {
        if (economy == null) {
            RegisteredServiceProvider<Economy> economyProvider;
            if ((economyProvider = Bukkit.getServicesManager().getRegistration(Economy.class)) != null) {
                economy = (Economy)economyProvider.getProvider();
            }
        }

        return economy;
    }
}

