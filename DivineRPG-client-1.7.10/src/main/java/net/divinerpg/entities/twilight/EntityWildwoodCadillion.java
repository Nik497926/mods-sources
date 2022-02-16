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

public class EntityWildwoodCadillion
extends EntityDivineRPGMob {
    public EntityWildwoodCadillion(World var1) {
        super(var1);
        this.setSize(1.0f, 1.3f);
        this.addAttackingAI();
        this.experienceValue = 40;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.wildwoodCadillionHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.wildwoodCadillionDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.wildwoodCadillionSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.wildwoodCadillionFollowRange);
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.cadillion);
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
        this.dropItem(ItemsFood.magicMeat, 1);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.dropItem(TwilightItemsOther.wildwoodSoul, 1);
        }
    }

    protected void dropRareDrop(int var1) {
        this.dropItem(TwilightItemsOther.wildwoodSoul, 2);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.wildwoodSoul;
    }

    public boolean isValidLightLevel() {
        return true;
    }

    @Override
    public String mobName() {
        return "Wildwood Cadillion";
    }
}

