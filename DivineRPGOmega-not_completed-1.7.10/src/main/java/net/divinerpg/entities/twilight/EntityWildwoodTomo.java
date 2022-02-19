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
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityWildwoodTomo
extends EntityPeacefulUntilAttacked {
    public EntityWildwoodTomo(World var1) {
        super(var1);
        this.setSize(1.1f, 1.0f);
        this.experienceValue = 30;
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.wildwoodTomoHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.wildwoodTomoDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.wildwoodTomoSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.wildwoodTomoFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.croak);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected void dropFewItems(boolean var1, int var2) {
        super.dropFewItems(var1, var2);
        this.dropItem(ItemsFood.magicMeat, 1);
        this.dropItem(TwilightItemsOther.wildwoodSoul, this.rand.nextInt(5) + var2 + 1);
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(VanillaItemsOther.blueShard, 1);
        }
    }

    protected Item getDropItem() {
        return TwilightItemsOther.wildwoodSoul;
    }

    @Override
    public String mobName() {
        return "Wildwood Tomo";
    }
}

