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
import net.divinerpg.utils.items.TwilightItemsCrops;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityApalachiaGolem
extends EntityDivineRPGMob {
    public EntityApalachiaGolem(World var1) {
        super(var1);
        this.addAttackingAI();
        this.setSize(1.0f, 2.8f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.apalachiaGolemHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.apalachiaGolemDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.apalachiaGolemSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.apalachiaGolemFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.growl);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.apalachiaSoul;
    }

    protected void dropFewItems(boolean var1, int var2) {
        if (this.rand.nextInt(10 - var2) == 0) {
            this.dropItem(VanillaItemsOther.mysticChunk, 1);
        }
        this.dropItem(ItemsFood.enrichedMagicMeat, 1);
        this.dropItem(TwilightItemsOther.apalachiaSoul, this.rand.nextInt(5) + var2 + 1);
        int i = this.rand.nextInt(4);
        if (i == 0) {
            this.dropItem(TwilightItemsCrops.pinkGlowboneSeeds, this.rand.nextInt(3) + 1);
        } else if (i == 1) {
            this.dropItem(TwilightItemsCrops.purpleGlowboneSeeds, this.rand.nextInt(3) + 1);
        }
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(VanillaItemsOther.greenShard, 1);
        }
    }

    @Override
    public String mobName() {
        return "Apalachia Golem";
    }
}

