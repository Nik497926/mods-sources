/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsCrops;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMegalith
extends EntityDivineRPGMob {
    public EntityMegalith(World var1) {
        super(var1);
        this.setSize(1.0f, 3.2f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.megalithHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.megalithDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.megalithSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.megalithFollowRange);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    public boolean attackEntityAsMob(Entity e) {
        boolean attack = super.attackEntityAsMob(e);
        if (attack) {
            if (e instanceof EntityLivingBase) {
                ((EntityLivingBase)e).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 2));
            }
            e.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * 1.5f), 0.1, (double)(MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * 1.5f));
        }
        return attack;
    }

    public int getTotalArmorValue() {
        return 10;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.megalith);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.megalithHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.megalithHurt);
    }

    public void dropFewItems(boolean beenHit, int lootingLevel) {
        this.dropItem(TwilightItemsOther.skythernSoul, this.rand.nextInt(2 + lootingLevel));
        if (this.rand.nextBoolean()) {
            this.dropItem(TwilightItemsCrops.skyPlantSeeds, this.rand.nextInt(3));
        }
    }

    @Override
    public String mobName() {
        return "Megalith";
    }
}

