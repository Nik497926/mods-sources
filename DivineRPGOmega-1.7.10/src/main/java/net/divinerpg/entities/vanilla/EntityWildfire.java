/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityWildfire
extends EntityDivineRPGMob
implements IRangedAttackMob {
    private static final ItemStack defaultHeldItem = new ItemStack(VanillaItemsWeapons.infernoBow, 1);

    public EntityWildfire(World par1World) {
        super(par1World);
        float moveSpeed = 0.25f;
        this.targetTasks.addTask(7, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 0.25, 15, 10.0f));
        this.isImmuneToFire = true;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.wildFireHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.wildFireDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.wildFireSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.wildFireFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.wildFire);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.wildFireHurt);
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    protected Item getDropItem() {
        return VanillaItemsOther.moltenShards;
    }

    protected void dropFewItems(boolean par1, int par2) {
        if (this.rand.nextInt(99) < 55) {
            this.dropItem(VanillaItemsOther.netheriumShards, 1);
            this.dropItem(VanillaItemsOther.moltenShards, 8);
        } else {
            this.dropItem(VanillaItemsOther.moltenShards, 4);
        }
    }

    protected void dropRareDrop(int var1) {
        if (this.rand.nextInt(5) == 0) {
            this.dropItem(VanillaItemsOther.hellstoneIngot, 1);
        } else {
            this.dropItem(VanillaItemsOther.netheriteIngot, 40);
        }
    }

    public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {
        EntityDivineArrow var2 = new EntityDivineArrow(this.worldObj, (EntityLivingBase)this, var1, 1.6f, 12.0f, 5.0f, "infernoArrow");
        var2.setFire(100);
        this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)var2);
    }

    @Override
    public String mobName() {
        return "Wildfire";
    }
}

