/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.projectile.EntityTwilightDemonShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityTwilightDemon
extends EntityDivineRPGBoss {
    int shooting;

    public EntityTwilightDemon(World var1) {
        super(var1);
        this.setSize(2.0f, 4.0f);
        this.tasks.addTask(1, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 40.0f, 50.0f));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.twilightDemonHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.twilightDemonDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.twilightDemonSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.twilightDemonFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.insect);
    }

    protected String getHurtSound() {
        return this.getLivingSound();
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted % 160 == 0) {
            this.shooting = 100;
        }
        this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 40.0);
        if (this.entityToAttack != null && !this.worldObj.isRemote && this.shooting > 0) {
            double tx = this.entityToAttack.posX - this.posX;
            double ty = this.entityToAttack.boundingBox.minY - this.posY - 2.0;
            double tz = this.entityToAttack.posZ - this.posZ;
            double angle = Math.atan(-tx / tz);
            EntityTwilightDemonShot e = new EntityTwilightDemonShot(this.worldObj, (EntityLivingBase)this);
            e.posZ += Math.sin(angle);
            e.posX += Math.cos(angle);
            e.setThrowableHeading(tx - Math.cos(angle), ty, tz - Math.sin(angle), 1.6f, 0.0f);
            this.worldObj.spawnEntityInWorld((Entity)e);
            EntityTwilightDemonShot e1 = new EntityTwilightDemonShot(this.worldObj, (EntityLivingBase)this);
            e1.posZ -= Math.sin(angle);
            e1.posX -= Math.cos(angle);
            e1.setThrowableHeading(tx + Math.cos(angle), ty, tz + Math.sin(angle), 1.6f, 0.0f);
            this.worldObj.spawnEntityInWorld((Entity)e1);
        }
        if (this.shooting > 0) {
            --this.shooting;
        }
    }

    public void dropFewItems(boolean par1, int par2) {
        int chance = this.rand.nextInt(99);
        switch (this.rand.nextInt(2)) {
            case 0: {
                this.dropItem(TwilightItemsWeapons.haliteBlitz, 1);
                break;
            }
            case 1: {
                this.dropItem(TwilightItemsWeapons.haliteBow, 1);
            }
        }
        if (chance < 50) {
            this.dropItem(Item.getItemFromBlock((Block)VanillaBlocks.twilightDemonStatue), 1);
        } else if (chance < 40) {
            this.dropItem(VanillaItemsOther.greenChunk, 1);
            if (chance < 30) {
                this.dropItem(VanillaItemsOther.tarnishedCrystal, 1);
            }
        }
    }

    @Override
    public String mobName() {
        return "Twilight Demon";
    }

    @Override
    public String name() {
        return "Twilight Demon";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

