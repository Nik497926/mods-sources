package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.inject.permissions.api.*;
import ru.obvilion.api.utils.InjectionUtils;

import java.util.UUID;

public class LPInjection {
    public static Class[] classes = new Class[] {
            IGroup.class, IPermission.class, IPrefix.class, ISuffix.class, IUser.class, LPGroup.class,
            LPPermission.class, LPPrefix.class, LPSuffix.class, LPUser.class
    };

    public static IPermissionsInjection getInjection() {
        for (Class c : classes) {
            InjectionUtils.injectClassN("LuckPerms", c);
            System.out.println("INJECTED " + c.getName());
        }

        Class<?> clazz = InjectionUtils.injectClass("LuckPerms", LPInjection.class);
        if (clazz != null)
            try {
                return (IPermissionsInjection) clazz.newInstance();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        return null;
    }

    public static final class Inj implements IPermissionsInjection {
        public LPUser getUser(String name) {
            return new LPUser(LuckPermsProvider.get().getUserManager().getUser(name));
        }

        public LPUser getUser(UUID uuid) {
            return new LPUser(LuckPermsProvider.get().getUserManager().getUser(uuid));
        }

        public LPGroup getGroup(String group) {
            Group g = LuckPermsProvider.get().getGroupManager().getGroup(group);
            return g == null ? null : new LPGroup(g);
        }
    }
}
