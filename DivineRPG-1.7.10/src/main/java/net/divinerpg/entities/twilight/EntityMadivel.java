/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMadivel
extends EntityDivineRPGMob {
    public EntityMadivel(World var1) {
        super(var1);
        this.addBasicAI();
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.madivelHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.madivelDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.madivelSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.madivelFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.madivel);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.madivelHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.madivelHurt);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.edenSoul;
    }

    protected void dropFewItems(boolean var1, int var2) {
        super.dropFewItems(var1, var2);
        if (this.isBurning()) {
            this.dropItem(ItemsFood.empoweredMeat, 1);
        } else {
            this.dropItem(ItemsFood.rawEmpoweredMeat, 1);
        }
    }

    @Override
    public String mobName() {
        return "Madivel";
    }
}

