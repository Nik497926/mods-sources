/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
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
        boolean var4;
        int var2 = 9;
        if (this.isPotionActive(Potion.damageBoost)) {
            var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }
        if (this.isPotionActive(Potion.weakness)) {
            var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }
        int var3 = 0;
        if (par1Entity instanceof EntityLiving) {
            var2 = (int)((float)var2 + EnchantmentHelper.getEnchantmentModifierLiving((EntityLivingBase)this, (EntityLivingBase)((EntityLiving)par1Entity)));
            var3 += EnchantmentHelper.getKnockbackModifier((EntityLivingBase)this, (EntityLivingBase)((EntityLiving)par1Entity));
        }
        if (var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)var2)) {
            int var5;
            if (var3 > 0) {
                par1Entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f), 0.1, (double)(MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f));
                this.motionX *= 0.6;
                this.motionZ *= 0.6;
            }
            if ((var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this)) > 0) {
                par1Entity.setFire(var5 * 4);
            }
        }
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
        if (par1 == 7) {
            this.playTameEffect(true);
        } else if (par1 == 6) {
            this.playTameEffect(false);
        } else {
            super.handleHealthUpdate(par1);
        }
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
        if (this.getIsBatHanging()) {
            this.motionZ = 0.0;
            this.motionY = 0.0;
            this.motionX = 0.0;
            this.posY = (double)MathHelper.floor_double((double)this.posY) + 1.0 - (double)this.height;
        } else {
            this.motionY *= (double)0.6f;
        }
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.getAttackTarget() != null) {
            int var1 = (int)this.getAttackTarget().posX;
            int var2 = (int)this.getAttackTarget().posY;
            int var3 = (int)this.getAttackTarget().posZ;
            this.currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
        } else if (this.getOwner() != null) {
            this.currentFlightTarget = this.worldObj.getPlayerEntityByName(this.func_152113_b()).getPlayerCoordinates();
        } else {
            if (this.rand.nextInt(200) == 0) {
                this.rotationYawHead = this.rand.nextInt(360);
            }
            if (this.worldObj.getClosestPlayerToEntity((Entity)this, 4.0) != null) {
                this.setIsBatHanging(false);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            }
        }
        if (this.currentFlightTarget != null) {
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

