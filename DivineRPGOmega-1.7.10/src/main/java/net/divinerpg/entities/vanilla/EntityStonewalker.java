/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityStonewalker
extends EntityDivineRPGMob {
    public EntityStonewalker(World par1World) {
        super(par1World);
        this.setSize(0.8f, 1.5f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.stonewalkerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.stonewalkerDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.stonewalkerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.stonewalkerFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.rock);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.caveMob);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.caveMob);
    }

    protected void dropFewItems(boolean b, int j) {
        if (this.rand.nextInt(99) < 30) {
            this.dropItem(JourneyItemsOther.caveCrystal, 1);
        }
        if (this.rand.nextInt(99) < 60) {
            this.dropItem(JourneyItemsOther.caveDust, 1);
        }
        if (this.rand.nextInt(99) < 50) {
            this.dropItem(JourneyItemsOther.caveDust, 3);
        }
        if (this.rand.nextInt(99) < 10) {
            this.dropItem(JourneyItemsOther.sapphire, 4);
        }
        if (this.rand.nextInt(99) < 8) {
            this.dropItem(JourneyItemsOther.shadiumIngot, 2);
        }
        if (this.rand.nextInt(99) < 10) {
            this.dropItem(JourneyItemsOther.luniumIngot, 2);
        }
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 40.0 && super.getCanSpawnHere();
    }

    @Override
    public String mobName() {
        return "Stonewalker";
    }
}

