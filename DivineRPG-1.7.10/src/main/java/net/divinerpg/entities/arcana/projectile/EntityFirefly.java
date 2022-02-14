/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana.projectile;

import net.divinerpg.entities.base.EntityHeatSeekingProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFirefly
extends EntityHeatSeekingProjectile {
    public EntityFirefly(World w) {
        super(w);
    }

    public EntityFirefly(World w, EntityLivingBase e) {
        super(w, e);
    }

    protected void onImpact(MovingObjectPosition pos) {
        if (pos.entityHit != null) {
            pos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), 15.0f);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

