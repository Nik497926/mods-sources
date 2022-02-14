/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.euca;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityGoldwing
extends EntityPeacefulUntilAttacked {
    public EntityGoldwing(World w) {
        super(w);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.goldwingHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.goldwingDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.goldwingSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.goldwingFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.bird);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.birdHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.birdDeath);
    }

    protected void dropFewItems(boolean b, int j) {
        this.dropItem(ItemsFood.rocMeat, 1);
        if (this.rand.nextInt(99) < 20) {
            this.dropItem(ItemsFood.rocMeat, 1);
        }
        if (this.rand.nextInt(99) < 8) {
            this.dropItem(ItemsFood.rocMeat, 2);
        }
    }

    @Override
    public boolean isValidLightLevel() {
        return true;
    }

    @Override
    public String mobName() {
        return "Roc";
    }
}

