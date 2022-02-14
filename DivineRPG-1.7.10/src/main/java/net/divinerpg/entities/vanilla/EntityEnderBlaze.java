/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.Direction
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  omegalevels.ItemGem$EnumType
 *  omegalevels.OmegaLevels
 */
package net.divinerpg.entities.vanilla;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.DivineRPG;
import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntityMagmaFireball;
import net.divinerpg.network.MessageBossBar;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import omegalevels.ItemGem;
import omegalevels.OmegaLevels;

public class EntityEnderBlaze
extends EntityDivineRPGMob {
    private int field_70846_g;
    private int time = 1;
    long timer;
    public int secondHeal = 10;
    private int level = 1;
    private int players = 1;
    private boolean updated = false;
    private int attackdelay;
    private int attackBuff;
    private boolean isBuff;

    public EntityEnderBlaze(World world, int time, int level) {
        super(world);
        this.experienceValue = 10;
        this.time = time;
        this.level = level;
        this.addAI();
        this.setSize(1.0f, 2.0f);
        this.attackdelay = 3;
    }

    public EntityEnderBlaze(World world) {
        super(world);
        this.experienceValue = 0;
        this.time = 500;
        this.level = 1;
        this.addAI();
        this.setSize(1.0f, 2.0f);
        this.attackdelay = 3;
    }

    protected void addAI() {
        this.tasks.addTask(3, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.isImmuneToFire = true;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }

    @Override
    public String mobName() {
        return "Ender-Blaze";
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.ancientBlazeHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.ancientBlazeDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.ancientBlazeSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.ancientBlazeFollowRange);
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.level = tag.getInteger("bossLevel");
        this.time = tag.getInteger("bossTimer");
        this.maxHurtResistantTime = 7;
        this.attackdelay = 3;
        this.updated = false;
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("bossLevel", this.level);
        tag.setInteger("bossTimer", this.time);
    }

    protected String getLivingSound() {
        return "mob.blaze.breathe";
    }

    protected String getHurtSound() {
        return "mob.blaze.hit";
    }

    protected String getDeathSound() {
        return "mob.blaze.death";
    }

    @SideOnly(value=Side.CLIENT)
    public int getBrightnessForRender(float brightness) {
        return 0xF000F0;
    }

    public float getBrightness(float brightness) {
        return 1.0f;
    }

    public void setInPortal() {
        if (this.timeUntilPortal > 0) {
            this.timeUntilPortal = this.getPortalCooldown();
        } else {
            double d0 = this.prevPosX - this.posX;
            double d1 = this.prevPosZ - this.posZ;
            if (!this.worldObj.isRemote && !this.inPortal) {
                this.teleportDirection = Direction.getMovementDirection((double)d0, (double)d1);
            }
            this.inPortal = false;
        }
    }

    public void onLivingUpdate() {
        if (!this.updated) {
            this.maxHurtResistantTime = 7;
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.ancientBlazeHealth * (double)this.level);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.ancientBlazeDamage * (double)this.level);
            this.setHealth((float)(EntityStats.ancientBlazeHealth * (double)this.level + 750.0));
            this.updated = true;
        }
        if (this.hurtResistantTime >= 6 && this.rand.nextInt(99) < 1 && this.entityToAttack != null) {
            this.teleportToEntity(this.entityToAttack);
            if (this.rand.nextInt(99) < 25) {
                this.entityToAttack.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 100.0f);
            }
            this.attackBuff = 20;
        }
        if (!this.worldObj.isRemote && this.ticksExisted % 20 == 0) {
            DivineRPG.network.sendToAllAround((IMessage)new MessageBossBar(this.time, this.level), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 30.0));
        }
        if (this.ticksExisted % 500 == 0) {
            this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 52.0);
            if (this.entityToAttack != null) {
                this.teleportToEntity(this.entityToAttack);
            }
            this.attackBuff = 20;
            this.isBuff = true;
        }
        if (this.ticksExisted % 40 == 0 && this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 100.0) != null) {
            this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 100.0);
        }
        if (this.ticksExisted % 20 == 0 && this.time > 0) {
            --this.time;
        }
        if (this.attackBuff <= 0) {
            this.isBuff = false;
        }
        if (!this.worldObj.isRemote && this.ticksExisted % this.attackdelay == 0 && this.entityToAttack != null || this.isBuff && this.attackBuff > 0 && this.entityToAttack != null) {
            --this.attackBuff;
            this.attackEntity(this.entityToAttack, this.getDistanceToEntity(this.entityToAttack));
            if (this.entityToAttack instanceof EntityPlayer) {
                EntityPlayer en = (EntityPlayer)this.entityToAttack;
                en.clearActivePotions();
            }
        }
        if (this.rand.nextInt(24) == 0) {
            this.worldObj.playSoundEffect(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, "fire.fire", 1.0f + this.rand.nextFloat(), this.rand.nextFloat() * 0.7f + 0.3f);
        }
        this.motionY = this.entityToAttack != null && this.posY < this.entityToAttack.posY + 2.0 ? 0.05 : 1.0E-4;
        for (int var1 = 0; var1 < 1; ++var1) {
            this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
            this.worldObj.spawnParticle("flame", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
        }
        super.onLivingUpdate();
    }

    private boolean teleportToEntity(Entity entity) {
        double d1 = entity.posX;
        double d2 = entity.posY;
        double d3 = entity.posZ;
        return this.teleportTo(d1, d2, d3);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    protected void attackEntity(Entity entity, float par2) {
        if (entity != null && par2 < 32.0f) {
            double var3 = entity.posX - this.posX;
            double var5 = entity.boundingBox.minY + (double)(entity.height / 2.0f) - (this.posY + (double)(this.height / 2.0f));
            double var7 = entity.posZ - this.posZ;
            if (this.attackTime == 0) {
                ++this.field_70846_g;
                if (this.field_70846_g == 1) {
                    this.attackTime = 2;
                } else if (this.field_70846_g <= this.level * this.players) {
                    this.attackTime = 1;
                } else {
                    this.attackTime = 3;
                    this.field_70846_g = 0;
                }
                if (this.field_70846_g > 1) {
                    float var9 = MathHelper.sqrt_float((float)par2) * 0.5f;
                    this.worldObj.playAuxSFXAtEntity(null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                    for (int var10 = 0; var10 < 1; ++var10) {
                        EntityMagmaFireball shot = new EntityMagmaFireball(this.worldObj, (EntityLivingBase)this, var3 + this.rand.nextGaussian() * (double)var9, var5 - 1.0, var7 + this.rand.nextGaussian() * (double)var9, 2.5f * (float)this.level, this);
                        shot.posY = this.posY + (double)(this.height / 2.0f) + 0.5;
                        if (this.worldObj.isRemote) continue;
                        this.worldObj.spawnEntityInWorld((Entity)shot);
                    }
                }
            }
            this.hasAttacked = true;
        }
    }

    public void onDeath(DamageSource damage) {
        if (this.time > 0) {
            this.setDead();
            ++this.level;
            EntityEnderBlaze e = new EntityEnderBlaze(this.worldObj, this.time + 60, this.level);
            e.setPosition(this.posX, this.posY, this.posZ);
            this.worldObj.spawnEntityInWorld((Entity)e);
            if (this.level > 20) {
                this.deathMessage();
            }
            if (this.level < 5) {
                this.dropItem(VanillaItemsOther.netheriteIngot, this.rand.nextInt(12));
            }
            this.dropItem(VanillaItemsOther.bloodgem, this.rand.nextInt(4));
            if (this.level > 5 && this.level < 10) {
                this.dropItem(VanillaItemsOther.netheriumShards, this.rand.nextInt(5));
            }
            if (this.level > 10 && this.level < 20 && this.rand.nextInt(3) == 0) {
                this.dropItem(VanillaItemsOther.mysticChunk, 1);
            }
            if (this.level == 10 || this.level == 15 || this.level == 20 || this.level == 25 || this.level == 30 || this.level == 35 | this.level == 40) {
                if (this.rand.nextInt(100) < 50) {
                    this.dropItem(VanillaItemsOther.mysticKey, 1);
                    this.dropItem(JourneyItemsOther.SpectralDust, 1);
                } else {
                    this.dropItem(VanillaItemsOther.boilKey, 1);
                    this.dropItem(JourneyItemsOther.SpectralEye, 1);
                }
            }
            if (this.level == 45 && !this.worldObj.isRemote) {
                this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, 8000));
                this.worldObj.setBlock((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0), (Block)Blocks.chest);
                IInventory te = (IInventory)this.worldObj.getTileEntity((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0));
                if (te != null) {
                    te.setInventorySlotContents(0, new ItemStack((Item)OmegaLevels.gem, 1, ItemGem.EnumType.PURPLE.ordinal()));
                    te.setInventorySlotContents(1, new ItemStack((Item)OmegaLevels.gem, 2, ItemGem.EnumType.BLUE.ordinal()));
                    if (this.rand.nextInt(100) < 8) {
                        te.setInventorySlotContents(2, new ItemStack((Item)OmegaLevels.gem, 4, ItemGem.EnumType.ORANGE.ordinal()));
                    }
                }
            }
            if (this.level >= 30 && this.rand.nextInt(3) == 0) {
                this.dropItem(VanillaItemsOther.mystic, 1);
            }
            if (this.level == 30 || this.level == 43) {
                this.dropItem(VanillaItemsOther.mysticSoul, 1);
            }
        } else {
            this.deathMessage();
            if (this.level > 10) {
                this.dropItem(VanillaItemsOther.mysticChunk, 1);
            }
            if (this.level >= 35 && this.rand.nextInt(100) < 10) {
                this.dropItem(VanillaItemsWeapons.heritageSword, 1);
            }
        }
    }

    private void deathMessage() {
        StringBuilder s = new StringBuilder();
        int iter = 0;
        for (Object obj : this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(20.0, 20.0, 20.0))) {
            if (!(obj instanceof EntityPlayerMP)) continue;
            EntityPlayerMP e = (EntityPlayerMP)obj;
            ++iter;
            s.append(e.getCommandSenderName()).append(" ");
        }
        if (iter > 1) {
            MinecraftServer.getServer().getConfigurationManager().sendChatMsg((IChatComponent)new ChatComponentText("\u00a7c\u0418\u0444\u0440\u0438\u0442 \u00a75\u041a\u0440\u0430\u044f \u00a74" + this.level + " \u00a74\u0443\u0440\u043e\u0432\u043d\u044f \u00a76\u0431\u044b\u043b \u0443\u0431\u0438\u0442 \u0438\u0433\u0440\u043e\u043a\u0430\u043c\u0438: \u00a7e" + s));
        } else {
            MinecraftServer.getServer().getConfigurationManager().sendChatMsg((IChatComponent)new ChatComponentText("\u00a7c\u0418\u0444\u0440\u0438\u0442 \u00a75\u041a\u0440\u0430\u044f \u00a74" + this.level + " \u00a74\u0443\u0440\u043e\u0432\u043d\u044f \u00a76\u0431\u044b\u043b \u0443\u0431\u0438\u0442 \u0438\u0433\u0440\u043e\u043a\u043e\u043c: \u00a7e" + s));
        }
    }

    private boolean teleportTo(double eX, double eY, double eZ) {
        int d3 = (int)eX + this.rand.nextInt(24) - 12;
        int d4 = (int)eY;
        int d5 = (int)eZ + this.rand.nextInt(24) - 12;
        this.setPositionAndUpdate(d3, d4, d5);
        for (int x = (int)this.posX - 3; x < (int)this.posX + 3; ++x) {
            for (int y = (int)this.boundingBox.minY - 2; y < (int)this.boundingBox.minY + 2; ++y) {
                for (int z = (int)this.posZ - 3; z < (int)this.posZ + 3; ++z) {
                    if (this.worldObj.getBlock(x, y, z) == Blocks.bedrock) continue;
                    this.worldObj.setBlockToAir(x, y, z);
                }
            }
        }
        int short1 = 128;
        for (int l = 0; l < short1; ++l) {
            double d6 = (double)l / ((double)short1 - 1.0);
            float f = (this.rand.nextFloat() - 0.5f) * 0.2f;
            float f1 = (this.rand.nextFloat() - 0.5f) * 0.2f;
            float f2 = (this.rand.nextFloat() - 0.5f) * 0.2f;
            double d7 = (double)d3 + (this.posX - (double)d3) * d6 + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
            double d8 = (double)d4 + (this.posY - (double)d4) * d6 + this.rand.nextDouble() * (double)this.height;
            double d9 = (double)d5 + (this.posZ - (double)d5) * d6 + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
            this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
        }
        this.worldObj.playSoundEffect((double)d3, (double)d4, (double)d5, "mob.endermen.portal", 1.0f, 1.0f);
        this.playSound("mob.endermen.portal", 1.0f, 1.0f);
        return true;
    }

    protected void fall(float p_fall_1_) {
    }

    protected Item getDropItem() {
        return Items.blaze_rod;
    }

    public boolean isBurning() {
        return false;
    }
}

