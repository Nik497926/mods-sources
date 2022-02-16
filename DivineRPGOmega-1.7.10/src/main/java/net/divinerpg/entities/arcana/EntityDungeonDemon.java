/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityDungeonDemon
extends EntityDivineRPGMob {
    public EntityDungeonDemon(World par1World) {
        super(par1World);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.dungeonPrisonerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.dungeonPrisonerDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.dungeonPrisonerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.dungeonPrisonerFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.dungeonPrisoner.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.dungeonHurt.getPrefixedName();
    }

    protected void dropFewItems(boolean var1, int var2) {
        this.dropItem(ArcanaItems.collector, 1);
    }

    protected String getDeathSound() {
        return Sounds.dungeonHurt.getPrefixedName();
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 40.0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    @Override
    public String mobName() {
        return "Dungeon Demon";
    }
}

