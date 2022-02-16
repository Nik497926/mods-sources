/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.World;

public class EntityEpiphite
extends EntityPeacefulUntilAttacked {
    public EntityEpiphite(World var1) {
        super(var1);
        this.setSize(0.8f, 1.0f);
        this.isImmuneToFire = true;
        this.experienceValue = 40;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.epiphiteHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.epiphiteDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.epiphiteSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.epiphiteFollowRange);
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.growl);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.growlHurt);
    }

    protected void dropFewItems(boolean beenHit, int lootingLevel) {
        super.dropFewItems(beenHit, lootingLevel);
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(TwilightItemsOther.wildwoodSoul, 1 + lootingLevel);
        }
        this.dropItem(ItemsFood.magicMeat, 1);
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        if (this.rand.nextInt(5) == 0) {
            this.worldObj.spawnEntityInWorld((Entity)new EntityLightningBolt(this.worldObj, e.posX, e.posY, e.posZ));
        }
        return super.attackEntityAsMob(e);
    }

    @Override
    public String mobName() {
        return "Epiphite";
    }
}

