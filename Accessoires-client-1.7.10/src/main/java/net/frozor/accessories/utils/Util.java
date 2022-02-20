/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.utils;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import net.frozor.accessories.client.item.animation.AbstractAnimation;

public class Util {
    public static String rus(String str) {
        try {
            return new String(str.getBytes(), StandardCharsets.UTF_8);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return str;
        }
    }

    public static String formattingBalance(int bal) {
        NumberFormat nf = DecimalFormat.getInstance(new Locale("ru", "RU"));
        DecimalFormatSymbols customSymbol = new DecimalFormatSymbols();
        customSymbol.setDecimalSeparator(',');
        customSymbol.setGroupingSeparator(' ');
        ((DecimalFormat)nf).setDecimalFormatSymbols(customSymbol);
        nf.setGroupingUsed(true);
        return nf.format(bal);
    }
}

