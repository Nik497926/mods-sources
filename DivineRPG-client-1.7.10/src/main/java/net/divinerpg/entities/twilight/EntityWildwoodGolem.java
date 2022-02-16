/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsCrops;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityWildwoodGolem
extends EntityDivineRPGMob {
    public EntityWildwoodGolem(World var1) {
        super(var1);
        this.setSize(1.0f, 2.8f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.wildwoodGolemHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.wildwoodGolemDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.wildwoodGolemSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.wildwoodGolemFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
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
        this.dropItem(ItemsFood.magicMeat, 1);
        this.dropItem(TwilightItemsOther.wildwoodSoul, this.rand.nextInt(3) + 1);
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(TwilightItemsCrops.moonbulbSeeds, this.rand.nextInt(3) + 1);
        }
    }

    @Override
    public String mobName() {
        return "Wildwood Golem";
    }
}

