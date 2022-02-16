/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityRaglokBomb
extends EntityThrowable {
    public EntityRaglokBomb(World par1World) {
        super(par1World);
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (!this.worldObj.isRemote) {
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 2.5f, false);
        }
        this.setDead();
    }

    protected float getGravityVelocity() {
        return 0.006f;
    }
}

