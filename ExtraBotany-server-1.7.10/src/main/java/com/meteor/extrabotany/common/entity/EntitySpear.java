/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import com.meteor.extrabotany.common.core.handler.WorldHandler;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySpear
extends EntityThrowable {
    public EntitySpear(World world) {
        super(world);
    }

    public EntitySpear(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    protected float getGravityVelocity() {
        return 0.18f;
    }

    protected void entityInit() {
        this.setSize(0.0f, 0.0f);
        this.dataWatcher.addObject(20, (Object)0);
    }

    public void onUpdate() {
        super.onUpdate();
        double y = this.motionY;
        EntityLivingBase thrower = this.getThrower();
        if (!(this.worldObj.isRemote || thrower != null && thrower instanceof EntityPlayer && !thrower.isDead)) {
            this.setDead();
        } else {
            EntityPlayer player = (EntityPlayer)thrower;
            if (!this.worldObj.isRemote) {
                AxisAlignedBB axis = AxisAlignedBB.getBoundingBox((double)this.posX, (double)this.posY, (double)this.posZ, (double)this.lastTickPosX, (double)this.lastTickPosY, (double)this.lastTickPosZ).expand(2.0, 2.0, 2.0);
                List<EntityLivingBase> entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
                for (EntityLivingBase living : entities) {
                    if (living == thrower || living.hurtTime != 0) continue;
                    if (player != null) {
                        living.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), 12.0f);
                        living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 40, 8));
                    } else {
                        living.attackEntityFrom(DamageSource.generic, 12.0f);
                    }
                    this.onImpact(new MovingObjectPosition((Entity)living));
                    return;
                }
            }
            this.setDelay(Math.max(this.getDelay() - 1, 0));
            this.motionY = this.getDelay() > 0 ? 0.0 : y;
            this.motionX = 0.0;
            this.motionZ = 0.0;
        }
    }

    protected void onImpact(MovingObjectPosition pos) {
        EntityLivingBase thrower = this.getThrower();
        if (pos.entityHit == null || pos.entityHit != thrower) {
            WorldHandler.createMagicExplosion((Entity)this, this.posX, this.posY, this.posZ, 2.0f, false);
            this.setDead();
        }
    }

    public int getDelay() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public void setDelay(int i) {
        this.dataWatcher.updateObject(20, (Object)i);
    }

    public void writeEntityToNBT(NBTTagCompound cmp) {
        super.writeEntityToNBT(cmp);
        cmp.setInteger("delay", this.getDelay());
    }

    public void readEntityFromNBT(NBTTagCompound cmp) {
        super.readEntityFromNBT(cmp);
        this.setDelay(cmp.getInteger("delay"));
    }
}

