/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;
import net.frozor.accessories.client.item.animation.AbstractAnimation;
import net.frozor.accessories.client.ui.UIScroll;
import org.apache.commons.lang3.StringUtils;

public class JsonUtils {
    public static float getFloat(JsonElement json, String memberName) {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            return json.getAsFloat();
        }
        throw new JsonSyntaxException("Expected " + memberName + UIScroll.l("\u000b\u0013DGI\u0002\u000b\u0006\u000b!G\bJ\u0013\u0007G\\\u0006XG") + JsonUtils.toString(json));
    }

    public static JsonArray getJsonArray(JsonObject json, String memberName, @Nullable JsonArray fallback) {
        return json.has(memberName) ? JsonUtils.getJsonArray(json.get(memberName), memberName) : fallback;
    }

    public static JsonArray getJsonArray(JsonElement json, String memberName) {
        if (json.isJsonArray()) {
            return json.getAsJsonArray();
        }
        throw new JsonSyntaxException("Excepted " + memberName + UIScroll.l("\u000b\u0013DGI\u0002\u000b\u0006\u000b-X\bE&Y\u0015J\u001e\u0007G\\\u0006XG") + JsonUtils.toString(json));
    }

    public static JsonArray getJsonArray(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return JsonUtils.getJsonArray(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + UIScroll.l("\u0007GN\u001f[\u0002H\u0013N\u0003\u000b\u0013DGM\u000eE\u0003\u000b\u0006\u000b-X\bE&Y\u0015J\u001e"));
    }

    public static String toString(JsonElement json) {
        String s = StringUtils.abbreviateMiddle((String)String.valueOf(json), (String)AbstractAnimation.l("\u000eO\u000e"), (int)10);
        if (json == null) {
            return UIScroll.l("E\u0012G\u000b\u000bOF\u000eX\u0014B\tLN");
        }
        if (json.isJsonNull()) {
            return AbstractAnimation.l("N\u0014L\r\u0000IJ\u0012O\u000f\t");
        }
        if (json.isJsonArray()) {
            return UIScroll.l("J\t\u000b\u0006Y\u0015J\u001e\u000bO") + s + AbstractAnimation.l("\t");
        }
        if (json.isJsonObject()) {
            return UIScroll.l("\u0006EGD\u0005A\u0002H\u0013\u000bO") + s + AbstractAnimation.l("\t");
        }
        if (json.isJsonPrimitive()) {
            JsonPrimitive jsonprimitive = json.getAsJsonPrimitive();
            if (jsonprimitive.isNumber()) {
                return UIScroll.l("JGE\u0012F\u0005N\u0015\u000bO") + s + AbstractAnimation.l("\t");
            }
            if (jsonprimitive.isBoolean()) {
                return UIScroll.l("\u0006\u000b\u0005D\bG\u0002J\t\u000bO") + s + AbstractAnimation.l("\t");
            }
        }
        return s;
    }
}

