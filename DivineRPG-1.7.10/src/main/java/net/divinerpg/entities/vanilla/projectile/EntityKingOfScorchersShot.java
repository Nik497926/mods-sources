/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla.projectile;

import net.divinerpg.entities.base.EntityStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityKingOfScorchersShot
extends EntityThrowable {
    public EntityKingOfScorchersShot(World par1World) {
        super(par1World);
    }

    public EntityKingOfScorchersShot(World par1World, EntityLivingBase e) {
        super(par1World, e);
    }

    public EntityKingOfScorchersShot(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    public float getGravityVelocity() {
        return 0.0f;
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote && this.ticksExisted > 400) {
            this.setDead();
        }
    }

    protected void onImpact(MovingObjectPosition pos) {
        if (!this.worldObj.isRemote) {
            if (pos.entityHit != null) {
                pos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), (float)EntityStats.kingOfScorchersDamage);
                pos.entityHit.setFire(8);
            }
            this.setDead();
        }
    }
}

