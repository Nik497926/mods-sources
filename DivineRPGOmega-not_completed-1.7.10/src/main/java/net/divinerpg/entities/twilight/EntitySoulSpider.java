/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntitySoulSpider
extends EntityDivineRPGMob {
    public EntitySoulSpider(World w) {
        super(w);
        this.setSize(0.3f, 0.3f);
        this.addAttackingAI();
        this.experienceValue = 0;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.soulSpiderHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.soulSpiderDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.soulSpiderSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.soulSpiderFollowRange);
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted > 600 && !this.worldObj.isRemote) {
            this.setDead();
        }
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.growl);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    @Override
    public String mobName() {
        return "Soul Spider";
    }
}

