/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import com.meteor.extrabotany.common.entity.EntityLycorisradiata;
import com.meteor.extrabotany.common.item.ModItems;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityLycorisradiataRed
extends EntityLycorisradiata {
    public EntityLycorisradiataRed(World world) {
        super(world);
    }

    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        if (wasRecentlyHit) {
            Random r1 = new Random();
            Random r2 = new Random();
            Random r3 = new Random();
            this.entityDropItem(new ItemStack(ModItems.material, r1.nextInt(2) + 1, 4), 1.0f);
            this.entityDropItem(new ItemStack(ModItems.material, r2.nextInt(2), 5), 1.0f);
            this.entityDropItem(new ItemStack(ModItems.material, r3.nextInt(2), 6), 1.0f);
        }
    }
}

