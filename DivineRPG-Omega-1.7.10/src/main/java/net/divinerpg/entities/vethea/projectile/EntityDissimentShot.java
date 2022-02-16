/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDissimentShot
extends EntityThrowable {
    public EntityDissimentShot(World var1) {
        super(var1);
    }

    public EntityDissimentShot(World world, EntityLiving entity) {
        super(world, (EntityLivingBase)entity);
        this.setLocationAndAngles(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
        this.posX -= (double)(MathHelper.cos((float)(this.rotationYaw / 180.0f * (float)Math.PI)) * 0.16f);
        this.posY -= (double)0.1f;
        this.posZ -= (double)(MathHelper.sin((float)(this.rotationYaw / 180.0f * (float)Math.PI)) * 0.16f);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0f;
        float f = 0.4f;
        this.motionX = -MathHelper.sin((float)(this.rotationYaw / 180.0f * (float)Math.PI)) * MathHelper.cos((float)(this.rotationPitch / 180.0f * (float)Math.PI)) * f;
        this.motionZ = MathHelper.cos((float)(this.rotationYaw / 180.0f * (float)Math.PI)) * MathHelper.cos((float)(this.rotationPitch / 180.0f * (float)Math.PI)) * f;
        this.motionY = -MathHelper.sin((float)((this.rotationPitch + this.func_70183_g()) / 180.0f * (float)Math.PI)) * f;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.func_70182_d(), 1.0f);
    }

    public EntityDissimentShot(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (var1.entityHit != null) {
            int var2 = 9;
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), (float)var2);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

