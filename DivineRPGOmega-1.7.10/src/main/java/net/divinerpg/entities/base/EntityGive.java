/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class EntityGive
extends EntityDivineRPGMob {
    private Item giveItem;
    private int amount;

    public EntityGive(World par1) {
        super(par1);
    }

    public EntityGive(World par1, Item par3, int par4) {
        this(par1);
        this.giveItem = par3;
        this.amount = par4;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    public boolean interact(EntityPlayer var1) {
        if (this.isEntityAlive()) {
            if (!this.worldObj.isRemote) {
                var1.inventory.addItemStackToInventory(new ItemStack(this.giveItem, this.amount));
                this.Interact(var1);
                this.setDead();
            }
            return true;
        }
        return super.interact(var1);
    }

    public abstract void Interact(EntityPlayer var1);

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.modVillagerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.modVillagerHealth);
    }
}

