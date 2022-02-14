/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemSpade
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
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemModShovel
extends ItemSpade {
    protected String name;

    public ItemModShovel(Item.ToolMaterial tool, String name) {
        super(tool);
        this.name = name;
        this.setCreativeTab(DivineRPGTabs.tools);
        this.setTextureName("divinerpg:" + name);
        this.setUnlocalizedName(name);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
    }

    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.efficiency(this.toolMaterial.getEfficiencyOnProperMaterial()));
        if (item.getMaxDamage() == -1) {
            list.add(TooltipLocalizer.infiniteUses());
        } else {
            int dur = item.getMaxDamage() - item.getItemDamage();
            double max = item.getMaxDamage();
            int res = (int)((double)dur / max * 100.0);
            list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
        }
    }
}

