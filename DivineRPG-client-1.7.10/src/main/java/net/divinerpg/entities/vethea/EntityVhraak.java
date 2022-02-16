/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityVhraak
extends VetheaMob {
    private int waitTick;
    private int lifeTick;
    private int spawnTick;

    public EntityVhraak(World var1) {
        super(var1);
        this.addAttackingAI();
        this.lifeTick = -1;
    }

    public EntityVhraak(World var1, int life) {
        this(var1);
        this.lifeTick = life;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.varaakHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.varaakDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.varaakSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.varaakFollowRange);
    }

    @Override
    public int getSpawnLayer() {
        return 4;
    }

    protected void updateAITasks() {
        if (this.getAttackTarget() != null && (double)this.getDistanceToEntity((Entity)this.getAttackTarget()) < 0.5) {
            this.waitTick = 30;
            this.hasAttacked = false;
        } else if (this.waitTick == 0) {
            if (this.spawnTick == 0 && this.lifeTick == -1) {
                EntityVhraak var2 = new EntityVhraak(this.worldObj, 30);
                var2.setLocationAndAngles(this.posX + 1.0, this.posY, this.posZ + 1.0, this.rotationYaw, this.rotationPitch);
                this.worldObj.spawnEntityInWorld((Entity)var2);
                var2 = new EntityVhraak(this.worldObj, 30);
                var2.setLocationAndAngles(this.posX - 1.0, this.posY, this.posZ + 1.0, this.rotationYaw, this.rotationPitch);
                this.worldObj.spawnEntityInWorld((Entity)var2);
                var2 = new EntityVhraak(this.worldObj, 30);
                var2.setLocationAndAngles(this.posX + 1.0, this.posY, this.posZ - 1.0, this.rotationYaw, this.rotationPitch);
                this.worldObj.spawnEntityInWorld((Entity)var2);
                var2 = new EntityVhraak(this.worldObj, 30);
                var2.setLocationAndAngles(this.posX - 1.0, this.posY, this.posZ - 1.0, this.rotationYaw, this.rotationPitch);
                this.worldObj.spawnEntityInWorld((Entity)var2);
                this.spawnTick = 50;
            } else if (this.spawnTick > 0) {
                --this.spawnTick;
            }
            super.updateAITasks();
        } else {
            --this.waitTick;
        }
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.lifeTick > 0) {
            --this.lifeTick;
        } else if (this.lifeTick == 0 && !this.worldObj.isRemote) {
            this.setDead();
        }
    }

    @Override
    protected String getLivingSound() {
        return Sounds.varaak.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.varaakHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        if (this.lifeTick == -1) {
            this.dropItem(VetheaItems.shinyPearls, 1);
        }
    }

    @Override
    public String mobName() {
        return "Vhraak";
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("LifeTick", this.lifeTick);
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.lifeTick = tag.getInteger("LifeTick");
    }
}

