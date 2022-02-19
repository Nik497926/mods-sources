/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package net.divinerpg.entities.lelyetia;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class StringUtil {
    public static IChatComponent getLocale(String key) {
        return new ChatComponentTranslation(key, new Object[0]);
    }

    public static IChatComponent getLocaleWithArguments(String key, String ... args) {
        return new ChatComponentTranslation(key, (Object[])args);
    }

    public static IChatComponent getColourLocaleWithArguments(String key, EnumChatFormatting colour, String ... args) {
        return new ChatComponentTranslation(key, (Object[])args).setChatStyle(new ChatStyle().setColor(colour));
    }

    public static IChatComponent getColourLocale(String key, EnumChatFormatting colour) {
        return new ChatComponentTranslation(key, new Object[0]).setChatStyle(new ChatStyle().setColor(colour));
    }

    public static String getColourLocaleString(String key, EnumChatFormatting colour) {
        return colour + I18n.format((String)key, (Object[])new Object[0]);
    }

    public static String getLocaleString(String key) {
        return I18n.format((String)key, (Object[])new Object[0]);
    }

    public static String getColourLocaleStringWithArguments(String key, EnumChatFormatting colour, String ... args) {
        return colour + I18n.format((String)key, (Object[])args);
    }

    public static String getLocaleStringWithArguments(String key, String ... args) {
        return I18n.format((String)key, (Object[])args);
    }
}

