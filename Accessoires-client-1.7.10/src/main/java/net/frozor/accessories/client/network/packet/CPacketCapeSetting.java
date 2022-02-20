/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.frozor.accessories.client.network.packet.IPacket;
import net.frozor.accessories.client.ui.UIScroll;

public class CPacketCapeSetting
implements IPacket {
    private boolean _isFirstJoin;
    private boolean _value;

    @Override
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);
            out.writeUTF("ACS:SHOW_CAPE");
            out.writeBoolean(this._value);
            out.writeBoolean(this._isFirstJoin);
            return bytes.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }

    public CPacketCapeSetting(boolean value, boolean isFirstJoin) {
        this._value = value;
        this._isFirstJoin = isFirstJoin;
    }
}

