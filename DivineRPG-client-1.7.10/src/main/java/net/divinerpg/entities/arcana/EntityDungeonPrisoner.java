/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.arcana.EntityDungeonDemon;
import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityDungeonPrisoner
extends EntityDivineRPGMob {
    public EntityDungeonPrisoner(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.dungeonPrisonerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.dungeonPrisonerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.dungeonPrisonerFollowRange);
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        EntityDungeonDemon var4 = new EntityDungeonDemon(this.worldObj);
        this.worldObj.playSoundAtEntity((Entity)this, Sounds.dungeonChange.getPrefixedName(), 1.0f, 1.0f);
        var4.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0f);
        this.worldObj.spawnEntityInWorld((Entity)var4);
        this.setDead();
        return true;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.dungeonPrisoner.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.dungeonHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return Sounds.dungeonHurt.getPrefixedName();
    }

    protected void dropFewItems(boolean var1, int var2) {
        this.dropItem(ArcanaItems.collector, 1);
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 40.0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    @Override
    public String mobName() {
        return "Dungeon Prisoner";
    }
}

