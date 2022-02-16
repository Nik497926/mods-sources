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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana.projectile;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.vanilla.projectile.EntitySparklerFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySparkler
extends EntityThrowable {
    public EntitySparkler(World var1) {
        super(var1);
    }

    public EntitySparkler(World var1, EntityPlayer var2) {
        super(var1, (EntityLivingBase)var2);
    }

    public EntitySparkler(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        for (int var3 = 0; var3 < 8; ++var3) {
            EntitySparklerFX var20 = new EntitySparklerFX(this.worldObj, this.posX, this.posY, this.posZ, 0.25 * this.rand.nextGaussian(), 0.25 * this.rand.nextGaussian(), 0.25 * this.rand.nextGaussian());
            FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)var20);
        }
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (var1.entityHit != null) {
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), 20.0f);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

