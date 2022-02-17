/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPumpkinSpider
extends EntityDivineRPGMob {
    public EntityPumpkinSpider(World par1World) {
        super(par1World);
        this.setSize(1.25f, 1.0f);
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte)0);
        this.dataWatcher.addObject(17, (Object)0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.pumpkinSpiderHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.pumpkinSpiderDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.pumpkinSpiderSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.pumpkinSpiderFollowRange);
    }

    @Override
    protected String getLivingSound() {
        return null;
    }

    @Override
    protected String getHurtSound() {
        return "mob.spider.say";
    }

    @Override
    protected String getDeathSound() {
        return "mob.spider.death";
    }

    public void addVelocity(double x, double y, double z) {
        if (this.getProvoked()) {
            super.addVelocity(x, y, z);
        }
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.getProvoked()) {
            this.renderYawOffset = 0.0f;
            if (this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 6.0) != null) {
                this.setProvoked();
            }
        } else if (!this.worldObj.isRemote) {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }

    @Override
    public boolean needsSpecialAI() {
        return true;
    }

    public boolean getProvoked() {
        return this.dataWatcher.getWatchableObjectInt(17) == 1;
    }

    public void setProvoked() {
        this.dataWatcher.updateObject(17, (Object)1);
        this.addBasicAI();
        this.addAttackingAI();
    }

    protected void dropFewItems(boolean hit, int looting) {
        this.dropItem(VanillaItemsOther.terranShards, this.rand.nextInt(3 + looting));
        this.dropItem(Item.getItemFromBlock((Block)Blocks.pumpkin), 1);
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.worldObj.getBlock((int)this.posX, MathHelper.floor_double((double)this.boundingBox.minY) - 1, (int)this.posZ) == Blocks.grass && super.getCanSpawnHere();
    }

    @Override
    public String mobName() {
        return "Pumpkin Spider";
    }

    public void setBesideClimbableBlock(boolean var1) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);
        var2 = var1 ? (byte)(var2 | 1) : (byte)(var2 & 0xFFFFFFFE);
        this.dataWatcher.updateObject(16, (Object)var2);
    }

    public boolean isBesideClimbableBlock() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public boolean isOnLadder() {
        return this.getProvoked() && this.isBesideClimbableBlock();
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setBoolean("Provoked", this.getProvoked());
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.getBoolean("Provoked")) {
            this.setProvoked();
        }
    }
}

