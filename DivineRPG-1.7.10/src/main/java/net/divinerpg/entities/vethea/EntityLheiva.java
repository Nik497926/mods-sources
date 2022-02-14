/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityLheiva
extends VetheaMob {
    public EntityLheiva(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.lheviaHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.lheviaDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.lheviaSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.lheviaFollowRange);
    }

    @Override
    public int getSpawnLayer() {
        return 3;
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getLivingSound() {
        return Sounds.lhevia.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.lheviaHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    public boolean attackEntityFrom(DamageSource source, float par2) {
        Entity var1 = source.getEntity();
        if (var1 != null && var1 instanceof EntityPlayer ? ((EntityPlayer)var1).inventory.hasItem(VetheaItems.heivaBelt) : source == DamageSource.outOfWorld) {
            return super.attackEntityFrom(source, par2);
        }
        return false;
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.polishedPearls, 1);
    }

    @Override
    public String mobName() {
        return "Lheiva";
    }
}

