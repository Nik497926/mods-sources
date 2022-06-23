/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.handler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.settings.KeyBinding;
import ru.simplemc.simplecore.mod.handler.keybindig.KeyBindingBase;
import ru.simplemc.simplecore.mod.handler.keybindig.KeyBindingRadioToggle;
import ru.simplemc.simplecore.mod.handler.keybindig.KeyBindingRadioVolumeAdd;
import ru.simplemc.simplecore.mod.handler.keybindig.KeyBindingRadioVolumeDecr;

@SideOnly(value=Side.CLIENT)
public class KeyHandler {
    private final List<KeyBindingBase> keyBindings = new ArrayList<KeyBindingBase>();

    public KeyHandler() {
        this.registerKeyBinding(new KeyBindingRadioToggle());
        this.registerKeyBinding(new KeyBindingRadioVolumeAdd());
        this.registerKeyBinding(new KeyBindingRadioVolumeDecr());
    }

    private void registerKeyBinding(KeyBindingBase keyBindingBase) {
        this.keyBindings.add(keyBindingBase);
        ClientRegistry.registerKeyBinding((KeyBinding)keyBindingBase);
    }

    @SubscribeEvent
    public void onInGameKeyInputEvent(InputEvent.KeyInputEvent event) {
        this.keyBindings.forEach(KeyBindingBase::run);
    }
}

