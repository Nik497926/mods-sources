/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.euca;

import net.divinerpg.entities.base.EntityDivineRPGFlying;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityNoExpShimmerer
extends EntityDivineRPGFlying {
    public int courseChangeCooldown = 0;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity = null;
    private int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;

    public EntityNoExpShimmerer(World var1) {
        super(var1);
        this.experienceValue = 0;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.shimmererHealth + 60.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.shimmererSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.shimmererFollowRange);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }

    public void onUpdate() {
        super.onUpdate();
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
        return Sounds.getSoundName(Sounds.shimmerer);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.shimmererHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.shimmererHurt);
    }

    @Override
    public String mobName() {
        return "CorallatorShimmerer";
    }

    public int getMaxSpawnedInChunk() {
        return 1;
    }
}

