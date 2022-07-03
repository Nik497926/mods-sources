/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.system;

import com.meteor.extrabotany.ExtraBotany;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.client.core.helper.IconHelper;

public class ItemMod
extends Item {
    public ItemMod() {
        this.setCreativeTab(ExtraBotany.tabExtraBotany);
    }

    public Item setUnlocalizedName(String par1Str) {
        GameRegistry.registerItem((Item)this, (String)par1Str);
        return super.setUnlocalizedName(par1Str);
    }

    public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
        return super.getUnlocalizedNameInefficiently(par1ItemStack);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = IconHelper.forItem((IIconRegister)par1IconRegister, (Item)this);
    }
}

