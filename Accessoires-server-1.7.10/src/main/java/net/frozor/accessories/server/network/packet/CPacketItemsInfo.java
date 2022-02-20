package net.frozor.accessories.server.network.packet;

import net.frozor.accessories.data.AccessoryItem;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CPacketItemsInfo implements IPacket {

    @Override
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);
            out.writeUTF("ACS:ITEMS_INFO");
            out.writeInt(AccessoryItem.ALL_INSTANCES.size());

            for (AccessoryItem item : AccessoryItem.ALL_INSTANCES) {
                out.writeUTF(item.name);
                out.writeInt(item.price);
                out.writeBoolean(false); // TODO: has
                out.writeUTF(item.author);
            }

            return bytes.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }
}