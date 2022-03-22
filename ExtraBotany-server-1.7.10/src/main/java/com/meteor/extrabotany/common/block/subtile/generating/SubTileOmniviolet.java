/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.generating;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;

public class SubTileOmniviolet
extends SubTileGenerating {
    private static final String TAG_BURN_TIME = "burnTime";
    private static final int FUEL_CAP = 32000;
    private static final int RANGE = 2;
    int burnTime = 0;
    int cd = 0;

    public LexiconEntry getEntry() {
        return LexiconModData.ominiviolet;
    }

    public int getMaxMana() {
        return 5000;
    }

    public int getValueForPassiveGeneration() {
        return ConfigHandler.efficiencyOminiviolet;
    }

    public int getColor() {
        return 9636250;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 2);
    }

    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.setInteger(TAG_BURN_TIME, this.burnTime);
    }

    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.burnTime = cmp.getInteger(TAG_BURN_TIME);
    }

    public boolean canGeneratePassively() {
        return this.burnTime > 0;
    }

    public int getDelayBetweenPassiveGeneration() {
        return 1;
    }
}

