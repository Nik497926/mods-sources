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

public class EntityGlacide
extends EntityDivineRPGMob {
    public EntityGlacide(World var1) {
        super(var1);
        this.addAttackingAI();
        this.setSize(0.8f, 1.8f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.glacideHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.glacideDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.glacideSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.glacideFollowRange);
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.glacide);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.glacideHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.glacideHurt);
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
        return "Glacide";
    }
}

