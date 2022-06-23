/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.manager.integration.bukkit.event;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ru.simplemc.simplecore.mod.manager.integration.bukkit.action.BlockInteractionAction;
import ru.simplemc.simplecore.mod.manager.integration.bukkit.event.CancellableEvent;

public class BlockInteractionEvent
extends CancellableEvent {
    private final Player player;
    private final Location location;
    private final BlockInteractionAction action;

    public BlockInteractionEvent(int x, int y, int z, EntityPlayer entityPlayer, BlockInteractionAction action) {
        this.player = Bukkit.getPlayer((UUID)entityPlayer.getUniqueID());
        this.location = new Location(this.player.getWorld(), (double)x, (double)y, (double)z);
        this.action = action;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Location getLocation() {
        return this.location;
    }

    public BlockInteractionAction getAction() {
        return this.action;
    }
}

