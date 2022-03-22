/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySakuraFall
extends EntityThrowable {
    private float roll;

    public EntitySakuraFall(World world) {
        super(world);
    }

    public EntitySakuraFall(World world, EntityLivingBase thrower, float roll) {
        super(world, thrower);
        this.roll = roll;
    }

    public float getRoll() {
        return this.roll;
    }

    protected float getGravityVelocity() {
        return 0.0f;
    }

    protected void entityInit() {
        this.setSize(0.0f, 0.0f);
    }

    public void onUpdate() {
        super.onUpdate();
        EntityLivingBase thrower = this.getThrower();
        if (!(this.worldObj.isRemote || thrower != null && thrower instanceof EntityPlayer && !thrower.isDead)) {
            this.setDead();
        } else {
            EntityPlayer player = (EntityPlayer)thrower;
            if (!this.worldObj.isRemote) {
                AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.lastTickPosX, this.lastTickPosY, this.lastTickPosZ).expand(2.0, 2.0, 2.0);
                List<EntityLivingBase> entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
                for (EntityLivingBase living : entities) {
                    if (living == thrower || living.hurtTime != 0) continue;
                    if (player != null) {
                        living.attackEntityFrom(DamageSource.causePlayerDamage(player), 14.0f);
                    } else {
                        living.attackEntityFrom(DamageSource.magic, 14.0f);
                    }
                    return;
                }
                if (this.ticksExisted > 15) {
                    this.setDead();
                }
            }
            this.motionY = 0.0;
            this.motionX = 0.0;
            this.motionZ = 0.0;
        }
    }

    protected void onImpact(MovingObjectPosition pos) {
    }
}

