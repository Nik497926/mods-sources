/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.iceika.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCarol
extends EntityThrowable {
    Random r = new Random();
    int color = this.r.nextInt(25);

    public EntityCarol(World var1) {
        super(var1);
    }

    public EntityCarol(World var1, EntityLivingBase var2) {
        super(var1, var2);
    }

    public EntityCarol(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        this.color = this.color >= 24 ? 0 : ++this.color;
        this.worldObj.spawnParticle("note", this.posX, this.posY, this.posZ, (double)this.color / 24.0, 0.0, 0.0);
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (var1.entityHit != null) {
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), 16.0f);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

