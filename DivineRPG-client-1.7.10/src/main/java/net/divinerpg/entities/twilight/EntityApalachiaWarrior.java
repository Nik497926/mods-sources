/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityApalachiaWarrior
extends EntityDivineRPGMob {
    private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.apalachiaBlade, 1);

    public EntityApalachiaWarrior(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.apalachiaWarriorHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.apalachiaWarriorDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.apalachiaWarriorSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.apalachiaWarriorFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    public void onLivingUpdate() {
        float var1;
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && (var1 = this.getBrightness(1.0f)) > 0.5f && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double((double)this.posX), MathHelper.floor_double((double)this.posY), MathHelper.floor_double((double)this.posZ)) && this.rand.nextFloat() * 30.0f < (var1 - 0.4f) * 2.0f) {
            this.setFire(8);
        }
        super.onLivingUpdate();
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.hiss);
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
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
        }
        this.dropItem(this.getDropItem(), 1);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.apalachiaSoul;
    }

    @Override
    public String mobName() {
        return "Apalachia Warrior";
    }
}

