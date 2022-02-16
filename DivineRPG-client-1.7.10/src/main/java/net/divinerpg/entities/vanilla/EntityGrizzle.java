/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGrizzle
extends EntityDivineRPGTameable {
    public EntityGrizzle(World par1World, EntityPlayer p, int color) {
        this(par1World);
        this.setTamed(true);
        this.func_152115_b(p.getUniqueID().toString());
        this.dataWatcher.updateObject(19, (Object)color);
    }

    public EntityGrizzle(World par1World) {
        super(par1World);
        this.setSize(0.8f, 1.2f);
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(19, (Object)0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.grizzleHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.grizzleSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.grizzleFollowRange);
    }

    public boolean interact(EntityPlayer player) {
        ItemStack stack = player.inventory.getCurrentItem();
        if (this.isTamed()) {
            ItemFood var3;
            if (stack != null && stack.getItem() instanceof ItemFood && (var3 = (ItemFood)stack.getItem()).isWolfsFavoriteMeat() && (double)this.getHealth() < EntityStats.grizzleHealth) {
                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
                this.heal(var3.func_150905_g(stack));
                if (stack.stackSize <= 0) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                }
                return true;
            }
        } else {
            this.setTamed(true);
            this.func_152115_b(player.getUniqueID().toString());
        }
        return super.interact(player);
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        return e.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)EntityStats.grizzleDamage);
    }

    @Override
    public String mobName() {
        return "Grizzle";
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.grizzle);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.grizzleHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.grizzleHurt);
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }

    public int getColor() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    public void setColor(int color) {
        this.dataWatcher.updateObject(19, (Object)color);
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("Color", this.getColor());
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.setColor(tag.getInteger("Color"));
    }
}

