/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui.toast;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import ru.simplemc.simplecore.mod.client.gui.toast.GuiToast;

@SideOnly(value=Side.CLIENT)
public class ToastManager {
    private static final GuiToast guiToast = new GuiToast(Minecraft.getMinecraft());

    @SubscribeEvent
    public void onRenderTickEvent(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            guiToast.drawToast();
        }
    }

    public static GuiToast getGuiToast() {
        return guiToast;
    }
}

