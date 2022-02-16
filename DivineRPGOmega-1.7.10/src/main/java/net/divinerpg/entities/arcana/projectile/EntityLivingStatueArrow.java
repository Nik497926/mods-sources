/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana.projectile;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLivingStatueArrow
extends EntityArrow {
    public EntityLivingStatueArrow(World world) {
        super(world);
        this.setSize(0.5f, 0.5f);
        this.setRealDamage(26.0);
    }

    public EntityLivingStatueArrow(World world, double x, double y, double z) {
        super(world);
        this.setRealDamage(26.0);
        this.setSize(0.5f, 0.5f);
        this.setPosition(x, y, z);
        this.yOffset = 0.0f;
    }

    public EntityLivingStatueArrow(World world, EntityLiving var2, EntityLiving var3, float var4, float var5) {
        super(world);
        this.setRealDamage(26.0);
        this.shootingEntity = var2;
        this.canBePickedUp = 0;
        this.posY = var2.posY + (double)var2.getEyeHeight() - (double)0.1f;
        double var6 = var3.posX - var2.posX;
        double var8 = var3.posY + (double)var3.getEyeHeight() - (double)0.7f - this.posY;
        double var10 = var3.posZ - var2.posZ;
        double var12 = MathHelper.sqrt_double((double)(var6 * var6 + var10 * var10));
        if (var12 >= 1.0E-7) {
            float var14 = (float)(Math.atan2(var10, var6) * 180.0 / Math.PI) - 90.0f;
            float var15 = (float)(-Math.atan2(var8, var12) * 180.0 / Math.PI);
            double var16 = var6 / var12;
            double var18 = var10 / var12;
            this.setLocationAndAngles(var2.posX + var16, this.posY, var2.posZ + var18, var14, var15);
            this.yOffset = 0.0f;
            float var20 = (float)var12 * 0.2f;
            this.setArrowHeading(var6, var8 + (double)var20, var10, var4, var5);
        }
    }

    public EntityLivingStatueArrow(World world, EntityLiving var2, float var3) {
        super(world);
        this.setRealDamage(6.0);
        this.shootingEntity = var2;
        this.canBePickedUp = 0;
        this.setSize(0.5f, 0.5f);
        this.setLocationAndAngles(var2.posX, var2.posY + (double)var2.getEyeHeight(), var2.posZ, var2.rotationYaw, var2.rotationPitch);
        this.posX -= (double)(MathHelper.cos((float)(this.rotationYaw / 180.0f * (float)Math.PI)) * 0.16f);
        this.posY -= (double)0.1f;
        this.posZ -= (double)(MathHelper.sin((float)(this.rotationYaw / 180.0f * (float)Math.PI)) * 0.16f);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0f;
        this.motionX = -MathHelper.sin((float)(this.rotationYaw / 180.0f * (float)Math.PI)) * MathHelper.cos((float)(this.rotationPitch / 180.0f * (float)Math.PI));
        this.motionZ = MathHelper.cos((float)(this.rotationYaw / 180.0f * (float)Math.PI)) * MathHelper.cos((float)(this.rotationPitch / 180.0f * (float)Math.PI));
        this.motionY = -MathHelper.sin((float)(this.rotationPitch / 180.0f * (float)Math.PI));
        this.setArrowHeading(this.motionX, this.motionY, this.motionZ, var3 * 1.5f, 1.0f);
    }

    public void setArrowHeading(double var1, double var3, double var5, float var7, float var8) {
        this.setRealDamage(26.0);
        float var9 = MathHelper.sqrt_double((double)(var1 * var1 + var3 * var3 + var5 * var5));
        var1 /= (double)var9;
        var3 /= (double)var9;
        var5 /= (double)var9;
        var1 += this.rand.nextGaussian() * (double)0.0075f * (double)var8;
        var3 += this.rand.nextGaussian() * (double)0.0075f * (double)var8;
        var5 += this.rand.nextGaussian() * (double)0.0075f * (double)var8;
        this.motionX = var1 *= (double)var7;
        this.motionY = var3 *= (double)var7;
        this.motionZ = var5 *= (double)var7;
        float var10 = MathHelper.sqrt_double((double)(var1 * var1 + var5 * var5));
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(var1, var5) * 180.0 / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(var3, var10) * 180.0 / Math.PI);
    }

    protected void setRealDamage(double dam) {
        this.setDamage(dam / 22.53846153846154);
    }
}

