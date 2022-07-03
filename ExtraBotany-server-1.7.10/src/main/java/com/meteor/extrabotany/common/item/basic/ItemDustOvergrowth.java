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

public class ItemDustOvergrowth
extends ItemMod {
    IIcon[] icons = new IIcon[2];

    public ItemDustOvergrowth() {
        this.setUnlocalizedName("dustOvergrowth");
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setMaxStackSize(64);
    }

    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + ":" + stack.getItemDamage();
    }

    public void registerIcons(IIconRegister ir) {
        this.icons = new IIcon[3];
        this.icons[0] = ir.registerIcon(ExtraBotany.modid + ":icon");
        this.icons[1] = ir.registerIcon(ExtraBotany.modid + ":Seed0");
        this.icons[2] = ir.registerIcon(ExtraBotany.modid + ":pile_sc");
    }

    public IIcon getIconFromDamage(int mt) {
        int meta = mt >= 3 ? 0 : mt;
        return this.icons[meta];
    }

    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack((Item)this, 1, 0));
        list.add(new ItemStack((Item)this, 1, 1));
        list.add(new ItemStack((Item)this, 1, 2));
    }
}

