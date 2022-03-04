package ru.obvilion.api.utils;

import net.luckperms.api.LuckPermsProvider;
import ru.obvilion.api.inject.InjectionManager;
import ru.obvilion.api.inject.permissions.api.IUser;

import java.util.List;
import java.util.UUID;

public class PexUtils {
    public static boolean supported() {
        if (!InjectionManager.initialized) {
            InjectionManager.init();
        }

        return InjectionManager.pex != null;
    }

    public static IUser getUser(UUID playerId) { return InjectionManager.pex.getUser(playerId); }
    public static IUser getUser(String userName) { return InjectionManager.pex.getUser(userName); }
}
