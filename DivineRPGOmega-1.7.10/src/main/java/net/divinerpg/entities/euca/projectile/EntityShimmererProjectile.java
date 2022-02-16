/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntitySmallFireball
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.euca.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityShimmererProjectile
extends EntitySmallFireball {
    public EntityShimmererProjectile(World w, EntityLivingBase e, double x, double y, double z) {
        super(w, e, x, y, z);
        this.setSize(0.3125f, 0.3125f);
        this.extinguish();
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (var1.entityHit != null) {
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.shootingEntity), 14.0f);
            var1.entityHit.motionY = 1.3;
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }

    public void setThrowableHeading(double d, double e, double f, float g, int i) {
    }
}

