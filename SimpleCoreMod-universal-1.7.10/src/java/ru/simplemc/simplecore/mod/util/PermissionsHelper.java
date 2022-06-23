/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.util;

import net.minecraft.entity.player.EntityPlayer;

public class PermissionsHelper {
    private static final String[] adminUsernames = new String[]{"Goodvise", "aesic", "yarikDOTA", "xb1tnatorV", "igorek181"};

    public static boolean isAdmin(String username) {
        for (String adminUsername : adminUsernames) {
            if (!adminUsername.equalsIgnoreCase(username)) continue;
            return true;
        }
        return false;
    }

    public static boolean isAdmin(EntityPlayer player) {
        return PermissionsHelper.isAdmin(player.getCommandSenderName());
    }
}

