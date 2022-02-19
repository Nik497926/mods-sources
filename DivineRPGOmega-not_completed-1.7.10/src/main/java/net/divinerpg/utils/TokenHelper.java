/*
 * Decompiled with CFR 0.152.
 */
package net.divinerpg.utils;

import net.divinerpg.utils.LogHelper;

public class TokenHelper {
    public static String replaceToken(String original, char token, Object replacement) {
        String result = original;
        for (int i = 0; i < original.length(); ++i) {
            char c = original.charAt(i);
            if (c != token) continue;
            TokenHelper.logReplacement(token, replacement, i);
            result = result.replaceAll(String.valueOf(token), replacement.toString());
        }
        return result;
    }

    private static void logReplacement(char token, Object replacement, int index) {
        LogHelper.debug("Replacing token '" + token + "' at index " + index + "with \"" + replacement + "\"");
    }
}

