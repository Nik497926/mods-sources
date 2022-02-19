package com.gamerforea.thaumcraft;

import com.google.common.base.Strings;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public final class ChatUtils
{
	public static ChatComponentText text(String text)
	{
		return new ChatComponentText(Strings.nullToEmpty(text));
	}

	public static ChatComponentTranslation translate(String text, Object... args)
	{
		return new ChatComponentTranslation(Strings.nullToEmpty(text), args);
	}

	public static <T extends IChatComponent> T color(T chatComponent, EnumChatFormatting color)
	{
		chatComponent.getChatStyle().setColor(color);
		return chatComponent;
	}

	public static <T extends IChatComponent> T italic(T chatComponent)
	{
		chatComponent.getChatStyle().setItalic(true);
		return chatComponent;
	}
}
