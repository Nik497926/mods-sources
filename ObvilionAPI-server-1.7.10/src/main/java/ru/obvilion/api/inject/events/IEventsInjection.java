package ru.obvilion.api.inject.events;

import org.bukkit.event.Listener;

public interface IEventsInjection {
    void addOnLoadListener(Runnable listener);

    void removeOnLoadListener(Runnable listener);

    void addOnEnableListener(Runnable listener);

    void removeOnEnableListener(Runnable listener);

    void addOnDisableListener(Runnable listener);

    void removeOnDisableListener(Runnable listener);

    void addBukkitListener(Listener listener);

    void removeBukkitListener(Listener listener);
}
