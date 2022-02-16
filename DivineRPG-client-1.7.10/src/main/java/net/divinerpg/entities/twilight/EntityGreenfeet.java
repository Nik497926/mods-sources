/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGreenfeet
extends EntityDivineRPGMob {
    public EntityGreenfeet(World w) {
        super(w);
        this.addAttackingAI();
        this.setSize(1.0f, 2.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.greenfeetHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.greenfeetDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.greenfeetSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.greenfeetFollowRange);
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
        return Sounds.getSoundName(Sounds.nesro);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.nesroHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.nesroHurt);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.edenSoul;
    }

    @Override
    public String mobName() {
        return "Greenfeet";
    }
}

