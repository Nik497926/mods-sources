/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.iceika;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.IceikaItems;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAlicanto
extends EntityDivineRPGMob {
    private ChunkCoordinates currentFlightTarget;
    private int flyTimer;

    public EntityAlicanto(World var1) {
        super(var1);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), true));
        this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 32.0f));
        this.tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(3, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.flyTimer = 0;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.alicantoHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.alicantoDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.alicantoSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.alicantoFollowRange);
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95f;
    }

    protected Item getDropItem() {
        return IceikaItems.iceShards;
    }

    @Override
    protected String getLivingSound() {
        return this.rand.nextInt(4) != 0 ? null : Sounds.getSoundName(Sounds.alicanto);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.alicantoHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.alicantoHurt);
    }

    public void onUpdate() {
        super.onUpdate();
        this.motionY *= (double)0.6f;
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.getAttackTarget() != null) {
            int var1 = (int)this.getAttackTarget().posX;
            int var2 = (int)this.getAttackTarget().posY;
            int var3 = (int)this.getAttackTarget().posZ;
            this.currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
        } else if (this.flyTimer != 0) {
            this.flyTimer = 120;
            this.currentFlightTarget = new ChunkCoordinates((int)(this.posX + (double)this.rand.nextInt(16)) - 8, (int)(this.posY + (double)this.rand.nextInt(32)) - 16, (int)(this.posZ + (double)this.rand.nextInt(16)) - 8);
        }
        if (this.currentFlightTarget != null) {
            double var1 = (double)this.currentFlightTarget.posX - this.posX;
            double var3 = (double)this.currentFlightTarget.posY - this.posY;
            double var5 = (double)this.currentFlightTarget.posZ - this.posZ;
            if (Math.signum(var1) != 0.0 || Math.signum(var3) != 0.0 || Math.signum(var5) != 0.0) {
                this.motionX += (Math.signum(var1) * 0.15 - this.motionX) * (double)0.1f;
                this.motionY += (Math.signum(var3) * 1.699999988079071 - this.motionY) * (double)0.1f;
                this.motionZ += (Math.signum(var5) * 0.15 - this.motionZ) * (double)0.1f;
                float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0f;
                float var8 = MathHelper.wrapAngleTo180_float((float)(var7 - this.rotationYaw));
                this.moveForward = 0.5f;
                this.rotationYaw += var8;
            }
            --this.flyTimer;
        }
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    public String mobName() {
        return "Alicanto";
    }
}

