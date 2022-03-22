/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public abstract class Ability {
    private final String name;

    public Ability(String name) {
        this.name = name;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(EntityPlayer player, List<String> tooltip) {
    }

    public void onEquipped(EntityPlayer player) {
    }

    public void onUnequipped(EntityPlayer player) {
    }

    public final String getName() {
        return this.name;
    }
}

