/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor.awake;

import com.meteor.extrabotany.common.item.relic.legendary.armor.CoreArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;

public class ItemAwakeOGLegs
extends ItemAwakeOGArmor {
    private int serverTick = 0;

    public ItemAwakeOGLegs() {
        super(2, "awakeoglegs", null);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        ItemStack legs = player.inventory.armorInventory[1];
        if (!world.isRemote && legs != null && ItemAwakeOGLegs.isRightPlayer(player, legs)) {
            if (this.serverTick < 20) {
                ++this.serverTick;
            } else {
                this.serverTick = 0;
                CoreArmor.checkCD(player, stack);
            }
            CoreArmor.awakeLevels(legs, (byte)2, player);
        }
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        return CoreArmor.getVisDiscount(itemStack, entityPlayer, aspect);
    }
}

