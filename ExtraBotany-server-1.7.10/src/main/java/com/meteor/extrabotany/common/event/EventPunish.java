/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.item.IRelic;

public class EventPunish {
    public static HashMap<UUID, Boolean> cacheModer = new HashMap();

    @SubscribeEvent
    public void onPickedUp(PlayerEvent.ItemPickupEvent event) {
        ItemStack stack = event.pickedUp.getEntityItem();
        EntityPlayer player = event.player;
        String b = "Made by Vazkii";
        if (!(stack.getItem() instanceof IRelic) || ((IRelic)stack.getItem()).getSoulbindUsername(stack).equals(b)) {
            // empty if block
        }
    }
}

