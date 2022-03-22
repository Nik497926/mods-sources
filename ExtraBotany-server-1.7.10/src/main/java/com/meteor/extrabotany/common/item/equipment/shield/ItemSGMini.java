/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.equipment.shield;

import com.meteor.extrabotany.common.item.equipment.shield.ItemShieldGeneratorBase;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ItemSGMini
extends ItemShieldGeneratorBase {
    public ItemSGMini() {
        super("SGMini");
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
        ItemSGMini.setMaxShield(stack, 10.0f);
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onPlayerHurt(LivingHurtEvent event) {
        EntityPlayer player;
        if (event.entity instanceof EntityPlayer && ItemSGMini.getShieldGenerator(player = (EntityPlayer)event.entity) != null && ItemSGMini.getShieldGenerator(player).getItem() == this) {
            ItemStack sg = ItemSGMini.getShieldGenerator(player);
            if (event.ammount > 10.0f) {
                ItemSGMini.setCurrentShield(sg, 0.0f);
            }
        }
    }

    @Override
    public float getGenerateSpeed() {
        return 0.15f;
    }

    @Override
    public int getCD() {
        return 100;
    }

    @Override
    public int getManaCost() {
        return 1;
    }
}

