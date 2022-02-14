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
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityApalachiaCadillion
extends EntityDivineRPGMob {
    public EntityApalachiaCadillion(World var1) {
        super(var1);
        this.setSize(1.0f, 1.3f);
        this.addAttackingAI();
        this.experienceValue = 40;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.apalachiaCadillionHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.apalachiaCadillionDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.apalachiaCadillionSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.apalachiaCadillionFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.cadillion);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected void dropFewItems(boolean var1, int var2) {
        if (this.rand.nextInt(10 - var2) == 0) {
            this.dropItem(VanillaItemsOther.mysticChunk, 1);
        }
        this.dropItem(TwilightItemsOther.apalachiaSoul, this.rand.nextInt(5 + var2) + 1);
        this.dropItem(ItemsFood.enrichedMagicMeat, 1);
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(VanillaItemsOther.greenShard, 1);
        }
    }

    public boolean isValidLightLevel() {
        return true;
    }

    @Override
    public String mobName() {
        return "Apalachia Cadillion";
    }
}

