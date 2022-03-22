/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.common.item.ModItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EventMobDrop {
    @SubscribeEvent
    public void onMobDeath(LivingDeathEvent event) {
        if (event.entity instanceof IMob && !event.entity.worldObj.isRemote) {
            Random r = event.entity.worldObj.rand;
            if (Math.random() < (double)0.03f && event.entity.worldObj.rand.nextInt(10) == 2) {
                event.entity.entityDropItem(new ItemStack(ModItems.key), 1.0f);
            }
            if (Math.random() > (double)0.97f && event.entity.worldObj.rand.nextInt(10) == 6) {
                event.entity.entityDropItem(new ItemStack(ModItems.boxs), 1.0f);
            }
        }
    }
}

