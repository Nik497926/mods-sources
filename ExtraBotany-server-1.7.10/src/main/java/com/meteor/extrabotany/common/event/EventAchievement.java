/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.item.ModItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class EventAchievement {
    @SubscribeEvent
    public void onItemPickedUp(PlayerEvent.ItemPickupEvent event) {
        EntityItem e = event.pickedUp;
        ItemStack stack = event.pickedUp.getEntityItem();
        EntityPlayer player = event.player;
        if (stack != null) {
            if (stack.getItem() == ModItems.material && stack.getItemDamage() == 1) {
                player.addStat(ModAchievement.O_blank, 1);
            }
            if (stack.getItem() instanceof ItemBlockSpecialFlower) {
                String type = ItemBlockSpecialFlower.getType(stack);
                if (type.equals("annoyobloom")) {
                    event.player.addStat(ModAchievement.F_annoy, 1);
                } else if (type.equals("artifaconia")) {
                    event.player.addStat(ModAchievement.F_artifa, 1);
                } else if (type.equals("diplopbamboo")) {
                    event.player.addStat(ModAchievement.F_diplop, 1);
                } else if (type.equals("icebirdium")) {
                    event.player.addStat(ModAchievement.F_ice, 1);
                } else if (type.equals("launchish")) {
                    event.player.addStat(ModAchievement.F_launch, 1);
                } else if (type.equals("necrofleur")) {
                    event.player.addStat(ModAchievement.F_necro, 1);
                } else if (type.equals("numeronbalsam")) {
                    event.player.addStat(ModAchievement.F_numeb, 1);
                } else if (type.equals("numerondandelife")) {
                    event.player.addStat(ModAchievement.F_numed, 1);
                } else if (type.equals("voiduim")) {
                    event.player.addStat(ModAchievement.F_void, 1);
                } else if (type.equals("volatilily")) {
                    event.player.addStat(ModAchievement.F_vola, 1);
                } else if (type.equals("woodienia")) {
                    event.player.addStat(ModAchievement.F_woo, 1);
                } else if (type.equals("blueenchantress")) {
                    event.player.addStat(ModAchievement.F_blue, 1);
                } else if (type.equals("candyflower")) {
                    event.player.addStat(ModAchievement.F_candy, 1);
                } else if (type.equals("geminiorchid")) {
                    event.player.addStat(ModAchievement.F_gemini, 1);
                } else if (type.equals("moonlightlily")) {
                    event.player.addStat(ModAchievement.F_moon, 1);
                } else if (type.equals("omniviolet")) {
                    event.player.addStat(ModAchievement.F_omni, 1);
                } else if (type.equals("pyschobloom")) {
                    event.player.addStat(ModAchievement.F_pyscho, 1);
                } else if (type.equals("stonesia")) {
                    event.player.addStat(ModAchievement.F_stone, 1);
                } else if (type.equals("sunshinelily")) {
                    event.player.addStat(ModAchievement.F_sun, 1);
                } else if (type.equals("infernoidisy")) {
                    event.player.addStat(ModAchievement.F_infer, 1);
                } else if (type.equals("judasvow")) {
                    event.player.addStat(ModAchievement.F_judas, 1);
                } else if (type.equals("manalinkuim")) {
                    event.player.addStat(ModAchievement.F_mana, 1);
                }
            }
        }
    }
}

