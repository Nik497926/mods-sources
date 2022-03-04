/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.OfflinePlayer
 */
package net.milkbowl.vault.economy;

import org.bukkit.OfflinePlayer;

public interface Economy {
    @Deprecated
    public boolean hasAccount(String var1);

    @Deprecated
    public boolean hasAccount(String var1, String var2);

    @Deprecated
    public double getBalance(String var1);

    public double getBalance(OfflinePlayer var1);

    @Deprecated
    public double getBalance(String var1, String var2);

    @Deprecated
    public boolean has(String var1, double var2);

    public boolean has(OfflinePlayer var1, double var2);

    @Deprecated
    public boolean has(String var1, String var2, double var3);

    @Deprecated
    public EconomyResponse withdrawPlayer(String var1, double var2);

    public EconomyResponse withdrawPlayer(OfflinePlayer var1, double var2);

    @Deprecated
    public EconomyResponse withdrawPlayer(String var1, String var2, double var3);

    @Deprecated
    public EconomyResponse depositPlayer(String var1, double var2);

    public EconomyResponse depositPlayer(OfflinePlayer var1, double var2);

    @Deprecated
    public EconomyResponse depositPlayer(String var1, String var2, double var3);

    @Deprecated
    public EconomyResponse createBank(String var1, String var2);

    @Deprecated
    public EconomyResponse isBankOwner(String var1, String var2);

    @Deprecated
    public EconomyResponse isBankMember(String var1, String var2);

    @Deprecated
    public boolean createPlayerAccount(String var1);

    @Deprecated
    public boolean createPlayerAccount(String var1, String var2);
}

