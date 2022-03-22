/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.prototype.extraamulets.common.ability.Abilities;
import com.prototype.extraamulets.common.ability.Ability;
import com.prototype.extraamulets.common.ability.BypassArmorAbility;
import com.prototype.extraamulets.common.ability.CriticalDamageAbility;
import com.prototype.extraamulets.common.ability.FallingProtectionAbility;
import com.prototype.extraamulets.common.ability.PotionEffectAbility;
import com.prototype.extraamulets.common.ability.ReduceMagicDamageAbility;
import com.prototype.extraamulets.common.amulet.Amulet;
import com.prototype.extraamulets.common.amulet.Amulets;
import com.prototype.extraamulets.common.registry.AbilityRegistry;
import com.prototype.extraamulets.common.render.RenderAmulet;
import com.prototype.extraamulets.common.resource.Resource;
import com.prototype.extraamulets.common.resource.model.base.ObjModel;
import com.prototype.extraamulets.common.resource.model.base.Texture;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.crash.CrashReport;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Logger;

public final class Config {
    public static final Type TYPE_MAP_STRING_AMULET = new TypeToken<Map<String, Amulet>>(){}.getType();

    public static void load(Logger logger) {
        Gson gson = new GsonBuilder().registerTypeAdapter(RenderAmulet.class, (Object)new RenderAmulet.RenderAmuletAdapter()).registerTypeAdapter(Amulets.class, (Object)new AmuletsGsonAdapter()).registerTypeAdapter(Amulet.class, (Object)new AmuletGsonAdapter()).registerTypeAdapter(Abilities.class, (Object)new AbilitiesGsonAdapter()).registerTypeAdapter(PotionEffectAbility.class, (Object)new PotionEffectAbility.GsonAdapter()).registerTypeAdapter(FallingProtectionAbility.class, (Object)new FallingProtectionAbility.GsonAdapter()).registerTypeAdapter(CriticalDamageAbility.class, (Object)new CriticalDamageAbility.GsonAdapter()).registerTypeAdapter(ReduceMagicDamageAbility.class, (Object)new ReduceMagicDamageAbility.GsonAdapter()).registerTypeAdapter(BypassArmorAbility.class, (Object)new BypassArmorAbility.GsonAdapter()).setPrettyPrinting().create();
        File directory = new File("./exconfigs");
        directory.mkdirs();
        Path path = new File(directory, "extraamulets.json").toPath();
        try {
            if (!Files.exists(path, new LinkOption[0])) {
                Config.createExampleConfig(logger, gson, path);
            }
            String json = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            Amulets amulets = (Amulets)gson.fromJson(json, Amulets.class);
            logger.info("Loaded <{}> amulets", new Object[]{amulets.getAmulets().size()});
            amulets.register();
        }
        catch (Exception ex) {
            throw new ReportedException(CrashReport.makeCrashReport((Throwable)ex, (String)"Exception when loading ExtraAmulets config"));
        }
    }

    private static void createExampleConfig(Logger logger, Gson gson, Path path) throws IOException {
        Resource<ObjModel> model = ObjModel.getManager().registerResource(new ResourceLocation("modid:models/model.swm"), (ObjModel.Properties)() -> false);
        Resource<Texture> texture = Texture.getManager().registerResource(new ResourceLocation("modid:textures/models/model.png"), new Texture.Properties(){

            @Override
            public boolean isClamp() {
                return true;
            }

            @Override
            public boolean isBlur() {
                return true;
            }
        });
        RenderAmulet render = new RenderAmulet(model, texture, true);
        HashMap<String, Amulet> amuletMap = new HashMap<String, Amulet>();
        ArrayList<Ability> abilityList = new ArrayList<Ability>();
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.field_76444_x.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.resistance.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.digSpeed.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.nightVision.id, Integer.MAX_VALUE, 0)));
        Abilities abilities = new Abilities(abilityList);
        amuletMap.put("earth", new Amulet(render, abilities));
        abilityList = new ArrayList();
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.damageBoost.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.fireResistance.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new CriticalDamageAbility(0.5f, 1.5f));
        abilityList.add(new BypassArmorAbility(0.5f));
        abilities = new Abilities(abilityList);
        amuletMap.put("fire", new Amulet(render, abilities));
        amuletMap.put("water", new Amulet(render, abilities));
        abilityList = new ArrayList();
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.regeneration.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.waterBreathing.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new ReduceMagicDamageAbility(0.5f));
        abilities = new Abilities(abilityList);
        amuletMap.put("water", new Amulet(render, abilities));
        abilityList = new ArrayList();
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.moveSpeed.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.jump.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.field_76434_w.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new FallingProtectionAbility(0.0f));
        abilities = new Abilities(abilityList);
        amuletMap.put("wind", new Amulet(render, abilities));
        abilityList = new ArrayList();
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.damageBoost.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.regeneration.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.moveSpeed.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.resistance.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new PotionEffectAbility(new PotionEffect(Potion.field_76443_y.id, Integer.MAX_VALUE, 0)));
        abilityList.add(new CriticalDamageAbility(0.5f, 1.5f));
        abilityList.add(new ReduceMagicDamageAbility(0.5f));
        abilities = new Abilities(abilityList);
        amuletMap.put("equality", new Amulet(render, abilities));
        Amulets amulets = new Amulets(amuletMap);
        String json = gson.toJson((Object)amulets, Amulets.class);
        Files.write(path, json.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        logger.info("Created config <{}> for example.", new Object[]{path.getFileName()});
    }

    public static class AbilitiesGsonAdapter
    implements JsonSerializer<Abilities>,
    JsonDeserializer<Abilities> {
        public Abilities deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            ArrayList<Ability> abilities = new ArrayList<Ability>();
            JsonArray array = json.getAsJsonArray();
            for (JsonElement element : array) {
                JsonObject object = element.getAsJsonObject();
                for (Map.Entry entry : object.entrySet()) {
                    abilities.add((Ability)context.deserialize((JsonElement)entry.getValue(), AbilityRegistry.getTypeByName((String)entry.getKey())));
                }
            }
            return new Abilities(abilities);
        }

        public JsonElement serialize(Abilities abilities, Type type, JsonSerializationContext context) {
            JsonArray array = new JsonArray();
            for (Ability ability : abilities.getAbilities()) {
                JsonObject object = new JsonObject();
                object.add(ability.getName(), context.serialize((Object)ability, ability.getClass()));
                array.add((JsonElement)object);
            }
            return array;
        }
    }

    public static class AmuletGsonAdapter
    implements JsonSerializer<Amulet>,
    JsonDeserializer<Amulet> {
        public Amulet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            RenderAmulet render = (RenderAmulet)context.deserialize(object.get("render"), RenderAmulet.class);
            Abilities abilities = (Abilities)context.deserialize(object.get("abilities"), Abilities.class);
            return new Amulet(render, abilities);
        }

        public JsonElement serialize(Amulet amulet, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.add("render", context.serialize((Object)amulet.getRender(), RenderAmulet.class));
            object.add("abilities", context.serialize((Object)amulet.getAbilities(), Abilities.class));
            return object;
        }
    }

    public static class AmuletsGsonAdapter
    implements JsonSerializer<Amulets>,
    JsonDeserializer<Amulets> {
        public Amulets deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = element.getAsJsonObject();
            Map amulets = (Map)context.deserialize(object.get("amulets"), TYPE_MAP_STRING_AMULET);
            return new Amulets(amulets);
        }

        public JsonElement serialize(Amulets amulets, Type type, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.add("amulets", context.serialize(amulets.getAmulets(), TYPE_MAP_STRING_AMULET));
            return object;
        }
    }
}

