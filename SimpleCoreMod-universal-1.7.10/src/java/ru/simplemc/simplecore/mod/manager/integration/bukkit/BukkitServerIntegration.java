/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.manager.integration.bukkit;

import java.math.BigDecimal;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import ru.simplemc.simplecore.mod.manager.integration.IServerIntegration;
import ru.simplemc.simplecore.mod.manager.integration.bukkit.action.BlockInteractionAction;
import ru.simplemc.simplecore.mod.manager.integration.bukkit.action.EconomyEventAction;
import ru.simplemc.simplecore.mod.manager.integration.bukkit.event.BlockInteractionEvent;
import ru.simplemc.simplecore.mod.manager.integration.bukkit.event.EconomyEvent;

public class BukkitServerIntegration
implements IServerIntegration {
    public BukkitServerIntegration() throws ClassNotFoundException {
        Class.forName("org.bukkit.Bukkit");
    }

    private static Player getBukkitPlayer(EntityPlayer player) {
        return Bukkit.getPlayer((UUID)player.getUniqueID());
    }

    private static Player getBukkitPlayer(String playerName) {
        return Bukkit.getPlayerExact((String)playerName);
    }

    @Override
    public boolean hasPermission(EntityPlayer player, String permission) {
        Player bukkitPlayer = BukkitServerIntegration.getBukkitPlayer(player);
        if (bukkitPlayer != null) {
            return bukkitPlayer.hasPermission(permission);
        }
        return false;
    }

    @Override
    public boolean hasPermission(String player, String permission) {
        Player bukkitPlayer = BukkitServerIntegration.getBukkitPlayer(player);
        if (bukkitPlayer != null) {
            return bukkitPlayer.hasPermission(permission);
        }
        return false;
    }

    @Override
    public boolean decrMoney(EntityPlayer player, BigDecimal amount) {
        return this.decrMoney(player.getCommandSenderName(), amount);
    }

    @Override
    public boolean decrMoney(String playerName, BigDecimal amount) {
        EconomyEvent event = new EconomyEvent(playerName, amount, EconomyEventAction.DECR);
        Bukkit.getPluginManager().callEvent((Event)event);
        return !event.isCancelled();
    }

    @Override
    public boolean addMoney(EntityPlayer player, BigDecimal amount) {
        return this.addMoney(player.getCommandSenderName(), amount);
    }

    @Override
    public boolean addMoney(String playerName, BigDecimal amount) {
        EconomyEvent event = new EconomyEvent(playerName, amount, EconomyEventAction.ADD);
        Bukkit.getPluginManager().callEvent((Event)event);
        return !event.isCancelled();
    }

    @Override
    public boolean canBreakBlock(EntityPlayer player, int x, int y, int z) {
        try {
            BlockInteractionEvent event = new BlockInteractionEvent(x, y, z, player, BlockInteractionAction.BREAK);
            Bukkit.getPluginManager().callEvent((Event)event);
            return !event.isCancelled();
        }
        catch (NullPointerException e) {
            return false;
        }
    }
}

