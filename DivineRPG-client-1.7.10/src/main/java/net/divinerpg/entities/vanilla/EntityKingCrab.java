/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityKingCrab
extends EntityPeacefulUntilAttacked {
    public EntityKingCrab(World var1) {
        super(var1);
        this.setSize(1.55f, 1.25f);
        this.experienceValue = 40;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.kingCrabHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.kingCrabDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.kingCrabSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.kingCrabFollowRange);
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.crab);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.crabHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.crabHurt);
    }

    protected void dropFewItems(boolean var1, int var2) {
        this.dropItem(VanillaItemsOther.crabClaw, this.rand.nextInt(2 + var2));
        this.dropItem(VanillaItemsOther.aquaticPellets, this.rand.nextInt(2 + var2) * 3);
    }

    protected Item getDropItem() {
        return VanillaItemsOther.crabClaw;
    }

    @Override
    public String mobName() {
        return "King Crab";
    }
}

