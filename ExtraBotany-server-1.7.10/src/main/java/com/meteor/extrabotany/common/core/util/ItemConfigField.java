/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.util;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.core.network.ItemConfigPacket;
import com.meteor.extrabotany.common.core.util.DataUtills;
import com.meteor.extrabotany.common.core.util.IConfigurableItem;
import com.meteor.extrabotany.common.core.util.InfoHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;

public class ItemConfigField {
    public Object value;
    public int slot;
    public int datatype;
    public String name;
    public int fieldid;
    public Object max;
    public Object min;
    public Object incroment;
    public String modifier;
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

    public ItemConfigField(int datatype, int slot, String name) {
        this.slot = slot;
        this.datatype = datatype;
        this.name = name;
    }

    public ItemConfigField(int datatype, Object value, int slot, String name) {
        this.value = value;
        this.slot = slot;
        this.datatype = datatype;
        this.name = name;
    }

    public ItemConfigField setMinMaxAndIncromente(Object min, Object max, Object incroment) {
        this.max = max;
        this.min = min;
        this.incroment = incroment;
        return this;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("button.de." + this.name + ".name");
    }

    public ItemConfigField readFromItem(ItemStack stack, Object defaultExpected) {
        this.value = DataUtills.readObjectFromCompound(IConfigurableItem.ProfileHelper.getProfileCompound(stack), this.datatype, this.name, defaultExpected);
        return this;
    }

    public ItemConfigField setModifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public String getFormattedValue() {
        if (this.datatype == 2 && !StringUtils.isNullOrEmpty(this.modifier) && this.modifier.equals("AOE")) {
            int i = (Integer)this.value;
            return (i *= 2) + 1 + "x" + (i + 1);
        }
        if (this.datatype == 6) {
            return (Boolean)this.value != false ? StatCollector.translateToLocal("gui.de.on.txt") : StatCollector.translateToLocal("gui.de.off.txt");
        }
        if (this.datatype == 4 && !StringUtils.isNullOrEmpty(this.modifier) && this.modifier.equals("PERCENT")) {
            return Math.round((double)((Float)this.value).floatValue() * 100.0) + "%";
        }
        if (this.datatype == 4 && !StringUtils.isNullOrEmpty(this.modifier) && this.modifier.equals("PLUSPERCENT")) {
            return "+" + Math.round((double)((Float)this.value).floatValue() * 100.0) + "%";
        }
        return String.valueOf(this.value);
    }

    public String getMaxFormattedValue() {
        if (this.datatype == 2 && !StringUtils.isNullOrEmpty(this.modifier) && this.modifier.equals("AOE")) {
            int i = (Integer)this.max;
            return (i *= 2) + 1 + "x" + (i + 1);
        }
        return String.valueOf(this.max);
    }

    public String getTooltipInfo() {
        return InfoHelper.ITC() + this.getLocalizedName() + ": " + InfoHelper.HITC() + this.getFormattedValue();
    }

    public void sendChanges() {
        ExtraBotany.networkawake.sendToServer(new ItemConfigPacket(this));
    }

    public int castToInt() {
        switch (this.datatype) {
            case 0: {
                return ((Byte)this.value).byteValue();
            }
            case 1: {
                return ((Short)this.value).shortValue();
            }
            case 2: {
                return (Integer)this.value;
            }
            case 3: {
                long l = (Long)this.value;
                return (int)l;
            }
            case 4: {
                float f = ((Float)this.value).floatValue();
                return (int)f;
            }
            case 5: {
                double d = (Double)this.value;
                return (int)d;
            }
            case 6: {
                return (Boolean)this.value != false ? 1 : 0;
            }
        }
        return 0;
    }

    public double castToDouble() {
        switch (this.datatype) {
            case 0: {
                return ((Byte)this.value).byteValue();
            }
            case 1: {
                return ((Short)this.value).shortValue();
            }
            case 2: {
                return ((Integer)this.value).intValue();
            }
            case 3: {
                long l = (Long)this.value;
                return l;
            }
            case 4: {
                float f = ((Float)this.value).floatValue();
                return f;
            }
            case 5: {
                return (Double)this.value;
            }
            case 6: {
                return (Boolean)this.value != false ? 1.0 : 0.0;
            }
        }
        return 0.0;
    }

    public double castMinToDouble() {
        switch (this.datatype) {
            case 0: {
                return ((Byte)this.min).byteValue();
            }
            case 1: {
                return ((Short)this.min).shortValue();
            }
            case 2: {
                return ((Integer)this.min).intValue();
            }
            case 3: {
                long l = (Long)this.min;
                return l;
            }
            case 4: {
                float f = ((Float)this.min).floatValue();
                return f;
            }
            case 5: {
                return (Double)this.min;
            }
            case 6: {
                return 0.0;
            }
        }
        return 0.0;
    }

    public double castMaxToDouble() {
        switch (this.datatype) {
            case 0: {
                return ((Byte)this.max).byteValue();
            }
            case 1: {
                return ((Short)this.max).shortValue();
            }
            case 2: {
                return ((Integer)this.max).intValue();
            }
            case 3: {
                long l = (Long)this.max;
                return l;
            }
            case 4: {
                float f = ((Float)this.max).floatValue();
                return f;
            }
            case 5: {
                return (Double)this.max;
            }
            case 6: {
                return 1.0;
            }
        }
        return 0.0;
    }
}

