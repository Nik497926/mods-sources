/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityWitherSkull
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.EntityParticleBullet;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTempleGuardian
extends EntityDivineRPGMob
implements IRangedAttackMob {
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack((IRangedAttackMob)this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 23, 10.0f);

    public EntityTempleGuardian(World par1World) {
        super(par1World);
        this.setSize(2.0f, 3.8f);
        this.tasks.addTask(1, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 1.0, 40, 20.0f));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.targetTasks.addTask(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 1, true));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityWolf.class, 6.0f, 1.0, 1.2));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.isImmuneToFire = true;
        if (par1World != null && !par1World.isRemote) {
            this.setCombatTask();
        }
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.mortumArcherHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.mortumArcherDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.mortumArcherSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.mortumArcherFollowRange);
    }

    protected String getLivingSound() {
        return "mob.blaze.breathe";
    }

    protected String getHurtSound() {
        return "mob.blaze.hit";
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.bossDeath);
    }

    public float getSoundVolume() {
        return 1.0f;
    }

    public void setCombatTask() {
        this.tasks.removeTask((EntityAIBase)this.aiArrowAttack);
        this.tasks.addTask(4, (EntityAIBase)this.aiArrowAttack);
    }

    public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
        super.setCurrentItemOrArmor(par1, par2ItemStack);
        if (!this.worldObj.isRemote && par1 == 0) {
            this.setCombatTask();
        }
    }

    public void onDeath(DamageSource damage) {
        this.worldObj.setBlock((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 0.0), (int)Math.floor(this.posZ + 0.0), (Block)Blocks.chest);
        TileEntityChest te = (TileEntityChest)this.worldObj.getTileEntity((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 0.0), (int)Math.floor(this.posZ + 0.0));
        switch (this.rand.nextInt(4)) {
            case 0: {
                te.setInventorySlotContents(2, new ItemStack(JourneyItemsOther.yellowGem, 3));
                te.setInventorySlotContents(11, new ItemStack(JourneyItemsOther.blueGem, 2));
                te.setInventorySlotContents(16, new ItemStack(JourneyItemsOther.greenGem, 5));
                te.setInventorySlotContents(5, new ItemStack(JourneyItemsOther.purpleGem, 2));
                break;
            }
            case 1: {
                te.setInventorySlotContents(1, new ItemStack(JourneyItemsOther.yellowGem, 4));
                te.setInventorySlotContents(14, new ItemStack(JourneyItemsOther.blueGem, 5));
                te.setInventorySlotContents(12, new ItemStack(JourneyItemsOther.greenGem, 2));
                te.setInventorySlotContents(4, new ItemStack(JourneyItemsOther.purpleGem, 6));
                break;
            }
            case 2: {
                te.setInventorySlotContents(14, new ItemStack(JourneyItemsOther.yellowGem, 2));
                te.setInventorySlotContents(1, new ItemStack(JourneyItemsOther.blueGem, 4));
                te.setInventorySlotContents(4, new ItemStack(JourneyItemsOther.greenGem, 6));
                te.setInventorySlotContents(12, new ItemStack(JourneyItemsOther.purpleGem, 2));
                break;
            }
            case 3: {
                te.setInventorySlotContents(4, new ItemStack(JourneyItemsOther.yellowGem, 5));
                te.setInventorySlotContents(12, new ItemStack(JourneyItemsOther.blueGem, 1));
                te.setInventorySlotContents(14, new ItemStack(JourneyItemsOther.greenGem, 4));
                te.setInventorySlotContents(1, new ItemStack(JourneyItemsOther.purpleGem, 2));
            }
        }
    }

    @Override
    public boolean canDespawn() {
        return false;
    }

    public void attackEntityWithRangedAttack(EntityLivingBase e, float f1) {
        if (this.rand.nextInt(99) < 25) {
            this.launchWitherSkullToEntity(0, e);
        }
        EntityParticleBullet bullet = new EntityParticleBullet(this.worldObj, (EntityLivingBase)this, 5, 0, "fire", 5, "hellstone");
        bullet.setThrowableHeading(e.posX - this.posX, e.posY - this.posY - 0.6, e.posZ - this.posZ, 1.0f, 0.0f);
        this.worldObj.playSoundAtEntity((Entity)this, Sounds.hammer.getPrefixedName(), 1.0f, 1.0f);
        this.worldObj.spawnEntityInWorld((Entity)bullet);
    }

    private void launchWitherSkullToEntity(int var1, EntityLivingBase e) {
        this.launchWitherSkullToCoords(var1, e.posX, e.posY + (double)e.getEyeHeight() * 0.5, e.posZ, var1 == 0 && this.rand.nextFloat() < 0.001f);
    }

    private void launchWitherSkullToCoords(int var1, double f2, double f4, double f6, boolean f8) {
        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
        double d3 = this.coordX(var1);
        double d4 = this.coordY(var1);
        double d5 = this.coordZ(var1);
        double d6 = f2 - d3;
        double d7 = f4 - d4;
        double d8 = f6 - d5;
        EntityWitherSkull skull = new EntityWitherSkull(this.worldObj, (EntityLivingBase)this, d6, d7, d8);
        skull.posY = d4;
        skull.posX = d3;
        skull.posZ = d5;
        this.worldObj.spawnEntityInWorld((Entity)skull);
    }

    private double coordX(int par1) {
        if (par1 <= 0) {
            return this.posX;
        }
        float f = (this.renderYawOffset + (float)(180 * (par1 - 1))) / 180.0f * (float)Math.PI;
        float f1 = MathHelper.cos((float)f);
        return this.posX + (double)f1 * 1.3;
    }

    private double coordY(int par1) {
        return par1 <= 0 ? this.posY + 3.0 : this.posY + 2.2;
    }

    private double coordZ(int par1) {
        if (par1 <= 0) {
            return this.posZ;
        }
        float f = (this.renderYawOffset + (float)(180 * (par1 - 1))) / 180.0f * (float)Math.PI;
        float f1 = MathHelper.sin((float)f);
        return this.posZ + (double)f1 * 1.3;
    }

    @Override
    public String mobName() {
        return "Temple Guardian";
    }
}

