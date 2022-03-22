/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.ability;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.prototype.extraamulets.common.ability.Ability;
import java.lang.reflect.Type;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public final class ReduceMagicDamageAbility
extends Ability {
    private final float reduceMagicDamage;

    public ReduceMagicDamageAbility(float reduceMagicDamage) {
        super("reduce_magic_damage");
        this.reduceMagicDamage = reduceMagicDamage;
    }

    @Override
    public void addInformation(EntityPlayer player, List<String> tooltip) {
        tooltip.add(StatCollector.translateToLocalFormatted((String)"ability.reduce_magic_damage.tooltip", (Object[])new Object[]{Float.valueOf(this.getReduceMagicDamage())}));
    }

    public float getReduceMagicDamage() {
        return this.reduceMagicDamage;
    }

    public static class GsonAdapter
    implements JsonSerializer<ReduceMagicDamageAbility>,
    JsonDeserializer<ReduceMagicDamageAbility> {
        public ReduceMagicDamageAbility deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = element.getAsJsonObject();
            float reduceMagicDamage = object.get("reduce-magic-damage").getAsFloat();
            return new ReduceMagicDamageAbility(reduceMagicDamage);
        }

        public JsonElement serialize(ReduceMagicDamageAbility ability, Type type, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("reduce-magic-damage", (Number)Float.valueOf(ability.getReduceMagicDamage()));
            return object;
        }
    }
}

