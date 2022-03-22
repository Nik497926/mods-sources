/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.plugin;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;

public interface IInjection {
    void deposit(EntityPlayer var1, int var2) throws Exception;

    void deposit(UUID var1, int var2) throws Exception;
}

