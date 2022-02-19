/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.OfflinePlayer
 */
package net.milkbowl.vault.economy;

import java.util.List;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

public interface Economy {
    public boolean isEnabled();

    public String getName();

    public boolean hasBankSupport();

    public int fractionalDigits();

    public String format(double var1);

    public String currencyNamePlural();

    public String currencyNameSingular();

    @Deprecated
    public boolean hasAccount(String var1);

    public boolean hasAccount(OfflinePlayer var1);

    @Deprecated
    public boolean hasAccount(String var1, String var2);

    public boolean hasAccount(OfflinePlayer var1, String var2);

    @Deprecated
    public double getBalance(String var1);

    public double getBalance(OfflinePlayer var1);

    @Deprecated
    public double getBalance(String var1, String var2);

    public double getBalance(OfflinePlayer var1, String var2);

    @Deprecated
    public boolean has(String var1, double var2);

    public boolean has(OfflinePlayer var1, double var2);

    @Deprecated
    public boolean has(String var1, String var2, double var3);

    public boolean has(OfflinePlayer var1, String var2, double var3);

    @Deprecated
    public EconomyResponse withdrawPlayer(String var1, double var2);

    public EconomyResponse withdrawPlayer(OfflinePlayer var1, double var2);

    @Deprecated
    public EconomyResponse withdrawPlayer(String var1, String var2, double var3);

    public EconomyResponse withdrawPlayer(OfflinePlayer var1, String var2, double var3);

    @Deprecated
    public EconomyResponse depositPlayer(String var1, double var2);

    public EconomyResponse depositPlayer(OfflinePlayer var1, double var2);

    @Deprecated
    public EconomyResponse depositPlayer(String var1, String var2, double var3);

    public EconomyResponse depositPlayer(OfflinePlayer var1, String var2, double var3);

    @Deprecated
    public EconomyResponse createBank(String var1, String var2);

    public EconomyResponse createBank(String var1, OfflinePlayer var2);

    public EconomyResponse deleteBank(String var1);

    public EconomyResponse bankBalance(String var1);

    public EconomyResponse bankHas(String var1, double var2);

    public EconomyResponse bankWithdraw(String var1, double var2);

    public EconomyResponse bankDeposit(String var1, double var2);

    @Deprecated
    public EconomyResponse isBankOwner(String var1, String var2);

    public EconomyResponse isBankOwner(String var1, OfflinePlayer var2);

    @Deprecated
    public EconomyResponse isBankMember(String var1, String var2);

    public EconomyResponse isBankMember(String var1, OfflinePlayer var2);

    public List<String> getBanks();

    @Deprecated
    public boolean createPlayerAccount(String var1);

    public boolean createPlayerAccount(OfflinePlayer var1);

    @Deprecated
    public boolean createPlayerAccount(String var1, String var2);

    public boolean createPlayerAccount(OfflinePlayer var1, String var2);
}

