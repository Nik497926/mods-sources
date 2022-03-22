/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.common.item.ItemMods;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemEfirFragment
extends ItemMods
implements IFlowerComponent {
    public ItemEfirFragment(String name) {
        super(name);
        this.setTextureName("extrabotania:efirfrag");
    }

    public boolean canFit(ItemStack itemStack, IInventory iInventory) {
        return false;
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "item.EfirFragment";
    }

    public int getParticleColor(ItemStack itemStack) {
        return 0;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bol) {
        super.addInformation(stack, player, list, bol);
        String s = ItemNBTHelper.getString(stack, "owner", "");
        if (!s.isEmpty()) {
            list.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("item.efir.owner") + s);
        }
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.eat;
    }
}

