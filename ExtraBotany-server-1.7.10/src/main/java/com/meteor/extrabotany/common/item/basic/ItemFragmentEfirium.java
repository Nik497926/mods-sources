/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.common.item.ItemMods;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.recipe.IFlowerComponent;

public class ItemFragmentEfirium
extends ItemMods
implements IFlowerComponent {
    public ItemFragmentEfirium(String name) {
        super(name);
        this.setTextureName("ExtraBotania:fragmentEfirium");
    }

    public boolean canFit(ItemStack itemStack, IInventory iInventory) {
        return false;
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "item.efiriumFragment";
    }

    public int getParticleColor(ItemStack itemStack) {
        return 0;
    }
}

