/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMoonWolf
extends EntityDivineRPGTameable {
    private float headRotationX;
    private float headRotationY;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;
    private boolean isShaking;
    private boolean shakingAndDry;

    public EntityMoonWolf(World world) {
        super(world);
        this.setSize(0.6f, 0.8f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.3f);
        if (!this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.moonWolfHealth);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        }
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.moonWolfFollowRange);
    }

    public void setTamed(boolean par1) {
        super.setTamed(par1);
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    protected void updateAITick() {
        this.dataWatcher.updateObject(18, (Object)Float.valueOf(this.getHealth()));
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)new Float(this.getHealth()));
        this.dataWatcher.addObject(19, (byte) 0);
    }

    protected void func_145780_a(int x, int y, int z, Block steppedOn) {
        this.playSound("mob.wolf.step", 0.15f, 1.0f);
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Angry", this.isAngry());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setAngry(compound.getBoolean("Angry"));
    }

    @Override
    protected String getLivingSound() {
        return this.isAngry() ? "mob.wolf.growl" : (this.rand.nextInt(3) == 0 ? (this.isTamed() && this.dataWatcher.getWatchableObjectFloat(18) < 10.0f ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    @Override
    protected String getHurtSound() {
        return "mob.wolf.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "mob.wolf.death";
    }

    protected float getSoundVolume() {
        return 0.4f;
    }

    protected Item getDropItem() {
        return null;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.isShaking && !this.shakingAndDry && !this.hasPath() && this.onGround) {
            this.shakingAndDry = true;
            this.timeWolfIsShaking = 0.0f;
            this.prevTimeWolfIsShaking = 0.0f;
            this.worldObj.setEntityState((Entity)this, (byte)8);
        }
        if (this.isAngry()) {
            this.setAttackTarget((EntityLivingBase)this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 24.0));
            if (this.rand.nextInt(400) == 0 && this.getAttackTarget() == null) {
                this.setAngry(false);
            }
        }
    }

    public void onUpdate() {
        super.onUpdate();
        this.headRotationY = this.headRotationX;
        this.headRotationX = this.isRotatingCalled() ? (this.headRotationX += (1.0f - this.headRotationX) * 0.4f) : (this.headRotationX += (0.0f - this.headRotationX) * 0.4f);
        if (this.isRotatingCalled()) {
            this.numTicksToChaseTarget = 10;
        }
        if (this.isWet()) {
            this.isShaking = true;
            this.shakingAndDry = false;
            this.timeWolfIsShaking = 0.0f;
            this.prevTimeWolfIsShaking = 0.0f;
        } else if ((this.isShaking || this.shakingAndDry) && this.shakingAndDry) {
            if (this.timeWolfIsShaking == 0.0f) {
                this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            }
            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05f;
            if (this.prevTimeWolfIsShaking >= 2.0f) {
                this.isShaking = false;
                this.shakingAndDry = false;
                this.prevTimeWolfIsShaking = 0.0f;
                this.timeWolfIsShaking = 0.0f;
            }
            if (this.timeWolfIsShaking > 0.4f) {
                float f = (float)this.boundingBox.minY;
                int i = (int)(MathHelper.sin((float)((this.timeWolfIsShaking - 0.4f) * (float)Math.PI)) * 7.0f);
                for (int j = 0; j < i; ++j) {
                    float f1 = (this.rand.nextFloat() * 2.0f - 1.0f) * this.width * 0.5f;
                    float f2 = (this.rand.nextFloat() * 2.0f - 1.0f) * this.width * 0.5f;
                    this.worldObj.spawnParticle("splash", this.posX + (double)f1, (double)(f + 0.8f), this.posZ + (double)f2, this.motionX, this.motionY, this.motionZ);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean getWolfShaking() {
        return this.isShaking;
    }

    @SideOnly(value=Side.CLIENT)
    public float getShadingWhileShaking(float par1) {
        return 0.75f + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1) / 2.0f * 0.25f;
    }

    @SideOnly(value=Side.CLIENT)
    public float getShakeAngle(float par1, float par2) {
        float f2 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1 + par2) / 1.8f;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        return MathHelper.sin((float)(f2 * (float)Math.PI)) * MathHelper.sin((float)(f2 * (float)Math.PI * 11.0f)) * 0.15f * (float)Math.PI;
    }

    public float getEyeHeight() {
        return this.height * 0.8f;
    }

    @SideOnly(value=Side.CLIENT)
    public float getInterestedAngle(float par1) {
        return (this.headRotationY + (this.headRotationX - this.headRotationY) * par1) * 0.15f * (float)Math.PI;
    }

    public int getVerticalFaceSpeed() {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        Entity entity = source.getEntity();
        this.aiSit.setSitting(false);
        if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
            par2 = (par2 + 1.0f) / 2.0f;
        }
        if (entity instanceof EntityPlayer && !this.isTamed()) {
            this.setAngry(true);
        }
        return super.attackEntityFrom(source, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        return entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)EntityStats.moonWolfDamage);
    }

    public boolean interact(EntityPlayer player) {
        ItemStack stack = player.inventory.getCurrentItem();
        if (this.isTamed()) {
            ItemFood food;
            if (stack != null && stack.getItem() instanceof ItemFood && (food = (ItemFood)stack.getItem()).isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 200.0f) {
                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
                this.heal(food.func_150905_g(stack));
                if (stack.stackSize <= 0) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                }
                return true;
            }
            if (!(!this.func_152114_e((EntityLivingBase)player) || this.worldObj.isRemote || stack != null && this.isBreedingItem(stack))) {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.setPathToEntity(null);
                this.setTarget(null);
                this.setAttackTarget(null);
            }
        } else if (stack != null && stack.getItem() == Items.bone && !this.isAngry()) {
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
            if (stack.stackSize <= 0) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }
            if (!this.worldObj.isRemote) {
                if (this.rand.nextInt(3) == 0) {
                    this.setTamed(true);
                    this.setPathToEntity(null);
                    this.setAttackTarget(null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0f);
                    this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
                    this.func_152115_b(player.getUniqueID().toString());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState((Entity)this, (byte)7);
                } else {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState((Entity)this, (byte)6);
                }
            }
            return true;
        }
        return super.interact(player);
    }

    @SideOnly(value=Side.CLIENT)
    public float getTailRotation() {
        return this.isAngry() ? 1.5393804f : (this.isTamed() ? (0.55f - (20.0f - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02f) * (float)Math.PI : 0.62831855f);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return stack == null ? false : (!(stack.getItem() instanceof ItemFood) ? false : ((ItemFood)stack.getItem()).isWolfsFavoriteMeat());
    }

    @Override
    public boolean isAngry() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    public void setAngry(boolean par1) {
        byte b = this.dataWatcher.getWatchableObjectByte(16);
        if (par1) {
            this.dataWatcher.updateObject(16, (Object)((byte)(b | 2)));
        } else {
            this.dataWatcher.updateObject(16, (Object)((byte)(b & 0xFFFFFFFD)));
        }
    }

    public EntityMoonWolf createChild(EntityAgeable par1EntityAgeable) {
        return null;
    }

    public boolean isRotatingCalled() {
        return this.dataWatcher.getWatchableObjectByte(19) == 1;
    }

    protected boolean canDespawn() {
        return !this.isTamed();
    }

    @Override
    public String mobName() {
        return "Moon Wolf";
    }
}

