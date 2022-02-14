/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.lelyetia;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.lelyetia.EntityGraw;
import net.divinerpg.entities.lelyetia.OrangeTrail;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGrawShot
extends EntityThrowable {
    private float damage = 40.0f;
    private static float explosionRadius = 2.5f;

    public EntityGrawShot(World par1World) {
        super(par1World);
    }

    public EntityGrawShot(World par1World, EntityGraw par3EntityPlayer) {
        super(par1World, (EntityLivingBase)par3EntityPlayer);
        this.setThrowableHeading(((Entity)this).motionX, ((Entity)this).motionY, ((Entity)this).motionZ, 3.0f, 1.0f);
    }

    public EntityGrawShot(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    protected float getGravityVelocity() {
        return 0.015f;
    }

    protected void onImpact(MovingObjectPosition movingobjectposition) {
        if (movingobjectposition.entityHit instanceof EntityLivingBase) {
            movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), this.damage);
        }
        ((Entity)this).worldObj.createExplosion((Entity)this, ((Entity)this).posX, ((Entity)this).posY, ((Entity)this).posZ, explosionRadius, false);
        this.setDead();
    }

    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        if (((Entity)this).worldObj.isRemote) {
            for (int var3 = 0; var3 < 8; ++var3) {
                OrangeTrail var4 = new OrangeTrail(((Entity)this).worldObj, ((Entity)this).posX, ((Entity)this).posY, ((Entity)this).posZ, 0.0, 0.0, 0.0, 8);
                FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)var4);
            }
        }
    }
}

