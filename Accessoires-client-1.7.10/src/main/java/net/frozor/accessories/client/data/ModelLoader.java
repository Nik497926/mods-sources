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
        Logger.info(String.format(UIItem.l("l\u001e`\u0007\u0005\u000fw\u0018j\u0018vj\u001fj\u00009"), item.getINDEX()));
        for (Map.Entry<ParseError, String> map : errors.entrySet()) {
            Object[] objectArray = new Object[2];
            objectArray[0] = map.getKey().name().replaceAll(UIItem.l("\u0015"), UIItem.l("j"));
            objectArray[1] = map.getValue();
            Logger.info(String.format(UIItem.l("\u0005g\u0005oVf\u0005:D>Mj^oV7"), objectArray));
        }
        Logger.info(UIItem.l("j"));
    }

    public static LinkedHashMap<CategoryType, LinkedHashMap<String, AccessoryItem>> initModels() {
        LinkedHashMap<CategoryType, LinkedHashMap<String, AccessoryItem>> modelsArray = new LinkedHashMap<CategoryType, LinkedHashMap<String, AccessoryItem>>();
        for (CategoryType type : CategoryType.values()) {
            modelsArray.put(type, new LinkedHashMap());
        }
        Logger.info(UIItem.l("\u0006J+A#K-\u0005'J.@&Vd\u000bd"));
        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse((Reader)new InputStreamReader(AccessoriesCore.class.getClassLoader().getResourceAsStream(fileIndexes)));
            for (Map.Entry map : element.getAsJsonObject().entrySet()) {
                String modelPath;
                if (((String)map.getKey()).equalsIgnoreCase(UIItem.l("z)J'H/K>"))) continue;
                JsonObject item = ((JsonElement)map.getValue()).getAsJsonObject();
                HashMap<ParseError, String> errors = new HashMap<ParseError, String>();
                AccessoryItem accessoryItem = new AccessoryItem((String)map.getKey(), CategoryType.valueOf(item.get(UIItem.l("Q3U/")).getAsString()));
                accessoryItem.setName(item.get(UIItem.l("K+H/")).getAsString());
                if (item.has(UIItem.l("D?Q\"J8"))) {
                    accessoryItem.setAuthor(item.get(UIItem.l("D?Q\"J8")).getAsString());
                }
                if (item.has(UIItem.l("$@="))) {
                    accessoryItem.setNews(item.get(UIItem.l("$@=")).getAsBoolean());
                }
                if (item.has(UIItem.l("L9r#K-"))) {
                    accessoryItem.setWing(item.get(UIItem.l("L9r#K-")).getAsBoolean());
                }
                if (!ModelLoader.fileExist(modelPath = String.format(UIItem.l("'J.@&Ve\u0000{\u00019\no\u0017nVe\u0000x\u00019\u000b%G "), accessoryItem.getType().name().toLowerCase(), item.get(UIItem.l("'J.@&")).getAsString()))) {
                    errors.put(ParseError.NOT_FOUND_MODEL, modelPath);
                } else {
                    accessoryItem.setModel(AdvancedModelLoader.loadModel((ResourceLocation)new ResourceLocation(UIItem.l("D)F/V9J8L/Vp") + modelPath)));
                }
                if (item.has(UIItem.l(")P9Q%H\u001e@2Q?W/"))) {
                    String customTexturePath = String.format(UIItem.l("H%A/I9\noVe\u00009\u000b:K-"), accessoryItem.getType().name().toLowerCase(), item.get(UIItem.l(")P9Q%H\u001e@2Q?W/")).getAsString());
                    accessoryItem.setTexture(new ResourceLocation(UIItem.l("D)F/V9J8L/Vp") + customTexturePath));
                    if (!ModelLoader.fileExist(customTexturePath)) {
                        errors.put(ParseError.NOT_FOUND_TEXTURE, customTexturePath);
                    }
                } else {
                    String localTexturePath = String.format(UIItem.l("'J.@&Ve\u0000{\u00019\no\u0017nVe\u0000x\u00019\u000b:K-"), accessoryItem.getType().name().toLowerCase(), accessoryItem.getINDEX());
                    accessoryItem.setTexture(new ResourceLocation(UIItem.l("D)F/V9J8L/Vp") + localTexturePath));
                    if (!ModelLoader.fileExist(localTexturePath)) {
                        errors.put(ParseError.NOT_FOUND_TEXTURE, localTexturePath);
                    }
                }
                InputStream inputStream = AccessoriesCore.class.getClassLoader().getResourceAsStream(String.format(UIItem.l("+V9@>VeD)F/V9J8L/VeH%A/I9\noVe\u0000x\u00019\no\u0017nVdO9J$"), accessoryItem.getType().name().toLowerCase(), item.get(UIItem.l("'J.@&")).getAsString()));
                if (inputStream != null) {
                    JsonObject jsonObject = jsonParser.parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject();
                    ItemTransformVec3f itemTransformVec3f = ItemTransformVec3f.DEFAULT;
                    if (jsonObject.getAsJsonObject().has(UIItem.l(".L9U&D3"))) {
                        JsonArray jsonArray;
                        JsonObject prefs = jsonObject.getAsJsonObject(UIItem.l(".L9U&D3"));
                        ItemTransformVec3f.Deserializer deserializer = new ItemTransformVec3f.Deserializer();
                        itemTransformVec3f = deserializer.deserialize(prefs.get(UIItem.l("M/D.")), null, null);
                        if (prefs.has(UIItem.l("M/D."))) {
                            JsonObject headJsonObject = prefs.getAsJsonObject(UIItem.l("M/D."));
                            for (Map.Entry headJson : headJsonObject.entrySet()) {
                                if (((String)headJson.getKey()).equalsIgnoreCase(UIItem.l("W%Q+Q#J$"))) {
                                    jsonArray = ((JsonElement)headJson.getValue()).getAsJsonArray();
                                    accessoryItem.getPreferencesRenderer().setRotation(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                                }
                                if (((String)headJson.getKey()).equalsIgnoreCase(UIItem.l(">W+K9I+Q#J$"))) {
                                    jsonArray = ((JsonElement)headJson.getValue()).getAsJsonArray();
                                    accessoryItem.getPreferencesRenderer().setTranslation(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                                }
                                if (!((String)headJson.getKey()).equalsIgnoreCase(UIItem.l("9F+I/"))) continue;
                                jsonArray = ((JsonElement)headJson.getValue()).getAsJsonArray();
                                accessoryItem.getPreferencesRenderer().setScale(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                            }
                        }
                        if (prefs.has(UIItem.l("-P#"))) {
                            JsonObject guiJsonObject = prefs.getAsJsonObject(UIItem.l("-P#"));
                            for (Map.Entry guiJson : guiJsonObject.entrySet()) {
                                if (((String)guiJson.getKey()).equalsIgnoreCase(UIItem.l("W%Q+Q#J$"))) {
                                    jsonArray = ((JsonElement)guiJson.getValue()).getAsJsonArray();
                                    accessoryItem.getPreferencesGui().setRotation(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                                }
                                if (((String)guiJson.getKey()).equalsIgnoreCase(UIItem.l(">W+K9I+Q#J$"))) {
                                    jsonArray = ((JsonElement)guiJson.getValue()).getAsJsonArray();
                                    accessoryItem.getPreferencesGui().setTranslation(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                                }
                                if (!((String)guiJson.getKey()).equalsIgnoreCase(UIItem.l("9F+I/"))) continue;
                                jsonArray = ((JsonElement)guiJson.getValue()).getAsJsonArray();
                                accessoryItem.getPreferencesGui().setScale(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
                            }
                        }
                    }
                    accessoryItem.headTransform = itemTransformVec3f;
                } else {
                    errors.put(ParseError.NOT_FOUND_JSON, String.format(UIItem.l("H%A/I9\noVe\u0000x\u00019\no\u0017nVdO9J$"), accessoryItem.getType().name().toLowerCase(), item.get(UIItem.l("'J.@&")).getAsString()));
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
        Logger.info(String.format(UIItem.l("m+VjG/@$\u0005&J+A/Aj\u0000.\u0005'J.@&Vd"), countItems));
        return modelsArray;
    }

    public static boolean fileExist(String path) {
        InputStream inputStream = AccessoriesCore.class.getClassLoader().getResourceAsStream(UIItem.l("+V9@>VeD)F/V9J8L/Ve") + path);
        return inputStream != null;
    }

    public static enum ParseError {
        NOT_FOUND_MODEL,
        NOT_FOUND_TEXTURE,
        NOT_FOUND_JSON;

    }
}

