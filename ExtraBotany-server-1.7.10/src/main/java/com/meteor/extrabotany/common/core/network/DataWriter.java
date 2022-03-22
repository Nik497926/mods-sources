/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network;

import com.meteor.extrabotany.common.core.util.LogHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DataWriter {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    DataOutputStream data = new DataOutputStream(this.bytes);

    public DataWriter add(int value) {
        try {
            this.data.writeInt(value);
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataWriter: " + var3.getMessage());
        }
        return this;
    }

    public DataWriter add(boolean value) {
        try {
            this.data.writeBoolean(value);
        }
        catch (IOException var3) {
            LogHelper.error(var3.getMessage());
        }
        return this;
    }

    public DataWriter add(byte value) {
        try {
            this.data.writeByte(value);
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataWriter: " + var3.getMessage());
        }
        return this;
    }

    public DataWriter add(String value) {
        try {
            this.data.writeUTF(value);
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataWriter: " + var3.getMessage());
        }
        return this;
    }

    public DataWriter add(short value) {
        try {
            this.data.writeShort(value);
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataWriter: " + var3.getMessage());
        }
        return this;
    }

    public DataWriter add(double value) {
        try {
            this.data.writeDouble(value);
        }
        catch (IOException var4) {
            LogHelper.error("ExtraBotaniaDataWriter: " + var4.getMessage());
        }
        return this;
    }

    public DataWriter add(float value) {
        try {
            this.data.writeFloat(value);
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataWriter: " + var3.getMessage());
        }
        return this;
    }

    public DataWriter add(long value) {
        try {
            this.data.writeLong(value);
        }
        catch (IOException var4) {
            LogHelper.error("ExtraBotaniaDataWriter: " + var4.getMessage());
        }
        return this;
    }

    public DataWriter add(byte[] value) {
        try {
            this.data.write(value);
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataWriter: " + var3.getMessage());
        }
        return this;
    }

    public DataWriter add(int[] value) {
        try {
            this.data.writeInt(value.length);
            for (int e = 0; e < value.length; ++e) {
                this.data.writeInt(value[e]);
            }
        }
        catch (IOException var3) {
            LogHelper.error("ExtraBotaniaDataWriter: " + var3.getMessage());
        }
        return this;
    }

    public DataWriter add(NBTTagCompound compound) {
        try {
            ByteBuf ex = Unpooled.buffer();
            ByteBufUtils.writeTag(ex, compound);
            byte[] arr = ex.array();
            this.data.writeInt(arr.length);
            this.data.write(arr);
        }
        catch (IOException var4) {
            LogHelper.error("ExtraBotaniaDataWriter: " + var4.getMessage());
        }
        return this;
    }

    public DataWriter add(ItemStack stack) {
        NBTTagCompound compound = new NBTTagCompound();
        stack.writeToNBT(compound);
        return this.add(compound);
    }

    public byte[] generate() {
        return this.bytes.toByteArray();
    }
}

