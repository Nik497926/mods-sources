/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard.Biome;

import com.meteor.extrabotany.common.world.Asgard.Biome.AsgardGenLayerBiome;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class WorldTypeAsgard
extends WorldType {
    public WorldTypeAsgard(int par1, String name) {
        super(name);
    }

    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer) {
        AsgardGenLayerBiome ret = new AsgardGenLayerBiome(200L, parentLayer, this);
        ret = (AsgardGenLayerBiome) GenLayerZoom.magnify((long)1000L, (GenLayer)ret, (int)2);
        return ret;
    }
}

