/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.common.potion.ModPotions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventHighDamageResistance {
    @SubscribeEvent
    public void HurtEvent(LivingHurtEvent event) {
        EntityPlayer player;
        if (event.entity instanceof EntityPlayerMP && (player = (EntityPlayer)event.entity).isPotionActive(ModPotions.slowparticlesorting) && event.ammount >= (float)((double)player.getMaxHealth() * 0.2) && event.ammount <= player.getMaxHealth() * 3.0f) {
            event.ammount = (float)((double)player.getMaxHealth() * 0.2);
        }
    }
}

