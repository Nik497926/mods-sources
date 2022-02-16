/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.EntityAngryBunny;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBunny
extends EntityDivineRPGTameable {
    public EntityBunny(World var1) {
        super(var1);
        this.setSize(0.5f, 0.5f);
        this.experienceValue = 40;
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(19, (Object)0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        if (!this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.bunnyHealth);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        }
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.bunnySpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.bunnyFollowRange);
    }

    protected boolean canDespawn() {
        return !this.isTamed();
    }

    public void onDeath(DamageSource var1) {
        super.onDeath(var1);
        if (!this.worldObj.isRemote && !this.isTamed()) {
            Entity var3 = var1.getEntity();
            if (var3 instanceof EntityPlayer) {
                ((EntityPlayer)var3).addStat((StatBase)DivineRPGAchievements.friendOrFoe, 1);
            }
            this.transform();
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        double i = EntityStats.bunnyDamage;
        if (this.isTamed()) {
            this.dataWatcher.updateObject(19, (Object)1);
        }
        return target.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)i);
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote && this.isTamed() && this.getAttackTarget() == null) {
            this.dataWatcher.updateObject(19, (Object)0);
        }
    }

    private void transform() {
        if (!this.worldObj.isRemote) {
            EntityAngryBunny e = new EntityAngryBunny(this.worldObj);
            e.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.worldObj.spawnEntityInWorld((Entity)e);
            this.setDead();
        }
    }

    public boolean interact(EntityPlayer player) {
        ItemStack held = player.inventory.getCurrentItem();
        if (this.isTamed()) {
            ItemFood food;
            if (held != null && held.getItem() instanceof ItemFood && (food = (ItemFood)held.getItem()).isWolfsFavoriteMeat() && this.getHealth() < 20.0f) {
                if (!player.capabilities.isCreativeMode) {
                    --held.stackSize;
                }
                this.heal(food.func_150905_g(held));
                if (held.stackSize <= 0) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                }
                return true;
            }
            if (player.getUniqueID().toString().equals(this.func_152113_b()) && !this.worldObj.isRemote) {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.setPathToEntity(null);
            }
        } else if (held != null && held.getItem() == TwilightItemsOther.edenSparkles) {
            if (!player.capabilities.isCreativeMode) {
                --held.stackSize;
            }
            if (held.stackSize <= 0) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }
            if (!this.worldObj.isRemote) {
                if (this.rand.nextInt(3) == 0) {
                    this.setTamed(true);
                    this.setPathToEntity(null);
                    this.setAttackTarget((EntityLivingBase)((EntityLiving)null));
                    this.aiSit.setSitting(true);
                    this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
                    this.setHealth(20.0f);
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

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.bunny);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.bunnyHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.bunnyHurt);
    }

    protected void dropFewItems(boolean beenHit, int lootingLevel) {
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(TwilightItemsOther.edenSoul, 1 + lootingLevel);
        }
    }

    @Override
    public String mobName() {
        return "Bunny";
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }

    @Override
    public EntityLivingBase getAttackTarget() {
        EntityLivingBase e = super.getAttackTarget();
        if (e != null && (this.isTamed() && this.getDistanceSqToEntity((Entity)e) < 144.0 || !this.isTamed())) {
            return e;
        }
        return null;
    }
}

