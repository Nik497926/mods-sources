/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.Direction
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import java.util.List;
import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.projectile.EntityRaglokBomb;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityRaglok
extends EntityDivineRPGBoss {
    private int ability;
    private final int DEFAULT = 0;
    private final int LIGHTNING = 1;
    private final int BLIND = 2;
    private final int BOMBS = 3;
    private final int SLOW = 4;
    private double prevPlayerX;
    private double prevPlayerY;
    private double prevPlayerZ;
    private int abilityCooldown;
    private boolean loaded = false;
    private int rangedAttackCounter;
    private int deathTicks;

    public EntityRaglok(World par1) {
        super(par1);
        this.addAttackingAI();
        this.isImmuneToFire = true;
        this.ability = 0;
        this.setSize(1.5f, 4.0f);
    }

    protected void updateAITasks() {
        this.manageAbilities();
        super.updateAITasks();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.raglokHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.raglokDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.raglokSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.raglokFollowRange);
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

    @Override
    public void onLivingUpdate() {
        int z;
        int y;
        int x;
        super.onLivingUpdate();
        EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 32.0);
        if (!this.worldObj.isRemote && this.ticksExisted % 5 == 0) {
            for (x = (int)this.posX - 2; x < (int)this.posX + 2; ++x) {
                for (y = (int)this.boundingBox.minY; y < (int)this.boundingBox.minY + 4; ++y) {
                    for (z = (int)this.posZ - 2; z < (int)this.posZ + 2; ++z) {
                        if (this.worldObj.getBlock(x, y, z) != Blocks.obsidian && this.worldObj.getBlock(x, y, z) != TwilightBlocks.apalachiaOre && this.worldObj.getBlock(x, y, z) != TwilightBlocks.edenOre && this.worldObj.getBlock(x, y, z) != TwilightBlocks.mortumOre && this.worldObj.getBlock(x, y, z) != TwilightBlocks.skythernOre && this.worldObj.getBlock(x, y, z) != TwilightBlocks.wildwoodOre) continue;
                        this.worldObj.setBlockToAir(x, y, z);
                    }
                }
            }
        }
        if (!this.worldObj.isRemote && this.ticksExisted % 140 == 0 && player != null) {
            for (x = (int)this.posX - 2; x < (int)this.posX + 2; ++x) {
                for (y = (int)this.boundingBox.minY; y < (int)this.boundingBox.minY + 4; ++y) {
                    for (z = (int)this.posZ - 2; z < (int)this.posZ + 2; ++z) {
                        if (this.worldObj.getBlock(x, y, z) != ArcanaBlocks.ancientBrick && this.worldObj.getBlock(x, y, z) != Blocks.bedrock && this.worldObj.getBlock(x, y, z) != ArcanaBlocks.ancientStone && this.worldObj.getBlock(x, y, z) != ArcanaBlocks.degradedBrick && this.worldObj.getBlock(x, y, z) != ArcanaBlocks.arcaniumMetal || this.getHealth() <= 120.0f) continue;
                        this.setPositionAndUpdate(player.posX, player.posY, player.posZ);
                        this.worldObj.newExplosion((Entity)this, this.posX + 1.0, this.posY + 2.0, this.posZ + 1.0, 8.0f, false, true);
                    }
                }
            }
        }
    }

    public void manageAbilities() {
        int i;
        EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0);
        if (this.handleLavaMovement() && this.ticksExisted % 40 == 0) {
            if (player != null && !player.handleLavaMovement()) {
                this.setPositionAndUpdate(player.posX, player.posY, player.posZ);
                this.setAttackTarget((EntityLivingBase)player);
            } else if (player != null && player.handleLavaMovement()) {
                for (i = 0; i < 3; ++i) {
                    EntityRaglokBomb var2 = new EntityRaglokBomb(this.worldObj);
                    var2.setPosition(player.posX + 5.0, player.posY + 5.0, player.posZ + 5.0);
                    var2.motionX = (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0;
                    var2.motionY = -0.16;
                    var2.motionZ = (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0;
                    this.worldObj.spawnEntityInWorld((Entity)var2);
                    ++this.rangedAttackCounter;
                }
            }
        }
        if (this.getAITarget() == player && this.lastDamage > 1.0f) {
            int r = this.rand.nextInt(99);
            if (player != null && r < 10) {
                int var5;
                if (this.getHealth() > 120.0f) {
                    int var52 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this);
                    if (var52 > 0) {
                        player.setFire(var52 * 4);
                    }
                    if (r < 2) {
                        player.setPositionAndUpdate(this.posX, this.posY, this.posZ);
                    }
                    this.lastDamage = 0.0f;
                }
                if ((var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this)) > 0) {
                    player.setFire(var5 * 4);
                }
                this.lastDamage = 0.0f;
            }
            this.lastDamage = 0.0f;
        }
        if (this.abilityCooldown == 0 || this.ability == 0) {
            this.abilityCooldown = 300;
            switch (this.rand.nextInt(3)) {
                case 0: {
                    this.ability = 2;
                    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
                    break;
                }
                case 1: {
                    this.ability = 3;
                    this.rangedAttackCounter = 0;
                    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
                    break;
                }
                case 2: {
                    this.ability = 4;
                    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
                }
            }
            this.message();
        } else if (this.abilityCooldown > 0) {
            --this.abilityCooldown;
        }
        if (this.ability == 4 && player != null) {
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, 1, true));
        }
        if (this.ability == 1) {
            if (this.abilityCooldown % 40 == 0 && player != null) {
                ++this.rangedAttackCounter;
            } else if (this.abilityCooldown % 40 != 0 && this.abilityCooldown % 20 == 0 && player != null) {
                this.prevPlayerX = player.posX;
                this.prevPlayerY = player.posY;
                this.prevPlayerZ = player.posZ;
            }
            if (this.rangedAttackCounter == 10) {
                this.ability = 0;
            }
        }
        if (this.ability == 3) {
            if (this.abilityCooldown % 30 == 0 && player != null) {
                for (i = 0; i < 3; ++i) {
                    EntityRaglokBomb var2 = new EntityRaglokBomb(this.worldObj);
                    var2.setPosition(player.posX, player.posY + 5.0, player.posZ);
                    var2.motionX = (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0;
                    var2.motionY = -0.14;
                    var2.motionZ = (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0;
                    this.worldObj.spawnEntityInWorld((Entity)var2);
                    ++this.rangedAttackCounter;
                }
                if (this.getHealth() < 1000.0f) {
                    if (this.getHealth() < 200.0f) {
                        player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, 1, true));
                    } else {
                        for (i = 0; i < 3; ++i) {
                            EntityRaglokBomb var2 = new EntityRaglokBomb(this.worldObj);
                            var2.setPosition(player.posX + 5.0, player.posY + 5.0, player.posZ + 5.0);
                            var2.motionX = (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0;
                            var2.motionY = -0.16;
                            var2.motionZ = (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0;
                            this.worldObj.spawnEntityInWorld((Entity)var2);
                            ++this.rangedAttackCounter;
                        }
                    }
                }
            }
            if (this.rangedAttackCounter == 12) {
                this.ability = 0;
            }
        }
    }

    private void message() {
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(64.0, 64.0, 64.0));
        block6: for (int var1 = 0; var1 < list.size(); ++var1) {
            if (!(list.get(var1) instanceof EntityPlayer)) continue;
            EntityPlayer player = (EntityPlayer)list.get(var1);
            switch (this.ability) {
                case 1: {
                    this.playSound(Sounds.raglokGuardian.getPrefixedName(), 1.0f, 1.0f);
                    if (!this.worldObj.isRemote) continue block6;
                    continue block6;
                }
                case 2: {
                    this.playSound(Sounds.raglokDark.getPrefixedName(), 1.0f, 1.0f);
                    if (!this.worldObj.isRemote) continue block6;
                }
                case 3: {
                    this.playSound(Sounds.raglokRain.getPrefixedName(), 1.0f, 1.0f);
                    if (!this.worldObj.isRemote) continue block6;
                    continue block6;
                }
                case 4: {
                    this.playSound(Sounds.raglokNothing.getPrefixedName(), 1.0f, 1.0f);
                    if (!this.worldObj.isRemote) continue block6;
                    continue block6;
                }
            }
        }
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Integer(100));
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getDeathSound() {
        EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0);
        return Sounds.raglokAvenge.getPrefixedName();
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        int var2 = (int)EntityStats.raglokDamage;
        int var3 = 0;
        boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)var2);
        if (var4) {
            int var5;
            if (this.ability == 4) {
                var3 = 5;
            }
            if (var3 > 0) {
                this.motionX *= 0.6;
                this.motionZ *= 0.6;
            }
            if ((var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this)) > 0) {
                par1Entity.setFire(var5 * 5);
            }
        }
        return var4;
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.isExplosion()) {
            return false;
        }
        return super.attackEntityFrom(source, amount);
    }

    public void onDeath(DamageSource damage) {
        int chance = this.rand.nextInt(99);
        this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, 3000));
        this.worldObj.setBlock((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0), (Block)Blocks.chest);
        IInventory te = (IInventory)this.worldObj.getTileEntity((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0));
        if (te != null) {
            te.setInventorySlotContents(0, new ItemStack(VanillaItemsOther.tarnishedCrystal, 1));
            if (this.rand.nextInt(5) == 0) {
                te.setInventorySlotContents(5, new ItemStack(JourneyItemsOther.eucaPortalGem, 1));
            }
            if (chance < 25) {
                te.setInventorySlotContents(2, new ItemStack(VanillaItemsWeapons.hitHealSword, 1));
            }
            if (chance > 12) {
                te.setInventorySlotContents(3, new ItemStack(VanillaItemsOther.ancientSword, 1));
            }
        }
    }

    @Override
    public String mobName() {
        return "Raglok";
    }

    @Override
    public String name() {
        return "Raglok";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

