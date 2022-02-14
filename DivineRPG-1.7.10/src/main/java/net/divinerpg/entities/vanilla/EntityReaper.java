/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityReaper
extends EntityDivineRPGMob {
    public EntityReaper(World par1World) {
        super(par1World);
        this.addAttackingAI();
        this.isImmuneToFire = true;
        this.setSize(0.7f, 1.2f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.reaperHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.reaperDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.reaperSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.reaperFollowRange);
    }

    protected String getLivingSound() {
        return "mob.wither.idle";
    }

    protected String getHurtSound() {
        return "mob.skeleton.hurt";
    }

    protected String getDeathSound() {
        return "mob.skeleton.death";
    }

    protected void dropFewItems(boolean b, int j) {
        if (this.rand.nextInt(100) < 70) {
            this.dropItem(JourneyItemsOther.withicDust, 1);
        }
        if (this.rand.nextInt(100) < 30) {
            this.dropItem(JourneyItemsOther.withicDust, 2);
        }
    }

    @Override
    public String mobName() {
        return "Reaper";
    }
}

