/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.lelyetia;

import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLelyetianWarrior
extends EntityMob {
    public EntityLelyetianWarrior(World par1World) {
        super(par1World);
        this.setSize(1.1f, 2.5f);
    }

    protected String getLivingSound() {
        return "divinerpg:LelyetianLiving";
    }

    protected String getDeathSound() {
        return "divinerpg:LelyetianHit";
    }

    protected String getHurtSound() {
        return "divinerpg:LelyetianHit";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.pig.step", 1.0f, 1.0f);
    }

    protected void dropFewItems(boolean par1, int par2) {
        if (this.rand.nextInt(99) < 10) {
            this.dropItem(VanillaItemsOther.mystic, 1);
        }
        if (this.rand.nextInt(20) == 15) {
            this.dropItem(JourneyItemsOther.SpectralEye, 1);
        }
    }

    public boolean getCanSpawnHere() {
        return ((Entity)this).worldObj.difficultySetting != EnumDifficulty.PEACEFUL && ((Entity)this).worldObj.checkNoEntityCollision(((Entity)this).boundingBox) && ((Entity)this).worldObj.getCollidingBoundingBoxes((Entity)this, ((Entity)this).boundingBox).isEmpty() && !((Entity)this).worldObj.isAnyLiquid(((Entity)this).boundingBox);
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    protected Entity findPlayerToAttack() {
        EntityPlayer entityPlayer = ((Entity)this).worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 16.0);
        return entityPlayer != null && this.canEntityBeSeen((Entity)entityPlayer) ? entityPlayer : null;
    }

    public void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(45.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.3);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(420.0);
    }
}

