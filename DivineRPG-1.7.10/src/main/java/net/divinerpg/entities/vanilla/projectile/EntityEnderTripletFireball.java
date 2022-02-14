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
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla.projectile;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.vanilla.projectile.EntityTripletFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnderTripletFireball
extends EntityFireball {
    public EntityEnderTripletFireball(World world, EntityLivingBase entity, double i, double j, double k) {
        super(world, entity, i, j, k);
    }

    public EntityEnderTripletFireball(World world) {
        super(world);
    }

    protected void onImpact(MovingObjectPosition mop) {
        if (!this.worldObj.isRemote) {
            if (mop != null && mop.entityHit != null && mop.entityHit instanceof EntityLivingBase) {
                mop.entityHit.attackEntityFrom(DamageSource.causeFireballDamage((EntityFireball)this, (Entity)this.shootingEntity), 10.0f);
            }
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 3.0f, false);
            this.setDead();
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        EntityTripletFX fx = new EntityTripletFX(this.worldObj, this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) / 6.0, this.posY + 0.5 + (this.rand.nextDouble() - this.rand.nextDouble()) / 6.0, this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) / 6.0, 0.0, 0.0, 0.0);
        FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)fx);
    }
}

