package ru.obvilion.cpanel.server.network.packet;

import ru.obvilion.cpanel.data.AccessoryItem;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CPacketItemsInfo implements IPacket {
    ArrayList<AccessoryItem> _items;

    @Override
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);
            out.writeUTF("ACS:ITEMS_INFO");
            out.writeInt(_items.size());

            for (AccessoryItem item : _items) {
                out.writeUTF(item.name);
                out.writeInt(item.price);
                out.writeBoolean(item.has);
                out.writeUTF(item.author);
            }

            return bytes.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }

    public CPacketItemsInfo(ArrayList<AccessoryItem> items) {
        this._items = items;
    }
}