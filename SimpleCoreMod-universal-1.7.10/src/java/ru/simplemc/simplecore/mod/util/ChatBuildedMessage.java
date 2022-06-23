/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ChatBuildedMessage {
    private final List<IChatComponent> components = new ArrayList<IChatComponent>();

    public ChatBuildedMessage() {
    }

    public ChatBuildedMessage(String value) {
        this.components.add((IChatComponent)new ChatComponentText(value));
    }

    public ChatBuildedMessage append(IChatComponent component) {
        this.components.add(component);
        return this;
    }

    public ChatBuildedMessage append(String message) {
        this.components.add((IChatComponent)new ChatComponentText(message));
        return this;
    }

    public void send(EntityPlayer player) {
        this.components.forEach(arg_0 -> ((EntityPlayer)player).addChatComponentMessage(arg_0));
    }

    public static ChatBuildedMessage create(String value) {
        return new ChatBuildedMessage(value);
    }
}

