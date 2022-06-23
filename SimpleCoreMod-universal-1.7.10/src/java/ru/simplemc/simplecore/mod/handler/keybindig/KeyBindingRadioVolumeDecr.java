/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.keybindig;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import ru.simplemc.simplecore.mod.client.gui.toast.SimpleToast;
import ru.simplemc.simplecore.mod.client.radio.RadioPlayerController;
import ru.simplemc.simplecore.mod.handler.keybindig.KeyBindingBase;

public class KeyBindingRadioVolumeDecr
extends KeyBindingBase {
    public KeyBindingRadioVolumeDecr() {
        super("key.simplecore.radio.volume_decr", 12);
    }

    @Override
    protected boolean isActive() {
        return this.getIsKeyPressed() && RadioPlayerController.isPlayerActive();
    }

    @Override
    protected void action() {
        if (RadioPlayerController.decrVolume()) {
            SimpleToast.addOrUpdate(SimpleToast.Type.RADIO, SimpleToast.ToastType.SUCCESS, (IChatComponent)new ChatComponentTranslation("gui.toast.radio.title", new Object[0]), (IChatComponent)new ChatComponentTranslation("gui.toast.radio.volume_is", new Object[]{RadioPlayerController.getVolume() + "%"}));
        } else {
            SimpleToast.addOrUpdate(SimpleToast.Type.RADIO, SimpleToast.ToastType.WARNING, (IChatComponent)new ChatComponentTranslation("gui.toast.radio.title", new Object[0]), (IChatComponent)new ChatComponentTranslation("gui.toast.radio.volume_is_null", new Object[]{RadioPlayerController.getVolume() + "%"}));
        }
    }
}

