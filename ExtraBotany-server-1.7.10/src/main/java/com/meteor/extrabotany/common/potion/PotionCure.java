/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.potion;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.potion.ModPotions;
import com.meteor.extrabotany.common.potion.PotionMods;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PotionCure
extends PotionMods {
    public PotionCure() {
        super(ConfigHandler.idPotionC, "cure", false, 15963926, 4);
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void LivingEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving.isPotionActive(ModPotions.cure)) {
            Collection potions = event.entityLiving.getActivePotionEffects();
            boolean flag = false;
            Iterator i$ = potions.iterator();
            if (i$.hasNext()) {
                PotionEffect potion = (PotionEffect)i$.next();
                int id = potion.getPotionID();
                if (((Boolean)ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[id], new String[]{"isBadEffect", "isBadEffect", "J"})).booleanValue()) {
                    event.entityLiving.removePotionEffect(id);
                    boolean bl = true;
                }
            }
        }
    }
}

