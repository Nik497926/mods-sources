/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.potion;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.potion.ModPotions;
import com.meteor.extrabotany.common.potion.PotionMods;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PotionDurance
extends PotionMods {
    public PotionDurance() {
        super(ConfigHandler.idPotionD, "durance", false, 10249382, 3);
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    @SubscribeEvent
    public void LivingEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving.isPotionActive(ModPotions.durance)) {
            event.entityLiving.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
        }
    }
}

