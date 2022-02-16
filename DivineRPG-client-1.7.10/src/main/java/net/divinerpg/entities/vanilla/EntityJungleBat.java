/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityJungleBat
extends EntityDivineRPGMob {
    private ChunkCoordinates currentFlightTarget;

    public EntityJungleBat(World par1World) {
        super(par1World);
        this.setSize(0.5f, 0.9f);
        this.setIsBatHanging(true);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.jungleBatHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.jungleBatDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.jungleBatSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.jungleBatFollowRange);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte) 0);
    }

    protected float getSoundVolume() {
        return 0.1f;
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95f;
    }

    @Override
    protected String getLivingSound() {
        return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : "mob.bat.idle";
    }

    @Override
    protected String getHurtSound() {
        return "mob.bat.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "mob.bat.death";
    }

    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity par1Entity) {
    }

    public boolean getIsBatHanging() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setIsBatHanging(boolean par1) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);
        if (par1) {
            this.dataWatcher.updateObject(16, (Object)((byte)(var2 | 1)));
        } else {
            this.dataWatcher.updateObject(16, (Object)((byte)(var2 & 0xFFFFFFFE)));
        }
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.getIsBatHanging()) {
            this.motionZ = 0.0;
            this.motionY = 0.0;
            this.motionX = 0.0;
            this.posY = (double)MathHelper.floor_double((double)this.posY) + 1.0 - (double)this.height;
        } else {
            this.motionY *= (double)0.6f;
        }
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.getIsBatHanging()) {
            if (!this.worldObj.getBlock(MathHelper.floor_double((double)this.posX), (int)this.posY + 1, MathHelper.floor_double((double)this.posZ)).isNormalCube()) {
                this.setIsBatHanging(false);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            } else {
                if (this.rand.nextInt(200) == 0) {
                    this.rotationYawHead = this.rand.nextInt(360);
                }
                if (this.worldObj.getClosestPlayerToEntity((Entity)this, 4.0) != null) {
                    this.setIsBatHanging(false);
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                }
            }
        } else {
            if (!(this.currentFlightTarget == null || this.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ) && this.currentFlightTarget.posY >= 1)) {
                this.currentFlightTarget = null;
            }
            if (this.currentFlightTarget == null || this.rand.nextInt(30) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0f) {
                this.currentFlightTarget = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
            }
            double d0 = (double)this.currentFlightTarget.posX + 0.5 - this.posX;
            double d1 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
            double d2 = (double)this.currentFlightTarget.posZ + 0.5 - this.posZ;
            this.motionX += (Math.signum(d0) * 0.5 - this.motionX) * (double)0.1f;
            this.motionY += (Math.signum(d1) * (double)0.7f - this.motionY) * (double)0.1f;
            this.motionZ += (Math.signum(d2) * 0.5 - this.motionZ) * (double)0.1f;
            float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0f;
            float f1 = MathHelper.wrapAngleTo180_float((float)(f - this.rotationYaw));
            this.moveForward = 0.5f;
            this.rotationYaw += f1;
            if (this.rand.nextInt(100) == 0 && this.worldObj.getBlock(MathHelper.floor_double((double)this.posX), (int)this.posY + 1, MathHelper.floor_double((double)this.posZ)).isNormalCube()) {
                this.setIsBatHanging(true);
            }
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (!this.worldObj.isRemote && this.getIsBatHanging()) {
            this.setIsBatHanging(false);
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.dataWatcher.updateObject(16, (Object)par1NBTTagCompound.getByte("BatFlags"));
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("BatFlags", this.dataWatcher.getWatchableObjectByte(16));
    }

    protected Item getDropItem() {
        return VanillaItemsOther.jungleShards;
    }

    @Override
    public String mobName() {
        return "Jungle Bat";
    }
}

