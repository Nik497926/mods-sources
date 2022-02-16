/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.event.world.BlockEvent$PlaceEvent
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.HashMap;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.world.BlockEvent;

public class EventEnsureVetheaSpawn {
    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent evt) {
        HashMap<Integer, ChunkCoordinates> map;
        ChunkCoordinates c;
        if (!evt.player.worldObj.isRemote && evt.player.dimension == ConfigurationHelper.vethea && (c = (map = Util.getPlayerSpawnChunkMap(evt.player)).get(ConfigurationHelper.vethea)) != null) {
            int x = c.posX;
            int y = c.posY;
            int z = c.posZ;
            if (evt.player.worldObj.getChunkProvider().chunkExists(MathHelper.floor_double((double)((double)c.posX / 16.0)), MathHelper.floor_double((double)((double)c.posZ / 16.0)))) {
                evt.player.worldObj.setBlock(x, y, z, Blocks.air);
                evt.player.worldObj.setBlock(x, y + 1, z, Blocks.air);
            }
        }
    }

    @SubscribeEvent
    public void place(BlockEvent.PlaceEvent evt) {
        HashMap<Integer, ChunkCoordinates> map;
        ChunkCoordinates c;
        if (evt.player.dimension == ConfigurationHelper.vethea && (c = (map = Util.getPlayerSpawnChunkMap(evt.player)).get(ConfigurationHelper.vethea)) != null) {
            int x = c.posX;
            int y = c.posY;
            int z = c.posZ;
            if (evt.x == x && (evt.y == y || evt.y == y + 1) && evt.z == z) {
                evt.setCanceled(true);
            }
        }
    }
}

