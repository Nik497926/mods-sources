/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGFlying;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntityEnderTripletFireball;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityEnderTriplets
extends EntityDivineRPGFlying {
    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity;
    private int aggroCooldown;
    public int prevAttackCounter;
    public int attackCounter;

    public EntityEnderTriplets(World par1World) {
        super(par1World);
        this.setSize(2.0f, 2.0f);
        this.isImmuneToFire = true;
        this.experienceValue = 5;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        if ("fireball".equals(par1DamageSource.getDamageType()) && par1DamageSource.getEntity() instanceof EntityPlayer) {
            super.attackEntityFrom(par1DamageSource, 1000.0f);
            EntityPlayer player = (EntityPlayer)par1DamageSource.getEntity();
            player.triggerAchievement((StatBase)DivineRPGAchievements.tripleDanger);
            return true;
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.enderTripletsHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.enderTripletsSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.enderTripletsFollowRange);
    }

    public void onLivingUpdate() {
        byte b0;
        byte b1;
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
            this.setDead();
        }
        this.despawnEntity();
        this.prevAttackCounter = this.attackCounter;
        double d0 = this.waypointX - this.posX;
        double d1 = this.waypointY - this.posY;
        double d2 = this.waypointZ - this.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        if (d3 < 1.0 || d3 > 3600.0) {
            this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f);
            this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f);
            this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f);
        }
        if (this.targetedEntity != null && this.targetedEntity.posY + 13.0 > this.posY) {
            this.waypointY += 4.0;
        }
        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3 = (double)MathHelper.sqrt_double((double)d3))) {
                this.motionX += d0 / d3 * 0.1;
                this.motionY += d1 / d3 * 0.1;
                this.motionZ += d2 / d3 * 0.1;
            } else {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }
        if (this.targetedEntity != null && this.targetedEntity.isDead) {
            this.targetedEntity = null;
        }
        if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
            this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 100.0);
            if (this.targetedEntity != null) {
                this.aggroCooldown = 20;
            }
        }
        double d4 = 64.0;
        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity((Entity)this) < d4 * d4) {
            double d5 = this.targetedEntity.posX - this.posX;
            double d6 = this.targetedEntity.boundingBox.minY + (double)(this.targetedEntity.height / 2.0f) - (this.posY + (double)(this.height / 2.0f)) - 5.0;
            double d7 = this.targetedEntity.posZ - this.posZ;
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0f / (float)Math.PI;
            if (this.canEntityBeSeen(this.targetedEntity)) {
                if (this.attackCounter == 10) {
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                }
                ++this.attackCounter;
                if (this.attackCounter == 20 && !this.worldObj.isRemote) {
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                    EntityEnderTripletFireball entitylargefireball = new EntityEnderTripletFireball(this.worldObj, (EntityLivingBase)this, d5, d6, d7);
                    double d8 = 4.0;
                    Vec3 vec3 = this.getLook(1.0f);
                    entitylargefireball.posX = this.posX + vec3.xCoord * d8;
                    entitylargefireball.posY = this.posY + (double)(this.height / 2.0f) + 0.5;
                    entitylargefireball.posZ = this.posZ + vec3.zCoord * d8;
                    this.worldObj.spawnEntityInWorld((Entity)entitylargefireball);
                    this.attackCounter = -40;
                }
            } else if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        } else {
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0f / (float)Math.PI;
            if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        }
        if (!this.worldObj.isRemote && (b1 = this.dataWatcher.getWatchableObjectByte(16)) != (b0 = (byte)(this.attackCounter > 10 ? 1 : 0))) {
            this.dataWatcher.updateObject(16, (Object)b0);
        }
    }

    private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
        double d4 = (this.waypointX - this.posX) / par7;
        double d5 = (this.waypointY - this.posY) / par7;
        double d6 = (this.waypointZ - this.posZ) / par7;
        AxisAlignedBB axisalignedbb = this.boundingBox.copy();
        int i = 1;
        while ((double)i < par7) {
            axisalignedbb.offset(d4, d5, d6);
            if (!this.worldObj.getCollidingBoundingBoxes((Entity)this, axisalignedbb).isEmpty()) {
                return false;
            }
            ++i;
        }
        return true;
    }

    @Override
    protected String getHurtSound() {
        return "mob.ghast.scream";
    }

    @Override
    protected String getDeathSound() {
        return "mob.ghast.death";
    }

    protected Item getDropItem() {
        return VanillaItemsOther.enderShards;
    }

    protected void dropFewItems(boolean par1, int par2) {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);
        for (int k = 0; k < j; ++k) {
            this.dropItem(VanillaItemsOther.enderShards, 3);
        }
        if (this.rand.nextInt(20) == 0) {
            this.dropItem(Util.toItem(Blocks.end_portal_frame), 1);
        }
    }

    protected float getSoundVolume() {
        return 10.0f;
    }

    @Override
    public String mobName() {
        return "Ender Triplet";
    }
}

