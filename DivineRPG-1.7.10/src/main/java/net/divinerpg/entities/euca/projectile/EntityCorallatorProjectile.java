/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityWitherSkull
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.euca.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCorallatorProjectile
extends EntityWitherSkull {
    public EntityCorallatorProjectile(World w) {
        super(w);
        this.setSize(0.3125f, 0.3125f);
    }

    public EntityCorallatorProjectile(World w, EntityLivingBase entity, double x, double y, double z) {
        super(w, entity, x, y, z);
        this.setSize(0.3125f, 0.3125f);
    }

    protected void onImpact(MovingObjectPosition obj) {
        if (!this.worldObj.isRemote) {
            if (obj.entityHit != null) {
                if (this.shootingEntity != null) {
                    if (obj.entityHit.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.shootingEntity), 20.0f) && !obj.entityHit.isEntityAlive()) {
                        this.shootingEntity.heal(20.0f);
                    }
                } else {
                    obj.entityHit.attackEntityFrom(DamageSource.magic, 20.0f);
                }
                if (obj.entityHit instanceof EntityLivingBase) {
                    int var2 = 0;
                    if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL) {
                        var2 = 10;
                    } else if (this.worldObj.difficultySetting == EnumDifficulty.HARD) {
                        var2 = 40;
                    }
                    if (var2 > 0) {
                        ((EntityLivingBase)obj.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * var2, 1));
                    }
                }
            }
            this.worldObj.newExplosion((Entity)this, this.posX, this.posY, this.posZ, 1.0f, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            this.setDead();
        }
    }
}

