/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntityPortalFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla.projectile;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityScorcherShot
extends EntityFireball {
    public EntityScorcherShot(World w) {
        super(w);
        this.setSize(0.3125f, 0.3125f);
    }

    public EntityScorcherShot(World w, EntityLivingBase shooter, double i, double j, double k) {
        this(w);
        this.setLocationAndAngles(shooter.posX, shooter.posY, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.setPosition(shooter.posX, shooter.posY, shooter.posZ);
        double d = MathHelper.sqrt_double((double)(i * i + j * j + k * k));
        this.accelerationX = i / d * 0.1;
        this.accelerationY = j / d * 0.1;
        this.accelerationZ = k / d * 0.1;
        this.shootingEntity = shooter;
    }

    protected void onImpact(MovingObjectPosition pos) {
        if (!this.worldObj.isRemote) {
            if (pos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int var2 = pos.blockX;
                int var3 = pos.blockY;
                int var4 = pos.blockZ;
                switch (pos.sideHit) {
                    case 0: {
                        --var3;
                        break;
                    }
                    case 1: {
                        ++var3;
                        break;
                    }
                    case 2: {
                        --var4;
                        break;
                    }
                    case 3: {
                        ++var4;
                        break;
                    }
                    case 4: {
                        --var2;
                        break;
                    }
                    case 5: {
                        ++var2;
                    }
                }
                if (this.worldObj.isAirBlock(var2, var3, var4)) {
                    this.worldObj.setBlock(var2, var3, var4, (Block)Blocks.fire);
                }
            } else if (pos.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                pos.entityHit.attackEntityFrom(DamageSource.causeFireballDamage((EntityFireball)this, (Entity)this.shootingEntity), 4.0f);
                if (this.rand.nextInt(3) == 0) {
                    pos.entityHit.setFire(5);
                }
            }
            this.setDead();
        }
    }

    public void onEntityUpdate() {
        super.onEntityUpdate();
        if (!this.worldObj.isRemote) {
            this.posX += (this.rand.nextDouble() - this.rand.nextDouble()) / 3.0;
            this.posY += (this.rand.nextDouble() - this.rand.nextDouble()) / 3.0;
            this.posZ += (this.rand.nextDouble() - this.rand.nextDouble()) / 3.0;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        if (this.worldObj.isRemote) {
            for (int i = 0; i < 3; ++i) {
                EntityPortalFX var20 = new EntityPortalFX(this.worldObj, this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0, this.posY + 0.5 + (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0, this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0, 0.0, 0.0, 0.0);
                FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)var20);
            }
        }
    }
}

