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
package net.divinerpg.entities.iceika;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.IceikaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFrosty
extends EntityPeacefulUntilAttacked {
    public EntityFrosty(World w) {
        super(w);
        this.setSize(1.0f, 2.5f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.frostyHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.frostyDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.frostySpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.frostyFollowRange);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean attack = super.attackEntityAsMob(entity);
        if (attack && entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 2, true));
            entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f))) * 2.5 * 0.5, 0.1, (double)MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * 2.5 * 0.5);
        }
        return attack;
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.frosty);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.frostyHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.frostyHurt);
    }

    public void dropFewItems(boolean beenHit, int lootingLevel) {
        this.dropItem(IceikaItems.peppermints, this.rand.nextInt(3));
        this.dropItem(IceikaItems.iceShards, this.rand.nextInt(4));
    }

    @Override
    public String mobName() {
        return "Frosty";
    }

    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }
}

