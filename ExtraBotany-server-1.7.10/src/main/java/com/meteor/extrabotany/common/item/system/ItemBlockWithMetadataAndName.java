/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.system;

import com.meteor.extrabotany.common.block.decor.BlockExtraStabilizer;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
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

    public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        super.addInformation(stack, p_77624_2_, p_77624_3_, p_77624_4_);
        if (Block.getBlockFromItem((Item)stack.getItem()) instanceof BlockExtraStabilizer) {
            p_77624_3_.add("\u00a79\u041d\u0435 \u0442\u0440\u0435\u0431\u0443\u0435\u0442 \u043f\u0430\u0440\u0443");
            p_77624_3_.add("\u00a79\u0414\u0430\u0435\u0442 \u0441\u0442\u0430\u0431\u0438\u043b\u044c\u043d\u043e\u0441\u0442\u0438: " + ((BlockExtraStabilizer)Block.getBlockFromItem((Item)stack.getItem())).stab[stack.getItemDamage()]);
        }
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, stack.getItemDamage());
    }

    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return this.field_150939_a instanceof ICraftAchievement ? ((ICraftAchievement)this.field_150939_a).getAchievementOnCraft(stack, player, matrix) : null;
    }

    public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
        return this.field_150939_a instanceof IPickupAchievement ? ((IPickupAchievement)this.field_150939_a).getAchievementOnPickup(stack, player, item) : null;
    }
}

