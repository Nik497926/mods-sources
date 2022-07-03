/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.weapon;

import com.meteor.extrabotany.ExtraBotany;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class ItemScissorBladeRed
extends ItemSword {
    public ItemScissorBladeRed(Item.ToolMaterial p_i45356_1_, String name) {
        super(p_i45356_1_);
        this.setUnlocalizedName(name).setCreativeTab((CreativeTabs)ExtraBotany.tabExtraBotany).setMaxStackSize(1).setTextureName("ExtraBotania:" + name);
        GameRegistry.registerItem((Item)this, (String)name);
    }
}

