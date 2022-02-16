/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityBohemite
extends VetheaMob {
    private int waitTick;

    public EntityBohemite(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.bohemiteHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.bohemiteDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.bohemiteSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.bohemiteFollowRange);
    }

    @Override
    public int getSpawnLayer() {
        return 3;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.bohemite.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.bohemiteHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.polishedPearls, 1);
    }

    @Override
    public String mobName() {
        return "Bohemite";
    }
}

