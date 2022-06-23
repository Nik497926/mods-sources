/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.radio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.client.gui.toast.SimpleToast;
import ru.simplemc.simplecore.mod.client.radio.RadioPlayerController;

public class RadioPlayerRunnable
implements Runnable {
    private volatile AdvancedPlayer advancedPlayer;

    @Override
    public void run() {
        InputStream inputStream;
        URLConnection connection;
        this.advancedPlayer = null;
        SimpleToast.addOrUpdate(SimpleToast.Type.RADIO, SimpleToast.ToastType.WARNING, (IChatComponent)new ChatComponentTranslation("gui.toast.radio.title", new Object[0]), (IChatComponent)new ChatComponentTranslation("gui.toast.radio.connecting", new Object[0]), 5000);
        try {
            connection = new URL("http://radio.simpleminecraft.ru:8000/live").openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
        }
        catch (IOException e) {
            SimpleCore.LOGGER.error("\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0438\u0442\u0441\u044f \u043a \u0440\u0430\u0434\u0438\u043e \u0441\u0435\u0440\u0432\u0435\u0440\u0443: {?}", (Throwable)e);
            RadioPlayerController.onRadioPlayerInitializeError();
            return;
        }
        try {
            inputStream = connection.getInputStream();
        }
        catch (IOException e) {
            SimpleCore.LOGGER.error("\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0438\u0442\u0441\u044f \u043a \u0440\u0430\u0434\u0438\u043e \u0441\u0435\u0440\u0432\u0435\u0440\u0443: {?}", (Throwable)e);
            RadioPlayerController.onRadioPlayerConnectionError();
            return;
        }
        try {
            this.advancedPlayer = new AdvancedPlayer(inputStream);
            RadioPlayerController.onRadioPlayerEnabled(this.advancedPlayer);
            this.advancedPlayer.play();
        }
        catch (JavaLayerException e) {
            SimpleCore.LOGGER.error("\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0438\u0442\u0441\u044f \u043a \u0440\u0430\u0434\u0438\u043e \u0441\u0435\u0440\u0432\u0435\u0440\u0443: {?}", (Throwable)e);
            RadioPlayerController.onRadioPlayerConnectionError();
        }
    }

    public Optional<AdvancedPlayer> getAdvancedPlayer() {
        return Optional.ofNullable(this.advancedPlayer);
    }
}

