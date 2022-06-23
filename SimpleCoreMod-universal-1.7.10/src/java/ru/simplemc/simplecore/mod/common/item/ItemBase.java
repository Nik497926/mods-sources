/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.item;

import net.minecraft.item.Item;

public class ItemBase
extends Item {
    public ItemBase(String name) {
        this.setUnlocalizedName("simplecore." + name);
        this.setTextureName("simplecore:" + name);
    }
}

