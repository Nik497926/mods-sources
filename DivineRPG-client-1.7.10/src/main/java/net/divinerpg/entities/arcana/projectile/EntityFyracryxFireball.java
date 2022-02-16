/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.entity.projectile.EntitySmallFireball
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFyracryxFireball
extends EntitySmallFireball {
    public EntityFyracryxFireball(World par1World) {
        super(par1World);
    }

    public EntityFyracryxFireball(World par1World, EntityLiving par2EntityLiving, double par3, double par5, double par7) {
        super(par1World, (EntityLivingBase)par2EntityLiving, par3, par5, par7);
    }

    @SideOnly(value=Side.CLIENT)
    public EntityFyracryxFireball(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
    }

    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        if (!this.worldObj.isRemote) {
            if (par1MovingObjectPosition.entityHit != null) {
                par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeFireballDamage((EntityFireball)this, (Entity)this.shootingEntity), 6.0f);
            }
            this.worldObj.newExplosion((Entity)null, this.posX, this.posY, this.posZ, 1.0f, true, false);
            this.setDead();
        }
    }
}

