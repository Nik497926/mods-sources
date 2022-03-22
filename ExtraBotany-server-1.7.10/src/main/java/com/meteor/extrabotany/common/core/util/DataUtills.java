/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.util;

import com.meteor.extrabotany.common.core.util.ItemNBTHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.text.DecimalFormat;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DataUtills {
    public static DataUtills instance = new DataUtills();

    public void writeObjectToBytes(ByteBuf bytes, int dataType, Object object) {
        switch (dataType) {
            case 0: {
                bytes.writeByte(((Byte)object).byteValue());
                break;
            }
            case 1: {
                bytes.writeShort(((Short)object).shortValue());
                break;
            }
            case 2: {
                bytes.writeInt(((Integer)object).intValue());
                break;
            }
            case 3: {
                bytes.writeLong(((Long)object).longValue());
                break;
            }
            case 4: {
                bytes.writeFloat(((Float)object).floatValue());
                break;
            }
            case 5: {
                bytes.writeDouble(((Double)object).doubleValue());
                break;
            }
            case 7: {
                bytes.writeChar(((Character)object).charValue());
                break;
            }
            case 8: {
                ByteBufUtils.writeUTF8String(bytes, (String) object);
                break;
            }
            case 6: {
                bytes.writeBoolean(((Boolean)object).booleanValue());
                break;
            }
            case 9: {
                bytes.writeInt(((IntPair)object).i1);
                bytes.writeInt(((IntPair)object).i2);
            }
        }
    }

    public Object readObjectFromBytes(ByteBuf bytes, int dataType) {
        switch (dataType) {
            case 0: {
                return bytes.readByte();
            }
            case 1: {
                return bytes.readShort();
            }
            case 2: {
                return bytes.readInt();
            }
            case 3: {
                return bytes.readLong();
            }
            case 4: {
                return Float.valueOf(bytes.readFloat());
            }
            case 5: {
                return bytes.readDouble();
            }
            case 7: {
                return Character.valueOf(bytes.readChar());
            }
            case 8: {
                return ByteBufUtils.readUTF8String(bytes);
            }
            case 6: {
                return bytes.readBoolean();
            }
            case 9: {
                IntPair tx = new IntPair(0, 0);
                tx.i1 = bytes.readInt();
                tx.i2 = bytes.readInt();
                return tx;
            }
        }
        return null;
    }

    public static void writeObjectToItem(ItemStack stack, Object value, int datatype, String name) {
        switch (datatype) {
            case 0: {
                ItemNBTHelper.setByte(stack, name, (Byte)value);
                break;
            }
            case 1: {
                ItemNBTHelper.setShort(stack, name, (Short)value);
                break;
            }
            case 2: {
                ItemNBTHelper.setInteger(stack, name, (Integer)value);
                break;
            }
            case 3: {
                ItemNBTHelper.setLong(stack, name, (Long)value);
                break;
            }
            case 4: {
                ItemNBTHelper.setFloat(stack, name, ((Float)value).floatValue());
                break;
            }
            case 5: {
                ItemNBTHelper.setDouble(stack, name, (Double)value);
                break;
            }
            case 8: {
                ItemNBTHelper.setString(stack, name, (String)value);
                break;
            }
            case 6: {
                ItemNBTHelper.setBoolean(stack, name, (Boolean)value);
            }
        }
    }

    public static void writeObjectToCompound(NBTTagCompound compound, Object value, int datatype, String name) {
        switch (datatype) {
            case 0: {
                compound.setByte(name, ((Byte)value).byteValue());
                break;
            }
            case 1: {
                compound.setShort(name, ((Short)value).shortValue());
                break;
            }
            case 2: {
                compound.setInteger(name, ((Integer)value).intValue());
                break;
            }
            case 3: {
                compound.setLong(name, ((Long)value).longValue());
                break;
            }
            case 4: {
                compound.setFloat(name, ((Float)value).floatValue());
                break;
            }
            case 5: {
                compound.setDouble(name, ((Double)value).doubleValue());
                break;
            }
            case 8: {
                compound.setString(name, (String)value);
                break;
            }
            case 6: {
                compound.setBoolean(name, ((Boolean)value).booleanValue());
            }
        }
    }

    public static Object readObjectFromItem(ItemStack stack, int dataType, String name, Object defaultExpected) {
        switch (dataType) {
            case 0: {
                return ItemNBTHelper.getByte(stack, name, (Byte)defaultExpected);
            }
            case 1: {
                return ItemNBTHelper.getShort(stack, name, (Short)defaultExpected);
            }
            case 2: {
                return ItemNBTHelper.getInteger(stack, name, (Integer)defaultExpected);
            }
            case 3: {
                return ItemNBTHelper.getLong(stack, name, (Long)defaultExpected);
            }
            case 4: {
                return Float.valueOf(ItemNBTHelper.getFloat(stack, name, ((Float)defaultExpected).floatValue()));
            }
            case 5: {
                return ItemNBTHelper.getDouble(stack, name, (Double)defaultExpected);
            }
            case 8: {
                return ItemNBTHelper.getString(stack, name, (String)defaultExpected);
            }
            case 6: {
                return ItemNBTHelper.getBoolean(stack, name, (Boolean)defaultExpected);
            }
        }
        return null;
    }

    public static Object readObjectFromItem(ItemStack stack, int dataType, String name) {
        switch (dataType) {
            case 0: {
                return ItemNBTHelper.getByte(stack, name, (byte)0);
            }
            case 1: {
                return ItemNBTHelper.getShort(stack, name, (short)0);
            }
            case 2: {
                return ItemNBTHelper.getInteger(stack, name, 0);
            }
            case 3: {
                return ItemNBTHelper.getLong(stack, name, 0L);
            }
            case 4: {
                return Float.valueOf(ItemNBTHelper.getFloat(stack, name, 0.0f));
            }
            case 5: {
                return ItemNBTHelper.getDouble(stack, name, 0.0);
            }
            case 8: {
                return ItemNBTHelper.getString(stack, name, "");
            }
            case 6: {
                return ItemNBTHelper.getBoolean(stack, name, false);
            }
        }
        return null;
    }

    public static Object readObjectFromCompound(NBTTagCompound compound, int dataType, String name, Object defaultExpected) {
        switch (dataType) {
            case 0: {
                return compound.hasKey(name) ? compound.getByte(name) : ((Byte)defaultExpected).byteValue();
            }
            case 1: {
                return compound.hasKey(name) ? compound.getShort(name) : ((Short)defaultExpected).shortValue();
            }
            case 2: {
                return compound.hasKey(name) ? compound.getInteger(name) : ((Integer)defaultExpected).intValue();
            }
            case 3: {
                return compound.hasKey(name) ? compound.getLong(name) : ((Long)defaultExpected).longValue();
            }
            case 4: {
                return Float.valueOf(compound.hasKey(name) ? compound.getFloat(name) : ((Float)defaultExpected).floatValue());
            }
            case 5: {
                return compound.hasKey(name) ? compound.getDouble(name) : ((Double)defaultExpected).doubleValue();
            }
            case 8: {
                return compound.hasKey(name) ? compound.getString(name) : (String)defaultExpected;
            }
            case 6: {
                return compound.hasKey(name) ? compound.getBoolean(name) : ((Boolean)defaultExpected).booleanValue();
            }
        }
        return null;
    }

    public static String formatFileSize(long size) {
        if (size <= 0L) {
            return "0";
        }
        String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int)(Math.log10(size) / Math.log10(1024.0));
        return new DecimalFormat("#,##0.#").format((double)size / Math.pow(1024.0, digitGroups)) + " " + units[digitGroups];
    }

    public static class XYZTri<X, Y, Z> {
        public X x;
        public Y y;
        public Z z;

        public XYZTri(X x, Y y, Z z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class XZPair<X, Z> {
        public X x;
        public Z z;

        public XZPair(X x, Z z) {
            this.x = x;
            this.z = z;
        }

        public X getKey() {
            return this.x;
        }

        public Z getValue() {
            return this.z;
        }
    }

    public static class IntPair {
        public int i1;
        public int i2;

        public IntPair(int i1, int i2) {
            this.i1 = i1;
            this.i2 = i2;
        }
    }
}

