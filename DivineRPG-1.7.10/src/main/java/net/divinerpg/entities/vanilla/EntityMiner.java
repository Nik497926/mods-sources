/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMiner
extends EntityDivineRPGMob {
    private static final ItemStack defaultHeldItem = new ItemStack(Items.diamond_pickaxe);

    public EntityMiner(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.minerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.minerDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.minerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.minerFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float f;
            float var1 = this.getBrightness(1.0f);
            if (f > 0.5f && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double((double)this.posX), MathHelper.floor_double((double)this.posY), MathHelper.floor_double((double)this.posZ)) && this.rand.nextFloat() * 30.0f < (var1 - 0.4f) * 2.0f) {
                this.setFire(8);
            }
        }
        super.onLivingUpdate();
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    protected void addRandomArmor() {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, defaultHeldItem);
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        if (this.getRNG().nextInt(5) > 0) {
            this.setCurrentItemOrArmor(0, defaultHeldItem);
        }
        return par1EntityLivingData;
    }

    protected Item getDropItem() {
        return Items.rotten_flesh;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    protected void dropRareDrop(int var1) {
        switch (this.rand.nextInt(5)) {
            case 0: {
                this.dropItem(Items.diamond_pickaxe, 3);
                break;
            }
            case 1: {
                this.dropItem(Items.gold_ingot, 10);
                break;
            }
            case 2: {
                this.dropItem(Items.iron_ingot, 10);
                break;
            }
            case 3: {
                this.dropItem(Items.diamond, 10);
                break;
            }
            case 4: {
                this.dropItem(Item.getItemFromBlock((Block)Blocks.torch), 32);
            }
        }
    }

    protected String getLivingSound() {
        return "mob.zombie.say";
    }

    protected String getHurtSound() {
        return "mob.zombie.hurt";
    }

    protected String getDeathSound() {
        return "mob.zombie.death";
    }

    @Override
    public String mobName() {
        return "Undead Miner";
    }
}

