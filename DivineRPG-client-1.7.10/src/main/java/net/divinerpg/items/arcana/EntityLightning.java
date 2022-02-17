/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLightning
extends EntityLightningBolt {
    private EntityPlayer caster;
    private int lightningState;
    public long boltVertex;
    private int boltLivingTime;
    private static final String __OBFID = "CL_00001666";

    public EntityLightning(World world) {
        super(world, 0.0, 0.0, 0.0);
    }

    public EntityLightning(World p_i1703_1_, double p_i1703_2_, double p_i1703_4_, double p_i1703_6_, EntityPlayer caster) {
        super(p_i1703_1_, p_i1703_2_, p_i1703_4_, p_i1703_6_);
        this.caster = caster;
        this.setLocationAndAngles(p_i1703_2_, p_i1703_4_, p_i1703_6_, 0.0f, 0.0f);
        this.lightningState = 2;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(3) + 1;
        if (!p_i1703_1_.isRemote && p_i1703_1_.getGameRules().getGameRuleBooleanValue("doFireTick") && (p_i1703_1_.difficultySetting == EnumDifficulty.NORMAL || p_i1703_1_.difficultySetting == EnumDifficulty.HARD) && p_i1703_1_.doChunksNearChunkExist(MathHelper.floor_double((double)p_i1703_2_), MathHelper.floor_double((double)p_i1703_4_), MathHelper.floor_double((double)p_i1703_6_), 10)) {
            int i = MathHelper.floor_double((double)p_i1703_2_);
            int j = MathHelper.floor_double((double)p_i1703_4_);
            int k = MathHelper.floor_double((double)p_i1703_6_);
            for (i = 0; i < 4; ++i) {
                j = MathHelper.floor_double((double)p_i1703_2_) + this.rand.nextInt(3) - 1;
                k = MathHelper.floor_double((double)p_i1703_4_) + this.rand.nextInt(3) - 1;
                int n = MathHelper.floor_double((double)p_i1703_6_) + this.rand.nextInt(3) - 1;
            }
        }
    }

    public void onUpdate() {
        if (this.lightningState == 2) {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0f, 0.8f + this.rand.nextFloat() * 0.2f);
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0f, 0.5f + this.rand.nextFloat() * 0.2f);
        }
        --this.lightningState;
        if (this.lightningState < 0) {
            if (this.boltLivingTime == 0) {
                this.setDead();
            } else if (this.lightningState < -this.rand.nextInt(10)) {
                --this.boltLivingTime;
                this.lightningState = 1;
                this.boltVertex = this.rand.nextLong();
                if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("doFireTick") && this.worldObj.doChunksNearChunkExist(MathHelper.floor_double((double)this.posX), MathHelper.floor_double((double)this.posY), MathHelper.floor_double((double)this.posZ), 10)) {
                    int i = MathHelper.floor_double((double)this.posX);
                    int j = MathHelper.floor_double((double)this.posY);
                    int n = MathHelper.floor_double((double)this.posZ);
                }
            }
        }
        if (this.lightningState >= 0) {
            if (this.worldObj.isRemote) {
                this.worldObj.lastLightningBolt = 2;
            } else {
                double d = 3.0;
            }
        }
    }

    protected void entityInit() {
    }

    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
    }

    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
    }
}

