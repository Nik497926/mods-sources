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

public class EntityDuo
extends VetheaMob {
    public static int ability;
    private final int SLOW = 0;
    private final int FAST = 1;
    private int abilityCoolDown;
    float moveSpeed = 0.25f;

    public EntityDuo(World par1) {
        super(par1);
        this.addAttackingAI();
        ability = 0;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.duoHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.duoDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.duoSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.duoFollowRange);
    }

    @Override
    public int getSpawnLayer() {
        return 1;
    }

    protected void updateAITasks() {
        if (ability == 0 && this.abilityCoolDown == 0) {
            ability = 1;
            this.abilityCoolDown = 350;
            this.setAIMoveSpeed(this.moveSpeed * 3.0f);
        }
        --this.abilityCoolDown;
        if (ability == 1 && this.abilityCoolDown == 0) {
            ability = 0;
            this.abilityCoolDown = 350;
            this.setAIMoveSpeed(this.moveSpeed);
        }
        super.updateAITasks();
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.duo.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.duoHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.dirtyPearls, 1);
    }

    @Override
    public String mobName() {
        return "Duo";
    }
}

