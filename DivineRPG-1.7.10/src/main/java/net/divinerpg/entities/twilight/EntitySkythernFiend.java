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

public class EntitySkythernFiend
extends EntityDivineRPGMob {
    public EntitySkythernFiend(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.skythernFiendHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.skythernFiendDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.skythernFiendSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.skythernFiendFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.insect);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.skythernSoul;
    }

    @Override
    public String mobName() {
        return "Skythern Fiend";
    }
}

