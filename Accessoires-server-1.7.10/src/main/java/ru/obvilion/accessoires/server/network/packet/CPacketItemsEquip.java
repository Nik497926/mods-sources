package ru.obvilion.accessoires.server.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CPacketItemsEquip implements IPacket  {
    private final String _user, _head, _face, _body, _familiar;

    @Override
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);
            out.writeUTF("ACS:ITEMS_EQUIP");
            out.writeUTF(this._user);
            out.writeUTF(this._head);
            out.writeUTF(this._face);
            out.writeUTF(this._body);
            out.writeUTF(this._familiar);
            out.writeBoolean(true);
            return bytes.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }

    public CPacketItemsEquip(String user, String head, String face, String body, String familiar) {
        this._user = user;
        this._head = head;
        this._face = face;
        this._body = body;
        this._familiar = familiar;
    }
}
