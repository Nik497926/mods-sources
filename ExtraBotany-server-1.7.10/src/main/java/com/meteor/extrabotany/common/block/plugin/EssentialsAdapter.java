/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.plugin;

import com.meteor.extrabotany.common.block.plugin.IInjection;
import com.meteor.extrabotany.common.block.plugin.InjectionUtils;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.melonbrew.fe.API;
import org.melonbrew.fe.Fe;
import org.melonbrew.fe.database.Account;

public class EssentialsAdapter {
    private static IInjection injection;
    private static boolean loading;
    private static boolean inject;

    public static void init() throws IllegalAccessException, InstantiationException {
        Class<?> clazz = InjectionUtils.injectClass("LockItems", EssentialsAdapter.class);
        injection = (IInjection)clazz.newInstance();
    }

    public static boolean autoInject() throws InstantiationException, IllegalAccessException {
        if (inject) {
            return true;
        }
        if (injection == null && !loading) {
            loading = true;
            EssentialsAdapter.init();
            inject = true;
            return true;
        }
        if (injection == null) {
            return true;
        }
        System.out.println("Can't connect to plugin");
        loading = false;
        return false;
    }

    public static void deposit(EntityPlayer player, int count) throws Exception {
        EssentialsAdapter.checkInject();
        injection.deposit(player, count);
    }

    public static void deposit(UUID player, int count) throws Exception {
        EssentialsAdapter.checkInject();
        injection.deposit(player, count);
    }

    private static boolean checkInject() throws InstantiationException, IllegalAccessException {
        EssentialsAdapter.autoInject();
        return inject;
    }

    static {
        loading = false;
        inject = false;
    }

    public static final class Inj
    implements IInjection {
        @Override
        public void deposit(EntityPlayer player, int count) throws Exception {
            String name = player.getGameProfile().getName();
            String uuid = String.valueOf(player.getUniqueID());
            if (Bukkit.getPluginManager().isPluginEnabled("Fe")) {
                Plugin plugin = Bukkit.getPluginManager().getPlugin("Fe");
                if (plugin instanceof Fe) {
                    API api = new API((Fe)plugin);
                    Account acc = api.getAccount(name);
                    acc.deposit(count);
                    return;
                }
                System.out.println("Error plugins!");
            }
            System.out.println("Not work plugin");
        }

        @Override
        public void deposit(UUID player, int count) throws Exception {
            if (Bukkit.getPluginManager().isPluginEnabled("Fe")) {
                Plugin plugin = Bukkit.getPluginManager().getPlugin("Fe");
                if (plugin instanceof Fe) {
                    API api = new API((Fe)plugin);
                    Player p = Bukkit.getPlayer(player);
                    if (p == null) {
                        throw new IllegalArgumentException("Not found account");
                    }
                    Account acc = api.getAccount(p.getName());
                    acc.deposit(count);
                    return;
                }
                System.out.println("Error plugins!");
            }
            System.out.println("Not work plugin");
        }
    }
}

