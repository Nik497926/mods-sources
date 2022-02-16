/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityGhast
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHellPig
extends EntityDivineRPGTameable {
    public EntityHellPig(World w) {
        super(w);
        this.setSize(0.7f, 0.5f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.hellPigSpeed);
        if (!this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.hellPigHealth);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.hellPigTamedHealth);
        }
    }

    @Override
    public String mobName() {
        return "Hell Pig";
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.worldObj.getBlock((int)this.posX, (int)this.posY - 1, (int)this.posZ) == Blocks.soul_sand && super.getCanSpawnHere();
    }

    public void setAttackTarget(EntityLivingBase par1EntityLivingBase) {
        super.setAttackTarget(par1EntityLivingBase);
        if (par1EntityLivingBase == null) {
            this.setAngry(false);
        } else if (!this.isTamed()) {
            this.setAngry(true);
        }
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)new Float(this.getHealth()));
    }

    protected void updateAITick() {
        this.dataWatcher.updateObject(18, (Object)Float.valueOf(this.getHealth()));
    }

    protected void func_145780_a(int x, int y, int z, Block b) {
        this.playSound("mob.wolf.step", 0.15f, 1.0f);
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Angry", this.isAngry());
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        Entity entity = par1DamageSource.getEntity();
        this.aiSit.setSitting(false);
        if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
            par2 = (par2 + 1.0f) / 2.0f;
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        int i = this.isTamed() ? 10 : 5;
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)i);
    }

    public void setTamed(boolean par1) {
        super.setTamed(par1);
        if (par1) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.hellPigTamedHealth);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.hellPigHealth);
        }
    }

    public boolean interact(EntityPlayer par1EntityPlayer) {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
        if (this.isTamed()) {
            ItemFood itemfood;
            if (itemstack != null && itemstack.getItem() instanceof ItemFood && (itemfood = (ItemFood)itemstack.getItem()).isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0f) {
                if (!par1EntityPlayer.capabilities.isCreativeMode) {
                    --itemstack.stackSize;
                }
                this.heal(itemfood.func_150905_g(itemstack));
                if (itemstack.stackSize <= 0) {
                    par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                }
                return true;
            }
        } else if (itemstack != null && itemstack.getItem() == Items.blaze_powder && !this.isAngry()) {
            if (!par1EntityPlayer.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }
            if (itemstack.stackSize <= 0) {
                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
            }
            if (!this.worldObj.isRemote) {
                if (this.rand.nextInt(3) == 0) {
                    this.setTamed(true);
                    this.setPathToEntity(null);
                    this.setAttackTarget(null);
                    this.setHealth(20.0f);
                    this.func_152115_b(par1EntityPlayer.getUniqueID().toString());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState((Entity)this, (byte)7);
                } else {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState((Entity)this, (byte)6);
                }
            }
            return true;
        }
        return super.interact(par1EntityPlayer);
    }

    @Override
    public boolean isAngry() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    private void setAngry(boolean b) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        if (b) {
            this.dataWatcher.updateObject(16, (Object)((byte)(b0 | 2)));
        } else {
            this.dataWatcher.updateObject(16, (Object)((byte)(b0 & 0xFFFFFFFD)));
        }
    }

    protected boolean canDespawn() {
        return !this.isTamed() && this.ticksExisted > 2400;
    }

    public boolean func_142018_a(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase) {
        if (!(par1EntityLivingBase instanceof EntityCreeper) && !(par1EntityLivingBase instanceof EntityGhast)) {
            EntityHellPig pig;
            if (par1EntityLivingBase instanceof EntityHellPig && (pig = (EntityHellPig)par1EntityLivingBase).isTamed() && pig.getOwner() == par2EntityLivingBase) {
                return false;
            }
            return par1EntityLivingBase instanceof EntityPlayer && par2EntityLivingBase instanceof EntityPlayer && !((EntityPlayer)par2EntityLivingBase).canAttackPlayer((EntityPlayer)par1EntityLivingBase) ? false : !(par1EntityLivingBase instanceof EntityHorse) || !((EntityHorse)par1EntityLivingBase).isTame();
        }
        return false;
    }
}

