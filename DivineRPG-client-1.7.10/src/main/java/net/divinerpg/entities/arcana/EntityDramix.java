/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityDramix
extends EntityDivineRPGBoss {
    private int firetick;

    public EntityDramix(World var1) {
        super(var1);
        this.setSize(0.85f, 3.0f);
        this.addAttackingAI();
        this.isImmuneToFire = true;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.dramixHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.dramixDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.dramixSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.dramixFollowRange);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    @Override
    public String mobName() {
        return "Dramix";
    }

    public void onLivingUpdate() {
        if (this.ticksExisted % 600 < 300) {
            this.addPotionEffect(new PotionEffect(Potion.invisibility.id, 5, 0, true));
        }
        super.onLivingUpdate();
    }

    @Override
    protected String getLivingSound() {
        return Sounds.dramix.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.dramixHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return Sounds.dramixHurt.getPrefixedName();
    }

    public void dropFewItems(boolean par1, int par2) {
        this.dropItem(ArcanaItems.dungeonTokens, 5);
    }

    @Override
    public String name() {
        return this.mobName();
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

