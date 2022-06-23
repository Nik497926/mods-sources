/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.keybindig;

import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import ru.simplemc.simplecore.mod.client.gui.toast.SimpleToast;
import ru.simplemc.simplecore.mod.client.radio.RadioPlayerController;
import ru.simplemc.simplecore.mod.handler.keybindig.KeyBindingBase;

public class KeyBindingRadioToggle
extends KeyBindingBase {
    public KeyBindingRadioToggle() {
        super("key.simplecore.radio.toggle", 36);
    }

    @Override
    protected boolean isActive() {
        return this.getIsKeyPressed();
    }

    @Override
    protected void action() {
        if (RadioPlayerController.isPlayerActive()) {
            RadioPlayerController.stopRadio(true);
        } else {
            try {
                RadioPlayerController.startRadio();
            }
            catch (IOException | JavaLayerException e) {
                SimpleToast.addOrUpdate(SimpleToast.Type.RADIO, SimpleToast.ToastType.ERROR, (IChatComponent)new ChatComponentTranslation("gui.toast.radio.title", new Object[0]), (IChatComponent)new ChatComponentTranslation("gui.toast.radio.initialize_error", new Object[0]));
            }
        }
    }
}

