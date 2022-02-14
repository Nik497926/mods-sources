/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityTheEye
extends EntityDivineRPGMob {
    private boolean hasPotion = false;

    public EntityTheEye(World par1World) {
        super(par1World);
        this.addAttackingAI();
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.theEye);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.theEyeHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.theEyeHurt);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.theEyeHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.theEyeDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.theEyeSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.theEyeFollowRange);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityPlayer p = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0);
        if (p != null) {
            Vec3 lookVec = p.getLook(1.0f).normalize();
            Vec3 lookAtMeVec = Vec3.createVectorHelper((double)(this.posX - p.posX), (double)(this.boundingBox.minY + (double)this.height - (p.posY + (double)p.getEyeHeight())), (double)(this.posZ - p.posZ));
            double distMagnitude = lookAtMeVec.lengthVector();
            double var7 = lookVec.dotProduct(lookAtMeVec = lookAtMeVec.normalize());
            if (var7 > 1.0 - 0.025 / distMagnitude && p.canEntityBeSeen((Entity)this)) {
                p.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 0, true));
                p.triggerAchievement((StatBase)DivineRPGAchievements.eyeOfEvil);
            }
        }
    }

    protected Item getDropItem() {
        return Item.getItemFromBlock((Block)Blocks.torch);
    }

    protected void dropFewItems(boolean par1, int par2) {
        int torchRand = this.rand.nextInt(2) + 1;
        this.dropItem(VanillaItemsOther.rupeeIngot, 1);
        this.dropItem(Item.getItemFromBlock((Block)Blocks.torch), 16 * torchRand);
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY <= 16.0 && super.getCanSpawnHere();
    }

    @Override
    public String mobName() {
        return "The Eye";
    }
}

