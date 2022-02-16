/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLiopleurodon
extends EntityDivineRPGMob {
    public EntityLiopleurodon(World var1) {
        super(var1);
        this.setSize(2.0f, 1.0f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.liopleurodonHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.liopleurodonDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.liopleurodonSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.liopleurodonFollowRange);
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

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.liopleurodon);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.liopleurodonHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.liopleurodonHurt);
    }

    protected float getSoundVolume() {
        return 0.05f;
    }

    protected void dropFewItems(boolean var1, int var2) {
        if (this.rand.nextInt(7) == 0) {
            this.dropItem(VanillaItemsOther.liopleurodonSkull, 1);
        }
        this.dropItem(VanillaItemsOther.liopleurodonTeeth, this.rand.nextInt(3 + var2));
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.rand.nextBoolean() && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.isInWater();
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

    public boolean isInWater() {
        return this.worldObj.getBlock((int)this.posX, MathHelper.floor_double((double)this.posY), (int)this.posZ).getMaterial() == Material.water;
    }

    protected void fall(float var1) {
    }

    public boolean isValidLightLevel() {
        return true;
    }

    @Override
    public String mobName() {
        return "Liopleurodon";
    }
}

