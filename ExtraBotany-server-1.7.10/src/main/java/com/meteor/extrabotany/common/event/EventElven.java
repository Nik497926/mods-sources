/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.common.core.handler.ElvenHandler;
import com.meteor.extrabotany.common.entity.EntityElven;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.event.entity.EntityEvent;

public class EventElven {
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityElven && ElvenHandler.get((EntityElven)event.entity) == null && event.entity instanceof EntityElven && event.entity.getExtendedProperties("elven") == null) {
            event.entity.registerExtendedProperties("elven", new ElvenHandler((EntityElven)event.entity));
        }
    }
}

