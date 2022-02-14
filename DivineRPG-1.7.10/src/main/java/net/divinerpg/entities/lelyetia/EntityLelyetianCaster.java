/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIRestrictSun
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.lelyetia;

import net.divinerpg.entities.lelyetia.EntityLelyetianShot;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLelyetianCaster
extends EntityMob
implements IRangedAttackMob {
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack((IRangedAttackMob)this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 60, 15.0f);

    public EntityLelyetianCaster(World par1World) {
        super(par1World);
        float moveSpeed = 0.45f;
        ((EntityLiving)this).tasks.addTask(7, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 64.0f));
        ((EntityLiving)this).tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        ((EntityLiving)this).tasks.addTask(2, (EntityAIBase)new EntityAIRestrictSun((EntityCreature)this));
        ((EntityLiving)this).tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, (double)0.45f));
        ((EntityLiving)this).tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        ((EntityLiving)this).tasks.addTask(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        ((EntityLiving)this).targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        ((EntityLiving)this).targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.setSize(0.6f, 2.3f);
    }

    public boolean getCanSpawnHere() {
        return ((Entity)this).worldObj.difficultySetting != EnumDifficulty.PEACEFUL && ((Entity)this).worldObj.checkNoEntityCollision(((Entity)this).boundingBox) && ((Entity)this).worldObj.getCollidingBoundingBoxes((Entity)this, ((Entity)this).boundingBox).isEmpty() && !((Entity)this).worldObj.isAnyLiquid(((Entity)this).boundingBox);
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    protected String getLivingSound() {
        return "divinerpg:LelyetianLiving";
    }

    protected String getDeathSound() {
        return "divinerpg:LelyetianDeath";
    }

    protected String getHurtSound() {
        return "divinerpg:LelyetianHit";
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    protected void dropFewItems(boolean par1, int par2) {
        if (this.rand.nextInt(6) == 1) {
            this.dropItem(JourneyItemsOther.SpectralDust, this.rand.nextInt(3));
        }
        if (this.rand.nextInt(20) == 15) {
            this.dropItem(VanillaItemsOther.arlemiteIngot, this.rand.nextInt(3));
        }
        if (this.rand.nextInt(20) == 14) {
            this.dropItem(VanillaItemsOther.rupeeIngot, this.rand.nextInt(3));
        }
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Integer(5));
    }

    public void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(25.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(420.0);
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.pig.step", 0.55f, 1.0f);
    }

    public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {
        EntityLelyetianShot var2 = new EntityLelyetianShot(((Entity)this).worldObj, this, 4.0f, 1);
        double var3 = var1.posX - ((Entity)this).posX;
        double var4 = var1.posY + (double)var1.getEyeHeight() - (double)1.1f - ((Entity)var2).posY;
        double var5 = var1.posZ - ((Entity)this).posZ;
        float var6 = MathHelper.sqrt_double((double)(var3 * var3 + var5 * var5)) * 0.2f;
        var2.setThrowableHeading(var3, var4 + (double)var6, var5, 1.6f, 12.0f);
        ((Entity)this).worldObj.spawnEntityInWorld((Entity)var2);
    }
}

