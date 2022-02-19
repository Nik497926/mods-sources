/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import ru.deathcry.deathtrade.proxy.ClientProxy;

public class EventHandlerer {
    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onEvent(InputEvent.KeyInputEvent event) {
        if (ClientProxy.tradeButton.isPressed()) {
            Entity entity = Minecraft.getMinecraft().objectMouseOver.entityHit;
            if (entity == null || !(entity instanceof EntityPlayer)) {
                Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)EventHandlerer.locMessage("message.hover_a_player", new Object[0]));
                return;
            }
            EntityPlayer player = (EntityPlayer)entity;
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/trade " + player.getDisplayName());
        }
    }

    public static ChatComponentText locMessage(String unlocal, Object ... args) {
        return new ChatComponentText(EventHandlerer.localize(unlocal, args));
    }

    public static String localize(String unlocal, Object ... args) {
        return String.format(StatCollector.translateToLocal((String)unlocal), args);
    }
}

