package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.utils.InjectionUtils;
import ru.tehkode.permissions.bukkit.PermissionsEx;

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
            boolean has = PermissionsEx.getPermissionManager().getGroup(group) == null;
            return has ? new PexGroup(group) : null;
        }

//        public void setGroupUntil(UUID playerId, String group, long val) { setOwnOption(playerId, "group-" + group + "-until", val+""); }
//        public void setGroupUntil(String userName, String group, long val) { setOwnOption(userName, "group-" + group + "-until", val+""); }
    }
}
