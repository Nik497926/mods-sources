/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity.gaia;

import com.meteor.extrabotany.common.entity.gaia.IMinion;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.relic.ItemRelic;

public class EntityMagicCycloneIgnis
extends Entity
implements IMinion {
    private static float X = 1.0f;
    private static float Z = 1.0f;
    private static final String TAG_ANGEL_X = "angelX";
    private static final String TAG_ANGEL_Z = "angelZ";

    public EntityMagicCycloneIgnis(World world) {
        super(world);
        this.setSize(0.0f, 0.0f);
    }

    public static void setAngel(float angelX, float angelZ) {
        X = angelX;
        Z = angelZ;
    }

    protected void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setFloat(TAG_ANGEL_X, X);
        nbt.setFloat(TAG_ANGEL_Z, Z);
    }

    protected void readEntityFromNBT(NBTTagCompound nbt) {
        float x = nbt.getFloat(TAG_ANGEL_X);
        float z = nbt.getFloat(TAG_ANGEL_Z);
        EntityMagicCycloneIgnis.setAngel(x, z);
    }

    public static boolean spawn(World world, double x, double y, double z, float angelX, float angelZ) {
        EntityMagicCycloneIgnis i = new EntityMagicCycloneIgnis(world);
        EntityMagicCycloneIgnis.setAngel(angelX, angelZ);
        i.setPosition(x, y, z);
        world.spawnEntityInWorld(i);
        return true;
    }

    public void onUpdate() {
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        super.onUpdate();
        if (this.ticksExisted >= 440) {
            this.setDead();
        }
        float range = 1.5f;
        for (int players = 0; players < 81; ++players) {
            double i$ = (double)(range * (float)players) * 0.02 * (double)X;
            double rz = (double)(range * (float)players) * 0.02 * (double)Z;
            Botania.proxy.wispFX(this.worldObj, this.posX - (double)range + Math.random() * i$, this.posY + (double)players * 0.05, this.posZ + (double)range - Math.random() * rz, 2.55f, 0.2f, 0.2f, 0.22f, -0.015f, 1.0f);
            if (players == 80) break;
        }
        if (!this.worldObj.isRemote) {
            List<EntityPlayer> var7 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.posX - (double)range, this.posY - (double)range, this.posZ - (double)range, this.posX + (double)range, this.posY + (double)range, this.posZ + (double)range));
            for (EntityPlayer player : var7) {
                if (this.ticksExisted % 20 == 0) {
                    player.setFire(5);
                }
                player.attackEntityFrom(DamageSource.magic, 5.5f);
                player.attackEntityFrom(ItemRelic.damageSource(), 4.5f);
                if (var7.size() <= 0) continue;
                this.worldObj.playSoundAtEntity(this, "botania:attack.fire", 0.6f, 0.8f + (float)Math.random() * 0.2f);
            }
        }
    }

    protected void entityInit() {
    }

    @Override
    public boolean canDestroy() {
        return true;
    }
}

