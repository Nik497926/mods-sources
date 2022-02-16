/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityApalachiaTomo
extends EntityPeacefulUntilAttacked {
    public EntityApalachiaTomo(World var1) {
        super(var1);
        this.setSize(1.1f, 1.0f);
        this.experienceValue = 40;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.apalachiaTomoHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.apalachiaTomoDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.apalachiaTomoSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.apalachiaTomoFollowRange);
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.croak);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected void dropFewItems(boolean var1, int var2) {
        int var3 = this.rand.nextInt(2 + var2);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.dropItem(this.getDropItem(), 1);
            this.dropItem(ItemsFood.enrichedMagicMeat, 2);
        }
        this.dropItem(this.getDropItem(), 1);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.apalachiaSoul;
    }

    @Override
    public String mobName() {
        return "Apalachia Tomo";
    }
}

