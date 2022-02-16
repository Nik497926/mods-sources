/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityParatiku
extends EntityDivineRPGTameable {
    private ChunkCoordinates currentFlightTarget;

    public EntityParatiku(World par1World) {
        super(par1World);
        this.setSize(0.9f, 0.9f);
        this.setIsBatHanging(true);
        this.addBasicAI();
    }

    public EntityParatiku(World w, EntityPlayer p) {
        this(w);
        this.setTamed(true);
        this.func_152115_b(p.getDisplayName());
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.paratikuHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.paratikuSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.paratikuFollowRange);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean var4 = false;
        return var4;
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (this.func_152113_b() == null) {
            par1NBTTagCompound.setString("Owner", "");
        } else {
            par1NBTTagCompound.setString("Owner", this.func_152113_b());
        }
        par1NBTTagCompound.setBoolean("Sitting", this.isSitting());
        par1NBTTagCompound.setByte("BatFlags", this.dataWatcher.getWatchableObjectByte(16));
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        String var2 = par1NBTTagCompound.getString("Owner");
        if (var2.length() > 0) {
            this.func_152115_b(var2);
            this.setTamed(true);
        }
        this.dataWatcher.updateObject(16, (Object)par1NBTTagCompound.getByte("BatFlags"));
    }

    protected void playTameEffect(boolean par1) {
        String var2 = "heart";
        if (!par1) {
            var2 = "smoke";
        }
        for (int var3 = 0; var3 < 7; ++var3) {
            double var4 = this.rand.nextGaussian() * 0.02;
            double var6 = this.rand.nextGaussian() * 0.02;
            double var8 = this.rand.nextGaussian() * 0.02;
            this.worldObj.spawnParticle(var2, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0f) - (double)this.width, this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0f) - (double)this.width, var4, var6, var8);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void handleHealthUpdate(byte par1) {
    }

    public EntityLivingBase getOwner() {
        return this.worldObj.getPlayerEntityByName(this.func_152113_b());
    }

    protected float getSoundVolume() {
        return 0.1f;
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95f;
    }

    @Override
    protected String getLivingSound() {
        return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : "mob.bat.idle";
    }

    @Override
    protected String getHurtSound() {
        return "mob.bat.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "mob.bat.death";
    }

    protected void collideWithEntity(Entity par1Entity) {
    }

    public boolean getIsBatHanging() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setIsBatHanging(boolean par1) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);
        if (par1) {
            this.dataWatcher.updateObject(16, (Object)((byte)(var2 | 1)));
        } else {
            this.dataWatcher.updateObject(16, (Object)((byte)(var2 & 0xFFFFFFFE)));
        }
    }

    public void onUpdate() {
        super.onUpdate();
    }

    protected void updateAITasks() {
        super.updateAITasks();
    }

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        if (!this.worldObj.isRemote && this.getIsBatHanging()) {
            this.setIsBatHanging(false);
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public String mobName() {
        return "Paratiku";
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }
}

