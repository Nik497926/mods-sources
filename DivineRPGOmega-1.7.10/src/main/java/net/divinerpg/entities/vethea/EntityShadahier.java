/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityShadahier
extends EntityDivineRPGMob {
    public EntityShadahier(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.shadahierHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.shadahierDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.shadahierSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.shadahierFollowRange);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 6.0);
        if (var1 != null) {
            var1.addPotionEffect(new PotionEffect(Potion.blindness.id, 1, 1));
        }
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getLivingSound() {
        return Sounds.shadahier.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.shadahierHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
    }

    @Override
    public String mobName() {
        return "Shadahier";
    }
}

