/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.projectile.EntityZoragonBomb;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityZoragon
extends EntityDivineRPGMob {
    private static final double spawnLayer = 4.0;
    private ChunkCoordinates currentFlightTarget;
    private int flyTimer = 0;
    private int special = 120;

    public EntityZoragon(World var1) {
        super(var1);
        this.setSize(6.0f, 6.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.zoragonHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.zoragonSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.zoragonFollowRange);
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 192.0 && this.posY > 144.0 && super.getCanSpawnHere();
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95f;
    }

    protected String getLivingSound() {
        return Sounds.zoragon.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.zoragonHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    public void onUpdate() {
        super.onUpdate();
        this.motionY *= (double)0.6f;
    }

    public void fall(float f) {
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.entityToAttack != null) {
            int targetX = (int)this.entityToAttack.posX;
            int targetY = (int)this.entityToAttack.posY;
            int targetZ = (int)this.entityToAttack.posZ;
            this.currentFlightTarget = new ChunkCoordinates(targetX, targetY + 15, targetZ);
            if (this.entityToAttack instanceof EntityPlayer && ((EntityPlayer)this.entityToAttack).capabilities.isCreativeMode) {
                this.entityToAttack = null;
            }
        } else if (this.flyTimer != 0) {
            this.flyTimer = 360;
            this.currentFlightTarget = new ChunkCoordinates((int)(this.posX + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f)), (int)(this.posY + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f)), (int)(this.posZ + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f)));
        }
        if (this.currentFlightTarget != null) {
            double distX = (double)this.currentFlightTarget.posX - this.posX;
            double distY = (double)this.currentFlightTarget.posY - this.posY;
            double distZ = (double)this.currentFlightTarget.posZ - this.posZ;
            if (Math.signum(distX) != 0.0 || Math.signum(distY) != 0.0 || Math.signum(distZ) != 0.0) {
                this.motionX += (Math.signum(distX) * 0.15 - this.motionX) * (double)0.1f;
                this.motionY += (Math.signum(distY) * 1.699999988079071 - this.motionY) * (double)0.1f;
                this.motionZ += (Math.signum(distZ) * 0.15 - this.motionZ) * (double)0.1f;
                float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0f;
                float var8 = MathHelper.wrapAngleTo180_float((float)(var7 - this.rotationYaw));
                this.moveForward = 0.5f;
                this.rotationYaw += var8;
            }
            if (Math.abs(distX) < 3.0 && Math.abs(distY) < 3.0 && Math.abs(distZ) < 3.0) {
                if (this.special == 0) {
                    this.special = 120;
                    EntityZoragonBomb bomb = new EntityZoragonBomb(this.worldObj, this.posX, this.posY - 1.0, this.posZ);
                    bomb.motionY = -0.1f;
                    this.worldObj.spawnEntityInWorld((Entity)bomb);
                } else {
                    --this.special;
                }
            }
            --this.flyTimer;
        }
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.shinyPearls, 1);
    }

    @Override
    public String mobName() {
        return "Zoragon";
    }
}

