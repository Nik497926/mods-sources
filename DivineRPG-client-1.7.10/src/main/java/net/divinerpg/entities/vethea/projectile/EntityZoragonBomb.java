/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityZoragonBomb
extends EntityThrowable {
    public EntityZoragonBomb(World par1World) {
        super(par1World);
    }

    public EntityZoragonBomb(World par1World, EntityLiving par2EntityLiving) {
        super(par1World, (EntityLivingBase)par2EntityLiving);
    }

    public EntityZoragonBomb(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        if (!this.worldObj.isRemote) {
            this.setDead();
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 3.0f, false);
        }
    }
}

