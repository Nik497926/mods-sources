/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard;

import com.meteor.extrabotany.common.world.Asgard.AsgardWorldProvider;
import net.minecraftforge.common.DimensionManager;

public class AsgardRegistry {
    public static final int dimensionId = 150;
    public static final int xCoordTP = 339;
    public static final int yCoordTP = 6;
    public static final int zCoordTP = 773;

    public static void mainRegistry() {
        AsgardRegistry.registerDimension();
    }

    public static void registerDimension() {
        DimensionManager.registerProviderType((int)150, AsgardWorldProvider.class, (boolean)false);
        DimensionManager.registerDimension((int)150, (int)150);
    }
}

