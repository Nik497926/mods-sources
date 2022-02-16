/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntityScorcherShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityScorcher
extends EntityDivineRPGMob {
    private float heightOffset = 0.5f;
    private int heightOffsetUpdateTime;
    private int anger;

    public EntityScorcher(World var1) {
        super(var1);
        this.isImmuneToFire = true;
        this.experienceValue = 20;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.scorcherHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.scorcherDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.scorcherSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.scorcherFollowRange);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Byte(0));
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.scorcher);
    }

    protected String getHurtSound() {
        return "mob.blaze.hit";
    }

    protected String getDeathSound() {
        return "mob.blaze.death";
    }

    public int getBrightnessForRender(float var1) {
        return 0xF000F0;
    }

    public float getBrightness(float var1) {
        return 1.0f;
    }

    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.isWet()) {
                this.attackEntityFrom(DamageSource.drown, 1.0f);
            }
            if (this.entityToAttack == null && this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 22.0) != null) {
                this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 22.0);
            }
            if (this.getEntityToAttack() != null && this.getEntityToAttack() instanceof EntityPlayer && ((EntityPlayer)this.getEntityToAttack()).capabilities.isCreativeMode) {
                this.entityToAttack = null;
            }
            --this.heightOffsetUpdateTime;
            if (this.heightOffsetUpdateTime <= 0) {
                this.heightOffsetUpdateTime = 100;
                this.heightOffset = 0.5f + (float)this.rand.nextGaussian() * 3.0f;
            }
        }
        if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
            this.motionY += ((double)0.3f - this.motionY) * (double)0.3f;
        } else if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() <= this.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
            this.attackEntity(this.getEntityToAttack(), this.getDistanceToEntity(this.getEntityToAttack()));
        }
        if (this.rand.nextInt(24) == 0) {
            this.worldObj.playSoundEffect(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, "fire.fire", 1.0f + this.rand.nextFloat(), this.rand.nextFloat() * 0.7f + 0.3f);
        }
        if (!this.onGround && this.motionY < 0.0) {
            this.motionY *= 0.6;
        }
        for (int var1 = 0; var1 < 2; ++var1) {
            this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
        }
        this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
        super.onLivingUpdate();
    }

    protected void attackEntity(Entity var1, float var2) {
        if (this.attackTime <= 0 && var2 < 2.0f && var1.boundingBox.maxY > this.boundingBox.minY && var1.boundingBox.minY < this.boundingBox.maxY) {
            this.attackTime = 0;
            this.attackEntityAsMob(var1);
        } else if (var2 < 30.0f) {
            double var3 = var1.posX - this.posX;
            double var5 = var1.boundingBox.minY + (double)(var1.height / 2.0f) - (this.posY + (double)(this.height / 2.0f));
            double var7 = var1.posZ - this.posZ;
            if (this.attackTime == 0) {
                ++this.anger;
                if (this.anger == 1) {
                    this.attackTime = 0;
                    this.idk(true);
                } else if (this.anger <= 4) {
                    this.attackTime = 6;
                } else {
                    this.attackTime = 0;
                    this.anger = 0;
                    this.idk(false);
                }
                if (this.anger > 1) {
                    float var9 = MathHelper.sqrt_float((float)var2) * 0.5f;
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                    for (int var10 = 0; var10 < 1; ++var10) {
                        EntityScorcherShot entity = new EntityScorcherShot(this.worldObj, (EntityLivingBase)this, var3, var5, var7);
                        entity.posY = this.posY + (double)(this.height / 2.0f) + 1.5;
                        this.worldObj.spawnEntityInWorld((Entity)entity);
                    }
                }
            }
            this.rotationYaw = (float)(Math.atan2(var7, var3) * 180.0 / Math.PI) - 90.0f;
            this.hasAttacked = true;
        }
    }

    protected void fall(float var1) {
    }

    protected Item getDropItem() {
        return VanillaItemsOther.purpleBlaze;
    }

    public boolean isBurning() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    protected void dropFewItems(boolean var1, int var2) {
        int var3 = this.rand.nextInt(2 + var2);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.dropItem(VanillaItemsOther.purpleBlaze, 1);
        }
        if (this.rand.nextInt(99) < 45) {
            this.dropItem(VanillaItemsOther.netheriumShards, 1);
        }
    }

    public void idk(boolean var1) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);
        var2 = var1 ? (byte)(var2 | 1) : (byte)(var2 & 0xFFFFFFFE);
        this.dataWatcher.updateObject(16, (Object)var2);
    }

    @Override
    public String mobName() {
        return "Scorcher";
    }
}

