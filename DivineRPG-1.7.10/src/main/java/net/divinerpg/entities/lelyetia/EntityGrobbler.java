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

import java.util.List;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGrobbler
extends EntityMob {
    public EntityGrobbler(World par1World) {
        super(par1World);
        this.setSize(1.4f, 2.2f);
    }

    protected String getLivingSound() {
        return "divinerpg:GrobblerLiving";
    }

    protected String getDeathSound() {
        return "divinerpg:GrobblerDeath";
    }

    protected String getHurtSound() {
        return "divinerpg:GrobblerHit";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.pig.step", 0.55f, 1.0f);
    }

    public boolean getCanSpawnHere() {
        return ((Entity)this).worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.rand.nextInt(5) == 2 && ((Entity)this).worldObj.checkNoEntityCollision(((Entity)this).boundingBox) && ((Entity)this).worldObj.getCollidingBoundingBoxes((Entity)this, ((Entity)this).boundingBox).isEmpty() && !((Entity)this).worldObj.isAnyLiquid(((Entity)this).boundingBox);
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    protected void dropFewItems(boolean par1, int par2) {
        if (this.rand.nextInt(99) <= 15) {
            this.dropItem(JourneyItemsWeapon.Monster, 1);
        }
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(11.0, 11.0, 11.0));
        for (EntityPlayer e : players) {
            if (e.capabilities.isCreativeMode) continue;
            e.addVelocity(Math.signum(((Entity)this).posX - e.posX) * 0.089, 0.0, Math.signum(((Entity)this).posZ - e.posZ) * 0.089);
        }
    }

    protected Entity findPlayerToAttack() {
        EntityPlayer entityPlayer = ((Entity)this).worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 16.0);
        return entityPlayer != null && this.canEntityBeSeen((Entity)entityPlayer) ? entityPlayer : null;
    }

    public void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
    }
}

