/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.generating;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.subtile.generating.SubTileDaybloom;

public class SubTileSunshinelily
extends SubTileDaybloom {
    public int getMaxMana() {
        return 100;
    }

    public int getValueForPassiveGeneration() {
        return ConfigHandler.efficiencySunshinelily;
    }

    public boolean canGeneratePassively() {
        boolean tempIsWorking = true;
        for (int x = -2; x <= 2; ++x) {
            for (int y = -2; y <= 2; ++y) {
                for (int z = -2; z <= 2; ++z) {
                    int zc;
                    int yc;
                    int xc;
                    if (x == 0 && y == 0 && z == 0) continue;
                    World world = this.supertile.getWorldObj();
                    Block bl = world.getBlock((xc = this.supertile.xCoord) + x, (yc = this.supertile.yCoord) + y, (zc = this.supertile.zCoord) + z);
                    if (!bl.isAir(world, x, y, z) && bl instanceof BlockSpecialFlower) {
                        tempIsWorking = false;
                    }
                    if (bl.isAir(world, x, y, z) || !(bl instanceof BlockFloatingSpecialFlower)) continue;
                    tempIsWorking = false;
                }
            }
        }
        boolean rain = this.supertile.getWorldObj().getWorldChunkManager().getBiomeGenAt(this.supertile.xCoord, this.supertile.zCoord).getIntRainfall() > 0 && (this.supertile.getWorldObj().isRaining() || this.supertile.getWorldObj().isThundering());
        boolean sky = this.supertile.getWorldObj().isDaytime() && !rain && this.supertile.getWorldObj().canBlockSeeTheSky(this.supertile.xCoord, this.supertile.yCoord + 1, this.supertile.zCoord);
        return sky && tempIsWorking;
    }

    public int getDelayBetweenPassiveGeneration() {
        return 10;
    }

    public boolean isPassiveFlower() {
        return false;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.sunshinelily;
    }

    public int getColor() {
        return 14784547;
    }
}

