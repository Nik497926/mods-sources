/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana.projectile;

import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityStar
extends EntityThrowable {
    private Color colour;

    public EntityStar(World par1World) {
        super(par1World);
        this.motionX = this.rand.nextGaussian() * 0.05;
        this.motionZ = this.rand.nextGaussian() * 0.05;
        this.motionY = -0.5;
        this.colour = new Color(this.rand.nextInt(255), this.rand.nextInt(255), this.rand.nextInt(255));
    }

    public EntityStar(World par1World, EntityLiving par2EntityLiving) {
        super(par1World, (EntityLivingBase)par2EntityLiving);
        this.motionX = this.rand.nextGaussian() * 0.05;
        this.motionZ = this.rand.nextGaussian() * 0.05;
        this.motionY = -0.5;
        this.colour = new Color(this.rand.nextInt(255), this.rand.nextInt(255), this.rand.nextInt(255));
    }

    public EntityStar(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.motionX = this.rand.nextGaussian() * 0.05;
        this.motionZ = this.rand.nextGaussian() * 0.05;
        this.motionY = -0.5;
        this.colour = new Color(this.rand.nextInt(255), this.rand.nextInt(255), this.rand.nextInt(255));
    }

    public Color getColour() {
        return this.colour;
    }

    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        if (par1MovingObjectPosition.entityHit != null) {
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), 20.0f);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

