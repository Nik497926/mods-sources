/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.euca;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDynaster
extends EntityDivineRPGMob {
    public EntityDynaster(World par1World) {
        super(par1World);
        this.addAttackingAI();
        this.setSize(1.0f, 1.7f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.dynasterHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.dynasterDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.dynasterSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.dynasterFollowRange);
    }

    public String getLivingSound() {
        return Sounds.getSoundName(Sounds.dynaster);
    }

    public String getHurtSound() {
        return Sounds.getSoundName(Sounds.dynasterHurt);
    }

    public String getDeathSound() {
        return Sounds.getSoundName(Sounds.dynasterDeath);
    }

    public boolean attackEntityFrom(DamageSource e, float a) {
        if (e.getSourceOfDamage() instanceof EntityPlayer) {
            ((EntityPlayer)e.getSourceOfDamage()).addPotionEffect(new PotionEffect(Potion.poison.id, 60, 1));
        }
        return super.attackEntityFrom(e, a);
    }

    protected void dropFewItems(boolean b, int j) {
        if (this.rand.nextInt(99) < 5) {
            this.dropItem(VanillaItemsOther.repairEssence, 1);
        }
        super.dropFewItems(b, j);
    }

    public boolean isValidLightLevel() {
        return true;
    }

    @Override
    public String mobName() {
        return "Dynaster";
    }
}

