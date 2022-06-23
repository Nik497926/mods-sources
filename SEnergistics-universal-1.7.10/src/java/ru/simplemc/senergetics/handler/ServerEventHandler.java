/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class ServerEventHandler {
    public static String SPAWNED_MOB_TAG = "silentspawner_mob_tag";

    @SubscribeEvent
    public void onLivingDropsEvent(LivingDropsEvent event) {
        if (event.entity.getEntityData().hasKey(SPAWNED_MOB_TAG)) {
            event.setCanceled(true);
        }
    }
}

