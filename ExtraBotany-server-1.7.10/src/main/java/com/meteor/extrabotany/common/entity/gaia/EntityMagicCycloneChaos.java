/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity.gaia;

import com.meteor.extrabotany.common.core.Sound;
import com.meteor.extrabotany.common.core.handler.EntityHandler;
import com.meteor.extrabotany.common.core.util.SoundHelper;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.gaia.IMinion;
import java.awt.Color;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.relic.ItemRelic;

public class EntityMagicCycloneChaos
extends Entity
implements IMinion {
    private static float X = 1.0f;
    private static float Z = 1.0f;
    private static final String TAG_ANGEL_X = "angelX";
    private static final String TAG_ANGEL_Z = "angelZ";
    public EntityGaiaIII summoner;

    public EntityMagicCycloneChaos(World world) {
        super(world);
        this.setSize(0.0f, 0.0f);
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    public static boolean spawn(World world, double x, double y, double z, float angelX, float angelZ) {
        EntityMagicCycloneChaos i = new EntityMagicCycloneChaos(world);
        EntityMagicCycloneChaos.setAngel(angelX, angelZ);
        i.setPosition(x, y, z);
        world.spawnEntityInWorld(i);
        return true;
    }

    public static void setAngel(float angelX, float angelZ) {
        X = angelX;
        Z = angelZ;
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setFloat(TAG_ANGEL_X, X);
        nbt.setFloat(TAG_ANGEL_Z, Z);
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        float x = nbt.getFloat(TAG_ANGEL_X);
        float z = nbt.getFloat(TAG_ANGEL_Z);
        EntityMagicCycloneChaos.setAngel(x, z);
    }

    public void onUpdate() {
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        super.onUpdate();
        if (this.ticksExisted >= 440) {
            this.setDead();
        }
        float range = 1.0f;
        float ran = 24.0f;
        Color color = new Color(7211116);
        for (int players = 0; players < 81; ++players) {
            double i$ = (double)(range * (float)players) * 0.02 * (double)X;
            double rz = (double)(range * (float)players) * 0.02 * (double)Z;
            Botania.proxy.wispFX(this.worldObj, this.posX - (double)range + Math.random() * i$, this.posY + (double)players * 0.05, this.posZ + (double)range - Math.random() * rz, (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, 0.22f, -0.015f, 1.0f);
            if (players == 80) break;
        }
        if (!this.worldObj.isRemote) {
            List<EntityPlayer> var9 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.posX - (double)range, this.posY - (double)range, this.posZ - (double)range, this.posX + (double)range, this.posY + (double)range, this.posZ + (double)range));
            for (EntityPlayer player : var9) {
                EntityHandler.knockBack(player, this, 10.0f, 8.0f);
                if (this.ticksExisted % 15 == 0) {
                    player.attackEntityFrom(DamageSource.magic, 7.0f);
                } else if (this.ticksExisted % 24 == 0) {
                    player.attackEntityFrom(ItemRelic.damageSource(), 7.0f);
                }
                if (var9.size() <= 0) continue;
                SoundHelper.playSoundAtEntity(this.worldObj, Sound.ATTACK_SHADOW, this, 0.8f + (float)Math.random() * 0.2f);
            }
        }
    }

    protected void entityInit() {
    }
}

