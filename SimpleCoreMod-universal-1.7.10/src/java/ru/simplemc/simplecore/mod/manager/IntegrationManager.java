/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.manager.integration.IServerIntegration;
import ru.simplemc.simplecore.mod.manager.integration.bukkit.BukkitServerIntegration;

public class IntegrationManager
implements IServerIntegration {
    private final List<IServerIntegration> integrations = new ArrayList<IServerIntegration>();

    public IntegrationManager() {
        this.createIntegration(BukkitServerIntegration.class).ifPresent(this.integrations::add);
    }

    private Optional<IServerIntegration> createIntegration(Class<? extends IServerIntegration> integrationClass) {
        try {
            return Optional.of(integrationClass.newInstance());
        }
        catch (Throwable e) {
            SimpleCore.LOGGER.error("Failed to initialize integration ", e);
            return Optional.empty();
        }
    }

    @Override
    public boolean hasPermission(EntityPlayer player, String permission) {
        Iterator<IServerIntegration> iterator = this.integrations.iterator();
        if (iterator.hasNext()) {
            IServerIntegration integration = iterator.next();
            return integration.hasPermission(player, permission);
        }
        return false;
    }

    @Override
    public boolean hasPermission(String playerName, String permission) {
        Iterator<IServerIntegration> iterator = this.integrations.iterator();
        if (iterator.hasNext()) {
            IServerIntegration integration = iterator.next();
            return integration.hasPermission(playerName, permission);
        }
        return false;
    }

    @Override
    public boolean decrMoney(EntityPlayer player, BigDecimal amount) {
        Iterator<IServerIntegration> iterator = this.integrations.iterator();
        if (iterator.hasNext()) {
            IServerIntegration integration = iterator.next();
            return integration.decrMoney(player, amount);
        }
        return false;
    }

    @Override
    public boolean decrMoney(String playerName, BigDecimal amount) {
        Iterator<IServerIntegration> iterator = this.integrations.iterator();
        if (iterator.hasNext()) {
            IServerIntegration integration = iterator.next();
            return integration.decrMoney(playerName, amount);
        }
        return false;
    }

    @Override
    public boolean addMoney(EntityPlayer player, BigDecimal amount) {
        Iterator<IServerIntegration> iterator = this.integrations.iterator();
        if (iterator.hasNext()) {
            IServerIntegration integration = iterator.next();
            return integration.addMoney(player, amount);
        }
        return false;
    }

    @Override
    public boolean addMoney(String playerName, BigDecimal amount) {
        Iterator<IServerIntegration> iterator = this.integrations.iterator();
        if (iterator.hasNext()) {
            IServerIntegration integration = iterator.next();
            return integration.addMoney(playerName, amount);
        }
        return false;
    }

    @Override
    public boolean canBreakBlock(EntityPlayer player, int x, int y, int z) {
        Iterator<IServerIntegration> iterator = this.integrations.iterator();
        if (iterator.hasNext()) {
            IServerIntegration integration = iterator.next();
            return integration.canBreakBlock(player, x, y, z);
        }
        return false;
    }
}

