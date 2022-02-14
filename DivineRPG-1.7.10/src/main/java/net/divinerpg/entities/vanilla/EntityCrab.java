/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityCrab
extends EntityPeacefulUntilAttacked {
    public EntityCrab(World var1) {
        super(var1);
        this.setSize(1.0f, 1.0f);
        this.experienceValue = 40;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.crabHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.crabDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.crabSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.crabFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.crab);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.crabHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.crabHurt);
    }

    protected void dropFewItems(boolean var1, int looting) {
        this.dropItem(VanillaItemsOther.crabClaw, this.rand.nextInt(2 + looting));
    }

    @Override
    public String mobName() {
        return "Crab";
    }
}

