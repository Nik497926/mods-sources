/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor.killer;

import com.meteor.extrabotany.common.item.relic.legendary.armor.CoreArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemKillerLegs
extends ItemKillerArmor
implements IVisDiscountGear {
    private int server_tick = 0;

    public ItemKillerLegs() {
        super(2, "killerlegs", null);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        NBTTagCompound nbt;
        super.onArmorTick(world, player, stack);
        if (ItemNBTHelper.getBoolean((ItemStack)stack, (String)"potion", (boolean)true) && !(nbt = ItemNBTHelper.getNBT((ItemStack)stack)).hasKey("lastDMG")) {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 40, 1, true));
        }
        if (!CoreArmor.getAnother(player, (byte)2, (byte)0) && !world.isRemote) {
            CoreArmor.generateManaAndVis(player, stack);
        }
        if (this.server_tick < 20) {
            ++this.server_tick;
        } else {
            this.server_tick = 0;
            CoreArmor.checkCD(player, stack);
        }
    }

    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        return CoreArmor.getVisDiscount(itemStack, entityPlayer, aspect);
    }
}

