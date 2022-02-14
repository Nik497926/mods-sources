/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityConstructor
extends EntityDivineRPGMob {
    private int angerLevel;
    private float moveSpeed = 0.23f;

    public EntityConstructor(World par1World) {
        super(par1World);
        this.setSize(0.5f, 1.0f);
        this.addAttackingAI();
        this.angerLevel = 0;
        this.stepHeight = 1.0f;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.constructorHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.constructorDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.constructorSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.constructorFollowRange);
    }

    public void onLivingUpdate() {
        if (this.entityToAttack != null) {
            this.faceEntity(this.entityToAttack, 100.0f, 100.0f);
        }
        if (!this.worldObj.isRemote && this.isEntityAlive() && this.entityToAttack != null && this.entityToAttack instanceof EntityPlayer && this.angerLevel < 3) {
            this.moveForward = 0.0f;
            this.moveStrafing = 0.0f;
            this.moveSpeed = 0.0f;
        }
        super.onLivingUpdate();
    }

    protected String getLivingSound() {
        return null;
    }

    protected String getHurtSound() {
        return Sounds.constructorHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return null;
    }

    public void dropFewItems(boolean beenHit, int lootingLevel) {
        if (this.rand.nextBoolean()) {
            return;
        }
        switch (this.rand.nextInt(4)) {
            case 1: {
                this.dropItem(ArcanaItems.sludgeKey, 1);
                break;
            }
            case 2: {
                this.dropItem(ArcanaItems.ancientKey, 1);
                break;
            }
            case 3: {
                this.dropItem(ArcanaItems.soulKey, 1);
            }
        }
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        this.worldObj.playSoundAtEntity((Entity)this, Sounds.constructorPunch.getPrefixedName(), 1.0f, 1.0f);
        return super.attackEntityAsMob(par1Entity);
    }

    @Override
    public String mobName() {
        return "Dungeon Constructor";
    }
}

