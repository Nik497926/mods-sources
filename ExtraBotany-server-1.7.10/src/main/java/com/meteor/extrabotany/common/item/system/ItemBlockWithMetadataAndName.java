/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.system;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.IPickupAchievement;

public class ItemBlockWithMetadataAndName
extends ItemBlockWithMetadata
implements IPickupAchievement,
ICraftAchievement {
    public ItemBlockWithMetadataAndName(Block par2Block) {
        super(par2Block, par2Block);
    }

    public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
        return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("tile.", "tile.");
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return super.getUnlocalizedName(par1ItemStack) + par1ItemStack.getItemDamage();
    }

    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return this.field_150939_a instanceof ICraftAchievement ? ((ICraftAchievement)this.field_150939_a).getAchievementOnCraft(stack, player, matrix) : null;
    }

    public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
        return this.field_150939_a instanceof IPickupAchievement ? ((IPickupAchievement)this.field_150939_a).getAchievementOnPickup(stack, player, item) : null;
    }
}

