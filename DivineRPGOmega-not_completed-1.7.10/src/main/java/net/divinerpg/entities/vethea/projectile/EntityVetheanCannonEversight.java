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
package net.divinerpg.entities.vethea.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityVetheanCannonEversight
extends EntityThrowable {
    public int damage;

    public EntityVetheanCannonEversight(World par1) {
        super(par1);
    }

    public EntityVetheanCannonEversight(World par1, EntityLivingBase par2, int par3) {
        super(par1, par2);
        this.damage = par3;
    }

    public EntityVetheanCannonEversight(World par1, double par2, double par4, double par6) {
        super(par1, par2, par4, par6);
    }

    protected void onImpact(MovingObjectPosition par1) {
        if (par1.entityHit != null) {
            par1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), (float)this.damage);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

