/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary;

import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import com.meteor.extrabotany.common.potion.ModPotions;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.lib.LibObfuscation;

public class ItemMaxwellDemon
extends ItemRelicAdv
implements IManaUsingItem {
    public ItemMaxwellDemon() {
        super("maxwelldemon");
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (ItemMaxwellDemon.isRightPlayer(player, stack)) {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        return stack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        super.onUsingTick(stack, player, count);
        if (ManaItemHandler.requestManaExact(stack, player, 10, true) && count % 5 == 0 && count == 5 && !player.worldObj.isRemote) {
            ReflectionHelper.setPrivateValue(EntityPlayer.class, player, 20, LibObfuscation.ITEM_IN_USE_COUNT);
            player.addPotionEffect(new PotionEffect(ModPotions.slowparticlesorting.getId(), 1200, 0));
            player.addPotionEffect(new PotionEffect(ModPotions.fastparticlesorting.getId(), 400, 0));
            if (player.getAbsorptionAmount() == 0.0f) {
                player.setAbsorptionAmount(10.0f);
            }
        }
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.eat;
    }

    public boolean usesMana(ItemStack stack) {
        return true;
    }
}

