/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityDivineRPGMob
extends EntityMob {
    public EntityDivineRPGMob(World par1World) {
        super(par1World);
        if (!this.needsSpecialAI()) {
            this.addBasicAI();
        }
    }

    public boolean needsSpecialAI() {
        return false;
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

    protected void addAttackingAI() {
        this.tasks.addTask(1, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, this.getMoveSpeed() * 4.0, false));
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }

    protected void addBasicAI() {
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    protected String getLivingSound() {
        return super.getLivingSound();
    }

    protected String getHurtSound() {
        return super.getHurtSound();
    }

    protected String getDeathSound() {
        return super.getDeathSound();
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public boolean getCanSpawnHere() {
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }
}

