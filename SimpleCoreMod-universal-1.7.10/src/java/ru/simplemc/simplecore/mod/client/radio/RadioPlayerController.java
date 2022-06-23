/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.radio;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import ru.simplemc.simplecore.mod.client.gui.toast.SimpleToast;

@SideOnly(value=Side.CLIENT)
public class RadioPlayerController {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor(runnable -> new Thread(runnable, "Radio - Thread"));
    private static RadioPlayerRunnable radioPlayerRunnable;
    private static Future<?> scheduledFuture;
    private static volatile float currentVolume;

    public static void onRadioPlayerEnabled(AdvancedPlayer advancedPlayer) {
        advancedPlayer.setVolume(currentVolume);
        SimpleToast.addOrUpdate(SimpleToast.Type.RADIO, SimpleToast.ToastType.SUCCESS, (IChatComponent)new ChatComponentTranslation("gui.toast.radio.title", new Object[0]), (IChatComponent)new ChatComponentTranslation("gui.toast.radio.enabled", new Object[0]));
    }

    public static void onRadioPlayerInitializeError() {
        SimpleToast.addOrUpdate(SimpleToast.Type.RADIO, SimpleToast.ToastType.ERROR, (IChatComponent)new ChatComponentTranslation("gui.toast.radio.title", new Object[0]), (IChatComponent)new ChatComponentTranslation("gui.toast.radio.connection_error", new Object[0]), 2000);
        RadioPlayerController.stopRadio(false);
    }

    public static void onRadioPlayerConnectionError() {
        SimpleToast.addOrUpdate(SimpleToast.Type.RADIO, SimpleToast.ToastType.ERROR, (IChatComponent)new ChatComponentTranslation("gui.toast.radio.title", new Object[0]), (IChatComponent)new ChatComponentTranslation("gui.toast.radio.connection_error", new Object[0]), 2000);
        RadioPlayerController.stopRadio(false);
    }

    @SubscribeEvent
    public void onPlayerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        RadioPlayerController.stopRadio(true);
    }

    public static void startRadio() throws IOException, JavaLayerException {
        if (scheduledFuture == null) {
            radioPlayerRunnable = new RadioPlayerRunnable();
            scheduledFuture = EXECUTOR_SERVICE.submit(radioPlayerRunnable);
        }
    }

    public static void stopRadio(boolean withNotify) {
        if (scheduledFuture != null) {
            if (!withNotify) {
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException interruptedException) {}
            } else {
                SimpleToast.addOrUpdate(SimpleToast.Type.RADIO, SimpleToast.ToastType.SUCCESS, (IChatComponent)new ChatComponentTranslation("gui.toast.radio.title", new Object[0]), (IChatComponent)new ChatComponentTranslation("gui.toast.radio.disabled", new Object[0]));
            }
            radioPlayerRunnable.getAdvancedPlayer().ifPresent(AdvancedPlayer::close);
            scheduledFuture.cancel(true);
            scheduledFuture = null;
        }
    }

    public static boolean addVolume() {
        float nextValue = currentVolume + 0.01f;
        if (nextValue <= 1.0f) {
            currentVolume = nextValue;
            RadioPlayerController.handleVolumeToPlayer();
            return true;
        }
        return false;
    }

    public static boolean decrVolume() {
        float nextValue = currentVolume - 0.01f;
        if (nextValue >= 0.0f) {
            currentVolume = nextValue;
            RadioPlayerController.handleVolumeToPlayer();
            return true;
        }
        return false;
    }

    public static void handleVolumeToPlayer() {
        radioPlayerRunnable.getAdvancedPlayer().ifPresent(advancedPlayer -> advancedPlayer.setVolume(currentVolume));
    }

    public static int getVolume() {
        return (int)(currentVolume * 100.0f);
    }

    public static boolean isPlayerActive() {
        return scheduledFuture != null && radioPlayerRunnable.getAdvancedPlayer().isPresent();
    }

    static {
        currentVolume = 0.05f;
    }
}

