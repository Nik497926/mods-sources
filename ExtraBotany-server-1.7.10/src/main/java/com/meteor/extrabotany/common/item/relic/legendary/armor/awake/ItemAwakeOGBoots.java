/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor.awake;

import com.meteor.extrabotany.common.item.relic.legendary.armor.CoreArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemAwakeOGBoots
extends ItemAwakeOGArmor
implements IVisDiscountGear {
    public ItemAwakeOGBoots() {
        super(3, "awakeogboots", null);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        super.addInformation(stack, player, list, adv);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        ItemStack boots = player.inventory.armorInventory[0];
        if (!world.isRemote && boots != null && ItemOGArmor.isRightPlayer(player, boots)) {
            CoreArmor.awakeLevels(boots, (byte)3, player);
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

