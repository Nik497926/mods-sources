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
import net.divinerpg.utils.items.JourneyItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGolder
extends EntityDivineRPGMob {
    public static final int ENTITY_TYPE = 23;

    public EntityGolder(World par1World) {
        super(par1World);
        this.addAttackingAI();
        this.setSize(1.2f, 1.7f);
        this.dataWatcher.updateObject(23, (Object)this.rand.nextInt(4));
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(23, (Object)0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.golderHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.golderDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.golderSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.golderFollowRange);
    }

    public String getLivingSound() {
        return Sounds.getSoundName(Sounds.reaper);
    }

    public String getHurtSound() {
        return Sounds.getSoundName(Sounds.reaperHurt);
    }

    public String getDeathSound() {
        return Sounds.getSoundName(Sounds.reaperHurt);
    }

    public boolean attackEntityFrom(DamageSource e, float a) {
        if (e.getSourceOfDamage() instanceof EntityPlayer) {
            ((EntityPlayer)e.getSourceOfDamage()).addPotionEffect(new PotionEffect(Potion.poison.id, 60, 1));
        }
        return super.attackEntityFrom(e, a);
    }

    protected void dropFewItems(boolean b, int j) {
        if (this.rand.nextInt(99) < 25) {
            this.dropItem(JourneyItemsOther.silverClump, 1);
        }
    }

    public boolean isValidLightLevel() {
        return true;
    }

    @Override
    public String mobName() {
        return "Golder";
    }
}

