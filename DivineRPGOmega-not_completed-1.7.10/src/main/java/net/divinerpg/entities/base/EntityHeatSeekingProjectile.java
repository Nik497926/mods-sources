/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityHeatSeekingProjectile
extends EntityThrowable {
    private Entity target = null;
    private boolean onlyPlayers = false;

    public EntityHeatSeekingProjectile(World w) {
        super(w);
    }

    public EntityHeatSeekingProjectile(World w, EntityLivingBase e) {
        super(w, e);
    }

    public void setPlayersOnly() {
        this.onlyPlayers = true;
    }

    public float getGravityVelocity() {
        return 0.0f;
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.worldObj.isRemote) {
            return;
        }
        List mobs = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(20.0, 20.0, 20.0));
        boolean findNewTarget = this.target == null || this.target != null && this.target.isDead;
        for (EntityLivingBase e : mobs) {
            if (e == this.getThrower() || this.onlyPlayers && (!this.onlyPlayers || !(e instanceof EntityPlayer)) || !findNewTarget || this.target != null && (this.target == null || !(this.getDistanceToEntity((Entity)e) < this.getDistanceToEntity(this.target)))) continue;
            this.target = e;
        }
        if (this.target != null) {
            Vec3 dir = Vec3.createVectorHelper((double)(this.target.posX - this.posX), (double)(this.target.posY - this.posY), (double)(this.target.posZ - this.posZ)).normalize();
            this.motionX = dir.xCoord / 1.5;
            this.motionY = dir.yCoord / 1.5;
            this.motionZ = dir.zCoord / 1.5;
        }
        if (this.ticksExisted > 50) {
            this.setDead();
        }
    }
}

