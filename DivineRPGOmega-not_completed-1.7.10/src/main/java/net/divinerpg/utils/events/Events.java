/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityMob
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
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.event.AnvilUpdateEvent
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.Random;
import net.divinerpg.enchantment.EnchanmtmentsRegistry;
import net.divinerpg.enchantment.EnchantmentsHelper;
import net.divinerpg.entities.vanilla.EntityCyclops;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.items.ItemTestSword;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
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
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class Events {
    Random rand = new Random();
    private static long timer;

    @SubscribeEvent
    public void onAnvil(AnvilUpdateEvent event) {
        if (event.left.getItem() == JourneyItemsOther.LuckyPickaxe) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void fallsave(TickEvent.PlayerTickEvent event) {
        ItemTestSword.onDeath((EntityLivingBase)event.player);
        if (event.player instanceof EntityPlayerMP && !event.player.isRiding()) {
            EntityPlayerMP playerMP = (EntityPlayerMP)event.player;
            if (playerMP.posY <= -25.0 && playerMP.dimension != 0) {
                Events.transferPlayerToDimension(playerMP, 0);
            }
        }
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
        Events.transferPlayerToWorld(player, oldWorldServer, newWorldServer);
        configManager.func_72375_a(player, oldWorldServer);
        Events.movePlayerToSpawn(player, newWorldServer);
        player.playerNetServerHandler.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        player.theItemInWorldManager.setWorld(newWorldServer);
        configManager.updateTimeAndWeatherForPlayer(player, newWorldServer);
        configManager.syncPlayerInventory(player);
        for (PotionEffect potioneffect : player.getActivePotionEffects()) {
            player.playerNetServerHandler.sendPacket((Packet)new S1DPacketEntityEffect(player.getEntityId(), potioneffect));
        }
        FMLCommonHandler.instance().firePlayerChangedDimensionEvent((EntityPlayer)player, oldDimension, newDimension);
    }

    @SubscribeEvent
    public void nomobspe(EntityJoinWorldEvent e) {
        if (e.entity.dimension == ConfigurationHelper.boiling && e.entity instanceof EntityCyclops) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void doneSoul(LivingDeathEvent e) {
        Entity attacker = e.source.getEntity();
        Entity target = e.entity;
        if (attacker instanceof EntityPlayer && target instanceof EntityMob && this.rand.nextInt(100) < 5 && !attacker.worldObj.isRemote) {
            e.entity.dropItem(JourneyItemsOther.doneSoul, 1);
        }
    }

    @SubscribeEvent
    public void OnObilie(LivingDeathEvent event) {
        Entity attacker = event.source.getEntity();
        Entity target = event.entity;
        if (attacker instanceof EntityPlayer && target instanceof EntityMob) {
            EntityPlayer attackerPlayer = (EntityPlayer)attacker;
            EntityMob targetMob = (EntityMob)target;
            int level = EnchantmentHelper.getEnchantmentLevel((int)EnchanmtmentsRegistry.OBILIE.effectId, (ItemStack)attackerPlayer.getHeldItem());
            if (EnchantmentsHelper.hasObilieEnchantment(attackerPlayer)) {
                switch (level) {
                    case 1: {
                        if (attackerPlayer.experienceTotal < 20) {
                            attackerPlayer.experience = (float)((double)attackerPlayer.experience + 1.6);
                        }
                        if (attackerPlayer.experienceTotal < 26) {
                            attackerPlayer.experience = (float)((double)attackerPlayer.experience + 0.7);
                        }
                        if (attackerPlayer.experienceTotal < 30) {
                            attackerPlayer.experience = (float)((double)attackerPlayer.experience + 0.4);
                        }
                        if (attackerPlayer.experienceTotal <= 30) break;
                        attackerPlayer.experience = (float)((double)attackerPlayer.experience + 0.27);
                        break;
                    }
                    case 2: {
                        if (attackerPlayer.experienceTotal < 20) {
                            attackerPlayer.experience = (float)((double)attackerPlayer.experience + 2.6);
                        }
                        if (attackerPlayer.experienceTotal < 26) {
                            attackerPlayer.experience = (float)((double)attackerPlayer.experience + 1.4);
                        }
                        if (attackerPlayer.experienceTotal < 30) {
                            attackerPlayer.experience = (float)((double)attackerPlayer.experience + 0.8);
                        }
                        if (attackerPlayer.experienceTotal <= 30) break;
                        attackerPlayer.experience = (float)((double)attackerPlayer.experience + 0.4);
                        break;
                    }
                    case 3: {
                        if (attackerPlayer.experienceTotal < 20) {
                            attackerPlayer.experience += 5.0f;
                        }
                        if (attackerPlayer.experienceTotal < 26) {
                            attackerPlayer.experience = (float)((double)attackerPlayer.experience + 2.6);
                        }
                        if (attackerPlayer.experienceTotal < 30) {
                            attackerPlayer.experience = (float)((double)attackerPlayer.experience + 1.2);
                        }
                        if (attackerPlayer.experienceTotal <= 30) break;
                        attackerPlayer.experience = (float)((double)attackerPlayer.experience + 0.7);
                    }
                }
            }
        }
    }
}

