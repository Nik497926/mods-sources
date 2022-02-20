package net.frozor.accessories.server.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CPacketBalance implements IPacket {
    private final int _balance;

    @Override
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);
            out.writeUTF("ACS:BALANCE");
            out.writeInt(this._balance);
            return bytes.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }

    public CPacketBalance(int value) {
        this._balance = value;
    }
}