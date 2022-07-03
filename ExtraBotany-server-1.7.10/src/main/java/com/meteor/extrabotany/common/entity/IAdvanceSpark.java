/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import net.minecraft.world.World;
import vazkii.botania.api.mana.spark.ISparkAttachable;

public interface IAdvanceSpark {
    public int getType();

    public void spawn(double var1, double var3, double var5, World var7, ISparkAttachable var8);

    public int getUpgrade();
}

