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
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.iceika.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.DivineRPG;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFractiteShot
extends EntityFireball {
    public EntityFractiteShot(World par1World) {
        super(par1World);
        this.isImmuneToFire = true;
    }

    @SideOnly(value=Side.CLIENT)
    public EntityFractiteShot(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
    }

    public EntityFractiteShot(World par1World, EntityLiving par2EntityLiving, double par3, double par5, double par7) {
        super(par1World, (EntityLivingBase)par2EntityLiving, par3, par5, par7);
    }

    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        if (!this.worldObj.isRemote) {
            if (par1MovingObjectPosition != null && par1MovingObjectPosition.entityHit != null) {
                par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeFireballDamage((EntityFireball)this, (Entity)this.shootingEntity), 12.0f);
            }
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 3.0f, false);
            this.setDead();
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        for (int i = 0; i < 5; ++i) {
            DivineRPG.proxy.spawnParticle(this.worldObj, this.posX, this.posY, this.posZ, "wildwood", true);
        }
    }
}

