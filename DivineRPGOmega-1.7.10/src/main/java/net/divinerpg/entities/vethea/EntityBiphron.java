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

public class EntityBiphron
extends VetheaMob {
    private boolean gravMove;

    public EntityBiphron(World var1) {
        super(var1);
        this.addAttackingAI();
        this.setSize(0.4f, 2.8f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.biphronHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.biphronDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.biphronSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.biphronFollowRange);
    }

    protected void fall(float par1) {
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.rand.nextInt(50) == 0) {
            boolean bl = this.gravMove = !this.gravMove;
        }
        if (this.gravMove) {
            this.motionY = 0.4;
        }
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getLivingSound() {
        return Sounds.biphron.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.biphronHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.cleanPearls, 1);
    }

    @Override
    public int getSpawnLayer() {
        return 2;
    }

    @Override
    public String mobName() {
        return "Biphron";
    }
}

