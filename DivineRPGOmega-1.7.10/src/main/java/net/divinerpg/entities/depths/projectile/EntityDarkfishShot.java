/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.depths.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDarkfishShot
extends EntityFireball {
    public EntityDarkfishShot(World w) {
        super(w);
        this.setSize(0.2125f, 0.2125f);
    }

    public EntityDarkfishShot(World w, EntityLivingBase shooter, double i, double j, double k) {
        this(w);
        this.setLocationAndAngles(shooter.posX, shooter.posY, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.setPosition(shooter.posX, shooter.posY, shooter.posZ);
        double d = MathHelper.sqrt_double((double)(i * i + j * j + k * k));
        this.accelerationX = i / d * 0.15;
        this.accelerationY = j / d * 0.15;
        this.accelerationZ = k / d * 0.15;
        this.shootingEntity = shooter;
    }

    protected void onImpact(MovingObjectPosition pos) {
        if (pos.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            pos.entityHit.attackEntityFrom(DamageSource.causeFireballDamage((EntityFireball)this, (Entity)this.shootingEntity), 6.0f);
        }
        this.setDead();
    }
}

