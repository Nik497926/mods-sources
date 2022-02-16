/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.EntityAngryBunny;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityKarot
extends EntityDivineRPGBoss {
    private int spawnTick = 240;

    public EntityKarot(World var1) {
        super(var1);
        this.setSize(3.25f, 4.0f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.karotHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.karotDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.karotSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.karotFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    public void onLivingUpdate() {
        if (this.spawnTick == 0 && !this.worldObj.isRemote) {
            EntityAngryBunny var2 = new EntityAngryBunny(this.worldObj);
            var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rand.nextFloat() * 360.0f, 0.0f);
            this.worldObj.spawnEntityInWorld((Entity)var2);
            this.worldObj.spawnParticle("reddust", var2.posX, var2.posY + 0.5, var2.posZ, this.rand.nextGaussian() * 2.0 - 1.0, this.rand.nextGaussian() * 2.0 - 1.0, this.rand.nextGaussian() * 2.0 - 1.0);
            this.spawnTick = 240;
        }
        --this.spawnTick;
        super.onLivingUpdate();
    }

    @Override
    protected String getLivingSound() {
        return null;
    }

    @Override
    protected String getHurtSound() {
        return null;
    }

    @Override
    protected String getDeathSound() {
        return null;
    }

    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (!this.worldObj.isRemote) {
            for (int i = 0; i < 5; ++i) {
                EntityAngryBunny var1 = new EntityAngryBunny(this.worldObj);
                var1.setPosition(this.posX, this.posY, this.posZ);
                this.worldObj.spawnEntityInWorld((Entity)var1);
            }
        }
        super.setDead();
    }

    public void dropFewItems(boolean par1, int par2) {
        this.dropItem(TwilightItemsWeapons.halitePhaser, 1);
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(Item.getItemFromBlock((Block)VanillaBlocks.karotStatue), 1);
        }
    }

    @Override
    public String mobName() {
        return "Karot";
    }

    @Override
    public String name() {
        return "Karot";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

