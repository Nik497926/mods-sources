/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.lelyetia;

import net.divinerpg.entities.lelyetia.EntityHunter;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityExohead
extends EntityMob
implements EntityHunter {
    @Override
    public int getLevReq() {
        return 71;
    }

    public EntityExohead(World par1World) {
        super(par1World);
        this.setSize(0.95f, 1.5f);
    }

    protected String getLivingSound() {
        return "divinerpg:ExoheadLiving";
    }

    protected String getDeathSound() {
        return "divinerpg:ExoheadDeath";
    }

    protected String getHurtSound() {
        return "divinerpg:ExoheadHit";
    }

    public boolean getCanSpawnHere() {
        return ((Entity)this).worldObj.difficultySetting != EnumDifficulty.PEACEFUL && ((Entity)this).worldObj.checkNoEntityCollision(((Entity)this).boundingBox) && ((Entity)this).worldObj.getCollidingBoundingBoxes((Entity)this, ((Entity)this).boundingBox).isEmpty() && !((Entity)this).worldObj.isAnyLiquid(((Entity)this).boundingBox);
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    protected void dropFewItems(boolean par1, int par2) {
        int chance = this.rand.nextInt(99);
        int chanceitem = this.rand.nextInt(2);
        if (chance < 70) {
            switch (chanceitem) {
                case 0: {
                    this.dropItem(JourneyItemsOther.DarkBones, 1);
                    break;
                }
                case 1: {
                    this.dropItem(JourneyItemsOther.DarkBones, 2);
                    break;
                }
                case 2: {
                    this.dropItem(JourneyItemsOther.DarkBones, 3);
                }
            }
        }
        if (chance < 20) {
            this.dropItem(JourneyItemsWeapon.swordcoll, 1);
        }
    }

    public void onDeath(DamageSource var1) {
        super.onDeath(var1);
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        Entity entity = par1DamageSource.getSourceOfDamage();
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    protected Entity findPlayerToAttack() {
        EntityPlayer entityPlayer = ((Entity)this).worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 16.0);
        return entityPlayer != null && this.canEntityBeSeen((Entity)entityPlayer) ? entityPlayer : null;
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.pig.step", 0.55f, 1.0f);
    }

    public void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(220.0);
    }

    public boolean attackEntityAsMob(Entity par1) {
        if (super.attackEntityAsMob(par1)) {
            if (par1 instanceof EntityLivingBase) {
                ((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.hunger.id, 200, 0));
            }
            return true;
        }
        return false;
    }
}

