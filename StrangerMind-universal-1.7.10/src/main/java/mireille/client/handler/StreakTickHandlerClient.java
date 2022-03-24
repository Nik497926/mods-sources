package mireille.client.handler;

import net.minecraft.client.multiplayer.*;
import mireille.api.*;
import mireille.common.entity.*;
import cpw.mods.fml.common.gameevent.*;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

import java.util.*;
import java.util.Map.Entry;

import cpw.mods.fml.common.eventhandler.*;
import net.minecraft.client.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import mireille.*;

public class StreakTickHandlerClient
{
    public float renderTick;
    public WorldClient worldInstance;
    public HashMap<String, ArrayList<StreakLocationInfo>> playerLoc;
    public HashMap<String, EntityStreak> streaks;
    
    public StreakTickHandlerClient() {
        this.playerLoc = new HashMap<String, ArrayList<StreakLocationInfo>>();
        this.streaks = new HashMap<String, EntityStreak>();
    }
    
    @SubscribeEvent
    public void renderTick(final TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            this.renderTick = event.renderTickTime;
            final Iterator<Map.Entry<String, EntityStreak>> iterator = this.streaks.entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<String, EntityStreak> e = iterator.next();
                if (e.getValue().parent != null) {
                    final EntityStreak streak = e.getValue();
                    if (e.getValue().parent.isDead) {
                        streak.setDead();
                        iterator.remove();
                    }
                    else {
                        this.updatePos(streak, streak.parent);
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void worldTick(ClientTickEvent event) {
       if (event.phase == Phase.END && Minecraft.getMinecraft().theWorld != null) {
          WorldClient world = Minecraft.getMinecraft().theWorld;
          if (this.worldInstance != world) {
             this.worldInstance = world;
             this.streaks.clear();
          }

          Iterator ite = this.streaks.entrySet().iterator();

          while(true) {
             Entry e;
             do {
                if (!ite.hasNext()) {
                   return;
                }

                e = (Entry)ite.next();
             } while(((EntityStreak)e.getValue()).worldObj.provider.dimensionId == world.provider.dimensionId && world.getWorldTime() - ((EntityStreak)e.getValue()).lastUpdate <= 10L);

             ((EntityStreak)e.getValue()).setDead();
             ite.remove();
          }
       }
    }
    
    @SubscribeEvent
    public void playerTick(final TickEvent.PlayerTickEvent event) {
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.END) {
            final EntityPlayer player = event.player;
            if (player.worldObj.getPlayerEntityByName(player.getCommandSenderName()) != player) {
                return;
            }
            final WorldClient world = Minecraft.getMinecraft().theWorld;
            EntityStreak hat = this.streaks.get(player.getCommandSenderName());
            if (hat == null || hat.isDead) {
                if (player.getCommandSenderName().equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.getCommandSenderName())) {
                    for (final Map.Entry<String, EntityStreak> e : this.streaks.entrySet()) {
                        e.getValue().setDead();
                    }
                }
                hat = new EntityStreak((World)world, (EntityLivingBase)player);
                this.streaks.put(player.getCommandSenderName(), hat);
                world.spawnEntityInWorld((Entity)hat);
            }
            final ArrayList<StreakLocationInfo> loc = this.getPlayerStreakLocationInfo(player);
            final StreakLocationInfo oldest = loc.get(0);
            loc.remove(0);
            loc.add(oldest);
            oldest.update(player);
            final StreakLocationInfo newest = loc.get(loc.size() - 2);
            final double distX = newest.posX - oldest.posX;
            final double distZ = newest.posZ - oldest.posZ;
            final StreakLocationInfo streakLocationInfo = newest;
            streakLocationInfo.startU += Math.sqrt(distX * distX + distZ * distZ) / newest.height;
            while (oldest.startU > 1.0) {
                final StreakLocationInfo streakLocationInfo2 = oldest;
                --streakLocationInfo2.startU;
            }
        }
    }
    
    public ArrayList<StreakLocationInfo> getPlayerStreakLocationInfo(final EntityPlayer player) {
        ArrayList<StreakLocationInfo> loc = this.playerLoc.get(player.getCommandSenderName());
        if (loc == null) {
            loc = new ArrayList<StreakLocationInfo>();
            this.playerLoc.put(player.getCommandSenderName(), loc);
        }
        final int time = StrangerMind.streakTime;
        if (loc.size() < time) {
            for (int i = 0; i < time - loc.size(); ++i) {
                loc.add(0, new StreakLocationInfo(player));
            }
        }
        else if (loc.size() > time) {
            loc.remove(0);
        }
        return loc;
    }
    
    public void updatePos(final EntityStreak streak, final EntityLivingBase parent) {
        streak.lastTickPosX = streak.parent.lastTickPosX;
        streak.lastTickPosY = streak.parent.lastTickPosY;
        streak.lastTickPosZ = streak.parent.lastTickPosZ;
        streak.prevPosX = streak.parent.prevPosX;
        streak.prevPosY = streak.parent.prevPosY;
        streak.prevPosZ = streak.parent.prevPosZ;
        streak.posX = streak.parent.posX;
        streak.posY = streak.parent.posY;
        streak.posZ = streak.parent.posZ;
    }
}
