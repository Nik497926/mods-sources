/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.ExtraBotany;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.common.item.ModItems;

public class EventKnowledgeTypeUnlock {
    @SubscribeEvent
    public void onItemPickedUp(PlayerEvent.ItemPickupEvent event) {
        ItemStack stack = event.pickedUp.getEntityItem();
        EntityPlayer player = event.player;
        ItemStack s = player.getHeldItem();
        if (s != null && s.getItem() == ModItems.lexicon) {
            ILexicon l = (ILexicon)s.getItem();
            if (stack != null) {
                if (stack.isItemEqual(new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 1))) {
                    if (!l.isKnowledgeUnlocked(s, ExtraBotany.extraKnowledge)) {
                        l.unlockKnowledge(s, ExtraBotany.extraKnowledge);
                        player.addChatMessage(new ChatComponentTranslation("botaniamisc.knowledgeUnlock").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
                    }
                } else if (stack.isItemEqual(new ItemStack(com.meteor.extrabotany.common.item.ModItems.dice6)) && !l.isKnowledgeUnlocked(s, ExtraBotany.legendaryKnowledge)) {
                    l.unlockKnowledge(s, ExtraBotany.legendaryKnowledge);
                    player.addChatMessage(new ChatComponentTranslation("botaniamisc.knowledgeUnlock2").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
                }
            }
        }
    }
}

