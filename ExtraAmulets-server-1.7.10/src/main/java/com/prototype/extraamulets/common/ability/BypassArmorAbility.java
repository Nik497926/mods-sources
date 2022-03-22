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

public final class BypassArmorAbility
extends Ability {
    private final float chance;

    public BypassArmorAbility(float chance) {
        super("bypass_armor");
        this.chance = chance;
    }

    @Override
    public void addInformation(EntityPlayer player, List<String> tooltip) {
        tooltip.add(StatCollector.translateToLocalFormatted((String)"ability.bypass_armor.tooltip", (Object[])new Object[]{Float.valueOf(this.getChance())}));
    }

    public float getChance() {
        return this.chance;
    }

    public static class GsonAdapter
    implements JsonSerializer<BypassArmorAbility>,
    JsonDeserializer<BypassArmorAbility> {
        public BypassArmorAbility deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = element.getAsJsonObject();
            float chance = object.get("chance").getAsFloat();
            return new BypassArmorAbility(chance);
        }

        public JsonElement serialize(BypassArmorAbility ability, Type type, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("chance", (Number)Float.valueOf(ability.getChance()));
            return object;
        }
    }
}

