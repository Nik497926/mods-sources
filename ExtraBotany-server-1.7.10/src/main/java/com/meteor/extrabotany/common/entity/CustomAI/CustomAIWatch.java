/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity.CustomAI;

import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

public class CustomAIWatch
extends EntityAIBase {
    private final EntityLiving theWatcher;
    protected Entity closestEntity;
    private final float maxDistanceForPlayer;
    private int lookTime;
    private final float field_75331_e;
    private final Class watchedClass;
    private static final String __OBFID = "CL_00001592";

    public CustomAIWatch(EntityLiving entity, Class clazz, float distance) {
        this.theWatcher = entity;
        this.watchedClass = clazz;
        this.maxDistanceForPlayer = distance;
        this.field_75331_e = 0.02f;
        this.setMutexBits(2);
    }

    public CustomAIWatch(EntityLiving entity, Class clazz, float distance, float chance) {
        this.theWatcher = entity;
        this.watchedClass = clazz;
        this.maxDistanceForPlayer = distance;
        this.field_75331_e = chance;
        this.setMutexBits(2);
    }

    public boolean shouldExecute() {
        if (this.theWatcher.getRNG().nextFloat() >= this.field_75331_e) {
            return false;
        }
        List b = this.theWatcher.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.theWatcher.posX - (double)this.maxDistanceForPlayer, this.theWatcher.posY - (double)this.maxDistanceForPlayer, this.theWatcher.posZ - (double)this.maxDistanceForPlayer, this.theWatcher.posX + (double)this.maxDistanceForPlayer + 1.0, this.theWatcher.posY + (double)this.maxDistanceForPlayer + 1.0, this.theWatcher.posZ + (double)this.maxDistanceForPlayer + 1.0));
        if (b.size() == 0) {
            return false;
        }
        boolean s = false;
        for (int i = 0; i < b.size(); ++i) {
            if (b.get(i) == null || !(b.get(i) instanceof EntityPlayer) || ItemKillerArmor.hasFullArmor((EntityPlayer)b.get(i))) continue;
            this.closestEntity = (Entity)b.get(i);
            s = true;
        }
        if (!s) {
            this.closestEntity = null;
        }
        return this.closestEntity != null;
    }

    public boolean continueExecuting() {
        return this.closestEntity.isEntityAlive() && (!(this.theWatcher.getDistanceSqToEntity(this.closestEntity) > (double) (this.maxDistanceForPlayer * this.maxDistanceForPlayer)) && this.lookTime > 0);
    }

    public void startExecuting() {
        this.lookTime = 40 + this.theWatcher.getRNG().nextInt(40);
    }

    public void resetTask() {
        this.closestEntity = null;
    }

    public void updateTask() {
        this.theWatcher.getLookHelper().setLookPosition(this.closestEntity.posX, this.closestEntity.posY + (double)this.closestEntity.getEyeHeight(), this.closestEntity.posZ, 10.0f, (float)this.theWatcher.getVerticalFaceSpeed());
        --this.lookTime;
    }
}

