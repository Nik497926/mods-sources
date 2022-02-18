package ru.obvilion.tablist.core;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import ru.obvilion.tablist.events.GuiEvents;

public class HeadThread implements Runnable {
    private String name;

    public HeadThread(String name) {
        this.name = name;
    }

    public void run() {
        this.updateResource(Core.url.replace("%s", this.name));
    }

    private synchronized void updateResource(String urls) {
        try {
            URL url = new URL(urls);
            if (((HttpURLConnection) url.openConnection()).getResponseCode() == 404) {
                return;
            }

            BufferedImage bufferedimage = ImageIO.read(url);
            GuiEvents.icons.put(this.name, bufferedimage);
            GuiEvents.done.put(this.name, true);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }
}
