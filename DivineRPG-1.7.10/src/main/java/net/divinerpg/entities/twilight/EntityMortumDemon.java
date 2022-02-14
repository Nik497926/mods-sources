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
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMortumDemon
extends EntityDivineRPGMob {
    public EntityMortumDemon(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.mortumDemonHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.mortumDemonDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.mortumDemonSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.mortumDemonFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.demonDarkness);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
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
        return "Demon of Darkness";
    }
}

