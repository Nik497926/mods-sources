/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor.killer;

import com.meteor.extrabotany.common.item.relic.legendary.armor.CoreArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemKillerChest
extends ItemKillerArmor
implements IVisDiscountGear {
    public ItemKillerChest() {
        super(1, "killerchest", null);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        if (ItemNBTHelper.getBoolean((ItemStack)stack, (String)"potion", (boolean)true) && (player.isPotionActive(Potion.resistance) && player.getActivePotionEffect(Potion.resistance).getAmplifier() <= 2 || !player.isPotionActive(Potion.resistance))) {
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 40, 2, true));
        }
        if (!CoreArmor.getAnother(player, (byte)1, (byte)0) && !world.isRemote) {
            CoreArmor.generateManaAndVis(player, stack);
        }
    }

    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        return CoreArmor.getVisDiscount(itemStack, entityPlayer, aspect);
    }
}

