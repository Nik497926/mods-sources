/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
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
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.block.Block;
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
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityReyvor
extends EntityDivineRPGBoss
implements IRangedAttackMob {
    private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.twilightBow, 1);

    public EntityReyvor(World var1) {
        super(var1);
        this.tasks.addTask(7, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 0.25, 40, 64.0f));
        this.targetTasks.addTask(6, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.reyvorHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.reyvorDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.reyvorSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.reyvorFollowRange);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Integer(100));
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.reyvor);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.reyvorHurt);
    }

    @Override
    protected String getDeathSound() {
        return null;
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    public void dropFewItems(boolean par1, int par2) {
        this.dropItem(TwilightItemsArmor.haliteLeggings, 1);
        if (this.rand.nextInt(3) == 0) {
            this.dropItem(Item.getItemFromBlock((Block)VanillaBlocks.reyvorStatue), 1);
        }
    }

    public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {
        EntityDivineArrow var2 = new EntityDivineArrow(this.worldObj, (EntityLivingBase)this, var1, 1.6f, 12.0f, 22.0f, "furyArrow");
        this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)var2);
    }

    @Override
    public String mobName() {
        return "Reyvor";
    }

    @Override
    public String name() {
        return "Reyvor";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

