package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.LuckPermsProvider;
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
            return new LPGroup(LuckPermsProvider.get().getGroupManager().getGroup(group));
        }

//        private List<Group> getGroupObjects(User user) {
//            return (List<Group>) user.getInheritedGroups(user.getQueryOptions());
//        }
//
//        private List<String> getGroups(User user) {
//            List<String> result = new ArrayList<>();
//
//            for (Group g : getGroupObjects(user))
//                result.add(g.getName());
//
//            return result;
//        }
//
//        private PrefixNode getOwnPrefixNode(User user) {
//            PrefixNode[] prefixes = user.getNodes(NodeType.PREFIX).toArray(new PrefixNode[0]);
//            return prefixes.length == 0 ? null : prefixes[0];
//        }
//
//        private SuffixNode getOwnSuffixNode(User user) {
//            SuffixNode[] suffixNodes = user.getNodes(NodeType.SUFFIX).toArray(new SuffixNode[0]);
//            return suffixNodes.length == 0 ? null : suffixNodes[0];
//        }
//
//        private void setOwnPrefixNode(User user, String prefix, long expire) {
//            PrefixNode.Builder b = PrefixNode.builder().prefix(prefix);
//            if (expire <= 0)
//                b.expiry(expire);
//
//            user.data().add(b.build());
//            saveUser(user);
//        }
//
//        private void setOwnSuffixNode(User user, String prefix, long expire) {
//            SuffixNode.Builder b = SuffixNode.builder().suffix(prefix);
//            if (expire <= 0)
//                b.expiry(expire);
//
//            user.data().add(b.build());
//            saveUser(user);
//        }
    }
}
