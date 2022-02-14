/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.init.Items
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityEdenTomo
extends EntityPeacefulUntilAttacked {
    public EntityEdenTomo(World var1) {
        super(var1);
        this.setSize(1.1f, 1.0f);
        this.experienceValue = 40;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.edenTomoHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.edenTomoDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.edenTomoSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.edenTomoFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.croak);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected void dropFewItems(boolean beenHit, int lootingLevel) {
        super.dropFewItems(beenHit, lootingLevel);
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(TwilightItemsOther.edenSoul, this.rand.nextInt(5) + lootingLevel + 1);
        }
        if (this.isBurning()) {
            this.dropItem(ItemsFood.empoweredMeat, 1);
        } else {
            this.dropItem(ItemsFood.rawEmpoweredMeat, 1);
        }
        if (this.rand.nextInt(3) == 0) {
            this.dropItem(Items.gold_ingot, this.rand.nextInt(3) + 1);
        }
    }

    @Override
    public String mobName() {
        return "Eden Tomo";
    }
}

