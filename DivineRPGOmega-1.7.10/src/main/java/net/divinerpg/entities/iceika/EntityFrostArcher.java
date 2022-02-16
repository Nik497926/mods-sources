/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.iceika;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.utils.items.IceikaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityFrostArcher
extends EntityDivineRPGMob
implements IRangedAttackMob {
    private static final ItemStack defaultHeldItem = new ItemStack(IceikaItems.icicleBow, 1);

    public EntityFrostArcher(World var1) {
        super(var1);
        this.tasks.addTask(4, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 2, 10.0f));
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 32.0f));
        this.tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 4.0));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.frostArcherHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.frostArcherDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.frostArcherSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.frostArcherFollowRange);
    }

    protected String getLivingSound() {
        return "mob.zombie.say";
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    protected String getHurtSound() {
        return "mob.zombie.hurt";
    }

    protected String getDeathSound() {
        return "mob.zombie.death";
    }

    protected Item getDropItem() {
        return IceikaItems.iceShards;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLiving, float f) {
        EntityArrow var2 = new EntityArrow(this.worldObj, (EntityLivingBase)this, par1EntityLiving, 1.6f, 12.0f);
        this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)var2);
    }

    @Override
    public String mobName() {
        return "Frost Archer";
    }
}

