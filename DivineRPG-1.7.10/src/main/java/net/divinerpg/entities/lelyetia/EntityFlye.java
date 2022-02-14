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
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.lelyetia;

import net.divinerpg.entities.lelyetia.EntityAIFlyingNew;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityFlye
extends EntityAIFlyingNew {
    private ChunkCoordinates currentFlightTarget;
    private int flyTimer;

    public EntityFlye(World var1) {
        super(var1);
        double moveSpeed = 0.35f;
        this.getNavigator().setAvoidsWater(true);
        ((EntityLiving)this).tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        ((EntityLiving)this).tasks.addTask(5, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, (double)0.35f, true));
        ((EntityLiving)this).tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 32.0f));
        ((EntityLiving)this).tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        ((EntityLiving)this).tasks.addTask(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, (double)0.35f));
        ((EntityLiving)this).targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        ((EntityLiving)this).targetTasks.addTask(3, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.flyTimer = 0;
        this.setSize(1.0f, 1.0f);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityPlayer var1 = ((Entity)this).worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 3.0);
        if (var1 == null || var1.getDistanceToEntity((Entity)this) > 3.0f) {
            return;
        }
        var1.setFire(4);
    }

    protected void dropFewItems(boolean par1, int par2) {
        if (this.rand.nextInt(40) == 15) {
            this.dropItem(JourneyItemsWeapon.PyroArchergun, 1);
        }
        if (this.rand.nextInt(100) < 25) {
            switch (this.rand.nextInt(3)) {
                case 0: {
                    this.dropItem(VanillaItemsOther.repairEssence, 1);
                    break;
                }
                case 1: {
                    this.dropItem(VanillaItemsOther.repairEssence, 2);
                    break;
                }
                case 2: {
                    this.dropItem(VanillaItemsOther.repairEssence, 3);
                    break;
                }
                case 3: {
                    this.dropItem(VanillaItemsOther.repairEssence, 4);
                }
            }
        }
    }

    public boolean getCanSpawnHere() {
        return ((Entity)this).worldObj.difficultySetting != EnumDifficulty.PEACEFUL && ((Entity)this).worldObj.checkNoEntityCollision(((Entity)this).boundingBox) && ((Entity)this).worldObj.getCollidingBoundingBoxes((Entity)this, ((Entity)this).boundingBox).isEmpty() && !((Entity)this).worldObj.isAnyLiquid(((Entity)this).boundingBox);
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.65f);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(11.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(135.0);
    }

    protected boolean isAIEnabled() {
        return true;
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95f;
    }

    protected String getLivingSound() {
        return "divinerpg:FlyeLiving";
    }

    protected String getHurtSound() {
        return "divinerpg:FlyeHit";
    }

    protected String getDeathSound() {
        return "divinerpg:FlyeDeath";
    }

    public void onUpdate() {
        super.onUpdate();
        ((Entity)this).motionY *= 0.2000000238418579;
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.getAttackTarget() != null) {
            int var1 = (int)this.getAttackTarget().posX;
            int var2 = (int)this.getAttackTarget().posY;
            int var3 = (int)this.getAttackTarget().posZ;
            this.currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
        } else if (this.flyTimer != 0) {
            this.flyTimer = 120;
            this.currentFlightTarget = new ChunkCoordinates((int)(((Entity)this).posX + (double)this.rand.nextInt(16) - 8.0), (int)(((Entity)this).posY + (double)this.rand.nextInt(32) - 16.0), (int)(((Entity)this).posZ + (double)this.rand.nextInt(16) - 8.0));
        }
        if (this.currentFlightTarget != null) {
            double var4 = (double)this.currentFlightTarget.posX - ((Entity)this).posX;
            double var5 = (double)((float)this.currentFlightTarget.posY + this.rand.nextFloat()) + 1.0 - this.posY;
            double var6 = (double)this.currentFlightTarget.posZ - ((Entity)this).posZ;
            if (Math.signum(var4) != 0.0 || Math.signum(var5) != 0.0 || Math.signum(var6) != 0.0) {
                ((Entity)this).motionX += (Math.signum(var4) * 0.15 - ((Entity)this).motionX) * (double)0.1f;
                ((Entity)this).motionY += (Math.signum(var5) * 1.699999988079071 - ((Entity)this).motionY) * (double)0.1f;
                ((Entity)this).motionZ += (Math.signum(var6) * 0.15 - ((Entity)this).motionZ) * (double)0.1f;
                float var7 = (float)(Math.atan2(((Entity)this).motionZ, ((Entity)this).motionX) * 180.0 / Math.PI) - 90.0f;
                float var8 = MathHelper.wrapAngleTo180_float((float)(var7 - ((Entity)this).rotationYaw));
                ((EntityLivingBase)this).moveForward = 0.5f;
                ((Entity)this).rotationYaw += var8;
            }
            --this.flyTimer;
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected void fall(float par1) {
    }

    @Override
    protected void updateFallState(double par1, boolean par3) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        if (!((Entity)this).isDead) {
            this.attackEntityAsMob((Entity)par1EntityPlayer);
        }
    }
}

