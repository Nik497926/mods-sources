/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTocaxin
extends VetheaMob {
    public int hurtTimer;

    public EntityTocaxin(World var1) {
        super(var1);
        this.addAttackingAI();
        this.hurtTimer = 0;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.tocaxinHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.tocaxinDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.tocaxinSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.tocaxinFollowRange);
    }

    @Override
    public int getSpawnLayer() {
        return 3;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityLivingBase var1 = this.getAttackTarget();
        if (var1 == null || var1.getDistanceToEntity((Entity)this) > 8.0f || this.hurtTimer != 0) {
            --this.hurtTimer;
            return;
        }
        this.hurtTimer = 10;
        var1.attackEntityFrom(DamageSource.magic, (float)((int)(8.0f - var1.getDistanceToEntity((Entity)this))));
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.tocaxin.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.tocaxinHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.polishedPearls, 1);
    }

    @Override
    public String mobName() {
        return "Tocxin";
    }
}

