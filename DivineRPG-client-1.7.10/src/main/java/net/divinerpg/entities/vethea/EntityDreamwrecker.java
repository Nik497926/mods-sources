/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityDreamwrecker
extends VetheaMob {
    public EntityDreamwrecker(World var1) {
        super(var1);
        this.addAttackingAI();
        this.setSize(0.8f, 4.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.dreamWreckerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.dreamWreckerDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.dreamWreckerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.dreamWreckerFollowRange);
    }

    @Override
    public int getSpawnLayer() {
        return 2;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0);
        if (var1 != null && var1.getDistanceToEntity((Entity)this) < 20.0f) {
            this.entityToAttack = var1;
        }
        if (this.getEntityToAttack() != null && this.getEntityToAttack() instanceof EntityPlayer && !this.getEntityToAttack().isDead && this.canEntityBeSeen(this.getEntityToAttack())) {
            this.getEntityToAttack().addVelocity(Math.signum(this.posX - this.getEntityToAttack().posX) * 0.029, 0.0, Math.signum(this.posZ - this.getEntityToAttack().posZ) * 0.029);
        }
        if (this.getEntityToAttack() != null && (this.getEntityToAttack().getDistanceToEntity((Entity)this) >= 20.0f || this.getEntityToAttack().isDead || ((EntityPlayer)this.getEntityToAttack()).capabilities.isCreativeMode)) {
            this.entityToAttack = null;
        }
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.dreamWrecker.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.dreamWreckerHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.cleanPearls, 1);
    }

    @Override
    public String mobName() {
        return "Dreamwrecker";
    }
}

