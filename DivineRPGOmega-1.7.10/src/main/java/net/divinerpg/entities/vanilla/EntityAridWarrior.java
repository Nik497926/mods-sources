/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityAridWarrior
extends EntityDivineRPGMob {
    private static final ItemStack defaultHeldItem = new ItemStack(VanillaItemsWeapons.shadowBow, 1);

    public EntityAridWarrior(World par1World) {
        super(par1World);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.aridWarriorHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.aridWarriorDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.aridWarriorSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.aridWarriorFollowRange);
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote) {
            this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 16.0);
            if (this.entityToAttack != null && this.ticksExisted % 18 == 0) {
                this.attackEntityWithRangedAttack((EntityLivingBase)this.entityToAttack);
            }
        }
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.aridWarrior);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.aridWarriorHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.aridWarriorHurt);
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(Item.getItemFromBlock((Block)Blocks.sandstone), this.rand.nextInt(10));
        this.entityDropItem(new ItemStack(Blocks.wool, this.rand.nextInt(10), 14), 0.0f);
    }

    public void attackEntityWithRangedAttack(EntityLivingBase e) {
        EntityArrow var2 = new EntityArrow(this.worldObj, (EntityLivingBase)this, e, 1.6f, 4.5f);
        var2.setDamage(1.5);
        this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)var2);
    }

    protected Item getDropItem() {
        return Item.getItemFromBlock((Block)Blocks.sandstone);
    }

    @Override
    public String mobName() {
        return "Arid Warrior";
    }
}

