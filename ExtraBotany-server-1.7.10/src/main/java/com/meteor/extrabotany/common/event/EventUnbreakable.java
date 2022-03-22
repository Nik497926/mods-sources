/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EventUnbreakable {
    @SubscribeEvent
    public void onExpire(ItemExpireEvent event) {
        if (event.entityItem instanceof EntityItemUnbreakable) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void safeAsgard(BlockEvent.BreakEvent event) {
        if (event.world.provider.dimensionId == 150 && event.block != Blocks.dirt && event.block != Blocks.stone && event.block != Blocks.grass && !event.block.getUnlocalizedName().toLowerCase().contains("ore")) {
            event.setCanceled(true);
        }
    }
}

