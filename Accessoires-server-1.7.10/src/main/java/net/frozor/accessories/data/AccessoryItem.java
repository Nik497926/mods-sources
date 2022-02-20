package net.frozor.accessories.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.frozor.accessories.utils.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class AccessoryItem {
    public static ArrayList<AccessoryItem> ALL_INSTANCES = new ArrayList<>();

    public String name;
    public String author = "Obvilion.ru";
    public int    price  = 10;

    public AccessoryItem(String name) {
        this.name = name;

        ALL_INSTANCES.add(this);
    }

    public static void load() {
        Thread thread = new Thread(() -> {
            ALL_INSTANCES.clear();

            try {
                URL url = new URL("https://obvilion.ru/api/files/temp/acc_data.json");
                URLConnection request = url.openConnection();
                request.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonArray json = root.getAsJsonArray();

                for (JsonElement o : json) {
                    JsonObject object = o.getAsJsonObject();

                    AccessoryItem item = new AccessoryItem(object.get("model").getAsString());
                    if (object.has("author")) {
                        item.author = object.get("author").getAsString();
                    }
                    if (object.has("price")) {
                        item.price = object.get("price").getAsInt();
                    }
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
