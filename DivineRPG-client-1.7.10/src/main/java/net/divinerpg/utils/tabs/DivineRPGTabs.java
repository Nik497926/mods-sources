/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package net.divinerpg.utils.tabs;

import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsTools;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DivineRPGTabs
extends CreativeTabs {
    private Item icon;
    private String name;
    public static DivineRPGTabs blocks = new DivineRPGTabs("Blocks");
    public static DivineRPGTabs ranged = new DivineRPGTabs("Ranged", "Ranged Weapons");
    public static DivineRPGTabs swords = new DivineRPGTabs("Swords", "Melee Weapons");
    public static DivineRPGTabs tools = new DivineRPGTabs("Tools");
    public static DivineRPGTabs items = new DivineRPGTabs("Materials", "Raw Materials");
    public static DivineRPGTabs armor = new DivineRPGTabs("Armor");
    public static DivineRPGTabs spawner = new DivineRPGTabs("Spawner");
    public static DivineRPGTabs utility = new DivineRPGTabs("Utility");
    public static DivineRPGTabs food = new DivineRPGTabs("Herbalism");
    public static DivineRPGTabs vethea = new DivineRPGTabs("Vethea");

    public DivineRPGTabs(String lable) {
        this(lable, lable);
    }

    public DivineRPGTabs(String lable, String name) {
        super(DivineRPGTabs.getNextID(), lable);
        this.name = name;
        LangRegistry.addTab(this);
    }

    public void setIcon(Item icon) {
        this.icon = icon;
    }

    public void setIcon(Block icon) {
        this.icon = Util.toItem(icon);
    }

    public Item getTabIconItem() {
        return this.icon;
    }

    public String getName() {
        return this.name;
    }

    public static void init() {
        blocks.setIcon(TwilightBlocks.edenOre);
        ranged.setIcon(VanillaItemsWeapons.vileStorm);
        swords.setIcon(VanillaItemsWeapons.aquaton);
        tools.setIcon(VanillaItemsTools.rupeeShickaxe);
        items.setIcon(VanillaItemsOther.rupeeIngot);
        armor.setIcon(VanillaItemsArmor.divineHelmet);
        spawner.setIcon(VanillaItemsOther.callWatcher);
        utility.setIcon(VanillaItemsOther.tarBucket);
        food.setIcon(ItemsFood.bacon);
        vethea.setIcon(VetheaItems.karosStaff);
    }
}

