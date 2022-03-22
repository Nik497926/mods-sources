/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventRealDamage {
    @SubscribeEvent
    public void HurtEvent(LivingHurtEvent event) {
        if (ExtraBotanyAPI.isRealDamage(event.source)) {
            event.entityLiving.setHealth(event.entityLiving.getHealth() - event.ammount);
            if (event.ammount >= event.entityLiving.getHealth()) {
                event.entityLiving.attackEntityFrom(ExtraBotanyAPI.damageSource[0], 5.0f);
            }
            event.ammount = 0.0f;
        }
    }
}

