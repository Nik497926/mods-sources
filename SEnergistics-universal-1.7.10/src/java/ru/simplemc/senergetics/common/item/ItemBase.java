/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import ru.simplemc.senergetics.SEnergetics;

public class ItemBase
extends Item {
    public ItemBase(String name) {
        this.setCreativeTab(SEnergetics.CREATIVE_TAB);
        this.setTextureName("senergetics:" + name);
        this.setUnlocalizedName(name);
        GameRegistry.registerItem((Item)this, (String)name, (String)"senergetics");
    }
}

