/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.common.item.ItemBackpack;
import ru.simplemc.simplecore.mod.handler.network.message.BackpackLockedMessage;

public class BackpackLockedMessageHandler
implements IMessageHandler<BackpackLockedMessage, IMessage> {
    public IMessage onMessage(BackpackLockedMessage message, MessageContext ctx) {
        EntityPlayerMP player;
        ItemStack itemStack;
        if (ctx.side.isClient()) {
            SimpleCore.LOGGER.error(this.getClass().getSimpleName() + " allowed only at server side!");
            return null;
        }
        if (message.getUUID() != null && !message.getUUID().equals("empty") && (itemStack = (player = ctx.getServerHandler().playerEntity).getHeldItem()) != null && itemStack.getItem() instanceof ItemBackpack) {
            if (!ItemBackpack.hasAccess(itemStack, (EntityPlayer)player)) {
                player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.backpack.access_error", new Object[0]));
                return null;
            }
            Optional<String> optionalUUID = ItemBackpack.getUUID(itemStack);
            optionalUUID.ifPresent(itemStackUUID -> {
                if (itemStackUUID.equals(message.getUUID())) {
                    ItemBackpack.setLocked(itemStack, !ItemBackpack.isLocked(itemStack));
                }
            });
        }
        return null;
    }
}

