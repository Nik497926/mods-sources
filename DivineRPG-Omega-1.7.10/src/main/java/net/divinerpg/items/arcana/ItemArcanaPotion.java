/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import java.util.List;
import net.divinerpg.client.ArcanaRenderer;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public class ItemArcanaPotion
extends ItemMod {
    protected int amountToAdd;
    private int type;

    public ItemArcanaPotion(String name, int amountToAdd, int type) {
        super(name, DivineRPGTabs.utility);
        this.type = type;
        this.amountToAdd = amountToAdd;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.arcanaRegen(this.amountToAdd));
    }

    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (ArcanaHelper.getProperties(player).getBarValue() != 200.0f || ArcanaRenderer.value != 200.0f) {
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
            if (this.type == 0) {
                ArcanaHelper.getProperties(player).forceRegen(this.amountToAdd);
            }
            player.triggerAchievement((StatBase)DivineRPGAchievements.yuk);
        }
        return stack;
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.drink;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 7;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (ArcanaHelper.getProperties(player).getBarValue() != 200.0f || ArcanaRenderer.value != 200.0f) {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        return stack;
    }

    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}

