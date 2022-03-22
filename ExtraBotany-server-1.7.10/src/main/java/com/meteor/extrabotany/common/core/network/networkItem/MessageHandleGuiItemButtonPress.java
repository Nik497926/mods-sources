/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network.networkItem;

import baubles.common.network.AbstractPacket;
import com.meteor.extrabotany.common.core.network.networkItem.MessageXYZ;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import java.util.EnumMap;
import java.util.LinkedList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MessageHandleGuiItemButtonPress
extends MessageXYZ<MessageHandleGuiItemButtonPress> {
    private int id;
    private EnumMap<Side, FMLEmbeddedChannel> channel;
    private final LinkedList<Class<? extends AbstractPacket>> packets = new LinkedList();
    private final Boolean isPostInit = false;

    public MessageHandleGuiItemButtonPress() {
    }

    public MessageHandleGuiItemButtonPress(ItemStack te, int id, EntityPlayer player) {
        super(te, player);
        this.id = id;
    }

    @Override
    public void handleClientSide(MessageHandleGuiItemButtonPress var1, EntityPlayer var2) {

    }

    @Override
    public void handleServerSide(MessageHandleGuiItemButtonPress var1, EntityPlayer var2) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        this.id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(this.id);
    }
}

