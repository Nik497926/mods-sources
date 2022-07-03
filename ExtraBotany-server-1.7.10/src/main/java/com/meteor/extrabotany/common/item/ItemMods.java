/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item;

import com.meteor.extrabotany.ExtraBotany;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemMods
extends Item {
    private String name;

    public ItemMods(String name) {
        this.setUnlocalizedName(name).setCreativeTab((CreativeTabs)ExtraBotany.tabExtraBotany).setMaxStackSize(64).setTextureName("ExtraBotania:" + name);
        this.name = name;
        GameRegistry.registerItem((Item)this, (String)name);
    }

    public String getName() {
        return this.name;
    }
}

