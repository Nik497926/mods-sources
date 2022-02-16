/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemModFood
extends ItemFood {
    protected String name;

    public ItemModFood(int food, float sat, boolean wolfFood, String name) {
        super(food, sat, wolfFood);
        this.name = name;
        this.setCreativeTab(DivineRPGTabs.food);
        this.setTextureName("divinerpg:" + name);
        this.setUnlocalizedName(name);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
    }

    public ItemModFood(String name, int food, float sat, boolean wolfFood, int potionID, int potionDuration, int potionAmplifier, float potionEffectProbability) {
        this(food, sat, wolfFood, name);
        this.setPotionEffect(potionID, potionDuration, potionAmplifier, potionEffectProbability);
    }

    public ItemStack onEaten(ItemStack item, World world, EntityPlayer player) {
        super.onEaten(item, world, player);
        if (item.getItem() == ItemsFood.chickenDinner) {
            player.triggerAchievement((StatBase)DivineRPGAchievements.mealToRemember);
        }
        return item;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add("Fills " + (double)this.func_150905_g(stack) / 2.0 + " Hunger Bars");
        list.add(this.func_150905_g(stack) + " Saturation");
        list.add(!this.isWolfsFavoriteMeat() ? Util.BLUE + "Pet Food:" + EnumChatFormatting.RESET + " false" : Util.BLUE + "Pet Food:" + EnumChatFormatting.RESET + " true");
    }
}

