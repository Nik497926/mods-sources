/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.EntityHerbomancerMinion;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityHerbomancer
extends VetheaMob {
    private int spawnTick;

    public EntityHerbomancer(World var1) {
        super(var1);
        this.addAttackingAI();
        this.spawnTick = 40;
    }

    @Override
    public int getSpawnLayer() {
        return 2;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.herbomancerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.herbomancerDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.herbomancerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.herbomancerFollowRange);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.spawnTick == 0 && !this.worldObj.isRemote) {
            EntityHerbomancerMinion var2 = new EntityHerbomancerMinion(this.worldObj);
            var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rand.nextFloat() * 360.0f, 0.0f);
            this.worldObj.spawnEntityInWorld((Entity)var2);
            this.worldObj.spawnParticle("reddust", var2.posX, var2.posY + 0.5, var2.posZ, this.rand.nextGaussian() * 2.0 - 1.0, this.rand.nextGaussian() * 2.0 - 1.0, this.rand.nextGaussian() * 2.0 - 1.0);
            this.worldObj.playSoundAtEntity((Entity)var2, "", 10.0f, 1.0f);
            this.spawnTick = 40;
        }
        --this.spawnTick;
    }

    protected String getLivingSound() {
        return Sounds.herbomancer.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.herbomancerHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.cleanPearls, 1);
    }

    @Override
    public String mobName() {
        return "Herbomancer";
    }
}

