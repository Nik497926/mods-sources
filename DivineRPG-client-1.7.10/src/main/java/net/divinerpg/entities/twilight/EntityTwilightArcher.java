/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.EntityApalachiaArcher;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityTwilightArcher
extends EntityApalachiaArcher {
    private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.twilightBow, 1);

    public EntityTwilightArcher(World var1) {
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
        return TwilightItemsOther.mortumSoul;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {
        EntityDivineArrow var2 = new EntityDivineArrow(this.worldObj, (EntityLivingBase)this, var1, 1.6f, 12.0f, 15.0f, "furyArrow");
        var2.setDamage(17.0);
        this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)var2);
    }

    @Override
    public String mobName() {
        return "Twilight Archer";
    }
}

