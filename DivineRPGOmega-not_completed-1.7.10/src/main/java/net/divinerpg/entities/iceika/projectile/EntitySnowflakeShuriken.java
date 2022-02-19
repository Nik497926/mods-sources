/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.iceika.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySnowflakeShuriken
extends EntityThrowable {
    public EntitySnowflakeShuriken(World var1) {
        super(var1);
    }

    public EntitySnowflakeShuriken(World var1, EntityLivingBase var3) {
        super(var1, var3);
    }

    public EntitySnowflakeShuriken(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (var1.entityHit != null) {
            int var2 = 7;
            if (var1.entityHit instanceof EntityBlaze) {
                var2 = 7;
            }
            if (var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), (float)var2)) {
                boolean bl = true;
            }
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

