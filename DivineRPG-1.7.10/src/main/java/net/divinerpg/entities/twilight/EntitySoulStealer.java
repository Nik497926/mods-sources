/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntitySoulStealer
extends EntityDivineRPGMob {
    public EntitySoulStealer(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    public int getTotalArmorValue() {
        return 10;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.soulStealerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.soulStealerDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.soulStealerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.soulStealerFollowRange);
    }

    public boolean attackEntityAsMob(Entity var1) {
        if (super.attackEntityAsMob(var1)) {
            if (var1 instanceof EntityLiving) {
                ((EntityLiving)var1).addPotionEffect(new PotionEffect(Potion.confusion.id, 240, 0));
                ((EntityLiving)var1).addPotionEffect(new PotionEffect(Potion.blindness.id, 240, 0));
            }
            return true;
        }
        return false;
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.insect);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.mortumSoul;
    }

    protected void dropFewItems(boolean var1, int var2) {
        this.dropItem(TwilightItemsOther.mortumSoul, this.rand.nextInt(5) + var2 + 1);
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(VanillaItemsOther.whiteShard, 1);
        }
    }

    @Override
    public String mobName() {
        return "Soul Stealer";
    }
}

