/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S07PacketRespawn
 *  net.minecraft.network.play.server.S1DPacketEntityEffect
 *  net.minecraft.network.play.server.S2BPacketChangeGameState
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package net.divinerpg.items.vanilla;

import cpw.mods.fml.common.FMLCommonHandler;
import java.util.List;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemTeleportationCrystal
extends ItemMod {
    public ItemTeleportationCrystal() {
        super("teleportationCrystal");
        this.setCreativeTab(DivineRPGTabs.utility);
        this.setMaxStackSize(1);
        this.setMaxDamage(10);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player instanceof EntityPlayerMP && !player.isRiding()) {
            EntityPlayerMP playerMP = (EntityPlayerMP)player;
            if (player.dimension != 0) {
                ItemTeleportationCrystal.transferPlayerToDimension(playerMP, 0);
            }
        }
        return stack;
    }

    public static void transferPlayerToWorld(EntityPlayerMP player, WorldServer oldWorld, WorldServer newWorld) {
        double moveFactor = oldWorld.provider.getMovementFactor() / newWorld.provider.getMovementFactor();
        double x = player.posX * moveFactor;
        double z = player.posZ * moveFactor;
        x = MathHelper.clamp_double((double)x, (double)-2.9999872E7, (double)2.9999872E7);
        z = MathHelper.clamp_double((double)z, (double)-2.9999872E7, (double)2.9999872E7);
        if (player.isEntityAlive()) {
            boolean flag1 = true;
            player.setLocationAndAngles(x, player.posY, z, player.rotationYaw, player.rotationPitch);
            newWorld.spawnEntityInWorld((Entity)player);
            newWorld.updateEntityWithOptionalForce((Entity)player, false);
        }
        player.setWorld((World)newWorld);
    }

    public static void movePlayerToSpawn(EntityPlayerMP player, WorldServer worldServer) {
        ChunkCoordinates bedSpawn = player.getBedLocation(0);
        if (bedSpawn != null) {
            ChunkCoordinates safeBedSpawn = EntityPlayer.verifyRespawnCoordinates((World)worldServer, (ChunkCoordinates)bedSpawn, (boolean)true);
            if (safeBedSpawn != null) {
                player.setLocationAndAngles((double)((float)safeBedSpawn.posX + 0.5f), (double)((float)safeBedSpawn.posY + 0.1f), (double)((float)safeBedSpawn.posZ + 0.5f), 0.0f, 0.0f);
                player.setSpawnChunk(bedSpawn, true);
            } else {
                player.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(0, 0.0f));
            }
        } else {
            ChunkCoordinates worldSpawn = worldServer.getSpawnPoint();
            player.setLocationAndAngles((double)((float)worldSpawn.posX + 0.5f), (double)((float)worldSpawn.posY + 0.1f), (double)((float)worldSpawn.posZ + 0.5f), 0.0f, 0.0f);
        }
        worldServer.theChunkProviderServer.loadChunk((int)player.posX >> 4, (int)player.posZ >> 4);
        while (!worldServer.getCollidingBoundingBoxes((Entity)player, player.boundingBox).isEmpty()) {
            player.setPosition(player.posX, player.posY + 1.0, player.posZ);
        }
    }

    public static void transferPlayerToDimension(EntityPlayerMP player, int newDimension) {
        ServerConfigurationManager configManager = player.mcServer.getConfigurationManager();
        int oldDimension = player.dimension;
        WorldServer oldWorldServer = configManager.getServerInstance().worldServerForDimension(oldDimension);
        WorldServer newWorldServer = configManager.getServerInstance().worldServerForDimension(0);
        player.dimension = newDimension;
        player.playerNetServerHandler.sendPacket((Packet)new S07PacketRespawn(player.dimension, player.worldObj.difficultySetting, player.worldObj.getWorldInfo().getTerrainType(), player.theItemInWorldManager.getGameType()));
        oldWorldServer.removePlayerEntityDangerously((Entity)player);
        player.isDead = false;
        ItemTeleportationCrystal.transferPlayerToWorld(player, oldWorldServer, newWorldServer);
        configManager.func_72375_a(player, oldWorldServer);
        ItemTeleportationCrystal.movePlayerToSpawn(player, newWorldServer);
        player.playerNetServerHandler.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        player.theItemInWorldManager.setWorld(newWorldServer);
        configManager.updateTimeAndWeatherForPlayer(player, newWorldServer);
        configManager.syncPlayerInventory(player);
        for (PotionEffect potioneffect : player.getActivePotionEffects()) {
            player.playerNetServerHandler.sendPacket((Packet)new S1DPacketEntityEffect(player.getEntityId(), potioneffect));
        }
        FMLCommonHandler.instance().firePlayerChangedDimensionEvent((EntityPlayer)player, oldDimension, newDimension);
    }

    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        list.add(Util.GOLD + StatCollector.translateToLocal((String)"item.teleportcrystal"));
        int dur = item.getMaxDamage() - item.getItemDamage();
        double max = item.getMaxDamage();
        int res = (int)((double)dur / max * 100.0);
        list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
    }
}

