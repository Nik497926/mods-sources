/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.common.entity.EntityExMachine;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.event.EventSkill;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.botania.common.item.relic.ItemRelic;

public class EventGaiaIII {
    private static float rankI = 0.75f;
    private static float rankII = 0.5f;
    private static float rankIII = 0.25f;

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void GaiaHurtEvent(LivingHurtEvent event) {
        if (event.entity instanceof EntityGaiaIII) {
            if (!(event.source.getSourceOfDamage() instanceof EntityPlayer)) {
                event.ammount = 0.0f;
            } else if (event.ammount != 1000.0f) {
                EntityGaiaIII gaia = (EntityGaiaIII)event.entity;
                if (gaia.getHealth() <= gaia.getMaxHealth() * rankI && gaia.worldObj.rand.nextInt(4) == 1) {
                    event.ammount = 0.0f;
                }
                if (gaia.getHealth() <= gaia.getMaxHealth() * rankIII && event.source == ItemRelic.damageSource()) {
                    event.ammount = 0.0f;
                }
                EntityPlayer cap = (EntityPlayer)event.source.getSourceOfDamage();
                boolean crit = cap.fallDistance > 0.0f && !cap.onGround && !cap.isOnLadder() && !cap.isInWater() && !cap.isPotionActive(Potion.blindness) && cap.ridingEntity == null;
                event.ammount = crit ? Math.min(90.0f, event.ammount) : Math.min(60.0f, event.ammount);
            }
        } else if (event.entity instanceof EntityExMachine) {
            if (!(event.source.getSourceOfDamage() instanceof EntityPlayer)) {
                event.ammount = 0.0f;
            } else if (event.ammount != 1000.0f) {
                EntityPlayer cap = (EntityPlayer)event.source.getSourceOfDamage();
                boolean crit = cap.fallDistance > 0.0f && !cap.onGround && !cap.isOnLadder() && !cap.isInWater() && !cap.isPotionActive(Potion.blindness) && cap.ridingEntity == null;
                int cap1 = crit ? 60 : 40;
                int n = cap1 = ((EntityExMachine)event.entity).getOgArmor(cap) != false ? (int)((double)cap1 * 1.5) : cap1;
                if (Math.random() <= 0.1) {
                    if (cap.getHealth() <= 5.0f) {
                        cap.setDead();
                    } else {
                        cap.setHealth(cap.getHealth() - 5.0f);
                    }
                    cap.addChatMessage((IChatComponent)new ChatComponentTranslation("\u00a76ExMachine\u00a7f: \u00a73\u0422\u0432\u043e\u0438 \u0430\u0442\u0430\u043a\u0438 \u0441\u043b\u0438\u0448\u043a\u043e\u043c \u043f\u0440\u0435\u0434\u0441\u043a\u0430\u0437\u0443\u0435\u043c\u044b!", new Object[0]));
                    event.ammount = 0.0f;
                } else {
                    float res = Math.min((float)cap1, event.ammount);
                    if (EventSkill.fullArm8lvl(cap) && EventSkill.isActiveSkill(15, cap)) {
                        res *= 1.1f;
                        event.ammount *= 1.1f;
                    }
                    event.ammount = res;
                }
            }
        }
    }
}

