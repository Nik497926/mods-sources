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

public class EntityWitherspine
extends EntityDivineRPGMob {
    public EntityWitherspine(World par1World) {
        super(par1World);
        this.addAttackingAI();
        this.isImmuneToFire = true;
        this.setSize(0.7f, 4.2f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.witherspineHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.witherspineDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.witherspineSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.witherspineFollowRange);
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
        if (this.rand.nextInt(100) < 80) {
            this.dropItem(JourneyItemsOther.withicDust, 1);
        }
        if (this.rand.nextInt(100) < 60) {
            this.dropItem(JourneyItemsOther.withicDust, 2);
        }
        if (this.rand.nextInt(100) < 60) {
            this.dropItem(JourneyItemsOther.withicSpine, 1);
        }
        if (this.rand.nextInt(100) < 25) {
            this.dropItem(JourneyItemsOther.withicSpine, 2);
        }
    }

    @Override
    public String mobName() {
        return "Witherspine";
    }
}

