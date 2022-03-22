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

public class ItemSGGaia
extends ItemShieldGeneratorBase {
    public ItemSGGaia() {
        super("SGGaia");
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
        ItemSGGaia.setMaxShield(stack, 22.0f);
    }

    @SubscribeEvent
    public void onPlayerHurt(LivingHurtEvent event) {
        EntityPlayer player;
        if (event.entity instanceof EntityPlayer && ItemSGGaia.getShieldGenerator(player = (EntityPlayer)event.entity) != null && ItemSGGaia.getShieldGenerator(player).getItem() == this) {
            ItemStack itemStack = ItemSGGaia.getShieldGenerator(player);
        }
    }

    @Override
    public float getGenerateSpeed() {
        return 0.25f;
    }

    @Override
    public int getCD() {
        return 180;
    }

    @Override
    public int getManaCost() {
        return 3;
    }
}

