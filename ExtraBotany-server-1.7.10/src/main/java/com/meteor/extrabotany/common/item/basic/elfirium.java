/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.common.entity.EntityExMachine;
import com.meteor.extrabotany.common.item.ItemMods;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.recipe.IFlowerComponent;

public class elfirium
extends ItemMods
implements IFlowerComponent {
    IIcon[] icons;

    public elfirium(String name) {
        super(name);
        this.setTextureName("extrabotania:elfirium");
    }

    public boolean canFit(ItemStack itemStack, IInventory iInventory) {
        return false;
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "item.elfirium";
    }

    public int getParticleColor(ItemStack itemStack) {
        return 0;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return EntityExMachine.spawn(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6, true);
    }
}

