/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.iceika;

import java.util.List;
import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.projectile.EntityWreckBouncingProjectile;
import net.divinerpg.entities.vethea.projectile.EntityWreckExplosiveShot;
import net.divinerpg.entities.vethea.projectile.EntityWreckShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityWreck
extends EntityDivineRPGBoss {
    private final int MELEE = 0;
    private final int ARCANA = 1;
    private final int RANGED = 2;
    private final int DEFAULT = 0;
    private final int CHARGE = 1;
    private final int PULL = 2;
    private final int FIRE = 3;
    private final int BOUNCE = 4;
    private final int FREEZE = 5;
    private final int SPEED = 6;
    private final int EXPLOSIONS = 7;
    private final int STRENGTH = 8;
    private boolean loaded = false;
    private int waitTick;
    private int abilityTimer;
    private int deathTicks;

    public EntityWreck(World par1) {
        super(par1);
        this.setSize(1.0f, 1.7f);
        this.tasks.addTask(3, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0, false));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 80.0f));
        this.targetTasks.addTask(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.setAbility(0);
        this.setAbilityType(0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.wreckHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.wreckDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.wreckSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.wreckFollowRange);
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(17, (Object)0);
        this.dataWatcher.addObject(18, (Object)0);
    }

    protected void updateAITasks() {
        this.manageAbilities();
        super.updateAITasks();
    }

    public void manageAbilities() {
        EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0);
        if (this.getHealth() < 1666.0f) {
            this.setAbilityType(2);
        } else if (this.getHealth() < 3333.0f && this.getHealth() > 1666.0f) {
            this.setAbilityType(1);
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.1);
            this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
        }
        if (this.abilityTimer > 0) {
            --this.abilityTimer;
        }
        if (this.getAbilityType() == 1 && (this.getAbility() == 1 || this.getAbility() == 2) || this.getAbilityType() == 2 && (this.getAbility() == 3 || this.getAbility() == 4 || this.getAbility() == 5)) {
            this.setAbility(0);
        }
        if (this.getAbility() == 0 && this.abilityTimer == 0) {
            this.abilityTimer = 200;
            block0 : switch (this.getAbilityType()) {
                case 0: {
                    switch (this.rand.nextInt(2)) {
                        case 0: {
                            this.setAbility(2);
                            this.setAIMoveSpeed(0.0f);
                            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.wreckSpeed);
                            break;
                        }
                        case 1: {
                            this.setAbility(1);
                            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.wreckSpeedFast);
                        }
                    }
                    break;
                }
                case 1: {
                    switch (this.rand.nextInt(3)) {
                        case 0: {
                            this.setAbility(3);
                            break;
                        }
                        case 1: {
                            this.setAbility(4);
                            break;
                        }
                        case 2: {
                            this.setAbility(5);
                        }
                    }
                    break;
                }
                case 2: {
                    switch (this.rand.nextInt(3)) {
                        case 0: {
                            this.setAbility(6);
                            break block0;
                        }
                        case 1: {
                            this.setAbility(7);
                            break block0;
                        }
                        case 2: {
                            this.setAbility(8);
                        }
                    }
                }
            }
            this.message();
        }
        if (this.getAbility() == 1 && this.abilityTimer == 0) {
            this.setAbility(0);
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.wreckSpeed);
        }
        if (this.getAbility() == 3 && player != null) {
            for (int i = 1; i < 20; ++i) {
                int var2 = (int)((this.posX - player.posX) * (double)i) / 5;
                int var3 = (int)((this.posZ - player.posZ) * (double)i) / 5;
                if (this.worldObj.getBlock((int)this.posX - var2, (int)this.posY, (int)this.posZ - var3) != Blocks.air) continue;
                this.worldObj.setBlock((int)this.posX - var2, (int)this.posY, (int)this.posZ - var3, (Block)Blocks.fire);
            }
            this.setAbility(0);
        }
        if (this.getAbility() == 5 && player != null) {
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 5));
            this.setAbility(0);
            this.abilityTimer = 100;
        }
        if (!this.worldObj.isRemote) {
            this.attackEntityWithRangedAttack((EntityLivingBase)player);
        }
    }

    private void message() {
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(64.0, 64.0, 64.0));
        block9: for (int var1 = 0; var1 < list.size(); ++var1) {
            if (!(list.get(var1) instanceof EntityPlayer)) continue;
            EntityPlayer player = (EntityPlayer)list.get(var1);
            switch (this.getAbility()) {
                case 1: {
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.wreck.charge")));
                    continue block9;
                }
                case 2: {
                    this.playSound(Sounds.feelSoulArksiane.getPrefixedName(), 1.0f, 1.0f);
                    if (this.worldObj.isRemote) continue block9;
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.wreck.pull")));
                    continue block9;
                }
                case 3: {
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.wreck.fire")));
                    continue block9;
                }
                case 5: {
                    this.playSound(Sounds.stopAtOnce.getPrefixedName(), 1.0f, 1.0f);
                    if (this.worldObj.isRemote) continue block9;
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.wreck.freeze")));
                    continue block9;
                }
                case 6: {
                    this.playSound(Sounds.wreckSpeed.getPrefixedName(), 1.0f, 1.0f);
                    if (this.worldObj.isRemote) continue block9;
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.wreck.speed")));
                    continue block9;
                }
                case 7: {
                    this.playSound(Sounds.explosions.getPrefixedName(), 1.0f, 1.0f);
                    if (this.worldObj.isRemote) continue block9;
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.wreck.explosion")));
                    continue block9;
                }
                case 8: {
                    this.playSound(Sounds.wreckStrength.getPrefixedName(), 1.0f, 1.0f);
                    if (this.worldObj.isRemote) continue block9;
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.wreck.strength")));
                }
            }
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && !this.loaded) {
            List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(30.0, 30.0, 30.0));
            for (EntityPlayer p : players) {
                p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.wreck.run")));
                p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.wreck.smell")));
                this.worldObj.playSoundAtEntity((Entity)p, Sounds.wreckIntro.getPrefixedName(), 1.0f, 1.0f);
            }
            this.loaded = true;
        }
        EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0);
        if (this.getAbility() != 2 || player != null) {
            // empty if block
        }
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.getAbilityType() != 0) {
            return false;
        }
        float amount = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int knockback = 0;
        boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), amount);
        if (var4) {
            int var5;
            if (this.getAbility() == 2) {
                this.setAbility(1);
                this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
                this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.wreckSpeedFast);
                knockback = 2;
            }
            if (knockback > 0) {
                // empty if block
            }
            if ((var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this)) > 0) {
                par1Entity.setFire(var5 * 4);
            }
        }
        return var4;
    }

    public void attackEntityWithRangedAttack(EntityLivingBase entity) {
        if (entity == null) {
            return;
        }
        double tx = entity.posX - this.posX;
        double ty = entity.boundingBox.minY - this.posY;
        double tz = entity.posZ - this.posZ;
        switch (this.getAbility()) {
            case 4: {
                EntityWreckBouncingProjectile projectile = new EntityWreckBouncingProjectile(this.worldObj, (EntityLivingBase)this, 35);
                projectile.setThrowableHeading(tx, ty, tz, 1.6f, 12.0f);
                this.worldObj.spawnEntityInWorld((Entity)projectile);
                this.setAbility(0);
                break;
            }
            case 6: {
                if (this.abilityTimer % 5 == 0) {
                    EntityWreckShot shot = new EntityWreckShot(this.worldObj, (EntityLivingBase)this, 15);
                    shot.setThrowableHeading(tx, ty, tz, 1.6f, 12.0f);
                    this.worldObj.spawnEntityInWorld((Entity)shot);
                }
                if (this.abilityTimer > 100) break;
                this.setAbility(0);
                break;
            }
            case 7: {
                if (this.abilityTimer % 40 == 0) {
                    EntityWreckExplosiveShot shot = new EntityWreckExplosiveShot(this.worldObj, (EntityLiving)this);
                    shot.setThrowableHeading(tx, ty, tz, 1.6f, 12.0f);
                    this.worldObj.spawnEntityInWorld((Entity)shot);
                }
                if (this.abilityTimer != 0) break;
                this.setAbility(0);
                break;
            }
            case 8: {
                if (this.abilityTimer % 40 == 0) {
                    EntityWreckShot shot = new EntityWreckShot(this.worldObj, (EntityLivingBase)this, 40);
                    shot.setThrowableHeading(tx, ty, tz, 1.6f, 12.0f);
                    this.worldObj.spawnEntityInWorld((Entity)shot);
                }
                if (this.abilityTimer != 0) break;
                this.setAbility(0);
            }
        }
    }

    public void onDeath(DamageSource damage) {
        int chance = this.rand.nextInt(99);
        this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, 7500));
        this.worldObj.setBlock((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0), (Block)Blocks.chest);
        IInventory te = (IInventory)this.worldObj.getTileEntity((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0));
        if (te != null) {
            if (this.rand.nextInt(5) == 0) {
                te.setInventorySlotContents(1, new ItemStack(JourneyItemsOther.eucaPortalGem, 1));
            }
            if (chance < 40) {
                te.setInventorySlotContents(2, new ItemStack(VanillaItemsOther.compressedArcana, 1));
            }
            if (chance < 20) {
                te.setInventorySlotContents(3, new ItemStack(VanillaItemsWeapons.arcanaSword, 1));
            }
            if (chance < 60) {
                te.setInventorySlotContents(4, new ItemStack(JourneyItemsOther.SpectralEye, 1));
            }
        }
    }

    public int getAbilityType() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    public void setAbilityType(int type) {
        this.dataWatcher.updateObject(17, (Object)type);
    }

    public int getAbility() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    public void setAbility(int ability) {
        this.dataWatcher.updateObject(18, (Object)ability);
    }

    @Override
    public String mobName() {
        return "Wreck";
    }

    @Override
    public String name() {
        return "Wreck";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

