/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class InfoItem
extends Item {
    public InfoItem() {
        this.setUnlocalizedName("extrabotania.calculator");
        this.setTextureName("extrabotania:calculator");
        GameRegistry.registerItem(this, "calculator");
    }
}

