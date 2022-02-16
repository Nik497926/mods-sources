/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.EntityApalachiaArcher;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySkythernArcher
extends EntityApalachiaArcher {
    private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.skythernBow, 1);

    public EntitySkythernArcher(World var1) {
        super(var1);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.skythernArcherHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.skythernArcherDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.skythernArcherSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.skythernArcherFollowRange);
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.archer);
    }

    @Override
    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.highHit);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.highHit);
    }

    @Override
    protected Item getDropItem() {
        return TwilightItemsOther.skythernSoul;
    }

    @Override
    public String mobName() {
        return "Skythern Archer";
    }
}

