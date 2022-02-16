/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.arcana.EntityParatiku;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWraith
extends EntityParatiku {
    private int age = 120;
    private ChunkCoordinates currentFlightTarget;

    public EntityWraith(World par1World, EntityPlayer owner) {
        super(par1World);
        this.setTamed(true);
        this.func_152115_b(owner.getDisplayName());
    }

    public EntityWraith(World par1World) {
        super(par1World);
    }

    @Override
    protected float getSoundVolume() {
        return 0.1f;
    }

    @Override
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95f;
    }

    @Override
    protected String getLivingSound() {
        return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : Sounds.wraith.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.wraithHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return Sounds.wraithHurt.getPrefixedName();
    }

    public boolean canBePushed() {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.getIsBatHanging()) {
            this.motionZ = 0.0;
            this.motionY = 0.0;
            this.motionX = 0.0;
            this.posY = (double)MathHelper.floor_double((double)this.posY) + 1.0 - (double)this.height;
        } else {
            this.motionY *= (double)0.6f;
        }
        --this.age;
        if (this.age == 0) {
            this.setDead();
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        if (this.getAttackTarget() != null) {
            int var1 = (int)this.getAttackTarget().posX;
            int var2 = (int)this.getAttackTarget().posY;
            int var3 = (int)this.getAttackTarget().posZ;
            this.currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
        } else if (this.getOwner() != null) {
            this.currentFlightTarget = this.worldObj.getPlayerEntityByName(this.func_152113_b()).getPlayerCoordinates();
        }
        if (this.getIsBatHanging()) {
            if (!this.worldObj.getBlock(MathHelper.floor_double((double)this.posX), (int)this.posY + 1, MathHelper.floor_double((double)this.posZ)).isNormalCube()) {
                this.setIsBatHanging(false);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            } else {
                if (this.rand.nextInt(200) == 0) {
                    this.rotationYawHead = this.rand.nextInt(360);
                }
                if (this.worldObj.getClosestPlayerToEntity((Entity)this, 4.0) != null) {
                    this.setIsBatHanging(false);
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                }
            }
        } else if (this.currentFlightTarget != null) {
            double var1 = (double)this.currentFlightTarget.posX - this.posX;
            double var3 = (double)this.currentFlightTarget.posY - this.posY;
            double var5 = (double)this.currentFlightTarget.posZ - this.posZ;
            if (Math.signum(var1) != 0.0 || Math.signum(var3) != 0.0 || Math.signum(var5) != 0.0) {
                this.motionX += (Math.signum(var1) * 0.5 - this.motionX) * (double)0.1f;
                this.motionY += (Math.signum(var3) * 1.699999988079071 - this.motionY) * (double)0.1f;
                this.motionZ += (Math.signum(var5) * 0.5 - this.motionZ) * (double)0.1f;
                float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0f;
                float var8 = MathHelper.wrapAngleTo180_float((float)(var7 - this.rotationYaw));
                this.moveForward = 0.5f;
                this.rotationYaw += var8;
            }
        }
    }
}

