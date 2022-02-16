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
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsWeapons;
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

public class EntityApalachiaArcher
extends EntityDivineRPGMob
implements IRangedAttackMob {
    private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.apalachiaBow, 1);

    public EntityApalachiaArcher(World var1) {
        super(var1);
        this.tasks.addTask(7, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 1.0, 15, 60, 15.0f));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.setSize(2.0f, 3.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.apalachiaArcherHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.apalachiaArcherDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.apalachiaArcherSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.apalachiaArcherFollowRange);
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    public int getTotalArmorValue() {
        return 10;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.archer);
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.highHit);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.highHit);
    }

    protected Item getDropItem() {
        return TwilightItemsOther.apalachiaSoul;
    }

    public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {
        EntityDivineArrow var2 = new EntityDivineArrow(this.worldObj, (EntityLivingBase)this, var1, 1.6f, 12.0f, 12.0f, "wildwoodArrow");
        this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)var2);
    }

    @Override
    public String mobName() {
        return "Enchanted Archer";
    }
}

