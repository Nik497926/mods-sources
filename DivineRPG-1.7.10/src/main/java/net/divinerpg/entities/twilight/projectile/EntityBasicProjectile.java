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
package net.divinerpg.entities.twilight.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBasicProjectile
extends EntityThrowable {
    public float damage;

    public EntityBasicProjectile(World var1) {
        super(var1);
    }

    public EntityBasicProjectile(World var1, EntityLivingBase var3, float dam) {
        super(var1, var3);
        this.damage = dam;
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void onUpdate() {
        super.onUpdate();
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (var1.entityHit != null) {
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), this.damage);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }

    protected float getGravityVelocity() {
        return 0.01f;
    }
}

