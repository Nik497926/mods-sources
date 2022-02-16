/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityHelio
extends EntityDivineRPGMob {
    public EntityHelio(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.helioHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.helioDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.helioSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.helioFollowRange);
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getLivingSound() {
        return Sounds.helio.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.helioHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    protected void dropFewItems(boolean par1, int par2) {
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(VanillaItemsOther.redShard, this.rand.nextInt(3 + par2));
        }
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(VanillaItemsOther.greenShard, this.rand.nextInt(3 + par2));
        }
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(VanillaItemsOther.whiteShard, this.rand.nextInt(3 + par2));
        }
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(VanillaItemsOther.blueShard, this.rand.nextInt(3 + par2));
        }
    }

    @Override
    public String mobName() {
        return "Helio";
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty();
    }
}

