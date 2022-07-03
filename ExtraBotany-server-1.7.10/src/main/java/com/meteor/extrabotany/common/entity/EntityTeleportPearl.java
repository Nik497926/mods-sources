/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityTeleportPearl
extends EntityThrowable {
    public EntityTeleportPearl(World world) {
        super(world);
    }

    public EntityTeleportPearl(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    @SideOnly(value=Side.CLIENT)
    public EntityTeleportPearl(World world, double posX, double posY, double posZ) {
        super(world, posX, posY, posZ);
    }

    protected float getGravityVelocity() {
        return 0.0015f;
    }

    protected void onImpact(MovingObjectPosition object) {
        if (object.entityHit != null) {
            // empty if block
        }
        for (int entityplayermp = 0; entityplayermp < 32; ++entityplayermp) {
            this.worldObj.spawnParticle("portal", this.posX, this.posY + this.rand.nextDouble() * 2.0, this.posZ, this.rand.nextGaussian(), 0.0, this.rand.nextGaussian());
        }
        if (!this.worldObj.isRemote) {
            if (this.getThrower() != null && this.getThrower() instanceof EntityPlayerMP) {
                EnderTeleportEvent event;
                EntityPlayerMP var4 = (EntityPlayerMP)this.getThrower();
                if (var4.playerNetServerHandler.func_147362_b().isChannelOpen() && var4.worldObj == this.worldObj && !MinecraftForge.EVENT_BUS.post((Event)(event = new EnderTeleportEvent((EntityLivingBase)var4, this.posX, this.posY, this.posZ, 5.0f)))) {
                    if (this.getThrower().isRiding()) {
                        this.getThrower().mountEntity((Entity)null);
                    }
                    this.getThrower().setPositionAndUpdate(event.targetX, event.targetY, event.targetZ);
                }
            }
            this.setDead();
        }
    }
}

