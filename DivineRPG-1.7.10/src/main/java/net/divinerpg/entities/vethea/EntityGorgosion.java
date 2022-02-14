/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGorgosion
extends EntityDivineRPGMob {
    public EntityGorgosion(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.gorgosionHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.gorgosionSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.gorgosionFollowRange);
    }

    protected void fall(float par1) {
        if (par1 > 2.0f) {
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 3.0f, false);
            return;
        }
        super.fall(par1);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 10.0) != null && this.rand.nextInt(30) == 0) {
            this.addVelocity(0.0, 1.0, 0.0);
        }
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getLivingSound() {
        return Sounds.gorgosion.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.gorgosionHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (source.isExplosion()) {
            return false;
        }
        return super.attackEntityFrom(source, par2);
    }

    protected void dropFewItems(boolean par1, int par2) {
    }

    @Override
    public String mobName() {
        return "Gorgosion";
    }
}

