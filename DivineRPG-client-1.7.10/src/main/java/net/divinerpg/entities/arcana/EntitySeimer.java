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
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.utils.events.ArcanaHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySeimer
extends EntityDivineRPGTameable {
    public EntitySeimer(World par1World, EntityPlayer p) {
        this(par1World);
        this.setTamed(true);
        this.func_152115_b(p.getUniqueID().toString());
    }

    public EntitySeimer(World par1World) {
        super(par1World);
        this.setSize(1.2f, 1.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.seimerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.seimerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.seimerFollowRange);
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
            this.func_152115_b(par1EntityPlayer.getUniqueID().toString());
        }
        return super.interact(par1EntityPlayer);
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.getOwner() != null && this.getOwner() instanceof EntityPlayer) {
            ArcanaHelper.getProperties((EntityPlayer)this.getOwner()).regen(1.0f);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        return e.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 6.0f);
    }

    @Override
    public String mobName() {
        return "Seimer";
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }
}

