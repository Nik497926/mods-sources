/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.version;

import com.meteor.extrabotany.common.core.version.ThreadUpdateChecker;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class UpdateChecker {
    public static int MESSAGE = 12;
    public static boolean doneChecking = false;
    public static String onlineVersion = "";
    public static boolean triedToWarnPlayer = false;
    public static boolean startedDownload = false;
    public static boolean downloadedFile = false;

    public void init() {
        new ThreadUpdateChecker();
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (doneChecking && event.phase == TickEvent.Phase.END && Minecraft.getMinecraft().thePlayer != null && !triedToWarnPlayer) {
            if (!onlineVersion.isEmpty()) {
                int clientBuild;
                EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                int onlineBuild = Integer.parseInt(onlineVersion.split("-")[1]);
                if (onlineBuild > (clientBuild = 21)) {
                    player.addChatComponentMessage(new ChatComponentTranslation("extrabotania.versioning.message" + player.worldObj.rand.nextInt(MESSAGE)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA)));
                    player.addChatComponentMessage(new ChatComponentTranslation("extrabotania.versioning.outdated", clientBuild, onlineBuild));
                    IChatComponent component = IChatComponent.Serializer.func_150699_a(StatCollector.translateToLocal("extrabotania.versioning.updateMessage").replaceAll("%version%", onlineVersion));
                    player.addChatComponentMessage(component);
                }
            }
            triedToWarnPlayer = true;
        }
    }
}

