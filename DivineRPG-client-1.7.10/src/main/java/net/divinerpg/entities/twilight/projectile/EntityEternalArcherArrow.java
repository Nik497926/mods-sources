/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S2BPacketChangeGameState
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight.projectile;

import java.util.List;
import net.divinerpg.DivineRPG;
import net.divinerpg.entities.base.EntityDivineArrow;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityEternalArcherArrow
extends EntityDivineArrow {
    private static final int FLAME = 0;
    private static final int WITHER = 1;
    private static final int EXPLODE = 2;
    private static final int SLOW = 3;
    private static final int NAUSEA = 4;
    private static final int BLIND = 5;

    public EntityEternalArcherArrow(World w) {
        super(w);
    }

    public EntityEternalArcherArrow(World w, EntityLivingBase shooter, EntityLivingBase target, int arm) {
        super(w, shooter, target, 1.6f, 5.0f, 26.0f, "furyArrow");
        this.dataWatcher.updateObject(18, (Object)arm);
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)0);
    }

    @Override
    public void onUpdate() {
        Block block;
        super.onEntityUpdate();
        if (this.getArcherAbility() == 0) {
            this.setFire(5);
            DivineRPG.proxy.spawnParticle(this.worldObj, this.posX, this.posY, this.posZ, "orangeFlame", true);
        }
        if (this.getArcherAbility() == 1) {
            DivineRPG.proxy.spawnParticle(this.worldObj, this.posX, this.posY, this.posZ, "blackFlame", true);
        }
        if (this.prevRotationPitch == 0.0f && this.prevRotationYaw == 0.0f) {
            float f = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180.0 / Math.PI);
        }
        if ((block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile)).getMaterial() != Material.air) {
            block.setBlockBoundsBasedOnState((IBlockAccess)this.worldObj, this.xTile, this.yTile, this.zTile);
            AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);
            if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper((double)this.posX, (double)this.posY, (double)this.posZ))) {
                this.inGround = true;
            }
        }
        if (this.arrowShake > 0) {
            --this.arrowShake;
        }
        if (this.inGround) {
            int j = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
            if (block == this.inTile && j == this.inData) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1200) {
                    this.setDead();
                }
            } else {
                this.inGround = false;
                this.motionX *= (double)(this.rand.nextFloat() * 0.2f);
                this.motionY *= (double)(this.rand.nextFloat() * 0.2f);
                this.motionZ *= (double)(this.rand.nextFloat() * 0.2f);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            float f1;
            int i;
            ++this.ticksInAir;
            Vec3 vec31 = Vec3.createVectorHelper((double)this.posX, (double)this.posY, (double)this.posZ);
            Vec3 vec3 = Vec3.createVectorHelper((double)(this.posX + this.motionX), (double)(this.posY + this.motionY), (double)(this.posZ + this.motionZ));
            MovingObjectPosition position = this.worldObj.func_147447_a(vec31, vec3, false, true, false);
            vec31 = Vec3.createVectorHelper((double)this.posX, (double)this.posY, (double)this.posZ);
            vec3 = Vec3.createVectorHelper((double)(this.posX + this.motionX), (double)(this.posY + this.motionY), (double)(this.posZ + this.motionZ));
            if (position != null) {
                vec3 = Vec3.createVectorHelper((double)position.hitVec.xCoord, (double)position.hitVec.yCoord, (double)position.hitVec.zCoord);
            }
            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
            double d0 = 0.0;
            for (i = 0; i < list.size(); ++i) {
                double d1;
                AxisAlignedBB axisalignedbb1;
                MovingObjectPosition movingobjectposition1;
                Entity entity1 = (Entity)list.get(i);
                if (!entity1.canBeCollidedWith() || entity1 == this.shootingEntity && this.ticksInAir < 5 || (movingobjectposition1 = (axisalignedbb1 = entity1.boundingBox.expand((double)(f1 = 0.3f), (double)f1, (double)f1)).calculateIntercept(vec31, vec3)) == null || !((d1 = vec31.distanceTo(movingobjectposition1.hitVec)) < d0) && d0 != 0.0) continue;
                entity = entity1;
                d0 = d1;
            }
            if (entity != null) {
                position = new MovingObjectPosition(entity);
            }
            if (position != null && position.entityHit != null && position.entityHit instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer)position.entityHit;
                if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer)) {
                    position = null;
                }
            }
            if (position != null) {
                if (position.entityHit != null) {
                    float f2 = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ));
                    int k = MathHelper.ceiling_double_int((double)((double)f2 * this.damageMin));
                    if ((double)k > this.damageMax) {
                        k = MathHelper.ceiling_double_int((double)this.damageMax);
                    }
                    DamageSource damagesource = null;
                    damagesource = this.shootingEntity == null ? DamageSource.causeArrowDamage((EntityArrow)this, (Entity)this) : DamageSource.causeArrowDamage((EntityArrow)this, (Entity)this.shootingEntity);
                    if (position.entityHit instanceof EntityLivingBase) {
                        if (this.getArcherAbility() == 1) {
                            ((EntityLivingBase)position.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 100, 2, true));
                        } else if (this.getArcherAbility() == 3) {
                            ((EntityLivingBase)position.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 2, true));
                        } else if (this.getArcherAbility() == 5) {
                            ((EntityLivingBase)position.entityHit).addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 0, true));
                        } else if (this.getArcherAbility() == 4) {
                            ((EntityLivingBase)position.entityHit).addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0, true));
                        }
                    }
                    if (this.isBurning()) {
                        position.entityHit.setFire(5);
                    }
                    if (this.getArcherAbility() == 0) {
                        position.entityHit.setFire(12);
                    }
                    if (position.entityHit.attackEntityFrom(damagesource, (float)k)) {
                        if (this.getArcherAbility() == 2) {
                            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 3.0f, false);
                        }
                        if (position.entityHit instanceof EntityLivingBase) {
                            float f4;
                            EntityLivingBase entitylivingbase = (EntityLivingBase)position.entityHit;
                            if (this.knockbackStrength > 0 && (f4 = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ))) > 0.0f) {
                                position.entityHit.addVelocity(this.motionX * (double)this.knockbackStrength * (double)0.6f / (double)f4, 0.1, this.motionZ * (double)this.knockbackStrength * (double)0.6f / (double)f4);
                            }
                            if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
                                EnchantmentHelper.func_151384_a((EntityLivingBase)entitylivingbase, (Entity)this.shootingEntity);
                                EnchantmentHelper.func_151385_b((EntityLivingBase)((EntityLivingBase)this.shootingEntity), (Entity)entitylivingbase);
                            }
                            if (this.shootingEntity != null && position.entityHit != this.shootingEntity && position.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(6, 0.0f));
                            }
                        }
                        this.playSound("random.bowhit", 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
                        if (!(position.entityHit instanceof EntityEnderman)) {
                            this.setDead();
                        }
                    } else {
                        this.motionX *= (double)-0.1f;
                        this.motionY *= (double)-0.1f;
                        this.motionZ *= (double)-0.1f;
                        this.rotationYaw += 180.0f;
                        this.prevRotationYaw += 180.0f;
                        this.ticksInAir = 0;
                    }
                } else {
                    this.xTile = position.blockX;
                    this.yTile = position.blockY;
                    this.zTile = position.blockZ;
                    this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
                    this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
                    this.motionX = (float)(position.hitVec.xCoord - this.posX);
                    this.motionY = (float)(position.hitVec.yCoord - this.posY);
                    this.motionZ = (float)(position.hitVec.zCoord - this.posZ);
                    float f2 = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ));
                    this.posX -= this.motionX / (double)f2 * (double)0.05f;
                    this.posY -= this.motionY / (double)f2 * (double)0.05f;
                    this.posZ -= this.motionZ / (double)f2 * (double)0.05f;
                    this.playSound("random.bowhit", 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
                    this.inGround = true;
                    this.arrowShake = 7;
                    this.setIsCritical(false);
                    if (this.inTile.getMaterial() != Material.air) {
                        this.inTile.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, (Entity)this);
                    }
                }
            }
            if (this.getIsCritical()) {
                for (i = 0; i < 4; ++i) {
                    this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double)i / 4.0, this.posY + this.motionY * (double)i / 4.0, this.posZ + this.motionZ * (double)i / 4.0, -this.motionX, -this.motionY + 0.2, -this.motionZ);
                }
            }
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f2 = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);
            this.rotationPitch = (float)(Math.atan2(this.motionY, f2) * 180.0 / Math.PI);
            while (this.rotationPitch - this.prevRotationPitch < -180.0f) {
                this.prevRotationPitch -= 360.0f;
            }
            while (this.rotationPitch - this.prevRotationPitch >= 180.0f) {
                this.prevRotationPitch += 360.0f;
            }
            while (this.rotationYaw - this.prevRotationYaw < -180.0f) {
                this.prevRotationYaw -= 360.0f;
            }
            while (this.rotationYaw - this.prevRotationYaw >= 180.0f) {
                this.prevRotationYaw += 360.0f;
            }
            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2f;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2f;
            float f3 = 0.99f;
            f1 = 0.05f;
            if (this.isInWater()) {
                for (int l = 0; l < 4; ++l) {
                    float f4 = 0.25f;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)f4, this.posY - this.motionY * (double)f4, this.posZ - this.motionZ * (double)f4, this.motionX, this.motionY, this.motionZ);
                }
                f3 = 0.8f;
            }
            if (this.isWet()) {
                this.extinguish();
            }
            this.motionX *= (double)f3;
            this.motionY *= (double)f3;
            this.motionZ *= (double)f3;
            this.motionY -= (double)f1;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.func_145775_I();
        }
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("ArcherAbility", this.dataWatcher.getWatchableObjectInt(18));
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.dataWatcher.updateObject(18, (Object)tag.getInteger("ArcherAbility"));
    }

    public int getArcherAbility() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }
}

