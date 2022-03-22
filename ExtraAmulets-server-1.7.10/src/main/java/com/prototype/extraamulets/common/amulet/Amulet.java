/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.amulet;

import com.prototype.extraamulets.common.ability.Abilities;
import com.prototype.extraamulets.common.item.ItemAmulet;
import com.prototype.extraamulets.common.render.RenderAmulet;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class Amulet {
    private final RenderAmulet renderAmulet;
    private final Abilities abilities;

    public Amulet(RenderAmulet renderAmulet, Abilities abilities) {
        this.renderAmulet = renderAmulet;
        this.abilities = abilities;
    }

    public void registerItem(String name) {
        name = String.format("amulet_%s", name);
        ItemAmulet amulet = new ItemAmulet(name, this);
        GameRegistry.registerItem((Item)amulet, (String)name);
    }

    public RenderAmulet getRender() {
        return this.renderAmulet;
    }

    public Abilities getAbilities() {
        return this.abilities;
    }
}

