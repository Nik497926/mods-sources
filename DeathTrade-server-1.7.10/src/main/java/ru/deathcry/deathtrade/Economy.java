/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade;

import net.minecraft.entity.player.EntityPlayer;
import ru.mryarik666.xmodpanel.utils.VaultUtils;

public class Economy {
    public static void transferBalance(EntityPlayer from, EntityPlayer to, double money) {
        VaultUtils.withdrawPlayer(from, money);
        VaultUtils.depositPlayer(to, money);
    }

    public static double getPlayerBalance(EntityPlayer user) {
        return VaultUtils.getMoney(user);
    }
}

