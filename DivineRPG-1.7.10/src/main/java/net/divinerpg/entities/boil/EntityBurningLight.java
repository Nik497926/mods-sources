/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.boil;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBurningLight
extends EntityDivineRPGMob {
    public EntityBurningLight(World w) {
        super(w);
        this.addAttackingAI();
        this.setSize(0.7f, 2.0f);
        this.isImmuneToFire = true;
    }

    public ItemStack getHeldItem() {
        return new ItemStack(VanillaItemsWeapons.enderFlameSword, 1);
    }

    protected String getLivingSound() {
        return "mob.blaze.breathe";
    }

    protected String getHurtSound() {
        return "mob.blaze.hit";
    }

    protected String getDeathSound() {
        return "mob.blaze.death";
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.burningLightHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.burningLightDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.burningLightSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.burningLightFollowRange);
    }

    protected void dropFewItems(boolean b, int j) {
        if (this.rand.nextInt(99) < 20 + j) {
            this.dropItem(JourneyItemsOther.boilChunk, 1);
        }
    }

    @Override
    public String mobName() {
        return "Burning Light";
    }
}

