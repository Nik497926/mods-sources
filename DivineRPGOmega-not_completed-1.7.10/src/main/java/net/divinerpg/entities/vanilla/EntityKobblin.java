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
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityKobblin
extends EntityDivineRPGMob {
    public EntityKobblin(World par1World) {
        super(par1World);
        this.setSize(0.75f, 1.0f);
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.kobblinHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.kobblinDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.kobblinSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.kobblinFollowRange);
    }

    protected String getLivingSound() {
        return null;
    }

    protected void func_145780_a(int x, int y, int z, Block b) {
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.kobblin);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.kobblin);
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
            if (this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 4.0) != null) {
                this.setProvoked();
                this.motionY = 0.6;
            }
        }
    }

    @Override
    public boolean needsSpecialAI() {
        return true;
    }

    public boolean getProvoked() {
        return this.dataWatcher.getWatchableObjectInt(18) == 1;
    }

    public void setProvoked() {
        this.dataWatcher.updateObject(18, (Object)1);
        this.addBasicAI();
        this.addAttackingAI();
    }

    protected Item getDropItem() {
        return VanillaItemsOther.terranShards;
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.worldObj.getBlock((int)this.posX, MathHelper.floor_double((double)this.boundingBox.minY) - 1, (int)this.posZ) == Blocks.grass && this.worldObj.getBlock((int)this.posX, MathHelper.floor_double((double)this.boundingBox.minY) - 2, (int)this.posZ) != Blocks.air && super.getCanSpawnHere();
    }

    @Override
    public String mobName() {
        return "Kobblin";
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

