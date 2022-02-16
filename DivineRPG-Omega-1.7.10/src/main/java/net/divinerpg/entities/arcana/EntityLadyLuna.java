/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.EntityLadyLunaSparkler;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLadyLuna
extends EntityDivineRPGBoss {
    private int deathTicks;
    private List<ChunkCoordinates> acidPositions = new ArrayList<ChunkCoordinates>();

    public EntityLadyLuna(World var1) {
        super(var1);
        this.addAttackingAI();
        this.setSize(1.25f, 3.9f);
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(19, (Object)500);
        this.dataWatcher.addObject(20, (Object)1);
    }

    protected void updateAITasks() {
        this.manageAbilities();
        super.updateAITasks();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.ladyLunaHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.ladyLunaDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.ladyLunaSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.ladyLunaFollowRange);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote) {
            if (!this.worldObj.isRemote && this.worldObj.getBlock((int)Math.round(this.posX) - 1, MathHelper.floor_double((double)this.posY) - 1, (int)Math.round(this.posZ) - 1).isOpaqueCube() && this.worldObj.getBlock((int)Math.round(this.posX) - 1, MathHelper.floor_double((double)this.posY), (int)Math.round(this.posZ) - 1) == Blocks.air) {
                this.worldObj.setBlock((int)Math.round(this.posX) - 1, MathHelper.floor_double((double)this.posY), (int)Math.round(this.posZ) - 1, VetheaBlocks.lunicAcid);
                if (!this.acidPositions.contains(Util.newChunkCoordinates((int)Math.round(this.posX) - 1, MathHelper.floor_double((double)this.posY), (int)Math.round(this.posZ) - 1))) {
                    this.acidPositions.add(Util.newChunkCoordinates((int)Math.round(this.posX) - 1, MathHelper.floor_double((double)this.posY), (int)Math.round(this.posZ) - 1));
                }
            }
            if (!this.worldObj.isRemote && this.getProtectionType() == 1 && this.ticksExisted % 60 == 0) {
                Iterator<ChunkCoordinates> iter = this.acidPositions.iterator();
                while (iter.hasNext()) {
                    ChunkCoordinates c = iter.next();
                    if (this.worldObj.getBlock(c.posX, c.posY, c.posZ) != VetheaBlocks.lunicAcid) {
                        iter.remove();
                        continue;
                    }
                    if (this.rand.nextInt(4) != 0) continue;
                    EntityLadyLunaSparkler e = new EntityLadyLunaSparkler(this.worldObj, (EntityLivingBase)this);
                    e.setPosition((double)c.posX + 0.5, c.posY, (double)c.posZ + 0.5);
                    this.worldObj.spawnEntityInWorld((Entity)e);
                }
            }
        }
    }

    @SubscribeEvent
    public void manageAbilities() {
        EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 42.0);
        if (this.getAITarget() == player && this.lastDamage > 0.0f) {
            int r = this.rand.nextInt(99);
            if (player != null) {
                if (r < 20) {
                    this.setPositionAndUpdate(this.getAITarget().posX, this.getAITarget().posY, this.getAITarget().posZ);
                    this.lastDamage = 0.0f;
                    this.setAttackTarget((EntityLivingBase)player);
                }
                this.lastDamage = 0.0f;
            }
        }
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getLivingSound() {
        switch (this.rand.nextInt(3)) {
            case 0: {
                return Sounds.ladyLuna1.getPrefixedName();
            }
            case 1: {
                return Sounds.ladyLuna2.getPrefixedName();
            }
            case 2: {
                return Sounds.ladyLuna3.getPrefixedName();
            }
        }
        return null;
    }

    protected String getHurtSound() {
        return Sounds.ladyLunaHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    public int getProtectionType() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public int getProtectionTimer() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    public void setProtectionType(int i) {
        if (i == 1) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.ladyLunaSpeed / 1.3);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.ladyLunaSpeed);
        }
        this.dataWatcher.updateObject(20, (Object)i);
    }

    public void setProtectionTimer(int i) {
        this.dataWatcher.updateObject(19, (Object)i);
    }

    public boolean attackEntityAsMob(Entity e) {
        int dam = (int)EntityStats.ladyLunaDamage;
        boolean var4 = e.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)(dam - 15));
        if (var4) {
            if (this.getHealth() < 100.0f) {
                this.motionX *= 0.6;
                this.motionZ *= 0.6;
                int var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this);
                if (var5 > 0) {
                    e.setFire(var5 * 4);
                }
            } else {
                this.motionX *= 0.6;
                this.motionZ *= 0.3;
                int var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this);
                if (var5 > 0) {
                    e.setFire(var5 * 4);
                }
            }
        }
        return var4;
    }

    public void onDeath(DamageSource damage) {
        int chance = this.rand.nextInt(99);
        this.worldObj.setBlock((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0), (Block)Blocks.chest);
        IInventory te = (IInventory)this.worldObj.getTileEntity((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0));
        if (te != null) {
            if (this.rand.nextInt(5) == 0) {
                te.setInventorySlotContents(5, new ItemStack(JourneyItemsOther.eucaPortalGem, 1));
            }
            te.setInventorySlotContents(0, new ItemStack(VanillaItemsOther.tarnishedCrystal, 1));
            if (chance < 30) {
                te.setInventorySlotContents(1, new ItemStack(VanillaItemsOther.poisonScroll, 1));
            }
            if (chance < 12) {
                te.setInventorySlotContents(2, new ItemStack(VanillaItemsWeapons.lunaSoulSword, 1));
            }
            if (chance > 12) {
                te.setInventorySlotContents(3, new ItemStack(VanillaItemsWeapons.lunaPresentSword, 1));
            }
        }
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;
        if (this.deathTicks >= 180 && this.deathTicks <= 200) {
            float var1 = (this.rand.nextFloat() - 0.5f) * 8.0f;
            float var2 = (this.rand.nextFloat() - 0.5f) * 4.0f;
            float var3 = (this.rand.nextFloat() - 0.5f) * 8.0f;
            this.worldObj.spawnParticle("hugeexplosion", this.posX + (double)var1, this.posY + 2.0 + (double)var2, this.posZ + (double)var3, 0.0, 0.0, 0.0);
        }
        if (!this.worldObj.isRemote && this.deathTicks > 150 && this.deathTicks % 5 == 0) {
            int var5;
            for (int var4 = 1200; var4 > 0; var4 -= var5) {
                var5 = EntityXPOrb.getXPSplit((int)var4);
                this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
            }
        }
        this.moveEntity(0.0, 0.1f, 0.0);
        this.renderYawOffset = this.rotationYaw += 20.0f;
        if (this.deathTicks == 200 && !this.worldObj.isRemote) {
            int var5;
            for (int var4 = 2000; var4 > 0; var4 -= var5) {
                var5 = EntityXPOrb.getXPSplit((int)var4);
                this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
            }
            this.setDead();
        }
    }

    @Override
    public String mobName() {
        return "Lady Luna";
    }

    @Override
    public String name() {
        return "Lady Luna";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("Immunity", this.getProtectionType());
        tag.setInteger("ImmunityCooldown", this.getProtectionTimer());
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.setProtectionType(tag.getInteger("Immunity"));
        this.setProtectionTimer(tag.getInteger("ImmunityCooldown"));
    }
}

