/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.ExtraBotany;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.common.item.ItemMod;

public class ItemModuleExtraAspects
extends ItemMod {
    IIcon[] icons = new IIcon[4];

    public ItemModuleExtraAspects() {
        this.setUnlocalizedName("moduleExtraAspects");
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public void registerIcons(IIconRegister ir) {
        this.icons = new IIcon[4];
        for (int i = 0; i < 4; ++i) {
            this.icons[i] = ir.registerIcon(ExtraBotany.modid.toLowerCase() + ":modeltier" + (i + 1));
        }
    }

    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + ":" + stack.getItemDamage();
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        int meta = stack.getItemDamage();
        return this.icons[meta < 4 ? meta : 0];
    }

    public IIcon getIconFromDamage(int meta) {
        return this.icons[meta < 4 ? meta : 0];
    }

    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 4; ++i) {
            list.add(new ItemStack((Item)this, 1, i));
        }
    }
}

