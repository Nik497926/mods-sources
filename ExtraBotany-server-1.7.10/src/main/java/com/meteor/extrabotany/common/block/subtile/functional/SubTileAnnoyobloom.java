/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import java.util.Random;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileAnnoyobloom
extends SubTileFunctional {
    private static final Random rand = new Random();
    private static final int RANGE = 2;
    private static final int DELAY = 35;
    Integer[] potID = new Integer[]{1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 21, 22, 23};

    public int getColor() {
        return 15655707;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.annoyobloom;
    }

    public boolean acceptsRedstone() {
        return true;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 2);
    }

    public int getMaxMana() {
        return 900;
    }
}

