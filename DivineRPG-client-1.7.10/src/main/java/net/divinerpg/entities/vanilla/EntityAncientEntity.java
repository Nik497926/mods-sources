/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityAncientEntity
extends EntityDivineRPGBoss {
    public EntityAncientEntity(World par1World) {
        super(par1World);
        this.setSize(4.0f, 6.5f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.ancientEntityHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.ancientEntityDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.ancientEntitySpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.ancientEntityFollowRange);
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        super.attackEntityAsMob(par1Entity);
        if (this.entityToAttack != null) {
            this.entityToAttack.addVelocity(this.motionX * 10.0, 3.0, this.motionZ * 10.0);
            if (this.entityToAttack instanceof EntityLiving) {
                ((EntityLivingBase)this.entityToAttack).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 0));
                this.playSound("mob.irongolem.throw", 1.0f, 1.0f);
            }
            return true;
        }
        return false;
    }

    @Override
    protected String getHurtSound() {
        return "mob.irongolem.hit";
    }

    @Override
    protected String getDeathSound() {
        return "mob.irongolem.death";
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VanillaItemsOther.divineShards, this.rand.nextInt(4) + 3);
        this.dropItem(VanillaItemsWeapons.sandslash, 1);
        this.dropItem(Item.getItemFromBlock((Block)VanillaBlocks.ancientEntityStatue), 1);
    }

    @Override
    public String mobName() {
        return "Ancient Entity";
    }

    @Override
    public String name() {
        return "Ancient Entity";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

