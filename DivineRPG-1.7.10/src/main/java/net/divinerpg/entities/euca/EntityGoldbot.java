/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.euca;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityGoldbot
extends EntityDivineRPGMob {
    public EntityGoldbot(World par1World) {
        super(par1World);
        this.addAttackingAI();
        this.setSize(0.7f, 1.2f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.goldbotHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.goldbotDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.goldbotSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.goldbotFollowRange);
    }

    public String getLivingSound() {
        return Sounds.getSoundName(Sounds.robot);
    }

    public String getHurtSound() {
        return Sounds.getSoundName(Sounds.robotHurt);
    }

    public String getDeathSound() {
        return Sounds.getSoundName(Sounds.robotDeath);
    }

    protected void dropFewItems(boolean b, int j) {
        if (this.rand.nextInt(99) < 55) {
            this.dropItem(JourneyItemsOther.golderDust, 1);
        }
        if (this.rand.nextInt(99) < 35) {
            this.dropItem(VanillaItemsOther.repairEssence, this.rand.nextInt(5));
        }
    }

    public boolean isValidLightLevel() {
        return true;
    }

    @Override
    public String mobName() {
        return "Goldbot";
    }
}

