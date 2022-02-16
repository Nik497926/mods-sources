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
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
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
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityVermsillion
extends EntityDivineRPGTameable {
    private float field_70926_e;
    private float field_70924_f;
    private boolean isShaking;
    private boolean field_70928_h;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;
    private int waitTick;

    public EntityVermsillion(World var1) {
        super(var1);
        this.setSize(0.6f, 0.8f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.vermsillionHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.vermsillionSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.vermsillionFollowRange);
    }

    protected void updateAITasks() {
        if (this.getAttackTarget() != null && this.waitTick <= 0) {
            this.waitTick = 50;
        } else if (this.waitTick == 1) {
            this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
            --this.waitTick;
        } else if (this.waitTick == 10) {
            this.setAIMoveSpeed(0.0f);
            --this.waitTick;
        } else if (this.waitTick > 0) {
            --this.waitTick;
            this.moveEntityWithHeading(0.0f, (float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
        }
        super.updateAITasks();
    }

    public void setAttackTarget(EntityLivingBase var1) {
        super.setAttackTarget(var1);
        if (var1 instanceof EntityPlayer) {
            this.setAngry(true);
        }
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(19, (byte) 0);
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public void writeEntityToNBT(NBTTagCompound var1) {
        super.writeEntityToNBT(var1);
        var1.setBoolean("Angry", this.isAngry());
    }

    public void readEntityFromNBT(NBTTagCompound var1) {
        super.readEntityFromNBT(var1);
        this.setAngry(var1.getBoolean("Angry"));
    }

    protected boolean canDespawn() {
        return this.isAngry();
    }

    @Override
    protected String getLivingSound() {
        return "";
    }

    @Override
    protected String getHurtSound() {
        return "";
    }

    @Override
    protected String getDeathSound() {
        return "";
    }

    protected float getSoundVolume() {
        return 0.4f;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.isShaking && !this.field_70928_h && !this.hasPath() && this.onGround) {
            this.field_70928_h = true;
            this.timeWolfIsShaking = 0.0f;
            this.prevTimeWolfIsShaking = 0.0f;
            this.worldObj.setEntityState((Entity)this, (byte)8);
        }
    }

    public void onUpdate() {
        super.onUpdate();
        this.field_70924_f = this.field_70926_e;
        this.field_70926_e = this.func_70922_bv() ? (this.field_70926_e += (1.0f - this.field_70926_e) * 0.4f) : (this.field_70926_e += (0.0f - this.field_70926_e) * 0.4f);
        if (this.func_70922_bv()) {
            this.numTicksToChaseTarget = 10;
        }
        if (this.isWet()) {
            this.isShaking = true;
            this.field_70928_h = false;
            this.timeWolfIsShaking = 0.0f;
            this.prevTimeWolfIsShaking = 0.0f;
        } else if ((this.isShaking || this.field_70928_h) && this.field_70928_h) {
            if (this.timeWolfIsShaking == 0.0f) {
                this.worldObj.playSoundAtEntity((Entity)this, "mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            }
            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05f;
            if (this.prevTimeWolfIsShaking >= 2.0f) {
                this.isShaking = false;
                this.field_70928_h = false;
                this.prevTimeWolfIsShaking = 0.0f;
                this.timeWolfIsShaking = 0.0f;
            }
            if (this.timeWolfIsShaking > 0.4f) {
                float var1 = (float)this.boundingBox.minY;
                int var2 = (int)(MathHelper.sin((float)((this.timeWolfIsShaking - 0.4f) * (float)Math.PI)) * 7.0f);
                for (int var3 = 0; var3 < var2; ++var3) {
                    float var4 = (this.rand.nextFloat() * 2.0f - 1.0f) * this.width * 0.5f;
                    float var5 = (this.rand.nextFloat() * 2.0f - 1.0f) * this.width * 0.5f;
                    this.worldObj.spawnParticle("splash", this.posX + (double)var4, (double)(var1 + 0.8f), this.posZ + (double)var5, this.motionX, this.motionY, this.motionZ);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean getWolfShaking() {
        return this.isShaking;
    }

    @SideOnly(value=Side.CLIENT)
    public float getShadingWhileShaking(float var1) {
        return 0.75f + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * var1) / 2.0f * 0.25f;
    }

    @SideOnly(value=Side.CLIENT)
    public float getShakeAngle(float var1, float var2) {
        float var3 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * var1 + var2) / 1.8f;
        if (var3 < 0.0f) {
            var3 = 0.0f;
        } else if (var3 > 1.0f) {
            var3 = 1.0f;
        }
        return MathHelper.sin((float)(var3 * (float)Math.PI)) * MathHelper.sin((float)(var3 * (float)Math.PI * 11.0f)) * 0.15f * (float)Math.PI;
    }

    @SideOnly(value=Side.CLIENT)
    public float getInterestedAngle(float var1) {
        return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * var1) * 0.15f * (float)Math.PI;
    }

    public float getEyeHeight() {
        return this.height * 0.8f;
    }

    public int getVerticalFaceSpeed() {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    public boolean attackEntityFrom(DamageSource var1, int var2) {
        Entity var3 = var1.getEntity();
        this.aiSit.setSitting(false);
        if (var3 != null && !(var3 instanceof EntityPlayer) && !(var3 instanceof EntityArrow)) {
            var2 = (var2 + 1) / 2;
        }
        return super.attackEntityFrom(var1, (float)var2);
    }

    @Override
    public boolean attackEntityAsMob(Entity var1) {
        int var2 = (int)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        var1.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * 3.0f * 0.5f), 0.1, (double)(MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * 3.0f * 0.5f));
        this.motionX *= 0.6;
        this.motionZ *= 0.6;
        return var1.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)var2);
    }

    public boolean interact(EntityPlayer var1) {
        ItemStack var2 = var1.inventory.getCurrentItem();
        if (this.isTamed()) {
            ItemFood var3;
            if (var2 != null && var2.getItem() instanceof ItemFood && (var3 = (ItemFood)var2.getItem()).isWolfsFavoriteMeat() && (double)this.dataWatcher.getWatchableObjectInt(18) < this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue()) {
                if (!var1.capabilities.isCreativeMode) {
                    --var2.stackSize;
                }
                this.heal(var3.func_150905_g(var2));
                if (var2.stackSize <= 0) {
                    var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                }
                return true;
            }
            if (var1.getDisplayName().equalsIgnoreCase(this.func_152113_b()) && !this.worldObj.isRemote && !this.isWheat(var2)) {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.setPathToEntity(null);
            }
        }
        return super.interact(var1);
    }

    @SideOnly(value=Side.CLIENT)
    public void handleHealthUpdate(byte var1) {
        if (var1 == 8) {
            this.field_70928_h = true;
            this.timeWolfIsShaking = 0.0f;
            this.prevTimeWolfIsShaking = 0.0f;
        } else {
            super.handleHealthUpdate(var1);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float getTailRotation() {
        return this.isAngry() ? 1.5393804f : (this.isTamed() ? (0.55f - (float)(20 - this.dataWatcher.getWatchableObjectInt(18)) * 0.02f) * (float)Math.PI : 0.62831855f);
    }

    public boolean isWheat(ItemStack var1) {
        return var1 == null ? false : (!(var1.getItem() instanceof ItemFood) ? false : ((ItemFood)var1.getItem()).isWolfsFavoriteMeat());
    }

    public int getMaxSpawnedInChunk() {
        return 8;
    }

    @Override
    public boolean isAngry() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    public void setAngry(boolean var1) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);
        if (var1) {
            this.dataWatcher.updateObject(16, (Object)((byte)(var2 | 2)));
        } else {
            this.dataWatcher.updateObject(16, (Object)((byte)(var2 & 0xFFFFFFFD)));
        }
    }

    public EntityAnimal createChild(EntityAgeable var1) {
        EntityVermsillion var2 = new EntityVermsillion(this.worldObj);
        var2.func_152115_b(this.func_152113_b());
        var2.setTamed(true);
        return var2;
    }

    public void func_70918_i(boolean var1) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(19);
        if (var1) {
            this.dataWatcher.updateObject(19, (Object)1);
        } else {
            this.dataWatcher.updateObject(19, (Object)0);
        }
    }

    public boolean canMateWith(EntityAnimal var1) {
        if (var1 == this) {
            return false;
        }
        if (!this.isTamed()) {
            return false;
        }
        if (!(var1 instanceof EntityVermsillion)) {
            return false;
        }
        EntityVermsillion var2 = (EntityVermsillion)var1;
        return !var2.isTamed() ? false : (var2.isSitting() ? false : this.isInLove() && var2.isInLove());
    }

    public boolean func_70922_bv() {
        return this.dataWatcher.getWatchableObjectByte(19) == 1;
    }

    @Override
    public boolean getCanSpawnHere() {
        int var3;
        int var2;
        int var1 = MathHelper.floor_double((double)this.posX);
        return this.getBlockPathWeight(var1, var2 = MathHelper.floor_double((double)this.boundingBox.minY), var3 = MathHelper.floor_double((double)this.posZ)) >= 0.0f && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    @Override
    public String mobName() {
        return "Vermsillion";
    }
}

