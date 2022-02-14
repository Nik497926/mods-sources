/*
 * Decompiled with CFR 0.152.
 */
package net.divinerpg.utils.entities;

import net.divinerpg.entities.depths.EntityDarkfish;
import net.divinerpg.entities.depths.EntityDepthsBeast;
import net.divinerpg.utils.Util;

public class DepthsEntityRegistry {
    public static void init() {
        Util.registerDivineRPGMob(EntityDepthsBeast.class, "DepthsBeast");
        Util.registerDivineRPGMob(EntityDarkfish.class, "Darkfish");
    }
}

