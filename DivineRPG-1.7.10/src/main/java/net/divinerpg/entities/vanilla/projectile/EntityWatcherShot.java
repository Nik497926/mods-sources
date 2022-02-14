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

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWatcherShot
extends EntityThrowable {
    public EntityWatcherShot(World w) {
        super(w);
    }

    public EntityWatcherShot(World var1, EntityLivingBase var2) {
        super(var1, var2);
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (!this.worldObj.isRemote) {
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 5.0f, true);
        }
        if (var1.entityHit != null) {
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), 4.0f);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

