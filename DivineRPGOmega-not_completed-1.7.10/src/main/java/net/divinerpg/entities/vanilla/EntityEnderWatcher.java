/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityEnderWatcher
extends EntityEnderman {
    public EntityEnderWatcher(World par1World) {
        super(par1World);
        this.setSize(0.6f, 0.6f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.enderWatcherHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.enderWatcherDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.enderWatcherSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.enderWatcherFollowRange);
    }

    protected Item getDropItem() {
        return VanillaItemsOther.enderShards;
    }

    protected void dropFewItems(boolean par1, int par2) {
        Item item = this.getDropItem();
        Item item2 = VanillaItemsOther.watchingEye;
        int j = this.rand.nextInt(2 + par2);
        for (int k = 0; k < j; ++k) {
            this.dropItem(item, 2);
            this.dropItem(item2, 1);
        }
    }
}

