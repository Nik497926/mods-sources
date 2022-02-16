/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHiveSoldier
extends EntityDivineRPGMob {
    public EntityHiveSoldier(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.hiveSoldierHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.hiveSoldierDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.hiveSoldierSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.hiveSoldierFollowRange);
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.hoverStinger.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.hoverStingerHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected Item getDropItem() {
        return VetheaItems.dirtyPearls;
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean var4;
        int var2 = (int)EntityStats.hiveSoldierDamage;
        if (this.isPotionActive(Potion.damageBoost)) {
            var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }
        if (this.isPotionActive(Potion.weakness)) {
            var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }
        int var3 = 0;
        if (par1Entity instanceof EntityLiving) {
            var2 = (int)((float)var2 + EnchantmentHelper.getEnchantmentModifierLiving((EntityLivingBase)this, (EntityLivingBase)((EntityLiving)par1Entity)));
            var3 += EnchantmentHelper.getKnockbackModifier((EntityLivingBase)this, (EntityLivingBase)((EntityLiving)par1Entity));
            ((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 32, 0));
        }
        if (var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)var2)) {
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
    public String mobName() {
        return "Hive Soldier";
    }
}

