/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Calendar;
import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntitySparklerFX;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityRainbour
extends EntityPeacefulUntilAttacked {
    private ChunkCoordinates spawnPosition;

    public EntityRainbour(World par1World) {
        super(par1World);
        this.setSize(0.9f, 0.9f);
        this.setIsBatHanging(true);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Byte(0));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.rainbourHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.rainbourDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.rainbourSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.rainbourFollowRange);
    }

    protected void collideWithEntity(Entity par1Entity) {
    }

    protected void collideWithNearbyEntities() {
    }

    public boolean getIsBatHanging() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setIsBatHanging(boolean par1) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        if (par1) {
            this.dataWatcher.updateObject(16, (Object)((byte)(b0 | 1)));
        } else {
            this.dataWatcher.updateObject(16, (Object)((byte)(b0 & 0xFFFFFFFE)));
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.dataWatcher.updateObject(16, (Object)par1NBTTagCompound.getByte("BatFlags"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("BatFlags", this.dataWatcher.getWatchableObjectByte(16));
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.getIsBatHanging()) {
            this.motionZ = 0.0;
            this.motionY = 0.0;
            this.motionX = 0.0;
            this.posY = (double)MathHelper.floor_double((double)this.posY) + 1.0 - (double)this.height;
        } else {
            this.motionY *= (double)0.6f;
        }
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.getEntityToAttack() != null) {
            this.spawnPosition = new ChunkCoordinates((int)Math.round(this.getEntityToAttack().posX), MathHelper.floor_double((double)this.getEntityToAttack().posY), (int)Math.round(this.getEntityToAttack().posZ));
        }
        if (this.getIsBatHanging()) {
            if (!this.worldObj.getBlock(MathHelper.floor_double((double)this.posX), (int)this.posY + 1, MathHelper.floor_double((double)this.posZ)).isNormalCube()) {
                this.setIsBatHanging(false);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            } else {
                if (this.rand.nextInt(200) == 0) {
                    this.rotationYawHead = this.rand.nextInt(360);
                }
                if (this.worldObj.getClosestPlayerToEntity((Entity)this, 4.0) != null) {
                    this.setIsBatHanging(false);
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                }
            }
        } else {
            if (!(this.spawnPosition == null || this.worldObj.isAirBlock(this.spawnPosition.posX, this.spawnPosition.posY, this.spawnPosition.posZ) && this.spawnPosition.posY >= 1)) {
                this.spawnPosition = null;
            }
            if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0f) {
                this.spawnPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
            }
            double d0 = (double)this.spawnPosition.posX + 0.5 - this.posX;
            double d1 = (double)this.spawnPosition.posY + 0.1 - this.posY;
            double d2 = (double)this.spawnPosition.posZ + 0.5 - this.posZ;
            this.motionX += (Math.signum(d0) * 0.5 - this.motionX) * (double)0.1f;
            this.motionY += (Math.signum(d1) * (double)0.7f - this.motionY) * (double)0.1f;
            this.motionZ += (Math.signum(d2) * 0.5 - this.motionZ) * (double)0.1f;
            float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0f;
            float f1 = MathHelper.wrapAngleTo180_float((float)(f - this.rotationYaw));
            this.moveForward = 0.5f;
            this.rotationYaw += f1;
            if (this.rand.nextInt(100) == 0 && this.worldObj.getBlock(MathHelper.floor_double((double)this.posX), (int)this.posY + 1, MathHelper.floor_double((double)this.posZ)).isNormalCube()) {
                this.setIsBatHanging(true);
            }
        }
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.rainbour);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.rainbourHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.rainbourHurt);
    }

    protected void dropFewItems(boolean var1, int var2) {
        this.dropItem(VanillaItemsOther.healingStone, 1);
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (!this.worldObj.isRemote && this.getIsBatHanging()) {
            this.setIsBatHanging(false);
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean getCanSpawnHere() {
        int var1 = MathHelper.floor_double((double)this.posY);
        if (var1 >= 40) {
            return false;
        }
        int var2 = MathHelper.floor_double((double)this.posX);
        int var3 = MathHelper.floor_double((double)this.posZ);
        int var4 = this.worldObj.getBlockLightValue(var2, var1, var3);
        int var5 = 4;
        Calendar var6 = this.worldObj.getCurrentDate();
        if (!(var6.get(2) + 1 == 10 && var6.get(5) >= 20 || var6.get(2) + 1 == 11 && var6.get(5) <= 3)) {
            if (this.rand.nextBoolean()) {
                return false;
            }
        } else {
            var5 = 7;
        }
        return var4 > this.rand.nextInt(var5) ? false : super.getCanSpawnHere();
    }

    @SideOnly(value=Side.CLIENT)
    public void onLivingUpdate() {
        super.onLivingUpdate();
        for (int var3 = 0; var3 < 8; ++var3) {
            EntitySparklerFX var20 = new EntitySparklerFX(this.worldObj, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, (this.rand.nextDouble() - 0.5) * 2.0, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5) * 2.0);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)var20);
        }
    }

    @Override
    public String mobName() {
        return "Rainbour";
    }
}

