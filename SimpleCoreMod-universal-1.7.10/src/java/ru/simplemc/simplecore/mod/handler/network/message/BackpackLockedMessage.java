/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import ru.simplemc.simplecore.mod.common.item.ItemBackpack;

public class BackpackLockedMessage
implements IMessage {
    private String UUID;

    public BackpackLockedMessage() {
    }

    public BackpackLockedMessage(ItemStack backpackItemStack) {
        if (backpackItemStack.getItem() instanceof ItemBackpack) {
            this.UUID = ItemBackpack.getUUID(backpackItemStack).orElse("empty");
        }
    }

    public void fromBytes(ByteBuf buf) {
        this.UUID = ByteBufUtils.readUTF8String((ByteBuf)buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.UUID);
    }

    public String getUUID() {
        return this.UUID;
    }
}

