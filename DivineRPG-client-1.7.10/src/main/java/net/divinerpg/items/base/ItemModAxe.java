/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ItemModAxe
extends ItemAxe {
    protected String name;

    public ItemModAxe(Item.ToolMaterial tool, String name) {
        super(tool);
        this.name = name;
        this.setUnlocalizedName(name);
        this.setTextureName("divinerpg:" + name);
        this.setCreativeTab(DivineRPGTabs.tools);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
    }

    public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
        infoList.add(TooltipLocalizer.efficiency(this.toolMaterial.getEfficiencyOnProperMaterial()));
        if (item.getMaxDamage() != -1) {
            infoList.add(TooltipLocalizer.usesRemaining(item.getMaxDamage() - item.getItemDamage()));
        } else {
            infoList.add(TooltipLocalizer.infiniteUses());
        }
    }
}

