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

public class EntityHellBot
extends EntityDivineRPGMob {
    public EntityHellBot(World par1World) {
        super(par1World);
        this.addAttackingAI();
        this.isImmuneToFire = true;
        this.setSize(0.7f, 1.2f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.hellBotHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.hellBotDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.hellBotSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.hellBotFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.robot);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.robotHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.robotDeath);
    }

    protected void dropFewItems(boolean b, int j) {
        if (this.rand.nextInt(100) < 70) {
            this.dropItem(JourneyItemsOther.flamingSpring, 1);
        }
        if (this.rand.nextInt(100) < 30) {
            this.dropItem(JourneyItemsOther.flamingSpring, 2);
        }
    }

    @Override
    public String mobName() {
        return "HellBot";
    }
}

