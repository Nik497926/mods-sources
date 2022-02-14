/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.iceika;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.IceikaItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityHastreus
extends EntityDivineRPGMob {
    public EntityHastreus(World var1) {
        super(var1);
        double moveSpeed = 0.3;
        this.setSize(1.0f, 1.9f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.hastreusHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.hastreusDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.hastreusSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.hastreusFollowRange);
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.hastreus);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.hastreusHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.hastreusHurt);
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
        return "Hastreus";
    }
}

