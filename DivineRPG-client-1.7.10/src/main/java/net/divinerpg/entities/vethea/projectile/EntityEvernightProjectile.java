/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEvernightProjectile
extends EntityThrowable {
    public int damage;
    public EntityLivingBase thrower;
    protected int bounces;

    public EntityEvernightProjectile(World par1) {
        super(par1);
    }

    public EntityEvernightProjectile(World par1, EntityLivingBase par2, int par3) {
        super(par1, par2);
        this.damage = par3;
        this.thrower = par2;
    }

    public EntityEvernightProjectile(World par1, double par2, double par4, double par6) {
        super(par1, par2, par4, par6);
        this.setVelocity(this.motionX * 0.01, this.motionY * 0.01, this.motionZ * 0.01);
    }

    protected void onImpact(MovingObjectPosition par1) {
        if (par1.entityHit != null && par1.entityHit != this.thrower) {
            EntityChicken chicken = new EntityChicken(par1.entityHit.worldObj);
            chicken.setLocationAndAngles(par1.entityHit.posX, par1.entityHit.posY, par1.entityHit.posZ, par1.entityHit.rotationYaw, 0.0f);
            chicken.onSpawnWithEgg(null);
            chicken.func_152117_i(true);
            par1.entityHit.worldObj.spawnEntityInWorld((Entity)chicken);
            if (!this.worldObj.isRemote) {
                this.setDead();
            }
            return;
        }
        if (par1.sideHit == 0 || par1.sideHit == 1) {
            this.motionY *= -1.0;
        } else if (par1.sideHit == 2 || par1.sideHit == 3) {
            this.motionZ *= -1.0;
        } else if (par1.sideHit == 4 || par1.sideHit == 5) {
            this.motionX *= -1.0;
        }
        ++this.bounces;
        if (this.bounces == 7) {
            this.setDead();
        }
    }
}

