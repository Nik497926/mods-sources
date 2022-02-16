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
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntitySkythernGolem
extends EntityDivineRPGMob {
    public EntitySkythernGolem(World var1) {
        super(var1);
        this.setSize(1.0f, 2.8f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.skythernGolemHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.skythernGolemDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.skythernGolemSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.skythernGolemFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.growl);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.skythernSoul;
    }

    @Override
    public String mobName() {
        return "Skythern Golem";
    }
}

