/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.generating;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.core.handler.MathHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.lib.LibData;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileGenerating;

public class SubTileGeminiorchid
extends SubTileGenerating {
    private static final int DELAY = 20;

    public void onUpdate() {
        super.onUpdate();
        if (this.mana < this.getMaxMana() && this.ticksExisted % 20 == 0) {
            int bA = LibData.getBlockTemperture(this.supertile.getWorldObj().getBlock(this.supertile.xCoord + 1, this.supertile.yCoord, this.supertile.zCoord + 1));
            int bB = LibData.getBlockTemperture(this.supertile.getWorldObj().getBlock(this.supertile.xCoord + 1, this.supertile.yCoord, this.supertile.zCoord - 1));
            int bC = LibData.getBlockTemperture(this.supertile.getWorldObj().getBlock(this.supertile.xCoord - 1, this.supertile.yCoord, this.supertile.zCoord + 1));
            int bD = LibData.getBlockTemperture(this.supertile.getWorldObj().getBlock(this.supertile.xCoord - 1, this.supertile.yCoord, this.supertile.zCoord - 1));
            int temA = Math.min(MathHandler.min(bA, bB, bC), bD);
            int temB = Math.max(MathHandler.max(bA, bB, bC), bD);
            if (temB - temA > 500) {
                this.mana += (temB - temA) / 1000 * ConfigHandler.efficiencyGeminiorchid + this.supertile.getWorldObj().rand.nextInt(4);
            }
        }
    }

    public int getDelayBetweenPassiveGeneration() {
        return 20;
    }

    public int getMaxMana() {
        return 400;
    }

    public int getColor() {
        return 15716665;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.geminiorchid;
    }
}

