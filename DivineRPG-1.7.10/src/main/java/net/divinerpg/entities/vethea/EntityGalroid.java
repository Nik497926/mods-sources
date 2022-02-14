/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGalroid
extends EntityDivineRPGMob {
    public EntityGalroid(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(19, (Object)200);
        this.dataWatcher.addObject(20, (Object)1);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    public boolean getInvincible() {
        return this.dataWatcher.getWatchableObjectInt(20) == 1;
    }

    public int getProtectionTimer() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    public void setInvincible(int i) {
        this.dataWatcher.updateObject(20, (Object)i);
    }

    public void setProtectionTimer(int i) {
        this.dataWatcher.updateObject(19, (Object)i);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.galroidHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.galroidDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.galroidSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.galroidFollowRange);
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getLivingSound() {
        return Sounds.galroid.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.galroidHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        int var2 = 10;
        boolean var3 = false;
        boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)var2);
        if (var4) {
            par1Entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f), 0.1, (double)(MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f));
            this.motionX *= 0.6;
            this.motionZ *= 0.6;
            this.heal(40.0f);
        }
        return var4;
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    protected void dropFewItems(boolean par1, int par2) {
        if (this.rand.nextInt(99) < 20 + par2) {
            this.dropItem(JourneyItemsOther.boilChunk, 1);
        }
    }

    @Override
    public String mobName() {
        return "Galroid";
    }
}

