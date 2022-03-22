/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.equipment.shield;

import com.meteor.extrabotany.common.item.equipment.shield.ItemShieldGeneratorBase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ItemSGEnhanced
extends ItemShieldGeneratorBase {
    public ItemSGEnhanced() {
        super("SGEnhanced");
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
        ItemSGEnhanced.setMaxShield(stack, 14.0f);
    }

    @SubscribeEvent
    public void onPlayerHurt(LivingHurtEvent event) {
        EntityPlayer player;
        if (event.entity instanceof EntityPlayer && ItemSGEnhanced.getShieldGenerator(player = (EntityPlayer)event.entity) != null && ItemSGEnhanced.getShieldGenerator(player).getItem() == this) {
            ItemStack itemStack = ItemSGEnhanced.getShieldGenerator(player);
        }
    }

    @Override
    public float getGenerateSpeed() {
        return 0.32f;
    }

    @Override
    public int getCD() {
        return 220;
    }

    @Override
    public int getManaCost() {
        return 8;
    }
}

