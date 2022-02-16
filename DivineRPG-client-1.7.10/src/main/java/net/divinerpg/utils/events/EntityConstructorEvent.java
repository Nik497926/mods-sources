/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.event.entity.EntityEvent$EntityConstructing
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.divinerpg.utils.FlyingHelper;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.events.ArcanaPortalCoords;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;

public class EntityConstructorEvent {
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing evt) {
        if (evt.entity instanceof EntityPlayer && ArcanaHelper.getProperties((EntityPlayer)evt.entity) == null) {
            ArcanaHelper.addProperties((EntityPlayer)evt.entity);
        }
        if (evt.entity instanceof EntityPlayer && FlyingHelper.getProperties((EntityPlayer)evt.entity) == null) {
            FlyingHelper.addProperties((EntityPlayer)evt.entity);
        }
        if (evt.entity instanceof EntityPlayer && ArcanaPortalCoords.getProperties((EntityPlayer)evt.entity) == null) {
            ArcanaPortalCoords.addProperties((EntityPlayer)evt.entity);
        }
    }
}

