package ru.obvilion.api.inject.events;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import ru.obvilion.api.ObvilionPlugin;

public class EventsInjection implements IEventsInjection {
    @Override
    public void addOnLoadListener(Runnable listener) {
        ObvilionPlugin.onLoadListeners.add(listener);
    }

    @Override
    public void removeOnLoadListener(Runnable listener) {
        ObvilionPlugin.onLoadListeners.remove(listener);
    }

    @Override
    public void addOnEnableListener(Runnable listener) {
        ObvilionPlugin.onEnableListeners.add(listener);
    }

    @Override
    public void removeOnEnableListener(Runnable listener) {
        ObvilionPlugin.onEnableListeners.remove(listener);
    }

    @Override
    public void addOnDisableListener(Runnable listener) {
        ObvilionPlugin.onDisableListeners.add(listener);
    }

    @Override
    public void removeOnDisableListener(Runnable listener) {
        ObvilionPlugin.onDisableListeners.remove(listener);
    }

    @Override
    public void addBukkitListener(Listener listener) {
        ObvilionPlugin.bukkitListeners.add(listener);

        ObvilionPlugin.INSTANCE.getServer()
                .getPluginManager()
                .registerEvents(listener, ObvilionPlugin.INSTANCE);
    }

    @Override
    public void removeBukkitListener(Listener listener) {
        ObvilionPlugin.bukkitListeners.remove(listener);
        HandlerList.unregisterAll(listener);
    }
}
