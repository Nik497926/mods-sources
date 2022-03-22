/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityItemUnbreakable
extends EntityItem {
    public EntityItemUnbreakable(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.isImmuneToFire = true;
    }

    public EntityItemUnbreakable(World worldIn, double x, double y, double z, ItemStack stack) {
        super(worldIn, x, y, z, stack);
        this.isImmuneToFire = true;
    }

    public EntityItemUnbreakable(World worldIn) {
        super(worldIn);
        this.isImmuneToFire = true;
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        return source.getDamageType().equals(DamageSource.outOfWorld.damageType);
    }
}

