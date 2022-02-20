/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.frozor.accessories.AccessoriesCore;
import net.frozor.accessories.client.ItemTransformVec3f;
import net.frozor.accessories.client.item.AccessoryItem;
import net.frozor.accessories.client.item.CategoryType;
import net.frozor.accessories.client.ui.UIItem;
import net.frozor.accessories.utils.Logger;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;

public class ModelLoader {
    private static String fileIndexes = "assets/accessories/models/indexes.json";

    public static void printError(AccessoryItem item, HashMap<ParseError, String> errors) {
        Logger.info(String.format("ITEM ERRORS : %s", item.getINDEX()));
        for (Map.Entry<ParseError, String> map : errors.entrySet()) {
            Object[] objectArray = new Object[2];
            objectArray[0] = map.getKey().name().replaceAll("_", " ");
            objectArray[1] = map.getValue();
            Logger.info(String.format(" - %s, path {%s}", objectArray));
        }
    }

    public static LinkedHashMap<CategoryType, LinkedHashMap<String, AccessoryItem>> initModels() {
        LinkedHashMap<CategoryType, LinkedHashMap<String, AccessoryItem>> modelsArray = new LinkedHashMap<CategoryType, LinkedHashMap<String, AccessoryItem>>();
        for (CategoryType type : CategoryType.values()) {
            modelsArray.put(type, new LinkedHashMap());
        }

        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(new InputStreamReader(AccessoriesCore.class.getClassLoader().getResourceAsStream(fileIndexes)));
            for (Map.Entry map : element.getAsJsonObject().entrySet()) {
                String modelPath;
                if (((String)map.getKey()).equalsIgnoreCase("_comment")) continue;
                JsonObject item = ((JsonElement)map.getValue()).getAsJsonObject();
                HashMap<ParseError, String> errors = new HashMap<ParseError, String>();
                AccessoryItem accessoryItem = new AccessoryItem((String)map.getKey(), CategoryType.valueOf(item.get("type").getAsString()));
                accessoryItem.setName(item.get("name").getAsString());
                if (item.has("author")) {
                    accessoryItem.setAuthor(item.get("author").getAsString());
                }
                if (item.has("new")) {
                    accessoryItem.setNews(item.get("new").getAsBoolean());
                }
                if (item.has("isWing")) {
                    accessoryItem.setWing(item.get("isWing").getAsBoolean());
                }
                if (!ModelLoader.fileExist(modelPath = String.format("models/%1$s/%2$s/%2$s.obj", accessoryItem.getType().name().toLowerCase(), item.get("model").getAsString()))) {
                    errors.put(ParseError.NOT_FOUND_MODEL, modelPath);
                } else {
                    accessoryItem.setModel(AdvancedModelLoader.loadModel(new ResourceLocation("accessories:" + modelPath)));
                }
                if (item.has("customTexture")) {
                    String customTexturePath = String.format("models/%s/%s.png", accessoryItem.getType().name().toLowerCase(), item.get("customTexture").getAsString());
                    accessoryItem.setTexture(new ResourceLocation("accessories:" + customTexturePath));
                    if (!ModelLoader.fileExist(customTexturePath)) {
                        errors.put(ParseError.NOT_FOUND_TEXTURE, customTexturePath);
                    }
                } else {
                    String localTexturePath = String.format("models/%1$s/%2$s/%2$s.png", accessoryItem.getType().name().toLowerCase(), accessoryItem.getINDEX());
                    accessoryItem.setTexture(new ResourceLocation("accessories:" + localTexturePath));
                    if (!ModelLoader.fileExist(localTexturePath)) {
                        errors.put(ParseError.NOT_FOUND_TEXTURE, localTexturePath);
                    }
                }
                InputStream inputStream = AccessoriesCore.class.getClassLoader().getResourceAsStream(String.format("assets/accessories/models/%s/%2$s/%2$s.json", accessoryItem.getType().name().toLowerCase(), item.get("model").getAsString()));
                if (inputStream != null) {
                    JsonObject jsonObject = jsonParser.parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject();
                    ItemTransformVec3f itemTransformVec3f = ItemTransformVec3f.DEFAULT;
                    if (jsonObject.getAsJsonObject().has("display")) {
                        JsonArray jsonArray;
                        JsonObject prefs = jsonObject.getAsJsonObject("display");
                        ItemTransformVec3f.Deserializer deserializer = new ItemTransformVec3f.Deserializer();
                        itemTransformVec3f = deserializer.deserialize(prefs.get("head"), null, null);
                        if (prefs.has("head")) {
                            JsonObject headJsonObject = prefs.getAsJsonObject("head");
                            for (Map.Entry headJson : headJsonObject.entrySet()) {
                                if (((String)headJson.getKey()).equalsIgnoreCase("rotation")) {
                                    jsonArray = ((JsonElement)headJson.getValue()).getAsJsonArray();
                                    accessoryItem.getPreferencesRenderer().setRotation(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                                }
                                if (((String)headJson.getKey()).equalsIgnoreCase("translation")) {
                                    jsonArray = ((JsonElement)headJson.getValue()).getAsJsonArray();
                                    accessoryItem.getPreferencesRenderer().setTranslation(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                                }
                                if (!((String)headJson.getKey()).equalsIgnoreCase("scale")) continue;
                                jsonArray = ((JsonElement)headJson.getValue()).getAsJsonArray();
                                accessoryItem.getPreferencesRenderer().setScale(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                            }
                        }
                        if (prefs.has("gui")) {
                            JsonObject guiJsonObject = prefs.getAsJsonObject("gui");
                            for (Map.Entry guiJson : guiJsonObject.entrySet()) {
                                if (((String)guiJson.getKey()).equalsIgnoreCase("rotation")) {
                                    jsonArray = ((JsonElement)guiJson.getValue()).getAsJsonArray();
                                    accessoryItem.getPreferencesGui().setRotation(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                                }
                                if (((String)guiJson.getKey()).equalsIgnoreCase("translation")) {
                                    jsonArray = ((JsonElement)guiJson.getValue()).getAsJsonArray();
                                    accessoryItem.getPreferencesGui().setTranslation(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                                }
                                if (!((String)guiJson.getKey()).equalsIgnoreCase("scale")) continue;
                                jsonArray = ((JsonElement)guiJson.getValue()).getAsJsonArray();
                                accessoryItem.getPreferencesGui().setScale(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                            }
                        }
                    }
                    accessoryItem.headTransform = itemTransformVec3f;
                } else {
                    errors.put(ParseError.NOT_FOUND_JSON, String.format("models/%s/%2$s/%2$s.json", accessoryItem.getType().name().toLowerCase(), item.get("model").getAsString()));
                }
                if (errors.size() > 0) {
                    ModelLoader.printError(accessoryItem, errors);
                    continue;
                }
                accessoryItem.initRendererCall();
                modelsArray.get((Object)accessoryItem.getType()).put(accessoryItem.getINDEX(), accessoryItem);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        int countItems = 0;
        for (LinkedHashMap map : modelsArray.values()) {
            countItems += map.values().size();
        }

        return modelsArray;
    }

    public static boolean fileExist(String path) {
        InputStream inputStream = AccessoriesCore.class.getClassLoader().getResourceAsStream("assets/accessories/" + path);
        return inputStream != null;
    }

    public static enum ParseError {
        NOT_FOUND_MODEL,
        NOT_FOUND_TEXTURE,
        NOT_FOUND_JSON;

    }
}

