/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.common.item.ItemMods;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.api.recipe.IFlowerComponent;

public class emptysoulsteel
extends ItemMods
implements IFlowerComponent {
    IIcon[] icons;

    public emptysoulsteel(String name) {
        super(name);
        this.setTextureName("extrabotania:emptysoulsteel");
    }

    public boolean canFit(ItemStack itemStack, IInventory iInventory) {
        return false;
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "item.emptysoulsteel";
    }

    public int getParticleColor(ItemStack itemStack) {
        return 0;
    }
}

