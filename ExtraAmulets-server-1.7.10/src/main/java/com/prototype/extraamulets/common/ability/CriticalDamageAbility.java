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

public final class CriticalDamageAbility
extends Ability {
    private final float chance;
    private final float damageModifier;

    public CriticalDamageAbility(float chance, float damageModifier) {
        super("critical_damage");
        this.chance = chance;
        this.damageModifier = damageModifier;
    }

    @Override
    public void addInformation(EntityPlayer player, List<String> tooltip) {
        tooltip.add(StatCollector.translateToLocalFormatted((String)"ability.critical_damage.tooltip", (Object[])new Object[]{Float.valueOf(this.getChance()), Float.valueOf(this.getDamageModifier())}));
    }

    public float getChance() {
        return this.chance;
    }

    public float getDamageModifier() {
        return this.damageModifier;
    }

    public static class GsonAdapter
    implements JsonSerializer<CriticalDamageAbility>,
    JsonDeserializer<CriticalDamageAbility> {
        public CriticalDamageAbility deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = element.getAsJsonObject();
            float chance = object.get("chance").getAsFloat();
            float damageModifier = object.get("damage-modifier").getAsFloat();
            return new CriticalDamageAbility(chance, damageModifier);
        }

        public JsonElement serialize(CriticalDamageAbility ability, Type type, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("chance", (Number)Float.valueOf(ability.getChance()));
            object.addProperty("damage-modifier", (Number)Float.valueOf(ability.getDamageModifier()));
            return object;
        }
    }
}

