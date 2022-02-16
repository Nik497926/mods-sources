/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityFlying
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityDivineRPGFlying
extends EntityFlying
implements IMob {
    private ChunkCoordinates spawnPosition;

    public EntityDivineRPGFlying(World par1World) {
        super(par1World);
        this.addBasicAI();
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

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    protected void addBasicAI() {
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(3, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (!(this.spawnPosition == null || this.worldObj.isAirBlock(this.spawnPosition.posX, this.spawnPosition.posY, this.spawnPosition.posZ) && this.spawnPosition.posY >= 1)) {
            this.spawnPosition = null;
        }
        if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0f) {
            this.spawnPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
        }
        double d0 = (double)this.spawnPosition.posX + 0.01 - this.posX;
        double d1 = (double)this.spawnPosition.posY + 0.1 - this.posY;
        double d2 = (double)this.spawnPosition.posZ + 0.01 - this.posZ;
        double d3 = MathHelper.sqrt_double((double)(d0 * d0 + d1 * d1 + d2 * d2));
        this.motionX += d0 / d3 * 0.05;
        this.motionY += d1 / d3 * 0.1;
        this.motionZ += d2 / d3 * 0.05;
        float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0f;
        float f1 = MathHelper.wrapAngleTo180_float((float)(f - this.rotationYaw));
        this.moveForward = 0.001f;
        this.rotationYaw += f1;
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

    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        this.attackEntityAsMob((Entity)par1EntityPlayer);
    }

    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere();
    }
}

