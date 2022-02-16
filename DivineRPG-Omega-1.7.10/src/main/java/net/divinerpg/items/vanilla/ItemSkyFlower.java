/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vanilla;

import java.util.List;
import net.divinerpg.items.base.ItemFastFood;
import net.divinerpg.utils.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemSkyFlower
extends ItemFastFood {
    public ItemSkyFlower() {
        super(1, 1.0f, false, "skyFlower");
        this.setAlwaysEdible();
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        super.onEaten(stack, world, player);
        player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 600, 0, true));
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600, 0, true));
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        super.addInformation(stack, player, list, par4);
        list.add(Util.GOLD + StatCollector.translateToLocal((String)"item.skyflower"));
    }
}

