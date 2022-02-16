/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.world.World;

public class EntityTheGrue
extends EntityDivineRPGMob {
    public EntityTheGrue(World par1World) {
        super(par1World);
        this.setSize(0.8f, 2.0f);
        this.addAttackingAI();
        this.tasks.addTask(0, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.6f));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.theGrueHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.theGrueDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.theGrueSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.theGrueFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.deathcryx);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.deathcryxHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.deathcryxHurt);
    }

    public void dropFewItems(boolean beenHit, int lootingLevel) {
        this.dropItem(VanillaItemsOther.arlemiteIngot, 1);
        if (this.rand.nextBoolean()) {
            this.dropItem(VanillaItemsOther.corruptedShards, 3);
        }
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY <= 16.0 && super.getCanSpawnHere();
    }

    @Override
    public String mobName() {
        return "The Grue";
    }
}

