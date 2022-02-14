/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.arcana.projectile.EntityFyracryxFireball;
import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFyracryx
extends EntityDivineRPGTameable
implements IRangedAttackMob {
    public EntityFyracryx(World par1World, EntityPlayer par2EntityPlayer) {
        this(par1World);
        this.setTamed(true);
        this.isImmuneToFire = true;
        this.func_152115_b(par2EntityPlayer.getUniqueID().toString());
    }

    public EntityFyracryx(World par1World) {
        super(par1World);
        this.setSize(2.0f, 2.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.fyracryxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.fyracryxSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.fyracryxFollowRange);
    }

    @Override
    public String mobName() {
        return "Fyracryx";
    }

    @Override
    protected String getLivingSound() {
        return Sounds.deathcryx.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.deathcryxHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return Sounds.deathcryx.getPrefixedName();
    }

    public boolean interact(EntityPlayer par1EntityPlayer) {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
        if (this.isTamed()) {
            ItemFood var3;
            if (var2 != null && var2.getItem() instanceof ItemFood && (var3 = (ItemFood)var2.getItem()).isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectInt(18) < 20) {
                if (!par1EntityPlayer.capabilities.isCreativeMode) {
                    --var2.stackSize;
                }
                this.heal(var3.func_150905_g(var2));
                if (var2.stackSize <= 0) {
                    par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                }
                return true;
            }
        } else {
            this.setTamed(true);
            this.func_152115_b(par1EntityPlayer.getDisplayName());
        }
        return super.interact(par1EntityPlayer);
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }

    public void onUpdate() {
        super.onUpdate();
    }

    public void attackEntityWithRangedAttack(EntityLivingBase e, float f) {
        double tx = e.posX - this.posX;
        double ty = e.boundingBox.minY - this.posY;
        double tz = e.posZ - this.posZ;
        float var9 = MathHelper.sqrt_float((float)this.getDistanceToEntity((Entity)this)) * 0.5f;
        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
        EntityFyracryxFireball var11 = new EntityFyracryxFireball(this.worldObj, (EntityLiving)this, tx + this.rand.nextGaussian() * (double)var9, ty, tz + this.rand.nextGaussian() * (double)var9);
        var11.posY = this.posY + (double)(this.height / 2.0f) + 0.5;
        this.worldObj.spawnEntityInWorld((Entity)var11);
    }
}

