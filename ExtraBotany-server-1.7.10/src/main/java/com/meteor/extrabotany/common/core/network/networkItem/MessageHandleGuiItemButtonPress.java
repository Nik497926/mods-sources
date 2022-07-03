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
    private LinkedList<Class<? extends AbstractPacket>> packets = new LinkedList();
    private Boolean isPostInit = false;

    public MessageHandleGuiItemButtonPress() {
    }

    public MessageHandleGuiItemButtonPress(ItemStack te, int id, EntityPlayer player) {
        super(te, player);
        this.id = id;
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

    @Override
    public void handleClientSide(MessageHandleGuiItemButtonPress message, EntityPlayer player) {
    }

    @Override
    public void handleServerSide(MessageHandleGuiItemButtonPress message, EntityPlayer player) {
        ItemStack it = player.inventory.getStackInSlot(player.inventory.currentItem);
        ItemStack arm = player.inventory.armorInventory[1];
        if (arm != null && arm.getItem() instanceof ItemAwakeOGArmor) {
//            if (message.id < 1000) {
//                ItemAwakeOGArmor.onGuiButtonPress((int)message.id, (EntityPlayer)player);
//            } else {
//                ItemAwakeOGArmor.onUseSkill((int)(message.id - 1000), (EntityPlayer)player);
//            }
            System.out.println("Пиши фатоше, это баг #1 из Extra Botany");
        } else if (arm != null && arm.getItem() instanceof ItemKillerArmor) {
            ((ItemKillerArmor)arm.getItem()).guiButtonPress(message.id, player);
        } else {
            System.out.println("No one to process the package");
        }
    }
}

