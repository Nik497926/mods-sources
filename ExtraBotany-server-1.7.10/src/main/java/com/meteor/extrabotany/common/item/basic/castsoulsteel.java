/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.common.item.ItemMods;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.api.recipe.IFlowerComponent;

public class castsoulsteel
extends ItemMods
implements IFlowerComponent {
    IIcon[] icons;

    public castsoulsteel(String name) {
        super(name);
        this.setTextureName("extrabotania:castsoulsteel");
    }

    public boolean canFit(ItemStack itemStack, IInventory iInventory) {
        return false;
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "item.castsoulsteel";
    }

    public int getParticleColor(ItemStack itemStack) {
        return 0;
    }
}

