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

public final class FallingProtectionAbility
extends Ability {
    private final float damageReduceModifier;

    public FallingProtectionAbility(float damageReduceModifier) {
        super("falling_protection");
        this.damageReduceModifier = damageReduceModifier;
    }

    @Override
    public void addInformation(EntityPlayer player, List<String> tooltip) {
        tooltip.add(StatCollector.translateToLocalFormatted((String)"ability.falling_protection.tooltip", (Object[])new Object[0]));
    }

    public float getDamageReduceModifier() {
        return this.damageReduceModifier;
    }

    public static class GsonAdapter
    implements JsonSerializer<FallingProtectionAbility>,
    JsonDeserializer<FallingProtectionAbility> {
        public FallingProtectionAbility deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = element.getAsJsonObject();
            float damageReduceModifier = object.get("damage-reduce-modifier").getAsFloat();
            return new FallingProtectionAbility(damageReduceModifier);
        }

        public JsonElement serialize(FallingProtectionAbility ability, Type type, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("damage-reduce-modifier", (Number)Float.valueOf(ability.getDamageReduceModifier()));
            return object;
        }
    }
}

