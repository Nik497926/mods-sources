/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network;

import com.meteor.extrabotany.common.core.util.DataUtills;
import com.meteor.extrabotany.common.core.util.IConfigurableItem;
import com.meteor.extrabotany.common.core.util.ItemConfigField;
import com.meteor.extrabotany.common.core.util.ItemNBTHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ItemConfigPacket
implements IMessage {
    public static final byte BYTE_ID = 0;
    public static final byte SHORT_ID = 1;
    public static final byte INT_ID = 2;
    public static final byte LONG_ID = 3;
    public static final byte FLOAT_ID = 4;
    public static final byte DOUBLE_ID = 5;
    public static final byte BOOLEAN_ID = 6;
    public static final byte CHAR_ID = 7;
    public static final byte STRING_ID = 8;
    public static final byte INT_PAIR_ID = 9;
    public byte datatype;
    public int slot;
    public Object value;
    public String name;
    public boolean renameProfile = false;

    public ItemConfigPacket() {
    }

    public ItemConfigPacket(ItemConfigField field) {
        this.datatype = (byte)field.datatype;
        this.slot = field.slot;
        this.value = field.value;
        this.name = field.name;
    }

    public ItemConfigPacket(int slot, String name) {
        this.datatype = (byte)6;
        this.slot = slot;
        this.value = false;
        this.name = name;
        this.renameProfile = true;
    }

    public void fromBytes(ByteBuf bytes) {
        this.datatype = bytes.readByte();
        this.slot = bytes.readInt();
        this.name = ByteBufUtils.readUTF8String((ByteBuf)bytes);
        this.value = DataUtills.instance.readObjectFromBytes(bytes, this.datatype);
        this.renameProfile = bytes.readBoolean();
    }

    public void toBytes(ByteBuf bytes) {
        bytes.writeByte((int)this.datatype);
        bytes.writeInt(this.slot);
        ByteBufUtils.writeUTF8String((ByteBuf)bytes, (String)this.name);
        DataUtills.instance.writeObjectToBytes(bytes, this.datatype, this.value);
        bytes.writeBoolean(this.renameProfile);
    }

    public static class Handler
    implements IMessageHandler<ItemConfigPacket, IMessage> {
        public IMessage onMessage(ItemConfigPacket message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if (message.slot >= player.inventory.getSizeInventory() || message.slot < 0) {
                return null;
            }
            ItemStack stack = player.inventory.getStackInSlot(message.slot);
            if (stack != null && stack.getItem() instanceof IConfigurableItem) {
                if (message.renameProfile) {
                    ItemNBTHelper.setString(stack, "ProfileName" + ItemNBTHelper.getInteger(stack, "ConfigProfile", 0), message.name);
                    return null;
                }
                IConfigurableItem item = (IConfigurableItem)stack.getItem();
                List<ItemConfigField> fields = item.getFields(stack, message.slot);
                for (ItemConfigField field : fields) {
                    ItemConfigField newValue;
                    if (!field.name.equals(message.name) || message.datatype != field.datatype || !((newValue = new ItemConfigField(message.datatype, message.value, message.slot, message.name)).castToDouble() <= field.castMaxToDouble()) || !(newValue.castToDouble() >= field.castMinToDouble())) continue;
                    DataUtills.writeObjectToCompound(IConfigurableItem.ProfileHelper.getProfileCompound(stack), message.value, message.datatype, message.name);
                }
            }
            return null;
        }
    }
}

