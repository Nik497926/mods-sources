/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla.projectile;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCorruptedBullet
extends EntityThrowable {
    public static float damage = 10.0f;
    public List<Entity> toExcludeList = new ArrayList<Entity>();

    public EntityCorruptedBullet(World var1) {
        super(var1);
    }

    public EntityCorruptedBullet(World var1, EntityLivingBase var2) {
        super(var1, var2);
    }

    public EntityCorruptedBullet(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (var1.entityHit != null) {
            if (!this.toExcludeList.contains(var1.entityHit)) {
                var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), damage);
            }
            List surrounding = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(1.0, 1.0, 1.0));
            for (Entity e : surrounding) {
                if (!(e instanceof EntityCorruptedBullet)) continue;
                ((EntityCorruptedBullet)e).toExcludeList.add(var1.entityHit);
            }
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

