/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBucket
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;

public class ItemModBucket
extends ItemBucket {
    protected String name;

    public ItemModBucket(Block liquid, String name) {
        super(liquid);
        this.name = name;
        this.setCreativeTab(DivineRPGTabs.utility);
        this.setTextureName("divinerpg:" + name);
        this.setUnlocalizedName(name);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
    }
}

