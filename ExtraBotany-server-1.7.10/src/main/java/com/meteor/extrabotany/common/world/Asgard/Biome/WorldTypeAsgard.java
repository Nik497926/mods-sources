/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard.Biome;

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
        GenLayer ret = new AsgardGenLayerBiome(200L, parentLayer, this);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }
}

