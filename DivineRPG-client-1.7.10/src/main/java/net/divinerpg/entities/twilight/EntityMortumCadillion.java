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

public class EntityMortumCadillion
extends EntityDivineRPGMob {
    public EntityMortumCadillion(World var1) {
        super(var1);
        this.addAttackingAI();
        this.setSize(1.0f, 1.3f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.mortumCadillionHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.mortumCadillionDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.mortumCadillionSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.mortumCadillionFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.cadillion);
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
        return TwilightItemsOther.mortumSoul;
    }

    @Override
    public String mobName() {
        return "Mortum Cadillion";
    }
}

