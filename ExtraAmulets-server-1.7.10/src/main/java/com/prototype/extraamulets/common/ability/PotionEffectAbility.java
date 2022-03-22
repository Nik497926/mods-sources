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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public final class PotionEffectAbility
extends Ability {
    private final PotionEffect effect;

    public PotionEffectAbility(PotionEffect potion) {
        super("potion_effect");
        this.effect = potion;
    }

    @Override
    public void addInformation(EntityPlayer player, List<String> tooltip) {
        PotionEffect effect = this.getPotionEffect();
        String text = String.format("%s (%s)", StatCollector.translateToLocal((String)effect.getEffectName()), StatCollector.translateToLocal((String)("enchantment.level." + (effect.getAmplifier() + 1))));
        if (Potion.potionTypes[effect.getPotionID()].isBadEffect()) {
            tooltip.add(EnumChatFormatting.RED + text);
        } else {
            tooltip.add(EnumChatFormatting.GRAY + text);
        }
    }

    @Override
    public void onEquipped(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            player.addPotionEffect(new PotionEffect(this.getPotionEffect()));
        }
    }

    @Override
    public void onUnequipped(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote && player.isPotionActive(this.getPotionEffect().getPotionID())) {
            player.removePotionEffect(this.getPotionEffect().getPotionID());
        }
    }

    public PotionEffect getPotionEffect() {
        return this.effect;
    }

    public static class GsonAdapter
    implements JsonSerializer<PotionEffectAbility>,
    JsonDeserializer<PotionEffectAbility> {
        public PotionEffectAbility deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = element.getAsJsonObject();
            int id = object.get("id").getAsInt();
            int amplifier = object.get("amplifier").getAsInt();
            boolean ambient = object.get("ambient").getAsBoolean();
            if (id < 0 || id >= Potion.potionTypes.length || Potion.potionTypes[id] == null) {
                throw new IllegalArgumentException("Potion with id <" + id + "> not found");
            }
            return new PotionEffectAbility(new PotionEffect(id, Integer.MAX_VALUE, amplifier, ambient));
        }

        public JsonElement serialize(PotionEffectAbility ability, Type type, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            PotionEffect effect = ability.getPotionEffect();
            object.addProperty("id", (Number)effect.getPotionID());
            object.addProperty("amplifier", (Number)effect.getAmplifier());
            object.addProperty("ambient", Boolean.valueOf(effect.getIsAmbient()));
            return object;
        }
    }
}

