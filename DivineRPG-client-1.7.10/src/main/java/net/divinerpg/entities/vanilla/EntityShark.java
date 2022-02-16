/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityShark
extends EntityPeacefulUntilAttacked {
    public EntityShark(World var1) {
        super(var1);
        this.setSize(0.75f, 0.75f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.sharkHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.sharkDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.sharkSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.sharkFollowRange);
    }

    public int getMaxSpawnedInChunk() {
        return 1;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.shark.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.sharkHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean var1, int var2) {
        int var3 = this.rand.nextInt(3 + var2) + 1;
        for (int var4 = 0; var4 < var3; ++var4) {
            this.entityDropItem(new ItemStack(VanillaItemsOther.sharkFin), 0.0f);
        }
    }

    public void onEntityUpdate() {
        int i = this.getAir();
        super.onEntityUpdate();
        if (this.isEntityAlive() && !this.isInWater()) {
            if (!this.worldObj.isRemote) {
                this.motionX = 0.0;
                this.motionY -= 0.08;
                this.motionY *= (double)0.98f;
                this.motionZ = 0.0;
            }
            this.setAir(--i);
            if (this.getAir() == -20) {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.drown, 2.0f);
            }
        } else {
            this.setAir(300);
        }
    }

    protected void fall(float var1) {
    }

    public boolean isInWater() {
        return this.worldObj.getBlock((int)this.posX, MathHelper.floor_double((double)this.posY), (int)this.posZ).getMaterial() == Material.water;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.isInWater();
    }

    public void onDeath(DamageSource d) {
        Entity var3;
        super.onDeath(d);
        if (!this.worldObj.isRemote && (var3 = d.getEntity()) instanceof EntityPlayer) {
            ((EntityPlayer)var3).addStat((StatBase)DivineRPGAchievements.feedingOnTheFish, 1);
        }
    }

    @Override
    public String mobName() {
        return "Shark";
    }
}

