/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLorgaFlight
extends EntityDivineRPGMob {
    private ChunkCoordinates currentFlightTarget;
    private int flyTimer;
    private int spawnTick;
    public boolean canSpawnMinions;

    public EntityLorgaFlight(World w) {
        this(w, true);
    }

    public EntityLorgaFlight(World var1, boolean canSpawnMinions) {
        super(var1);
        this.canSpawnMinions = canSpawnMinions;
        this.flyTimer = 0;
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.lorgaFlightHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.lorgaFlightSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.lorgaFlightFollowRange);
    }

    public void fall(float f) {
    }

    protected String getLivingSound() {
        return Sounds.lorgaFlight.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.lorgaFlightHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.spawnTick == 0 && this.canSpawnMinions && !this.worldObj.isRemote) {
            this.spawnTick = 200;
            EntityLorgaFlight var2 = new EntityLorgaFlight(this.worldObj, false);
            var2.setLocationAndAngles(this.posX + 1.0, this.posY, this.posZ + 1.0, this.rotationYaw, this.rotationPitch);
            this.worldObj.spawnEntityInWorld((Entity)var2);
        } else if (this.spawnTick > 0) {
            --this.spawnTick;
        }
    }

    public void onUpdate() {
        super.onUpdate();
        this.motionY *= (double)0.6f;
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.entityToAttack != null) {
            int var1 = (int)this.entityToAttack.posX;
            int var2 = (int)this.entityToAttack.posY;
            int var3 = (int)this.entityToAttack.posZ;
            this.currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
        } else if (this.flyTimer != 0) {
            this.flyTimer = 120;
            this.currentFlightTarget = new ChunkCoordinates((int)(this.posX + (double)this.rand.nextInt(16)) - 8, (int)(this.posY + (double)this.rand.nextInt(32)) - 16, (int)(this.posZ + (double)this.rand.nextInt(16)) - 8);
        }
        if (this.currentFlightTarget != null) {
            double var1 = (double)this.currentFlightTarget.posX - this.posX;
            double var3 = (double)this.currentFlightTarget.posY - this.posY;
            double var5 = (double)this.currentFlightTarget.posZ - this.posZ;
            if (Math.signum(var1) != 0.0 || Math.signum(var3) != 0.0 || Math.signum(var5) != 0.0) {
                this.motionX += (Math.signum(var1) * 0.15 - this.motionX) * (double)0.1f;
                this.motionY += (Math.signum(var3) * 1.699999988079071 - this.motionY) * (double)0.1f;
                this.motionZ += (Math.signum(var5) * 0.15 - this.motionZ) * (double)0.1f;
                float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0f;
                float var8 = MathHelper.wrapAngleTo180_float((float)(var7 - this.rotationYaw));
                this.moveForward = 0.5f;
                this.rotationYaw += var8;
            }
            --this.flyTimer;
        }
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setBoolean("CanSpawnMinions", this.canSpawnMinions);
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.canSpawnMinions = tag.getBoolean("CanSpawnMinions");
    }

    protected void dropFewItems(boolean par1, int par2) {
    }

    @Override
    public String mobName() {
        return "Lorgaflight";
    }
}

