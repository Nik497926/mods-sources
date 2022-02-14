/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySnapper
extends EntityDivineRPGTameable {
    public EntitySnapper(World par1World, EntityPlayer p) {
        this(par1World);
        this.setTamed(true);
        this.func_152115_b(p.getUniqueID().toString());
    }

    public EntitySnapper(World par1World) {
        super(par1World);
        this.setSize(0.6f, 0.5f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.snapperHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.snapperSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.snapperFollowRange);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    public boolean interact(EntityPlayer player) {
        ItemStack stack = player.inventory.getCurrentItem();
        if (this.isTamed()) {
            ItemFood var3;
            if (stack != null && stack.getItem() instanceof ItemFood && (var3 = (ItemFood)stack.getItem()) == Items.fish && (double)this.getHealth() < EntityStats.snapperHealth) {
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
        return e.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)EntityStats.snapperDamage);
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.getOwner() != null && this.getOwner() instanceof EntityPlayer && this.rand.nextInt(3000) == 0) {
            this.getOwner().addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 5, 0));
        }
    }

    @Override
    public String mobName() {
        return "Snapper";
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }
}

