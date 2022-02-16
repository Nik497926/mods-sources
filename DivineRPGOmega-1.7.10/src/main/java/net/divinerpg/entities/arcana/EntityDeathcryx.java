/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDeathcryx
extends EntityDivineRPGMob {
    public EntityDeathcryx(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.deathcryxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.deathcryxDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.deathcryxSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.deathcryxFollowRange);
    }

    @Override
    public String mobName() {
        return "Deathcryx";
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getLivingSound() {
        return Sounds.deathcryx.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.deathcryxHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return Sounds.deathcryxHurt.getPrefixedName();
    }

    protected Item getDropItem() {
        return ArcanaItems.collector;
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean var4;
        float var2 = (float)EntityStats.deathcryxDamage;
        if (this.isPotionActive(Potion.damageBoost)) {
            var2 += (float)(3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier());
        }
        if (this.isPotionActive(Potion.weakness)) {
            var2 -= (float)(2 << this.getActivePotionEffect(Potion.weakness).getAmplifier());
        }
        int var3 = 0;
        if (par1Entity instanceof EntityLivingBase) {
            var2 += EnchantmentHelper.getEnchantmentModifierLiving((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)par1Entity));
            var3 += EnchantmentHelper.getKnockbackModifier((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)par1Entity));
            ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 320, 2));
        }
        if (var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), var2)) {
            int var5;
            if (var3 > 0) {
                par1Entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f), 0.1, (double)(MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f));
                this.motionX *= 0.6;
                this.motionZ *= 0.6;
            }
            if ((var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this)) > 0) {
                par1Entity.setFire(var5 * 4);
            }
        }
        return var4;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 40.0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }
}

