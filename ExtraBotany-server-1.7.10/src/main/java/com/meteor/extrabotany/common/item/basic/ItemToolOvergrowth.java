/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.ExtraBotany;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.IIcon;

public class ItemToolOvergrowth
extends ItemHoe {
    IIcon[] icons = new IIcon[2];

    public ItemToolOvergrowth() {
        super(Item.ToolMaterial.EMERALD);
        this.setUnlocalizedName("toolOvergrowth");
        this.setMaxDamage(120);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }

    public Item setUnlocalizedName(String par1Str) {
        GameRegistry.registerItem((Item)this, (String)par1Str);
        return super.setUnlocalizedName(par1Str);
    }

    public void registerIcons(IIconRegister ir) {
        this.icons = new IIcon[1];
        this.icons[0] = ir.registerIcon(ExtraBotany.modid + ":seed");
    }

    public IIcon getIconFromDamage(int mt) {
        return this.icons[0];
    }
}

