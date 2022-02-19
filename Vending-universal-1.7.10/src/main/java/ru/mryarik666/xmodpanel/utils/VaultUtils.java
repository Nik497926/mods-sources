/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  org.bukkit.Bukkit
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.plugin.RegisteredServiceProvider
 */
package ru.mryarik666.xmodpanel.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.UUID;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

@SideOnly(value=Side.SERVER)
public final class VaultUtils {
    private static Economy economy;

    public static boolean hasMoney(String player, double amount) {
        OfflinePlayer p = Bukkit.getOfflinePlayer((String)player);
        return VaultUtils.getEconomy().has(p, amount);
    }

    public static boolean hasMoney(EntityPlayer player, double amount) {
        OfflinePlayer p = Bukkit.getOfflinePlayer((UUID)player.getUniqueID());
        return VaultUtils.getEconomy().has(p, amount);
    }

    public static boolean withdrawPlayer(String player, double amount) {
        OfflinePlayer p = Bukkit.getOfflinePlayer((String)player);
        return VaultUtils.getEconomy().withdrawPlayer(p, amount).transactionSuccess();
    }

    public static boolean withdrawPlayer(EntityPlayer player, double amount) {
        OfflinePlayer p = Bukkit.getOfflinePlayer((UUID)player.getUniqueID());
        return VaultUtils.getEconomy().withdrawPlayer(p, amount).transactionSuccess();
    }

    public static boolean depositPlayer(EntityPlayer player, double amount) {
        OfflinePlayer p = Bukkit.getOfflinePlayer((UUID)player.getUniqueID());
        return VaultUtils.getEconomy().depositPlayer(p, amount).transactionSuccess();
    }

    public static boolean depositPlayer(String playerName, double amount) {
        return VaultUtils.getEconomy().depositPlayer(playerName, amount).transactionSuccess();
    }

    private static Economy getEconomy() {
        RegisteredServiceProvider economyProvider;
        if (economy == null && (economyProvider = Bukkit.getServicesManager().getRegistration(Economy.class)) != null) {
            economy = (Economy)economyProvider.getProvider();
        }
        return economy;
    }
}

