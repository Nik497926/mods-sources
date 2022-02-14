/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCaseKey
extends Item {
    private String name;

    public ItemCaseKey(String name) {
        this.name = name;
        this.setUnlocalizedName("divine_key");
        this.setTextureName("divinerpg:" + name);
        this.setCreativeTab(DivineRPGTabs.items);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem(this);
        this.setMaxStackSize(64);
        this.setMaxDamage(1);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(I18n.format((String)(this.name + ".info"), (Object[])new Object[0]));
    }
}

