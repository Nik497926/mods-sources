/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.depths;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityDepthsBeast
extends EntityDivineRPGMob {
    public EntityDepthsBeast(World par1World) {
        super(par1World);
        this.addAttackingAI();
        this.setSize(1.2f, 2.5f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.depthsBeastHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.depthsBeastDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.depthsBeastSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.depthsBeastFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.magmaGiant);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.spyclopsHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.spyclopsHurt);
    }

    public void onLivingUpdate() {
        EntityPlayer p;
        super.onLivingUpdate();
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float f = this.getBrightness(1.0f);
        }
        if ((p = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0)) != null) {
            Vec3 lookVec = p.getLook(1.0f).normalize();
            Vec3 lookAtMeVec = Vec3.createVectorHelper((double)(this.posX - p.posX), (double)(this.boundingBox.minY + (double)this.height - (p.posY + (double)p.getEyeHeight())), (double)(this.posZ - p.posZ));
            double distMagnitude = lookAtMeVec.lengthVector();
            double var7 = lookVec.dotProduct(lookAtMeVec = lookAtMeVec.normalize());
            if (var7 > 1.0 - 0.025 / distMagnitude && p.canEntityBeSeen((Entity)this)) {
                p.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 0, true));
            }
        }
    }

    protected Item getDropItem() {
        return VanillaItemsOther.crabClaw;
    }

    @Override
    public String mobName() {
        return "DepthsBeast";
    }
}

