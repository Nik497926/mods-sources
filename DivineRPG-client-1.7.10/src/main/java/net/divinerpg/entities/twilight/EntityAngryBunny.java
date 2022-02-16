/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityAngryBunny
extends EntityDivineRPGMob {
    public EntityAngryBunny(World var1) {
        super(var1);
        this.setSize(1.5f, 2.0f);
        this.experienceValue = 40;
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.angryBunnyHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.angryBunnySpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.angryBunnyFollowRange);
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        double i = EntityStats.angryBunnyDamage;
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)i);
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.hiss);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    @Override
    public String mobName() {
        return "Angry Bunny";
    }
}

