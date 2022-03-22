/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic;

import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemRelicAdv
extends ItemRelic {
    public ItemRelicAdv(String name) {
        super(name);
    }

    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        EntityItemUnbreakable entity = new EntityItemUnbreakable(world, location.posX, location.posY, location.posZ, itemstack);
        if (location instanceof EntityItem) {
            entity.delayBeforeCanPickup = 40;
        }
        entity.motionX = location.motionX;
        entity.motionY = location.motionY;
        entity.motionZ = location.motionZ;
        return entity;
    }
}

