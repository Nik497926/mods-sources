/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGalroid
extends VetheaMob {
    public EntityGalroid(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(19, (Object)200);
        this.dataWatcher.addObject(20, (Object)1);
    }

    @Override
    public int getSpawnLayer() {
        return 3;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.getProtectionTimer() <= 0 && !this.getInvincible()) {
            this.setProtectionTimer(200);
            this.setInvincible(1);
        } else if (this.isEntityInvulnerable() && this.getProtectionTimer() <= 0) {
            this.setProtectionTimer(200);
            this.setInvincible(0);
        }
        this.setProtectionTimer(this.getProtectionTimer() - 1);
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

    @Override
    protected String getLivingSound() {
        return Sounds.galroid.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.galroidHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean var4;
        float var2 = 25.0f;
        if (this.isPotionActive(Potion.damageBoost)) {
            var2 += (float)(3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier());
        }
        if (this.isPotionActive(Potion.weakness)) {
            var2 -= (float)(2 << this.getActivePotionEffect(Potion.weakness).getAmplifier());
        }
        int var3 = 0;
        if (par1Entity instanceof EntityLiving) {
            var2 += EnchantmentHelper.getEnchantmentModifierLiving((EntityLivingBase)this, (EntityLivingBase)((EntityLiving)par1Entity));
            var3 += EnchantmentHelper.getKnockbackModifier((EntityLivingBase)this, (EntityLivingBase)((EntityLiving)par1Entity));
        }
        if (var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), var2)) {
            int var5;
            if (var3 > 0) {
                par1Entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f), 0.4, (double)(MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f));
                this.motionX *= 0.6;
                this.motionZ *= 0.6;
            }
            if ((var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this)) > 0) {
                par1Entity.setFire(var5 * 4);
            }
        }
        return var4;
    }

    public boolean isEntityInvulnerable() {
        return this.getInvincible();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.polishedPearls, 1);
    }

    @Override
    public String mobName() {
        return "Galroid";
    }
}

