/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityGolemOfRejuv
extends EntityDivineRPGTameable {
    private int healTimer;

    public EntityGolemOfRejuv(World par1World) {
        super(par1World);
        this.setSize(1.0f, 2.2f);
        this.healTimer = 0;
    }

    public EntityGolemOfRejuv(World par1World, EntityPlayer p) {
        this(par1World);
        this.func_152115_b(p.getUniqueID().toString());
        this.setTamed(true);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.golemRejuvHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.golemRejuvSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.golemRejuvFollowRange);
    }

    @Override
    protected String getLivingSound() {
        return Sounds.golem.getPrefixedName();
    }

    public boolean interact(EntityPlayer par1EntityPlayer) {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
        if (this.isTamed()) {
            ItemFood var3;
            if (var2 != null && var2.getItem() instanceof ItemFood && (var3 = (ItemFood)var2.getItem()).isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectInt(18) < 60) {
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
        if (this.getOwner() != null) {
            if (this.healTimer == 16) {
                this.getOwner().heal(1.0f);
                this.healTimer = 0;
            } else {
                ++this.healTimer;
            }
        }
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }

    @Override
    public String mobName() {
        return "Golem of Rejuv'";
    }
}

