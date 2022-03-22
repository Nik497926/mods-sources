/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileVolatilily
extends SubTileFunctional {
    private static final int COST = 50;
    private static final int RANGE = 3;
    private static final int[][] OFFSETS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};

    public int getColor() {
        return 0;
    }

    public int getMaxMana() {
        return 50;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.volatilily;
    }

    public boolean acceptsRedstone() {
        return true;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 3);
    }
}

