/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network;

import com.meteor.extrabotany.common.core.util.LogHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DataReader {
    ByteArrayInputStream input;
    DataInputStream dataStream;
    public byte ID;

    public DataReader(byte[] data) {
        this(data, true);
    }

    public DataReader(byte[] data, boolean getID) {
        this.input = new ByteArrayInputStream(data);
        this.dataStream = new DataInputStream(this.input);
        if (getID) {
            try {
                this.ID = this.dataStream.readByte();
            }
            catch (IOException var4) {
                LogHelper.error("ExtraBotaniaDataReader (getID): " + var4.toString(), new Object[0]);
                var4.printStackTrace();
            }
        }
    }

    public int getInt() {
        int value = 0;
        try {
            value = this.dataStream.readInt();
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataReader (getInt): " + var3.toString(), new Object[0]);
            var3.printStackTrace();
        }
        return value;
    }

    public float getFloat() {
        float value = 0.0f;
        try {
            value = this.dataStream.readFloat();
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataReader (getFloat): " + var3.toString(), new Object[0]);
            var3.printStackTrace();
        }
        return value;
    }

    public double getDouble() {
        double value = 0.0;
        try {
            value = this.dataStream.readDouble();
        }
        catch (IOException var4) {
            LogHelper.error("ExtraBotaniaDataReader (getDouble): " + var4.toString(), new Object[0]);
            var4.printStackTrace();
        }
        return value;
    }

    public boolean getBoolean() {
        boolean value = false;
        try {
            value = this.dataStream.readBoolean();
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataReader (getBoolean): " + var3.toString(), new Object[0]);
            var3.printStackTrace();
        }
        return value;
    }

    public String getString() {
        String value = "";
        try {
            value = this.dataStream.readUTF();
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataReader (getString): " + var3.toString(), new Object[0]);
            var3.printStackTrace();
        }
        return value;
    }

    public byte getByte() {
        byte value = 0;
        try {
            value = this.dataStream.readByte();
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataReader (getByte): " + var3.toString(), new Object[0]);
            var3.printStackTrace();
        }
        return value;
    }

    public short getShort() {
        short value = 0;
        try {
            value = this.dataStream.readShort();
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataReader (getShort): " + var3.toString(), new Object[0]);
            var3.printStackTrace();
        }
        return value;
    }

    public long getLong() {
        long value = 0L;
        try {
            value = this.dataStream.readLong();
        }
        catch (IOException var4) {
            LogHelper.error("ExtraBotaniaDataReader (getLong): " + var4.toString(), new Object[0]);
            var4.printStackTrace();
        }
        return value;
    }

    public NBTTagCompound getNBTTagCompound() {
        NBTTagCompound data = null;
        try {
            int e = this.dataStream.readInt();
            byte[] bytes = new byte[e];
            this.dataStream.read(bytes);
            ByteBuf buf = Unpooled.copiedBuffer((byte[])bytes);
            data = ByteBufUtils.readTag((ByteBuf)buf);
        }
        catch (IOException var5) {
            LogHelper.error("ExtraBotaniaDataReader (getNBTTagCompound): " + var5.toString(), new Object[0]);
            var5.printStackTrace();
        }
        return data;
    }

    public byte[] getRemainingBytes() {
        byte[] remaining = null;
        try {
            remaining = new byte[this.dataStream.available()];
            this.dataStream.read(remaining);
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataReader (getRemainingBytes): " + var3.toString(), new Object[0]);
            var3.printStackTrace();
        }
        return remaining;
    }

    public ItemStack getItemStack() {
        NBTTagCompound compound = this.getNBTTagCompound();
        if (compound == null) {
            return null;
        }
        ItemStack stack = ItemStack.loadItemStackFromNBT((NBTTagCompound)compound);
        return stack;
    }

    public int[] getIntArray() {
        try {
            int[] e = new int[this.dataStream.readInt()];
            for (int i = 0; i < e.length; ++i) {
                e[i] = this.dataStream.readInt();
            }
            return e;
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataReader (getIntArray): " + var3.toString(), new Object[0]);
            var3.printStackTrace();
            return new int[0];
        }
    }
}

