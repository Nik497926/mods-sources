package ru.obvilion.accessoires.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.obvilion.accessoires.utils.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class AccessoryItem {
    public static ArrayList<AccessoryItem> CASHED_INSTANCES = new ArrayList<>();

    public String  name;
    public String  author = "Obvilion.ru";
    public int     price  = 10;
    public boolean has    = false;

    public AccessoryItem(String name) {
        this.name = name;
    }

    public static AccessoryItem get(String modelId) {
        for (AccessoryItem item : CASHED_INSTANCES) {
            if (item.name.equals(modelId)) return item;
        }

        return null;
    }

    public static AccessoryItem copy(AccessoryItem item) {
        AccessoryItem result = new AccessoryItem(item.name);
        result.has = item.has;
        result.author = item.author;
        result.price = item.price;

        return result;
    }

    public static void load() {
        Thread thread = new Thread(() -> {
            CASHED_INSTANCES.clear();

            try {
                URL url = new URL("https://obvilion.ru/api/game/accessories");
                URLConnection request = url.openConnection();
                request.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonArray json = root.getAsJsonArray();

                for (JsonElement o : json) {
                    JsonObject object = o.getAsJsonObject();

                    AccessoryItem item = new AccessoryItem(object.get("modelId").getAsString());
                    if (object.has("author") && !object.get("author").isJsonNull()) {
                        item.author = object.get("author").getAsString();
                    }
                    if (object.has("cost") && !object.get("cost").isJsonNull()) {
                        item.price = object.get("cost").getAsInt();
                    }

                    CASHED_INSTANCES.add(item);
                }
            } catch (Exception e) {
                Logger.info("Unable to get Accessories list!");
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        }, "Accessories loader");

        thread.setDaemon(true);
        thread.start();
    }
}
