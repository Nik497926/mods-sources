/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$ServerTickEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.WorldServer
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.divinerpg.utils.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;

public class Ticker {
    public static int tick;
    public static int timerSword;
    private static HashMap<String, Integer> cooldowns;

    public static void addCooldown(String var0) {
        cooldowns.put(var0, 0);
    }

    @SubscribeEvent
    public void tickServer(TickEvent.ServerTickEvent evt) {
        if (evt.phase == TickEvent.Phase.END) {
            if (++tick > 100000) {
                tick = 0;
            }
            if (timerSword > 0) {
                --timerSword;
            }
            if (tick % 20 == 0) {
                Iterator<Map.Entry<String, Integer>> it = cooldowns.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Integer> entry = it.next();
                    entry.setValue(entry.getValue() + 1);
                    if (entry.getValue() < 15) continue;
                    MinecraftServer mc = MinecraftServer.getServer();
                    for (WorldServer worldServer : mc.worldServers) {
                        EntityPlayer player = worldServer.getPlayerEntityByName(entry.getKey());
                        if (player == null) continue;
                        player.setPositionAndUpdate((double)mc.getEntityWorld().provider.getSpawnPoint().posX, (double)mc.getEntityWorld().provider.getSpawnPoint().posY, (double)mc.getEntityWorld().provider.getSpawnPoint().posZ);
                        player.addChatMessage((IChatComponent)new ChatComponentText(Util.YELLOW + "\u0412\u044b \u0431\u044b\u043b\u0438 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u044b \u043d\u0430 \u0441\u043f\u0430\u0432\u043d"));
                    }
                    it.remove();
                }
            }
        }
    }

    static {
        cooldowns = new HashMap();
        timerSword = 0;
    }
}

