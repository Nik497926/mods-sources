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

public class EntityEdenCadillion
extends EntityDivineRPGMob {
    public EntityEdenCadillion(World var1) {
        super(var1);
        this.setSize(1.0f, 1.3f);
        this.addAttackingAI();
        this.experienceValue = 20;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.edenCadillionHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.edenCadillionDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.edenCadillionSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.edenCadillionFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.cadillion);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected void dropFewItems(boolean var1, int var2) {
        if (this.isBurning()) {
            this.dropItem(ItemsFood.empoweredMeat, 1);
        } else {
            this.dropItem(ItemsFood.rawEmpoweredMeat, 1);
        }
        this.dropItem(TwilightItemsOther.edenSoul, this.rand.nextInt(5) + 1);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.edenSoul;
    }

    public boolean isValidLightLevel() {
        return true;
    }

    @Override
    public String mobName() {
        return "Eden Cadillion";
    }
}

