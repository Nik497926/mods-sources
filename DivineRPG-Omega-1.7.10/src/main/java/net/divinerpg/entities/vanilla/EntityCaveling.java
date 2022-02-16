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

public class EntityCaveling
extends EntityDivineRPGMob {
    public EntityCaveling(World par1World) {
        super(par1World);
        this.setSize(1.0f, 2.0f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.cavelingHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.cavelingDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.cavelingSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.cavelingFollowRange);
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
        if (this.rand.nextInt(99) < 10) {
            this.dropItem(JourneyItemsOther.caveCrystal, 1);
        }
        if (this.rand.nextInt(99) < 80) {
            this.dropItem(JourneyItemsOther.caveDust, 1);
        }
        if (this.rand.nextInt(99) < 50) {
            this.dropItem(JourneyItemsOther.caveDust, 3);
        }
        if (this.rand.nextInt(99) < 40) {
            this.dropItem(JourneyItemsOther.stoneClump, 2);
        }
        if (this.rand.nextInt(99) < 20) {
            this.dropItem(JourneyItemsOther.caveDust, 6);
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
        return "Caveling";
    }
}

