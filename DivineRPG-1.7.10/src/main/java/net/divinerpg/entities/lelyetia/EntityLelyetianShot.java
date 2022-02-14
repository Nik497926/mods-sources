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
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.lelyetia;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.lelyetia.OrangeTrail;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityLelyetianShot
extends EntityThrowable {
    private float damage;
    private int clr;
    private int age = 0;

    public EntityLelyetianShot(World par1World) {
        super(par1World);
    }

    public EntityLelyetianShot(World par1World, EntityMob entityGoblin, float dmg, int color) {
        super(par1World, (EntityLivingBase)entityGoblin);
        this.clr = color;
        this.damage = dmg;
    }

    public EntityLelyetianShot(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    protected float getGravityVelocity() {
        return 0.075f;
    }

    protected void onImpact(MovingObjectPosition movingobjectposition) {
        if (movingobjectposition.entityHit instanceof EntityLivingBase) {
            movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), this.damage);
        }
        if (!((Entity)this).worldObj.isRemote) {
            this.setDead();
        }
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.age == 40) {
            if (!((Entity)this).worldObj.isRemote) {
                this.setDead();
            }
        } else {
            ++this.age;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void onEntityUpdate() {
        super.onEntityUpdate();
        for (int var3 = 0; var3 < 8; ++var3) {
            OrangeTrail var4 = new OrangeTrail(((Entity)this).worldObj, ((Entity)this).posX, ((Entity)this).posY, ((Entity)this).posZ, 0.0, 0.0, 0.0, 5);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)var4);
        }
    }
}

