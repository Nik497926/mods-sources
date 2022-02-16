/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.arcana.projectile.EntityLivingStatueArrow;
import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class EntityLivingStatue
extends EntityDivineRPGMob
implements IRangedAttackMob {
    public EntityLivingStatue(World var1) {
        super(var1);
        this.tasks.addTask(4, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 10, 60.0f));
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.livingStatueHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.livingStatueSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.livingStatueFollowRange);
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 40.0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    protected void dropFewItems(boolean var1, int var2) {
        this.dropItem(ArcanaItems.collector, 1);
    }

    protected String getLivingSound() {
        return null;
    }

    protected String getHurtSound() {
        return Sounds.highHit.getPrefixedName();
    }

    protected String getDeathSound() {
        return Sounds.highHit.getPrefixedName();
    }

    public void attackEntityWithRangedAttack(EntityLivingBase entityliving, float f) {
        EntityLivingStatueArrow var1 = new EntityLivingStatueArrow(this.worldObj, (EntityLiving)this, 15.0f);
        this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)var1);
    }

    @Override
    public String mobName() {
        return "Living Statue";
    }
}

