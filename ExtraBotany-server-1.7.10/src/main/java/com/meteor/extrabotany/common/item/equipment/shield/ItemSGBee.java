/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.equipment.shield;

import com.meteor.extrabotany.common.item.equipment.shield.ItemShieldGeneratorBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSGBee
extends ItemShieldGeneratorBase {
    public ItemSGBee() {
        super("SGBee");
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
        ItemSGBee.setMaxShield(stack, 44.0f);
    }

    @Override
    public float getGenerateSpeed() {
        return 0.28f;
    }

    @Override
    public int getCD() {
        return 140;
    }

    @Override
    public int getManaCost() {
        return 2;
    }
}

