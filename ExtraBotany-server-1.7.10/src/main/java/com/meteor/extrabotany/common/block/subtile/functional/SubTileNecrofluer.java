/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileNecrofluer
extends SubTileFunctional {
    private static final int RANGE = 3;
    private static final int DELAY = 600;
    private final int cd = 0;
    private static final String[] asp = new String[]{"vacuos", "lux", "tempestas", "motus", "gelum", "vitreus", "victus", "venenum", "potentia", "permutatio", "metallum", "mortuus", "volatus", "tenebrae", "spiritus", "sano", "iter", "alienis", "praecantatio", "auram", "vitium", "limus", "herba", "arbor", "bestia", "corpus", "exanimis", "cognitio", "sensus", "humanus", "messis", "perfodio", "instrumentum", "meto", "telum", "tutamen", "fames", "lucrum", "fabrico", "pannus", "machina", "vinculum"};

    public int getColor() {
        return 0x858585;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.necrofleur;
    }

    public int getMaxMana() {
        return 2000;
    }

    public boolean acceptsRedstone() {
        return true;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 1);
    }
}

