/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 */
package net.divinerpg.blocks.vanilla.container.tileentity;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAltarOfCorruption
extends TileEntity {
    public int tickCount;
    public float pageFlip;
    public float pageFlipPrev;
    public float bx;
    public float bz;
    public float bookSpread;
    public float bookSpreadPrev;
    public float bookRotation2;
    public float bookRotationPrev;
    public float bookRotation;
    private static Random rand = new Random();

    public void updateEntity() {
        float var7;
        super.updateEntity();
        this.bookSpreadPrev = this.bookSpread;
        this.bookRotationPrev = this.bookRotation2;
        EntityPlayer var1 = this.worldObj.getClosestPlayer((double)((float)this.xCoord + 0.5f), (double)((float)this.yCoord + 0.5f), (double)((float)this.zCoord + 0.5f), 3.0);
        if (var1 != null) {
            double var2 = var1.posX - (double)((float)this.xCoord + 0.5f);
            double var4 = var1.posZ - (double)((float)this.zCoord + 0.5f);
            this.bookRotation = (float)Math.atan2(var4, var2);
            this.bookSpread += 0.1f;
            if (this.bookSpread < 0.5f || rand.nextInt(40) == 0) {
                float var6 = this.bx;
                do {
                    this.bx += (float)(rand.nextInt(4) - rand.nextInt(4));
                } while (var6 == this.bx);
            }
        } else {
            this.bookRotation += 0.02f;
            this.bookSpread -= 0.1f;
        }
        while (this.bookRotation2 >= (float)Math.PI) {
            this.bookRotation2 -= (float)Math.PI * 2;
        }
        while (this.bookRotation2 < (float)(-Math.PI)) {
            this.bookRotation2 += (float)Math.PI * 2;
        }
        while (this.bookRotation >= (float)Math.PI) {
            this.bookRotation -= (float)Math.PI * 2;
        }
        while (this.bookRotation < (float)(-Math.PI)) {
            this.bookRotation += (float)Math.PI * 2;
        }
        for (var7 = this.bookRotation - this.bookRotation2; var7 >= (float)Math.PI; var7 -= (float)Math.PI * 2) {
        }
        while (var7 < (float)(-Math.PI)) {
            var7 += (float)Math.PI * 2;
        }
        this.bookRotation2 += var7 * 0.4f;
        if (this.bookSpread < 0.0f) {
            this.bookSpread = 0.0f;
        }
        if (this.bookSpread > 1.0f) {
            this.bookSpread = 1.0f;
        }
        ++this.tickCount;
        this.pageFlipPrev = this.pageFlip;
        float var3 = (this.bx - this.pageFlip) * 0.4f;
        float var8 = 0.2f;
        if (var3 < -var8) {
            var3 = -var8;
        }
        if (var3 > var8) {
            var3 = var8;
        }
        this.bz += (var3 - this.bz) * 0.9f;
        this.pageFlip += this.bz;
    }
}

