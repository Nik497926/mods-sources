/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGFlying;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.projectile.EntityDissimentShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityDissiment
extends EntityDivineRPGFlying {
    private static final double spawnLayer = 4.0;
    public int courseChangeCooldown = 0;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity = null;
    private int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;

    public EntityDissiment(World par1World) {
        super(par1World);
        this.isImmuneToFire = true;
        this.experienceValue = 5;
        this.setSize(4.0f, 4.0f);
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 192.0 && this.posY > 144.0 && super.getCanSpawnHere();
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Integer(100));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.dissimentHealth);
    }

    public void onLivingUpdate() {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
            this.setDead();
        }
        if (!this.worldObj.isRemote) {
            this.prevAttackCounter = this.attackCounter;
            if (this.targetedEntity != null && this.targetedEntity.isDead) {
                this.targetedEntity = null;
            }
            if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
                this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 100.0);
                if (this.targetedEntity != null) {
                    this.aggroCooldown = 200;
                }
            }
            double var9 = 128.0;
            if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity((Entity)this) < var9 * var9) {
                double var11 = this.targetedEntity.posX - this.posX;
                double var13 = this.targetedEntity.boundingBox.minY + (double)(this.targetedEntity.height / 2.0f) - (this.posY + (double)(this.height / 2.0f));
                double var15 = this.targetedEntity.posZ - this.posZ;
                this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(var11, var15)) * 180.0f / (float)Math.PI;
                if (this.canEntityBeSeen(this.targetedEntity) && this.attackCounter == 0) {
                    EntityDissimentShot var17 = new EntityDissimentShot(this.worldObj, (EntityLiving)this);
                    double var18 = 4.0;
                    Vec3 var20 = this.getLook(1.0f);
                    var17.posX = this.posX + var20.xCoord * var18;
                    var17.posY = this.posY + (double)(this.height / 2.0f) + 0.5;
                    var17.posZ = this.posZ + var20.zCoord * var18;
                    this.worldObj.spawnEntityInWorld((Entity)var17);
                    this.attackCounter = 5;
                }
                if (this.attackCounter > 0) {
                    --this.attackCounter;
                }
            } else {
                this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0f / (float)Math.PI;
                if (this.attackCounter > 0) {
                    --this.attackCounter;
                }
            }
        }
        super.onLivingUpdate();
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
        return Sounds.dissiment.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.dissimentHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.shinyPearls, 1);
    }

    protected float getSoundVolume() {
        return 10.0f;
    }

    @Override
    public String mobName() {
        return "Dissiment";
    }
}

