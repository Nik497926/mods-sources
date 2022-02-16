/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIOwnerHurtByTarget
 *  net.minecraft.entity.ai.EntityAIOwnerHurtTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import net.divinerpg.entities.base.EntityAIOwnerWithoutTeleport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityDivineRPGTameable
extends EntityTameable {
    public EntityDivineRPGTameable(World w) {
        super(w);
        this.addBasicAI();
        this.setTamed(false);
    }

    public double getHP() {
        return this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
    }

    public double getMoveSpeed() {
        return this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
    }

    public double getAttackDamage() {
        return this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
    }

    public double getFollowRange() {
        return this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
    }

    public double getKnockbackResistance() {
        return this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue();
    }

    public abstract String mobName();

    protected String getLivingSound() {
        return super.getLivingSound();
    }

    protected String getHurtSound() {
        return super.getHurtSound();
    }

    protected String getDeathSound() {
        return super.getDeathSound();
    }

    protected void addBasicAI() {
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)this.aiSit);
        this.tasks.addTask(3, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, true));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIOwnerWithoutTeleport(this, 1.0, 10.0f, 2.0f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAIOwnerHurtByTarget((EntityTameable)this));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAIOwnerHurtTarget((EntityTameable)this));
        this.targetTasks.addTask(3, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
    }

    public EntityLivingBase getAttackTarget() {
        return this.isTamed() || this.isAngry() ? super.getAttackTarget() : null;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public boolean getCanSpawnHere() {
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    public boolean attackEntityAsMob(Entity e) {
        boolean attack = super.attackEntityAsMob(e);
        if (attack && e instanceof EntityLiving) {
            ((EntityLiving)e).setRevengeTarget((EntityLivingBase)this);
        }
        return attack;
    }

    public boolean isAngry() {
        return false;
    }
}

