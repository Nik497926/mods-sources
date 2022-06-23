/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.manager.integration;

import java.math.BigDecimal;
import net.minecraft.entity.player.EntityPlayer;

public interface IServerIntegration {
    public boolean hasPermission(EntityPlayer var1, String var2);

    public boolean hasPermission(String var1, String var2);

    public boolean decrMoney(EntityPlayer var1, BigDecimal var2);

    public boolean decrMoney(String var1, BigDecimal var2);

    public boolean addMoney(EntityPlayer var1, BigDecimal var2);

    public boolean addMoney(String var1, BigDecimal var2);

    public boolean canBreakBlock(EntityPlayer var1, int var2, int var3, int var4);
}

