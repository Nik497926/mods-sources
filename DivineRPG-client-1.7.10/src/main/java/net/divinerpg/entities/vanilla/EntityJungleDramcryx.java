/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.StatBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityJungleDramcryx
extends EntityDivineRPGMob {
    public EntityJungleDramcryx(World par1World) {
        super(par1World);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.jungleDramcryxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.jungleDramcryxDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.jungleDramcryxSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.jungleDramcryxFollowRange);
    }

    public void onDeath(DamageSource d) {
        super.onDeath(d);
        if (d.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)d.getEntity();
            player.triggerAchievement((StatBase)DivineRPGAchievements.dramcryxDeath);
        }
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.dramcryx);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.dramcryxHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.dramcryxHurt);
    }

    protected Item getDropItem() {
        return VanillaItemsOther.jungleShards;
    }

    @Override
    public String mobName() {
        return "Jungle Dramcryx";
    }
}

