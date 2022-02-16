/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntityFrostShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.ItemsFood;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityFrost
extends EntityDivineRPGMob {
    private float heightOffset = 0.5f;
    private int heightOffsetUpdateTime;
    private int anger;

    public EntityFrost(World var1) {
        super(var1);
        this.experienceValue = 20;
        this.setSize(1.0f, 1.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.frostHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.frostDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.frostSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.frostFollowRange);
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.frost);
    }

    @Override
    protected String getHurtSound() {
        return "mob.blaze.hit";
    }

    @Override
    protected String getDeathSound() {
        return "mob.blaze.death";
    }

    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.entityToAttack == null && this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 22.0) != null) {
                this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 22.0);
            }
            if (this.getEntityToAttack() != null && this.getEntityToAttack() instanceof EntityPlayer && ((EntityPlayer)this.getEntityToAttack()).capabilities.isCreativeMode) {
                this.entityToAttack = null;
            }
            --this.heightOffsetUpdateTime;
            if (this.heightOffsetUpdateTime <= 0) {
                this.heightOffsetUpdateTime = 100;
                this.heightOffset = 0.5f + (float)this.rand.nextGaussian() * 3.0f;
            }
        }
        if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() + 5.0 > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
            this.motionY += ((double)0.3f - this.motionY) * (double)0.3f;
        } else if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() <= this.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
            this.attackEntity(this.getEntityToAttack(), this.getDistanceToEntity(this.getEntityToAttack()));
        }
        if (!this.onGround && this.motionY < 0.0) {
            this.motionY *= 0.6;
        }
        super.onLivingUpdate();
    }

    protected void attackEntity(Entity par1Entity, float par2) {
        if (this.attackTime <= 0 && par2 < 2.0f && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY) {
            this.attackTime = 20;
            this.attackEntityAsMob(par1Entity);
        } else if (par2 < 30.0f) {
            double var3 = par1Entity.posX - this.posX;
            double var5 = par1Entity.boundingBox.minY - 1.0 - this.posY;
            double var7 = par1Entity.posZ - this.posZ;
            if (this.attackTime == 0) {
                ++this.anger;
                this.attackTime = 10;
                if (this.anger == 3) {
                    this.attackTime = 40;
                    this.anger = 0;
                }
                float var9 = MathHelper.sqrt_float((float)par2) * 0.5f;
                this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                EntityFrostShot entity = new EntityFrostShot(this.worldObj, (EntityLivingBase)this, var3, var5, var7);
                entity.posY = this.posY + (double)(this.height / 2.0f) + 1.5;
                this.worldObj.spawnEntityInWorld((Entity)entity);
            }
            this.rotationYaw = (float)(Math.atan2(var7, var3) * 180.0 / Math.PI) - 90.0f;
            this.hasAttacked = true;
        }
    }

    protected void fall(float par1) {
    }

    protected void dropFewItems(boolean var1, int var2) {
        if (this.rand.nextBoolean()) {
            this.dropItem(ItemsFood.tomato, 2 + this.rand.nextInt(2 + var2));
        }
        this.dropItem(IceikaItems.iceShards, 3 + this.rand.nextInt(3 + var2));
        if (this.rand.nextInt(3) == 0) {
            this.dropItem(IceikaItems.iceStone, this.rand.nextInt(2) + 1);
        }
    }

    @Override
    public String mobName() {
        return "Frost";
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox.addCoord(0.0, 0.5, 0.0)).isEmpty();
    }
}

