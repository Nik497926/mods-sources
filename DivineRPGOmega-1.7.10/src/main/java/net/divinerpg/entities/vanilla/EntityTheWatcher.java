/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.Direction
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntityWatcherShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTheWatcher
extends EntityDivineRPGBoss {
    public int courseChangeCooldown = 0;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity = null;
    private int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;
    private ChunkCoordinates spawnPosition;

    public EntityTheWatcher(World var1) {
        super(var1);
        this.setSize(4.0f, 4.0f);
        this.isImmuneToFire = true;
        this.experienceValue = 5000;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.theWatcherHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.theWatcherSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.theWatcherFollowRange);
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

    public void onUpdate() {
        super.onUpdate();
        this.motionY *= (double)0.6f;
    }

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.prevAttackCounter = this.attackCounter;
        double var1 = this.waypointX - this.posX;
        double var3 = this.waypointY - this.posY;
        double var5 = this.waypointZ - this.posZ;
        double var7 = MathHelper.sqrt_double((double)(var1 * var1 + var3 * var3 + var5 * var5));
        if (var7 < 1.0 || var7 > 60.0) {
            this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f);
            this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f);
            this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f);
        }
        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7)) {
                this.motionX += var1 / var7 * 0.1;
                this.motionY += var3 / var7 * 0.1;
                this.motionZ += var5 / var7 * 0.1;
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
        double var9 = 100.0;
        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity((Entity)this) < var9 * var9) {
            double tx = this.targetedEntity.posX - this.posX;
            double ty = this.targetedEntity.boundingBox.minY - this.posY;
            double tz = this.targetedEntity.posZ - this.posZ;
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(tx, tz)) * 180.0f / (float)Math.PI;
            if (this.canEntityBeSeen(this.targetedEntity)) {
                if (this.attackCounter == 20) {
                    this.worldObj.playSoundAtEntity((Entity)this, Sounds.roar.getPrefixedName(), 10.0f, 0.9f);
                }
                ++this.attackCounter;
                if (this.attackCounter == 20) {
                    EntityWatcherShot shot = new EntityWatcherShot(this.worldObj, (EntityLivingBase)this);
                    shot.setThrowableHeading(tx, ty, tz, 1.6f, 12.0f);
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
    }

    private boolean isCourseTraversable(double var1, double var3, double var5, double var7) {
        double var9 = (this.waypointX - this.posX) / var7;
        double var11 = (this.waypointY - this.posY) / var7;
        double var13 = (this.waypointZ - this.posZ) / var7;
        AxisAlignedBB var15 = this.boundingBox.copy();
        int var16 = 1;
        while ((double)var16 < var7) {
            var15.offset(var9, var11, var13);
            if (this.worldObj.getCollidingBoundingBoxes((Entity)this, var15).size() > 0) {
                return false;
            }
            ++var16;
        }
        return true;
    }

    public void moveEntityWithHeading(float par1, float par2) {
        if (this.isInWater()) {
            this.moveFlying(par1, par2, 0.02f);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)0.8f;
            this.motionY *= (double)0.8f;
            this.motionZ *= (double)0.8f;
        } else if (this.handleLavaMovement()) {
            this.moveFlying(par1, par2, 0.02f);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5;
            this.motionY *= 0.5;
            this.motionZ *= 0.5;
        } else {
            float f2 = 0.91f;
            if (this.onGround) {
                f2 = this.worldObj.getBlock((int)MathHelper.floor_double((double)this.posX), (int)(MathHelper.floor_double((double)this.boundingBox.minY) - 1), (int)MathHelper.floor_double((double)this.posZ)).slipperiness * 0.91f;
            }
            float f3 = 0.16277136f / (f2 * f2 * f2);
            this.moveFlying(par1, par2, this.onGround ? 0.1f * f3 : 0.02f);
            f2 = 0.91f;
            if (this.onGround) {
                f2 = this.worldObj.getBlock((int)MathHelper.floor_double((double)this.posX), (int)(MathHelper.floor_double((double)this.boundingBox.minY) - 1), (int)MathHelper.floor_double((double)this.posZ)).slipperiness * 0.91f;
            }
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)f2;
            this.motionY *= (double)f2;
            this.motionZ *= (double)f2;
        }
        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.posX - this.prevPosX;
        double d0 = this.posZ - this.prevPosZ;
        float f4 = MathHelper.sqrt_double((double)(d1 * d1 + d0 * d0)) * 4.0f;
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4f;
        this.limbSwing += this.limbSwingAmount;
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (!(this.spawnPosition == null || this.worldObj.isAirBlock(this.spawnPosition.posX, this.spawnPosition.posY, this.spawnPosition.posZ) && this.spawnPosition.posY >= 1)) {
            this.spawnPosition = null;
        }
        if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0f) {
            this.spawnPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
        }
        double d0 = (double)this.spawnPosition.posX + 0.01 - this.posX;
        double d1 = (double)this.spawnPosition.posY + 0.1 - this.posY;
        double d2 = (double)this.spawnPosition.posZ + 0.01 - this.posZ;
        double d3 = MathHelper.sqrt_double((double)(d0 * d0 + d1 * d1 + d2 * d2));
        this.motionX += d0 / d3 * 0.05;
        this.motionY += d1 / d3 * 0.1;
        this.motionZ += d2 / d3 * 0.05;
        float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0f;
        float f1 = MathHelper.wrapAngleTo180_float((float)(f - this.rotationYaw));
        this.moveForward = 0.001f;
        this.rotationYaw += f1;
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.roar);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.roar);
    }

    protected Item getDropItem() {
        return VanillaItemsOther.netheriteIngot;
    }

    protected void dropFewItems(boolean var1, int var2) {
        this.dropItem(VanillaItemsOther.netheriteIngot, 100);
        this.dropItem(VanillaItemsOther.bluefireStone, 1);
        this.dropItem(VanillaItemsOther.divineShards, this.rand.nextInt(4) + 3);
        if (this.rand.nextInt(99) < 40) {
            this.dropItem(VanillaItemsOther.fireScroll, 1);
        }
        if (this.rand.nextInt(99) < 49) {
            this.dropItem(Item.getItemFromBlock((Block)VanillaBlocks.watcherStatue), 1);
        }
    }

    @Override
    public String mobName() {
        return "The Watcher";
    }

    @Override
    public String name() {
        return "The Watcher";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

