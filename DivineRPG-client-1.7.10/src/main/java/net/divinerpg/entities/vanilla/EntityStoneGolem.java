/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.base.IAttackTimer;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityStoneGolem
extends EntityDivineRPGTameable
implements IAttackTimer {
    public EntityStoneGolem(World w, EntityPlayer p) {
        this(w);
        this.setTamed(true);
        this.func_152115_b(p.getUniqueID().toString());
    }

    public EntityStoneGolem(World var1) {
        super(var1);
        this.setSize(1.3f, 2.5f);
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(19, (Object)0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.stoneGolemHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.stoneGolemSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.stoneGolemFollowRange);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.getAttackTimer() > 0) {
            this.dataWatcher.updateObject(19, (Object)(this.getAttackTimer() - 1));
        }
    }

    @Override
    public int getAttackTimer() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    public boolean interact(EntityPlayer player) {
        ItemStack stack = player.inventory.getCurrentItem();
        if (this.isTamed()) {
            ItemFood var3;
            if (stack != null && stack.getItem() instanceof ItemFood && ((var3 = (ItemFood)stack.getItem()) == Item.getItemFromBlock((Block)Blocks.stone) || var3 == Item.getItemFromBlock((Block)Blocks.cobblestone) && this.dataWatcher.getWatchableObjectInt(18) < 20)) {
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
    public boolean getCanSpawnHere() {
        int var3;
        int var2;
        int var1 = MathHelper.floor_double((double)this.posX);
        return this.getBlockPathWeight(var1, var2 = MathHelper.floor_double((double)this.boundingBox.minY), var3 = MathHelper.floor_double((double)this.posZ)) >= 0.0f && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        boolean attack = e.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)EntityStats.stoneGolemDamage);
        if (attack) {
            e.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f))), 0.1, (double)MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)));
            this.dataWatcher.updateObject(19, (Object)10);
        }
        return attack;
    }

    @Override
    public String mobName() {
        return "Stone Golem";
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }
}

