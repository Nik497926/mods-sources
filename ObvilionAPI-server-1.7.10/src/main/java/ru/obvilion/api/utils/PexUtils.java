package ru.obvilion.api.utils;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.List;
import java.util.UUID;

public class PexUtils {
    public static boolean supported() {
        try {
            Class.forName("ru.tehkode.permissions.bukkit.PermissionsEx");
        } catch (ClassNotFoundException e) { return false; }

        return true;
    }

    public static PermissionUser getUser(UUID playerId) { return PermissionsEx.getPermissionManager().getUser(playerId); }
    public static PermissionUser getUser(String userName) { return PermissionsEx.getPermissionManager().getUser(userName); }

    public static String getPrefix(UUID playerId) { return getUser(playerId).getPrefix(); }
    public static String getPrefix(String userName) { return getUser(userName).getPrefix(); }

    public static String getSuffix(UUID playerId) { return getUser(playerId).getSuffix(); }
    public static String getSuffix(String userName) { return getUser(userName).getSuffix(); }

    public static void addGroup(UUID playerId, String group) { getUser(playerId).addGroup(group); }
    public static void addGroup(String userName, String group) { getUser(userName).addGroup(group); }

    public static void removeGroup(UUID playerId, String group) { getUser(playerId).removeGroup(group); }
    public static void removeGroup(String userName, String group) { getUser(userName).removeGroup(group); }

    public static List<String> getGroups(UUID playerId) { return getUser(playerId).getParentIdentifiers(); }
    public static List<String> getGroups(String userName) { return getUser(userName).getParentIdentifiers(); }

    public static boolean hasGroup(UUID playerId, String group) { return getGroups(playerId).contains(group); }
    public static boolean hasGroup(String userName, String group) { return getGroups(userName).contains(group); }

    public static boolean hasPermission(UUID playerId, String perm) { return getUser(playerId).has(perm); }
    public static boolean hasPermission(String userName, String perm) { return getUser(userName).has(perm); }
    public static boolean hasPermission(UUID playerId, String perm, String world) { return getUser(playerId).has(perm, world); }
    public static boolean hasPermission(String userName, String perm, String world) { return getUser(userName).has(perm, world); }

    public static void addPermission(UUID playerId, String perm) { getUser(playerId).addPermission(perm); }
    public static void addPermission(String userName, String perm) { getUser(userName).addPermission(perm); }
    public static void addPermission(UUID playerId, String perm, String world) { getUser(playerId).addPermission(perm, world); }
    public static void addPermission(String userName, String perm, String world) { getUser(userName).addPermission(perm, world); }

    public static void removePermission(UUID playerId, String perm) { getUser(playerId).removePermission(perm); }
    public static void removePermission(String userName, String perm) { getUser(userName).removePermission(perm); }
    public static void removePermission(UUID playerId, String perm, String world) { getUser(playerId).removePermission(perm, world); }
    public static void removePermission(String userName, String perm, String world) { getUser(userName).removePermission(perm, world); }

    public static String getOwnPrefix(UUID playerId) { return getUser(playerId).getOwnPrefix(); }
    public static String getOwnPrefix(String userName) { return getUser(userName).getOwnPrefix(); }

    public static void setOwnPrefix(UUID playerId, String prefix) { getUser(playerId).setPrefix(prefix, null); }
    public static void setOwnPrefix(String userName, String prefix) { getUser(userName).setPrefix(prefix, null); }
    public static void setOwnPrefix(UUID playerId, String prefix, String world) { getUser(playerId).setPrefix(prefix, world); }
    public static void setOwnPrefix(String userName, String prefix, String world) { getUser(userName).setPrefix(prefix, world); }

    public static String getOwnSuffix(UUID playerId) { return getUser(playerId).getOwnSuffix(); }
    public static String getOwnSuffix(String userName) { return getUser(userName).getOwnSuffix(); }

    public static void setOwnSuffix(UUID playerId, String suffix) { getUser(playerId).setSuffix(suffix, null); }
    public static void setOwnSuffix(String userName, String suffix) { getUser(userName).setSuffix(suffix, null); }
    public static void setOwnSuffix(UUID playerId, String suffix, String world) { getUser(playerId).setSuffix(suffix, world); }
    public static void setOwnSuffix(String userName, String suffix, String world) { getUser(userName).setSuffix(suffix, world); }

    public static String getOwnOption(UUID playerId, String opt) { return getUser(playerId).getOwnOption(opt); }
    public static String getOwnOption(String userName, String opt) { return getUser(userName).getOwnOption(opt); }

    public static void setOwnOption(UUID playerId, String opt, String val) { getUser(playerId).setOption(opt, val); }
    public static void setOwnOption(String userName, String opt, String val) { getUser(userName).setOption(opt, val); }

    public static void setGroupUntil(UUID playerId, String group, String val) { setOwnOption(playerId, "group-" + group + "-until", val); }
    public static void setGroupUntil(String userName, String group, String val) { setOwnOption(userName, "group-" + group + "-until", val); }

    public static String getGroup(UUID playerId) {
        List<String> l = getGroups(playerId);
        if (l.size() == 0) return null;

        return l.get(l.size() - 1);
    }
    public static String getGroup(String userName) {
        List<String> l = getGroups(userName);
        if (l.size() == 0) return null;

        return l.get(l.size() - 1);
    }
}
