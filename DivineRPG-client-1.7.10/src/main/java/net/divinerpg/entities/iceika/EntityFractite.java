/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.iceika;

import net.divinerpg.entities.base.EntityDivineRPGFlying;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.iceika.projectile.EntityFractiteShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.IceikaItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityFractite
extends EntityDivineRPGFlying {
    public int courseChangeCooldown = 0;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity = null;
    private int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;
    private ChunkCoordinates currentFlightTarget;

    public EntityFractite(World var1) {
        super(var1);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 32.0f));
        this.tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.setSize(1.7f, 1.3f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.fractiteHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.fractiteSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.fractiteFollowRange);
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
            this.setDead();
        }
        this.prevAttackCounter = this.attackCounter;
        double var1 = this.waypointX - this.posX;
        double var3 = this.waypointY - this.posY;
        double var5 = this.waypointZ - this.posZ;
        double var7 = var1 * var1 + var3 * var3 + var5 * var5;
        if (var7 < 1.0 || var7 > 3600.0) {
            this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f);
            this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f);
            this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f);
        }
        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7 = (double)MathHelper.sqrt_double((double)var7))) {
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
        double var9 = 64.0;
        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity((Entity)this) < var9 * var9) {
            double var11 = this.targetedEntity.posX - this.posX;
            double var13 = this.targetedEntity.boundingBox.minY - 5.0 - this.posY;
            double var15 = this.targetedEntity.posZ - this.posZ;
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(var11, var15)) * 180.0f / (float)Math.PI;
            if (this.canEntityBeSeen(this.targetedEntity)) {
                ++this.attackCounter;
                if (this.attackCounter == 20) {
                    if (!this.worldObj.isRemote) {
                        this.worldObj.playSoundAtEntity(this.targetedEntity, Sounds.fractiteAttack.getPrefixedName(), 1.0f, 1.0f);
                    }
                    EntityFractiteShot var17 = new EntityFractiteShot(this.worldObj, (EntityLiving)this, var11, var13, var15);
                    double var18 = 4.0;
                    Vec3 var20 = this.getLook(1.0f);
                    var17.posX = this.posX + var20.xCoord * var18;
                    var17.posY = this.posY + (double)(this.height / 2.0f) + 0.5;
                    var17.posZ = this.posZ + var20.zCoord * var18;
                    if (!this.worldObj.isRemote) {
                        this.worldObj.spawnEntityInWorld((Entity)var17);
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

    @Override
    protected void fall(float par1) {
    }

    @Override
    protected void updateFallState(double par1, boolean par3) {
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
            float var3 = 0.91f;
            if (this.onGround) {
                var3 = 0.54600006f;
            }
            float var8 = 0.16277136f / (var3 * var3 * var3);
            this.moveFlying(par1, par2, this.onGround ? 0.1f * var8 : 0.02f);
            var3 = 0.91f;
            if (this.onGround) {
                var3 = 0.54600006f;
                Block block = this.worldObj.getBlock(MathHelper.floor_double((double)this.posX), MathHelper.floor_double((double)this.boundingBox.minY) - 1, MathHelper.floor_double((double)this.posZ));
            }
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)var3;
            this.motionY *= (double)var3;
            this.motionZ *= (double)var3;
        }
        this.prevRotationYaw = this.rotationYaw;
        double var10 = this.posX - this.prevPosX;
        double var9 = this.posZ - this.prevPosZ;
        float var7 = MathHelper.sqrt_double((double)(var10 * var10 + var9 * var9)) * 4.0f;
        if (var7 > 1.0f) {
            var7 = 1.0f;
        }
        this.rotationYaw += (var7 - this.rotationYaw) * 0.4f;
        this.limbSwing += this.rotationYaw;
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

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.fractite);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.fractiteHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.fractiteHurt);
    }

    protected Item getDropItem() {
        return IceikaItems.iceShards;
    }

    protected void dropFewItems(boolean var1, int var2) {
        int var3 = this.rand.nextInt(2 + var2);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.dropItem(IceikaItems.iceShards, 3);
        }
    }

    @Override
    public String mobName() {
        return "Fractite";
    }
}

