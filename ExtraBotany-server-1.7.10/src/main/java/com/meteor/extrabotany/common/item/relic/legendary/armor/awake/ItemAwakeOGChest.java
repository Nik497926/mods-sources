/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor.awake;

import com.meteor.extrabotany.common.item.relic.legendary.armor.CoreArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemAwakeOGChest
extends ItemAwakeOGArmor
implements IVisDiscountGear {
    public ItemAwakeOGChest() {
        super(1, "awakeogchest", null);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        ItemStack chest = player.inventory.armorInventory[2];
        if (!world.isRemote && chest != null && ItemOGArmor.isRightPlayer(player, chest)) {
            CoreArmor.awakeLevels(chest, (byte)1, player);
        }
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        if (itemStack != null) {
            if (ItemNBTHelper.getInt(itemStack, "level", 1) > 2) {
                return 5;
            }
            if (ItemNBTHelper.getInt(itemStack, "level", 1) > 5) {
                return 10;
            }
        }
        return 0;
    }
}

