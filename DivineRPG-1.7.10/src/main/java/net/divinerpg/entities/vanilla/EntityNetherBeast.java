/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.Direction
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import java.util.List;
import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityNetherBeast
extends EntityDivineRPGBoss {
    private int attackTimer;

    public EntityNetherBeast(World par1World) {
        super(par1World);
        this.addAI();
        this.setSize(3.0f, 3.5f);
    }

    public void setInPortal() {
        if (this.timeUntilPortal > 0) {
            this.timeUntilPortal = this.getPortalCooldown();
        } else {
            double d0 = this.prevPosX - this.posX;
            double d1 = this.prevPosZ - this.posZ;
            if (!this.worldObj.isRemote && !this.inPortal) {
                this.teleportDirection = Direction.getMovementDirection((double)d0, (double)d1);
            }
            this.inPortal = false;
        }
    }

    @Override
    public void onLivingUpdate() {
        EntityPlayer player;
        super.onLivingUpdate();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if (this.ticksExisted % 100 == 0 && (player = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 32.0)) != null) {
            this.setPosition(player.posX, player.posY, player.posZ);
            player.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 100.0f);
        }
        if (this.ticksExisted % 100 == 0) {
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(16.0, 16.0, 16.0));
        }
    }

    protected void addAI() {
        this.tasks.addTask(3, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.isImmuneToFire = true;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.netherBeastHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.netherBeastDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.netherBeastSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.netherBeastFollowRange);
    }

    public boolean attackEntityAsMob(Entity e) {
        this.attackTimer = 5;
        this.worldObj.setEntityState((Entity)this, (byte)4);
        boolean flag = e.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)this.getAttackDamage());
        if (flag) {
            e.motionY += 1.0000000059604646;
            e.setFire(10 + this.rand.nextInt(10));
        }
        this.playSound("mob.irongolem.throw", 1.0f, 1.0f);
        return flag;
    }

    public String getLivingSound() {
        return Sounds.getSoundName(Sounds.netherBeast);
    }

    public String getHurtSound() {
        return Sounds.getSoundName(Sounds.netherBeastHurt);
    }

    public String getDeathSound() {
        return Sounds.bossDeath.getPrefixedName();
    }

    @Override
    public String name() {
        return "NetherBeast";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }

    @Override
    public String mobName() {
        return "NetherBeast";
    }

    public void onDeath(DamageSource damage) {
        this.worldObj.setBlock((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0), (Block)Blocks.chest);
        IInventory te = (IInventory)this.worldObj.getTileEntity((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0));
        if (this.rand.nextInt(99) < 45) {
            te.setInventorySlotContents(3, new ItemStack(JourneyItemsOther.flamingSpring, 1));
        }
        te.setInventorySlotContents(1, new ItemStack(JourneyItemsWeapon.bloodcrustSword, 1));
        if (this.rand.nextInt(99) < 30) {
            te.setInventorySlotContents(2, new ItemStack(JourneyItemsWeapon.netherBeastSword, 1));
        }
    }
}

