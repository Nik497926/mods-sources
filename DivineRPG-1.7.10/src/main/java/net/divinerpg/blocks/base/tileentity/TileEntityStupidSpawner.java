/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.base.tileentity;

import java.util.Random;
import net.divinerpg.DivineRPG;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityStupidSpawner
extends TileEntity {
    private String entityName;
    private int spawnTimer;
    private boolean spawnParticles = false;
    private Random rand = new Random();

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.entityName = tag.getString("EntityName");
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("EntityName", this.entityName);
    }

    public void updateEntity() {
        super.updateEntity();
        if (this.worldObj.isRemote) {
            if (this.spawnParticles) {
                for (int n = 0; n < 3; ++n) {
                    DivineRPG.proxy.spawnParticle(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5, "blackFlame", true, 3);
                }
            }
        } else if (this.worldObj.getClosestPlayer((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5, 8.0) != null) {
            if (this.spawnTimer > 0) {
                --this.spawnTimer;
            }
            if (this.spawnTimer == 0) {
                int c = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand(8.0, 6.0, 8.0)).size();
                if (c < 3) {
                    for (int i = 0; i < 3; ++i) {
                        int z;
                        int y;
                        int x;
                        AxisAlignedBB boundingBox;
                        Entity e = EntityList.createEntityByName((String)this.entityName, (World)this.worldObj);
                        if (e == null || !this.worldObj.checkNoEntityCollision(boundingBox = AxisAlignedBB.getBoundingBox((double)((double)(x = this.xCoord + this.rand.nextInt(9) - 4) + e.boundingBox.minX), (double)((double)(y = this.yCoord + this.rand.nextInt(3) - 1) + e.boundingBox.minY), (double)((double)(z = this.zCoord + this.rand.nextInt(9) - 4) + e.boundingBox.minZ), (double)((double)x + e.boundingBox.maxX), (double)((double)y + e.boundingBox.maxY), (double)((double)z + e.boundingBox.maxZ))) || !this.worldObj.getCollidingBoundingBoxes(e, boundingBox).isEmpty() || this.worldObj.isAnyLiquid(boundingBox)) continue;
                        e.setLocationAndAngles((double)x, (double)y, (double)z, (float)this.rand.nextInt(360), 0.0f);
                        this.worldObj.spawnEntityInWorld(e);
                    }
                }
                this.spawnTimer = 400;
            }
        }
    }

    public void setEntityName(String name) {
        this.entityName = name;
    }

    public void setSpawnParticles(boolean spawnParticles) {
        this.spawnParticles = spawnParticles;
    }
}

