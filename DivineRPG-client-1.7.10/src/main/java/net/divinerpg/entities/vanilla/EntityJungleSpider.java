/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityJungleSpider
extends EntityDivineRPGMob {
    public EntityJungleSpider(World var1) {
        super(var1);
        this.setSize(1.4f, 0.9f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.jungleSpiderHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.jungleSpiderDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.jungleSpiderSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.jungleSpiderFollowRange);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte)0);
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote) {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }

    public double getMountedYOffset() {
        return (double)this.height * 0.75 - 0.5;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected Entity findPlayerToAttack() {
        float var1 = this.getBrightness(1.0f);
        if (var1 < 1.0f) {
            double var2 = 16.0;
            return this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, var2);
        }
        return null;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.jungleSpider);
    }

    @Override
    protected String getHurtSound() {
        return "mob.spider.say";
    }

    @Override
    protected String getDeathSound() {
        return "mob.spider.death";
    }

    protected void attackEntity(Entity var1, float var2) {
        if (var2 > 2.0f && var2 < 6.0f && this.rand.nextInt(10) == 0) {
            if (this.onGround) {
                double var3 = var1.posX - this.posX;
                double var5 = var1.posZ - this.posZ;
                float var7 = MathHelper.sqrt_double((double)(var3 * var3 + var5 * var5));
                this.motionX = var3 / (double)var7 * 0.5 * (double)0.8f + this.motionX * (double)0.2f;
                this.motionZ = var5 / (double)var7 * 0.5 * (double)0.8f + this.motionZ * (double)0.2f;
                this.motionY = 0.4f;
            }
        } else {
            super.attackEntity(var1, var2);
        }
    }

    public boolean attackEntityAsMob(Entity entity) {
        boolean var4;
        int var2 = (int)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        if (this.isPotionActive(Potion.damageBoost)) {
            var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }
        if (this.isPotionActive(Potion.weakness)) {
            var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }
        int var3 = 0;
        if (entity instanceof EntityLiving) {
            var2 = (int)((float)var2 + EnchantmentHelper.getEnchantmentModifierLiving((EntityLivingBase)this, (EntityLivingBase)((EntityLiving)entity)));
            var3 += EnchantmentHelper.getKnockbackModifier((EntityLivingBase)this, (EntityLivingBase)((EntityLiving)entity));
        }
        if (var4 = entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)var2)) {
            if (var3 > 0) {
                entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f), 0.1, (double)(MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f));
                this.motionX *= 0.6;
                this.motionZ *= 0.6;
            }
            int var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this);
            if (entity instanceof EntityLivingBase) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
            }
            if (var5 > 0) {
                entity.setFire(var5 * 4);
            }
        }
        return var4;
    }

    protected void dropFewItems(boolean var1, int var2) {
        int var4;
        int var3 = this.rand.nextInt(2 + var2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.dropItem(VanillaItemsOther.jungleShards, 1);
        }
        var3 = this.rand.nextInt(2 + var2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.dropItem(VanillaItemsOther.jungleShards, 2);
        }
    }

    public boolean isOnLadder() {
        return this.isBesideClimbableBlock();
    }

    public void setInWeb() {
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public boolean isBesideClimbableBlock() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean var1) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);
        var2 = var1 ? (byte)(var2 | 1) : (byte)(var2 & 0xFFFFFFFE);
        this.dataWatcher.updateObject(16, (Object)var2);
    }

    @Override
    public String mobName() {
        return "Jungle Spider";
    }
}

