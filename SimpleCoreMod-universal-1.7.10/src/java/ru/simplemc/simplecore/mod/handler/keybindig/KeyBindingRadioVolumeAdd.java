/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler.keybindig;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import ru.simplemc.simplecore.mod.client.gui.toast.SimpleToast;
import ru.simplemc.simplecore.mod.client.radio.RadioPlayerController;
import ru.simplemc.simplecore.mod.handler.keybindig.KeyBindingBase;

public class KeyBindingRadioVolumeAdd
extends KeyBindingBase {
    public KeyBindingRadioVolumeAdd() {
        super("key.simplecore.radio.volume_add", 13);
    }

    @Override
    protected boolean isActive() {
        return this.getIsKeyPressed() && RadioPlayerController.isPlayerActive();
    }

    @Override
    protected void action() {
        if (RadioPlayerController.addVolume()) {
            SimpleToast.addOrUpdate(SimpleToast.Type.RADIO, SimpleToast.ToastType.SUCCESS, (IChatComponent)new ChatComponentTranslation("gui.toast.radio.title", new Object[0]), (IChatComponent)new ChatComponentTranslation("gui.toast.radio.volume_is", new Object[]{RadioPlayerController.getVolume() + "%"}));
        } else {
            SimpleToast.addOrUpdate(SimpleToast.Type.RADIO, SimpleToast.ToastType.WARNING, (IChatComponent)new ChatComponentTranslation("gui.toast.radio.title", new Object[0]), (IChatComponent)new ChatComponentTranslation("gui.toast.radio.volume_is_full", new Object[]{RadioPlayerController.getVolume() + "%"}));
        }
    }
}

