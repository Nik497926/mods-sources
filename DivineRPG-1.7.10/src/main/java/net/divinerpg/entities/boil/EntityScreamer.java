/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.boil;

import net.divinerpg.entities.base.EntityDivineRPGFlying;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.projectile.EntityCoriShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityScreamer
extends EntityDivineRPGFlying {
    public int courseChangeCooldown = 0;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity = null;
    private int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;

    public EntityScreamer(World var1) {
        super(var1);
        this.isImmuneToFire = true;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.biphron);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.biphronHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.biphronHurt);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.screamerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.screamerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.screamerFollowRange);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }

    public void onUpdate() {
        byte b0;
        byte b1;
        super.onUpdate();
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
            this.setDead();
        }
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
        double d4 = 32.0;
        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity((Entity)this) < d4 * d4) {
            double d5 = this.targetedEntity.posX - this.posX;
            double d6 = this.targetedEntity.boundingBox.minY + 1.0 - this.posY;
            double d7 = this.targetedEntity.posZ - this.posZ;
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0f / (float)Math.PI;
            if (this.canEntityBeSeen(this.targetedEntity)) {
                ++this.attackCounter;
                if (this.attackCounter == 20) {
                    this.worldObj.playSoundAtEntity(this.targetedEntity, Sounds.getSoundName(Sounds.coriShoot), 1.0f, 1.0f);
                    EntityCoriShot shot = new EntityCoriShot(this.worldObj, (EntityLivingBase)this, 30.0f);
                    shot.setThrowableHeading(d5, d6, d7, 1.6f, 4.0f);
                    if (!this.worldObj.isRemote) {
                        this.worldObj.spawnEntityInWorld((Entity)shot);
                    }
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
        double var9 = (this.waypointX - this.posX) / par7;
        double var11 = (this.waypointY - this.posY) / par7;
        double var13 = (this.waypointZ - this.posZ) / par7;
        AxisAlignedBB var15 = this.boundingBox.copy();
        int var16 = 1;
        while ((double)var16 < par7) {
            var15.offset(var9, var11, var13);
            if (!this.worldObj.getCollidingBoundingBoxes((Entity)this, var15).isEmpty()) {
                return false;
            }
            ++var16;
        }
        return true;
    }

    protected void dropFewItems(boolean b, int j) {
        if (this.rand.nextInt(99) < 15) {
            this.dropItem(JourneyItemsOther.boilChunk, 1);
        }
    }

    public boolean isBurning() {
        return false;
    }

    public int getMaxSpawnedInChunk() {
        return 2;
    }

    @Override
    public String mobName() {
        return "Screamer";
    }
}

