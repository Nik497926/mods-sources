/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventRingInInventory {
    private Item Ring = null;

    @SubscribeEvent
    public void StengthOnPlayer(LivingHurtEvent evt) {
        if (evt.source.getEntity() != null && evt.source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)evt.source.getEntity();
            ItemStack inv = player.inventory.getCurrentItem();
            this.Ring = inv != null ? inv.getItem() : null;
            DamageSource damageSource = evt.source;
        }
    }
}

