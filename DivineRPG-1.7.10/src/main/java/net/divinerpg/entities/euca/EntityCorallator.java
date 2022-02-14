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
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.Direction
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.euca;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.euca.EntityNoExpShimmerer;
import net.divinerpg.entities.euca.projectile.EntityCorallatorProjectile;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCorallator
extends EntityDivineRPGBoss {
    private int spawnTimer;
    private double waypointX;
    private double waypointY;
    private double waypointZ;
    private int courseChangeCooldown;
    private int prevAttackCounter = 0;
    private Entity targetedEntity = null;
    private int aggroCooldown = 0;
    private int attackCounter = 0;

    public EntityCorallator(World par1World) {
        super(par1World);
        this.tasks.addTask(1, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 32.0f));
        this.setSize(7.0f, 4.0f);
        this.spawnTimer = 0;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.corallatorHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.corallatorDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.corallatorSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.corallatorFollowRange);
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

    public String getLivingSound() {
        return "mob.wither.idle";
    }

    protected String getHurtSound() {
        return "mob.wither.hurt";
    }

    protected String getDeathSound() {
        return "mob.wither.death";
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.prevAttackCounter = this.attackCounter;
        double var1 = this.waypointX - this.posX;
        double var3 = this.waypointY - this.posY;
        double var5 = this.waypointZ - this.posZ;
        double var7 = MathHelper.sqrt_double((double)(var1 * var1 + var3 * var3 + var5 * var5));
        if (this.worldObj.isRemote) {
            if (this.rand.nextInt(24) == 0 && !this.isSneaking()) {
                this.worldObj.playSound(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, "fire.fire", 1.0f + this.rand.nextFloat(), this.rand.nextFloat() * 0.7f + 0.3f, false);
            }
            for (int i = 0; i < 2; ++i) {
                this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
            }
        }
        if (!this.onGround && this.motionY < 0.0) {
            this.motionY *= 0.6;
        }
        if (this.getHealth() <= this.getMaxHealth() / 2.0f) {
            if (this.spawnTimer == 0 && !this.worldObj.isRemote) {
                EntityNoExpShimmerer z = new EntityNoExpShimmerer(this.worldObj);
                z.setLocationAndAngles(this.posX + 3.0, this.posY, this.posZ, this.rand.nextFloat() * 360.0f, 0.0f);
                EntityNoExpShimmerer z1 = new EntityNoExpShimmerer(this.worldObj);
                z1.setLocationAndAngles(this.posX - 3.0, this.posY, this.posZ, this.rand.nextFloat() * 360.0f, 0.0f);
                EntityNoExpShimmerer z2 = new EntityNoExpShimmerer(this.worldObj);
                z2.setLocationAndAngles(this.posX, this.posY, this.posZ + 3.0, this.rand.nextFloat() * 360.0f, 0.0f);
                EntityNoExpShimmerer z3 = new EntityNoExpShimmerer(this.worldObj);
                z3.setLocationAndAngles(this.posX, this.posY, this.posZ - 3.0, this.rand.nextFloat() * 360.0f, 0.0f);
                this.worldObj.spawnEntityInWorld((Entity)z);
                this.worldObj.spawnEntityInWorld((Entity)z1);
                this.worldObj.spawnEntityInWorld((Entity)z2);
                this.worldObj.spawnEntityInWorld((Entity)z3);
                this.spawnTimer = 640;
            }
            --this.spawnTimer;
        }
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
            double tz = this.targetedEntity.posZ - this.posZ;
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(tx, tz)) * 180.0f / (float)Math.PI;
            if (this.canEntityBeSeen(this.targetedEntity)) {
                ++this.attackCounter;
                if (this.attackCounter == 20) {
                    this.attackEntityWithRangedAttack(this.targetedEntity);
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

    public void onDeath(DamageSource damage) {
        this.worldObj.setBlock((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0), (Block)Blocks.chest);
        IInventory te = (IInventory)this.worldObj.getTileEntity((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0));
        te.setInventorySlotContents(0, new ItemStack(JourneyItemsOther.celestiumIngot, 4));
        te.setInventorySlotContents(3, new ItemStack(JourneyItemsOther.celestiumBlade, 1));
        if (this.rand.nextInt(99) < 50) {
            te.setInventorySlotContents(2, new ItemStack(JourneyItemsWeapon.coreMender, 1));
        }
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
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

    public void attackEntityWithRangedAttack(Entity e) {
        this.launchWitherSkullToEntity(e, 0);
    }

    private void launchWitherSkullToEntity(Entity e, int var1) {
        this.launchWitherSkullToCoords(0, e.posX, e.posY + (double)e.getEyeHeight() * 0.5, e.posZ, var1 == 0 && this.rand.nextFloat() < 0.001f);
    }

    private void launchWitherSkullToCoords(int var1, double f2, double f4, double f6, boolean f8) {
        double d3 = this.coordX(var1);
        double d4 = this.coordY(var1);
        double d5 = this.coordZ(var1);
        double d6 = f2 - d3;
        double d7 = f4 - d4;
        double d8 = f6 - d5;
        EntityCorallatorProjectile skull = new EntityCorallatorProjectile(this.worldObj, (EntityLivingBase)this, d6, d7, d8);
        skull.posY = d4;
        skull.posX = d3;
        skull.posZ = d5;
        this.worldObj.spawnEntityInWorld((Entity)skull);
    }

    @Override
    public String name() {
        return "Corallator";
    }

    @Override
    public String mobName() {
        return "Corallator";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }

    private double coordX(int par1) {
        if (par1 <= 0) {
            return this.posX;
        }
        float f = (this.renderYawOffset + (float)(180 * (par1 - 1))) / 180.0f * (float)Math.PI;
        float f1 = MathHelper.cos((float)f);
        return this.posX + (double)f1 * 1.3;
    }

    private double coordY(int par1) {
        return par1 <= 0 ? this.posY + 3.0 : this.posY + 2.2;
    }

    private double coordZ(int par1) {
        if (par1 <= 0) {
            return this.posZ;
        }
        float f = (this.renderYawOffset + (float)(180 * (par1 - 1))) / 180.0f * (float)Math.PI;
        float f1 = MathHelper.sin((float)f);
        return this.posZ + (double)f1 * 1.3;
    }
}

