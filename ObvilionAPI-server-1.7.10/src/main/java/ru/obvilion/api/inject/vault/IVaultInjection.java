package ru.obvilion.api.inject.vault;

import ru.obvilion.api.inject.vault.api.IEcoUser;

import java.util.UUID;

public interface IVaultInjection {
    IEcoUser getUser(UUID uuid);

    IEcoUser getUser(String name);
}
