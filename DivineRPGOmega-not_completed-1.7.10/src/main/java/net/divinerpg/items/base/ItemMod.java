/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMod
extends Item {
    protected String name;
    protected int healAmount = 0;

    public ItemMod(String name) {
        this(name, name, DivineRPGTabs.items);
    }

    public ItemMod(String name, DivineRPGTabs tab) {
        this(name, name, tab);
    }

    public ItemMod(String name, String textureName, DivineRPGTabs tab) {
        this.name = name;
        this.setUnlocalizedName(name);
        this.setTextureName("divinerpg:" + textureName);
        this.setCreativeTab(tab);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem(this);
    }

    public ItemMod setHealAmount(int healAmount) {
        this.healAmount = healAmount;
        return this;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (this.healAmount != 0 && player.getHealth() < player.getMaxHealth()) {
            player.heal((float)this.healAmount);
            player.inventory.consumeInventoryItem((Item)this);
        }
        return stack;
    }
}

