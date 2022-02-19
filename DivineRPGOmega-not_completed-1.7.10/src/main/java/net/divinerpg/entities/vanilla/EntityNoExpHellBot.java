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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityNoExpHellBot
extends EntityDivineRPGMob {
    public EntityNoExpHellBot(World par1World) {
        super(par1World);
        this.addAttackingAI();
        this.isImmuneToFire = true;
        this.experienceValue = 0;
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

    @Override
    public String mobName() {
        return "DungeonBot";
    }
}

