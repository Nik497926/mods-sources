package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.utils.InjectionUtils;

import java.util.UUID;

public class PexInjection {
    public static IPermissionsInjection getInjection() {
        Class<?> clazz = InjectionUtils.injectClass("PermissionsEx", PexInjection.class);
        if (clazz != null)
            try {
                return (IPermissionsInjection) clazz.newInstance();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        return null;
    }

    public static final class Inj implements IPermissionsInjection {
        public PexUser getUser(UUID playerId) {
            return new PexUser(playerId);
        }
        public PexUser getUser(String userName) {
            return new PexUser(userName);
        }
        public PexGroup getGroup(String group) {
            return new PexGroup(group);
        }

//        public String getPrefix(UUID playerId) { return getUser(playerId).getPrefix(); }
//        public String getPrefix(String userName) { return getUser(userName).getPrefix(); }
//
//        public String getSuffix(UUID playerId) { return getUser(playerId).getSuffix(); }
//        public String getSuffix(String userName) { return getUser(userName).getSuffix(); }
//
//        public void addGroup(UUID playerId, String group) { getUser(playerId).addGroup(group); }
//        public void addGroup(String userName, String group) { getUser(userName).addGroup(group); }
//
//        public void removeGroup(UUID playerId, String group) { getUser(playerId).removeGroup(group); }
//        public void removeGroup(String userName, String group) { getUser(userName).removeGroup(group); }
//
//        public List<String> getGroups(UUID playerId) { return getUser(playerId).getParentIdentifiers(); }
//        public List<String> getGroups(String userName) { return getUser(userName).getParentIdentifiers(); }
//
//        public boolean hasPermission(UUID playerId, String perm) { return getUser(playerId).has(perm); }
//        public boolean hasPermission(String userName, String perm) { return getUser(userName).has(perm); }
//        public boolean hasPermission(UUID playerId, String perm, String world) { return getUser(playerId).has(perm, world); }
//        public boolean hasPermission(String userName, String perm, String world) { return getUser(userName).has(perm, world); }
//
//        public void addPermission(UUID playerId, String perm) { getUser(playerId).addPermission(perm); }
//        public void addPermission(String userName, String perm) { getUser(userName).addPermission(perm); }
//        public void addPermission(UUID playerId, String perm, String world) { getUser(playerId).addPermission(perm, world); }
//        public void addPermission(String userName, String perm, String world) { getUser(userName).addPermission(perm, world); }
//
//        public void removePermission(UUID playerId, String perm) { getUser(playerId).removePermission(perm); }
//        public void removePermission(String userName, String perm) { getUser(userName).removePermission(perm); }
//        public void removePermission(UUID playerId, String perm, String world) { getUser(playerId).removePermission(perm, world); }
//        public void removePermission(String userName, String perm, String world) { getUser(userName).removePermission(perm, world); }
//
//        public String getOwnPrefix(UUID playerId) { return getUser(playerId).getOwnPrefix(); }
//        public String getOwnPrefix(String userName) { return getUser(userName).getOwnPrefix(); }
//
//        public void setOwnPrefix(UUID playerId, String prefix) { getUser(playerId).setPrefix(prefix, null); }
//        public void setOwnPrefix(String userName, String prefix) { getUser(userName).setPrefix(prefix, null); }
//        public void setOwnPrefix(UUID playerId, String prefix, String world) { getUser(playerId).setPrefix(prefix, world); }
//        public void setOwnPrefix(String userName, String prefix, String world) { getUser(userName).setPrefix(prefix, world); }
//
//        public String getOwnSuffix(UUID playerId) { return getUser(playerId).getOwnSuffix(); }
//        public String getOwnSuffix(String userName) { return getUser(userName).getOwnSuffix(); }
//
//        public void setOwnSuffix(UUID playerId, String suffix) { getUser(playerId).setSuffix(suffix, null); }
//        public void setOwnSuffix(String userName, String suffix) { getUser(userName).setSuffix(suffix, null); }
//        public void setOwnSuffix(UUID playerId, String suffix, String world) { getUser(playerId).setSuffix(suffix, world); }
//        public void setOwnSuffix(String userName, String suffix, String world) { getUser(userName).setSuffix(suffix, world); }
//
//        public String getOwnOption(UUID playerId, String opt) { return getUser(playerId).getOwnOption(opt); }
//        public String getOwnOption(String userName, String opt) { return getUser(userName).getOwnOption(opt); }
//
//        public void setOwnOption(UUID playerId, String opt, String val) { getUser(playerId).setOption(opt, val); }
//        public void setOwnOption(String userName, String opt, String val) { getUser(userName).setOption(opt, val); }
//
//        public void setGroupUntil(UUID playerId, String group, long val) { setOwnOption(playerId, "group-" + group + "-until", val+""); }
//        public void setGroupUntil(String userName, String group, long val) { setOwnOption(userName, "group-" + group + "-until", val+""); }
    }
}
