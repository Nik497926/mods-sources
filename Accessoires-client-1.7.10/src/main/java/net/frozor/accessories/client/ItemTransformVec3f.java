/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Type;
import net.frozor.accessories.client.ui.UIItem;
import net.frozor.accessories.client.ui.UIScroll;
import net.frozor.accessories.utils.JsonUtils;
import net.frozor.accessories.utils.MathHelper;
import org.lwjgl.util.vector.ReadableVector3f;
import org.lwjgl.util.vector.Vector3f;

@SideOnly(value=Side.CLIENT)
public class ItemTransformVec3f {
    public final Vector3f scale;
    public final Vector3f rotation;
    public final Vector3f translation;
    public static final ItemTransformVec3f DEFAULT = new ItemTransformVec3f(new Vector3f(), new Vector3f(), new Vector3f(1.0f, 1.0f, 1.0f));

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (this.getClass() != p_equals_1_.getClass()) {
            return false;
        }
        ItemTransformVec3f itemtransformvec3f = (ItemTransformVec3f)p_equals_1_;
        return this.rotation.equals((Object)itemtransformvec3f.rotation) && this.scale.equals((Object)itemtransformvec3f.scale) && this.translation.equals((Object)itemtransformvec3f.translation);
    }

    public ItemTransformVec3f(Vector3f rotation, Vector3f translation, Vector3f scale) {
        this.rotation = new Vector3f((ReadableVector3f)rotation);
        this.translation = new Vector3f((ReadableVector3f)translation);
        this.scale = new Vector3f((ReadableVector3f)scale);
    }

    public String toString() {
        return UIScroll.l("b\u0013N\n\u007f\u0015J\tX\u0001D\u0015F1N\u0004\u0018\u0001P\u0015D\u0013J\u0013B\bEZ") + this.rotation + UIItem.l("\tjQ8D$V&D>L%Kw") + this.translation + UIScroll.l("\u0007GX\u0004J\u000bNZ") + this.scale + '}';
    }

    public int hashCode() {
        int i = this.rotation.hashCode();
        i = 31 * i + this.translation.hashCode();
        i = 31 * i + this.scale.hashCode();
        return i;
    }

    @SideOnly(value=Side.CLIENT)
    public static class Deserializer
    implements JsonDeserializer<ItemTransformVec3f> {
        private static final Vector3f TRANSLATION_DEFAULT;
        private static final Vector3f ROTATION_DEFAULT;
        private static final Vector3f SCALE_DEFAULT;

        private /* synthetic */ Vector3f parseVector3f(JsonObject jsonObject, String key, Vector3f defaultValue) {
            if (!jsonObject.has(key)) {
                return defaultValue;
            }
            JsonArray jsonarray = JsonUtils.getJsonArray(jsonObject, key);
            if (jsonarray.size() != 3) {
                throw new JsonParseException(UIScroll.l("\"S\u0017N\u0004_\u0002OG\u0018G") + key + UIItem.l("\u0005<D&P/Vf\u0005,J?K.\u001fj") + jsonarray.size());
            }
            float[] afloat = new float[3];
            for (int i = 0; i < afloat.length; ++i) {
                afloat[i] = JsonUtils.getFloat(jsonarray.get(i), key + UIScroll.l("<") + i + UIItem.l("\u0017"));
            }
            return new Vector3f(afloat[0], afloat[1], afloat[2]);
        }

        public ItemTransformVec3f deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            Vector3f vector3f = this.parseVector3f(jsonobject, UIScroll.l("Y\b_\u0006_\u000eD\t"), ROTATION_DEFAULT);
            Vector3f vector3f1 = this.parseVector3f(jsonobject, UIItem.l(">W+K9I+Q#J$"), TRANSLATION_DEFAULT);
            vector3f1.scale(0.0625f);
            vector3f1.x = MathHelper.clamp(vector3f1.x, -5.0f, 5.0f);
            vector3f1.y = MathHelper.clamp(vector3f1.y, -5.0f, 5.0f);
            vector3f1.z = MathHelper.clamp(vector3f1.z, -5.0f, 5.0f);
            Vector3f vector3f2 = this.parseVector3f(jsonobject, UIScroll.l("\u0014H\u0006G\u0002"), SCALE_DEFAULT);
            vector3f2.x = MathHelper.clamp(vector3f2.x, -4.0f, 4.0f);
            vector3f2.y = MathHelper.clamp(vector3f2.y, -4.0f, 4.0f);
            vector3f2.z = MathHelper.clamp(vector3f2.z, -4.0f, 4.0f);
            return new ItemTransformVec3f(vector3f, vector3f1, vector3f2);
        }

        static {
            ROTATION_DEFAULT = new Vector3f(0.0f, 0.0f, 0.0f);
            TRANSLATION_DEFAULT = new Vector3f(0.0f, 0.0f, 0.0f);
            SCALE_DEFAULT = new Vector3f(1.0f, 1.0f, 1.0f);
        }
    }
}

