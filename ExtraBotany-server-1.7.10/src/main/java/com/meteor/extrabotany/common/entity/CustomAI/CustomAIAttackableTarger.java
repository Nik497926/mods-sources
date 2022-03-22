/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity.CustomAI;

import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import java.util.Comparator;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

public class CustomAIAttackableTarger
extends EntityAITarget {
    private final Class targetClass;
    private final int targetChance;
    private final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
    private final IEntitySelector targetEntitySelector;
    private EntityLivingBase targetEntity;
    private static final String __OBFID = "CL_00001620";

    public CustomAIAttackableTarger(EntityCreature entity, Class clazz, int chance, boolean shouldCheckSight) {
        this(entity, clazz, chance, shouldCheckSight, false);
    }

    public CustomAIAttackableTarger(EntityCreature entity, Class clazz, int chance, boolean shouldCheckSight, boolean nearbyOnly) {
        this(entity, clazz, chance, shouldCheckSight, nearbyOnly, null);
    }

    public CustomAIAttackableTarger(EntityCreature entity, Class clazz, int chance, boolean shouldCheckSight, boolean nearbyOnly, final IEntitySelector p_i1665_6_) {
        super(entity, shouldCheckSight, nearbyOnly);
        this.targetClass = clazz;
        this.targetChance = chance;
        this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(entity);
        this.setMutexBits(1);
        this.targetEntitySelector = new IEntitySelector(){
            private static final String __OBFID = "CL_00001621";

            public boolean isEntityApplicable(Entity entity1) {
                return entity1 instanceof EntityLivingBase && ((p_i1665_6_ == null || p_i1665_6_.isEntityApplicable(entity1)) && CustomAIAttackableTarger.this.isSuitableTarget((EntityLivingBase) entity1, false));
            }
        };
    }

    public boolean shouldExecute() {
        if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
            return false;
        }
        List b = this.taskOwner.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.taskOwner.posX - 4.0, this.taskOwner.posY - 4.0, this.taskOwner.posZ - 4.0, this.taskOwner.posX + 4.0 + 1.0, this.taskOwner.posY + 4.0 + 1.0, this.taskOwner.posZ + 4.0 + 1.0));
        if (b.size() == 0) {
            return false;
        }
        boolean s = false;
        for (int i = 0; i < b.size(); ++i) {
            if (b.get(i) == null || !(b.get(i) instanceof EntityPlayer) || ItemKillerArmor.hasFullArmor((EntityPlayer)b.get(i))) continue;
            this.targetEntity = (EntityLivingBase)b.get(i);
            s = true;
        }
        if (!s) {
            this.targetEntity = null;
        }
        return this.targetEntity != null;
    }

    public void startExecuting() {
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }

    public static class Sorter
    implements Comparator {
        private final Entity theEntity;
        private static final String __OBFID = "CL_00001622";

        public Sorter(Entity entity) {
            this.theEntity = entity;
        }

        public int compare(Entity p_compare_1_, Entity p_compare_2_) {
            double d1;
            double d0 = this.theEntity.getDistanceSqToEntity(p_compare_1_);
            return d0 < (d1 = this.theEntity.getDistanceSqToEntity(p_compare_2_)) ? -1 : (d0 > d1 ? 1 : 0);
        }

        public int compare(Object entity1, Object entity2) {
            return this.compare((Entity)entity1, (Entity)entity2);
        }
    }
}

