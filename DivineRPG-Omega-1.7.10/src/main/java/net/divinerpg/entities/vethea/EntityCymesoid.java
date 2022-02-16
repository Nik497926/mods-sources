/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.Vec3
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
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityCymesoid
extends EntityDivineRPGMob {
    public EntityCymesoid(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.cymesoidHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.cymesoidDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.cymesoidSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.cymesoidFollowRange);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0);
        if (var1 == null) {
            return;
        }
        Vec3 var3 = var1.getLook(1.0f).normalize();
        Vec3 var4 = Vec3.createVectorHelper((double)(this.posX - var1.posX), (double)(this.boundingBox.minY + (double)(this.height / 2.0f) - (var1.posY + (double)var1.getEyeHeight())), (double)(this.posZ - var1.posZ));
        double var5 = var4.lengthVector();
        double var7 = var3.dotProduct(var4 = var4.normalize());
        if (var7 > 1.0 - 0.025 / var5 && var1.canEntityBeSeen((Entity)this)) {
            this.addPotionEffect(new PotionEffect(Potion.invisibility.id, 200, 1));
        }
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getLivingSound() {
        return Sounds.cymesoid.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.cymesoidHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
    }

    @Override
    public String mobName() {
        return "Cymesoid";
    }
}

