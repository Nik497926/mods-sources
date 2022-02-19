/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.iceika;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.IceikaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityRollum
extends EntityDivineRPGMob {
    public EntityRollum(World var1) {
        super(var1);
        this.addAttackingAI();
        this.setSize(1.3f, 2.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.rollumHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.rollumDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.rollumSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.rollumFollowRange);
    }

    public boolean attackEntityAsMob(Entity e) {
        super.attackEntityAsMob(e);
        e.addVelocity(this.motionX * 3.0, 0.3, this.motionZ * 3.0);
        return true;
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.rollum);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.rollumHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.rollumHurt);
    }

    protected Item getDropItem() {
        return IceikaItems.iceShards;
    }

    protected void dropFewItems(boolean var1, int var2) {
        int var3 = this.rand.nextInt(2 + var2);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.dropItem(IceikaItems.iceShards, 3);
        }
    }

    @Override
    public String mobName() {
        return "Rollum";
    }
}

