/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.equipment.shield;

import com.meteor.extrabotany.common.item.equipment.shield.ItemShieldGeneratorBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSGQuick
extends ItemShieldGeneratorBase {
    public ItemSGQuick() {
        super("SGQuick");
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
        ItemSGQuick.setMaxShield(stack, 6.0f);
    }

    @Override
    public float getGenerateSpeed() {
        return 0.6f;
    }

    @Override
    public int getCD() {
        return 70;
    }

    @Override
    public int getManaCost() {
        return 5;
    }
}

