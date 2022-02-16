/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityFlying
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 *  omegalevels.ItemGem$EnumType
 *  omegalevels.OmegaLevels
 */
package net.divinerpg.entities.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.entities.lelyetia.BossClear;
import net.divinerpg.entities.lelyetia.EntityBoss;
import net.divinerpg.entities.lelyetia.EntityGrawShot;
import net.divinerpg.entities.lelyetia.EntityNoRange;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.divinerpg.utils.items.SkinItems;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import omegalevels.ItemGem;
import omegalevels.OmegaLevels;

public class EntityGraw
extends EntityFlying
implements IMob,
EntityNoRange,
EntityBoss {
    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity;
    private int pullcounter = 0;
    private boolean isPull = false;
    private int musicTick = 1;
    private int aggroCooldown;
    public int prevAttackCounter;
    public int attackCounter;
    private int explosionStrength = 3;
    private static final String __OBFID = "CL_00001689";

    public EntityGraw(World par1World) {
        super(par1World);
        this.setSize(5.0f, 3.5f);
        this.isImmuneToFire = false;
        this.experienceValue = 5;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource == DamageSource.inWall) {
            BossClear.clear((int)((Entity)this).posX, (int)((Entity)this).posY, (int)((Entity)this).posZ, ((Entity)this).worldObj, (EntityLivingBase)this);
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    public void onDeath(DamageSource var1) {
        super.onDeath(var1);
        if (!((Entity)this).worldObj.isRemote && var1.getEntity() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer)var1.getEntity();
            List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(50.0, 50.0, 50.0));
            for (EntityPlayer entityPlayer : players) {
            }
        }
    }

    protected void dropFewItems(boolean par1, int par2) {
        int chance = this.rand.nextInt(99);
        this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, 3000));
        this.worldObj.setBlock((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0), (Block)Blocks.chest);
        IInventory te = (IInventory)this.worldObj.getTileEntity((int)Math.floor(this.posX + 0.0), (int)Math.floor(this.posY + 1.0), (int)Math.floor(this.posZ + 0.0));
        if (te != null) {
            te.setInventorySlotContents(0, new ItemStack(VanillaItemsOther.mystic, 2));
            te.setInventorySlotContents(1, new ItemStack(VanillaItemsOther.arlemiteIngot, 16));
            te.setInventorySlotContents(2, new ItemStack(VanillaItemsOther.rupeeIngot, 16));
            if (chance < 23) {
                te.setInventorySlotContents(3, new ItemStack(JourneyItemsOther.SpectralEye, 2));
            }
            if (chance < 49) {
                te.setInventorySlotContents(4, new ItemStack(VanillaItemsOther.mysticSoul, 2));
            }
            if (chance > 51 && chance < 53) {
                te.setInventorySlotContents(4, new ItemStack(VanillaItemsOther.mysticSoul, 3));
            }
            if ((double)chance < 0.32122 && this.worldObj.getTotalWorldTime() >= 13000L) {
                te.setInventorySlotContents(8, new ItemStack(JourneyItemsWeapon.spectralSword, 1));
            }
            if (chance < 11) {
                int chance3 = this.rand.nextInt(99);
                if (chance3 < 25) {
                    te.setInventorySlotContents(5, new ItemStack(SkinItems.awakenedpremBoots, 1));
                } else if (chance3 > 25 && chance < 50) {
                    te.setInventorySlotContents(5, new ItemStack(SkinItems.awakenedpremLeggings, 1));
                } else if (chance3 > 50 && chance < 75) {
                    te.setInventorySlotContents(5, new ItemStack(SkinItems.awakenedpremHelmet, 1));
                } else if (chance3 > 75) {
                    te.setInventorySlotContents(5, new ItemStack(SkinItems.awakenedpremChestplate, 1));
                }
            }
            if (chance < 2) {
                te.setInventorySlotContents(6, new ItemStack((Item)OmegaLevels.gem, 1, ItemGem.EnumType.ORANGE.ordinal()));
            }
            if (chance < 60) {
                te.setInventorySlotContents(7, new ItemStack(IceikaItems.snowflake, 12));
            }
        }
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (((Entity)this).worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
            this.despawnEntity();
        }
        --this.musicTick;
        if (this.musicTick == 0) {
            this.musicTick = 195;
        }
        if (((Entity)this).ticksExisted % 17 == 0) {
            for (int i = (int)((Entity)this).posX - 7; i < (int)(((Entity)this).posX + 7.0); ++i) {
                for (int j = (int)((Entity)this).posY - 2; j < (int)(((Entity)this).posY + 5.0); ++j) {
                    for (int k = (int)((Entity)this).posZ - 7; k < (int)(((Entity)this).posZ + 7.0); ++k) {
                        Block b;
                        if (((Entity)this).worldObj.isRemote || (b = ((Entity)this).worldObj.getBlock(i, j, k)) != JourneyBlocks.WoodAchony && b != JourneyBlocks.WoodChurry && b != JourneyBlocks.LelyetianCore && b != JourneyBlocks.LeavesAchony && b != JourneyBlocks.LeavesChurry) continue;
                        ((Entity)this).worldObj.setBlock(i, j, k, Blocks.air);
                    }
                }
            }
        }
        if (((Entity)this).posY > 110.0) {
            ((Entity)this).motionY -= 4.5;
            ((Entity)this).motionX += (double)(-1.5f + (float)this.rand.nextInt(3));
            ((Entity)this).motionZ += (double)(-1.5f + (float)this.rand.nextInt(3));
        }
        if (this.pullcounter == 0) {
            if (this.isPull) {
                this.isPull = false;
                this.pullcounter = 340;
            } else {
                this.isPull = true;
                this.pullcounter = 60;
            }
        }
        if (!this.isPull) {
            --this.pullcounter;
            List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(85.0, 85.0, 85.0));
            for (EntityPlayer e : players) {
                if (e.capabilities.isCreativeMode) continue;
                e.addVelocity(Math.signum(((Entity)this).posX - e.posX) * 0.008, Math.signum(((Entity)this).posY - e.posY) * 0.005, Math.signum(((Entity)this).posZ - e.posZ) * 0.008);
            }
        } else {
            --this.pullcounter;
            List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(85.0, 85.0, 85.0));
            for (EntityPlayer e : players) {
                if (e.capabilities.isCreativeMode) continue;
                e.addVelocity(Math.signum(((Entity)this).posX - e.posX) * 0.139, Math.signum(((Entity)this).posY - e.posY) * 0.04, Math.signum(((Entity)this).posZ - e.posZ) * 0.139);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_110182_bF() {
        return this.dataWatcher.getWatchableObjectByte(16) != 0;
    }

    public boolean getCanSpawnHere() {
        return ((Entity)this).worldObj.difficultySetting != EnumDifficulty.PEACEFUL && ((Entity)this).worldObj.checkNoEntityCollision(((Entity)this).boundingBox) && ((Entity)this).worldObj.getCollidingBoundingBoxes((Entity)this, ((Entity)this).boundingBox).isEmpty() && !((Entity)this).worldObj.isAnyLiquid(((Entity)this).boundingBox) && this.rand.nextInt(4) == 2;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(3300.0);
    }

    protected void updateEntityActionState() {
        byte b2;
        byte b1;
        if (!((Entity)this).worldObj.isRemote && ((Entity)this).worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
            this.setDead();
        }
        if (!((Entity)this).worldObj.isRemote && ((Entity)this).worldObj.checkNoEntityCollision(((Entity)this).boundingBox) && ((Entity)this).worldObj.getCollidingBoundingBoxes((Entity)this, ((Entity)this).boundingBox).isEmpty() && !((Entity)this).worldObj.isAnyLiquid(((Entity)this).boundingBox)) {
            this.setDead();
        }
        this.despawnEntity();
        this.prevAttackCounter = this.attackCounter;
        double d0 = this.waypointX - ((Entity)this).posX;
        double d2 = this.waypointY - ((Entity)this).posY;
        double d3 = this.waypointZ - ((Entity)this).posZ;
        double d4 = d0 * d0 + d2 * d2 + d3 * d3;
        if (d4 < 1.0 || d4 > 3600.0) {
            this.waypointX = ((Entity)this).posX + (double)(this.rand.nextFloat() * 2.0f) - 16.0;
            this.waypointY = ((Entity)this).posY + (double)(this.rand.nextFloat() * 1.5f) - 16.0;
            this.waypointZ = ((Entity)this).posZ + (double)(this.rand.nextFloat() * 2.0f) - 16.0;
        }
        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d4 = (double)MathHelper.sqrt_double((double)d4))) {
                ((Entity)this).motionX += d0 / d4 * 0.1;
                ((Entity)this).motionY += d2 / d4 * 0.1;
                ((Entity)this).motionZ += d3 / d4 * 0.1;
            } else {
                this.waypointX = ((Entity)this).posX;
                this.waypointY = ((Entity)this).posY;
                this.waypointZ = ((Entity)this).posZ;
            }
        }
        if (this.targetedEntity != null && this.targetedEntity.isDead) {
            this.targetedEntity = null;
        }
        if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
            this.targetedEntity = ((Entity)this).worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 100.0);
            if (this.targetedEntity != null) {
                this.aggroCooldown = 10;
            }
        }
        double d5 = 64.0;
        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity((Entity)this) < 4096.0) {
            float n;
            double d6 = this.targetedEntity.posX - ((Entity)this).posX;
            double d7 = this.targetedEntity.boundingBox.minY + (double)(this.targetedEntity.height / 2.0f) - ((Entity)this).posY + (double)(((Entity)this).height / 2.0f);
            double d8 = this.targetedEntity.posZ - ((Entity)this).posZ;
            ((Entity)this).rotationYaw = n = -((float)Math.atan2(d6, d8)) * 180.0f / (float)Math.PI;
            ((EntityLivingBase)this).renderYawOffset = n;
            if (this.canEntityBeSeen(this.targetedEntity)) {
                ++this.attackCounter;
                if (this.attackCounter == 5) {
                    EntityGrawShot entitylargefireball = new EntityGrawShot(((Entity)this).worldObj, this);
                    double var3 = this.targetedEntity.posX - ((Entity)this).posX;
                    double var4 = this.targetedEntity.posY + (double)this.targetedEntity.getEyeHeight() - (double)1.1f - ((Entity)entitylargefireball).posY;
                    double var5 = this.targetedEntity.posZ - ((Entity)this).posZ;
                    float var6 = MathHelper.sqrt_double((double)(var3 * var3 + var5 * var5)) * 0.2f;
                    entitylargefireball.setThrowableHeading(var3, var4 + (double)var6, var5, 1.6f, 12.0f);
                    ((Entity)this).worldObj.spawnEntityInWorld((Entity)entitylargefireball);
                    this.attackCounter = -10;
                }
            } else if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        } else {
            float n2;
            ((Entity)this).rotationYaw = n2 = -((float)Math.atan2(((Entity)this).motionX, ((Entity)this).motionZ)) * 180.0f / (float)Math.PI;
            ((EntityLivingBase)this).renderYawOffset = n2;
            if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        }
        if (!((Entity)this).worldObj.isRemote && (b1 = this.dataWatcher.getWatchableObjectByte(16)) != (b2 = (byte)(this.attackCounter > 10 ? 1 : 0))) {
            this.dataWatcher.updateObject(16, (Object)b2);
        }
    }

    private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
        double d4 = (this.waypointX - ((Entity)this).posX) / par7;
        double d5 = (this.waypointY - ((Entity)this).posY) / par7;
        double d6 = (this.waypointZ - ((Entity)this).posZ) / par7;
        AxisAlignedBB axisalignedbb = ((Entity)this).boundingBox.copy();
        int i = 1;
        while ((double)i < par7) {
            axisalignedbb.offset(d4, d5, d6);
            if (!((Entity)this).worldObj.getCollidingBoundingBoxes((Entity)this, axisalignedbb).isEmpty()) {
                return false;
            }
            ++i;
        }
        return true;
    }

    protected String getLivingSound() {
        return "divinerpg:GrawLiving";
    }

    protected String getHurtSound() {
        return "divinerpg:GrawHit";
    }

    protected String getDeathSound() {
        return "divinerpg:GrawDeath";
    }

    public int getMaxSpawnedInChunk() {
        return 8;
    }
}

