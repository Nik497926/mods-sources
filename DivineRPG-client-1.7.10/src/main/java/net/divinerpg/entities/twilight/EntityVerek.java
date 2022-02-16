/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityVerek
extends EntityDivineRPGMob {
    public EntityVerek(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.verekHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.verekDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.verekSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.verekFollowRange);
    }

    public int getTotalArmorValue() {
        return 6;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.verek);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.verekHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.verekHurt);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.wildwoodSoul;
    }

    protected void dropFewItems(boolean var1, int var2) {
        int var3 = this.rand.nextInt(2 + var2);
        this.dropItem(ItemsFood.magicMeat, 1);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.dropItem(TwilightItemsOther.wildwoodSoul, 2);
        }
    }

    @Override
    public String mobName() {
        return "Verek";
    }
}

