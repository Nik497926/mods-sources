/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui.toast;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.simplemc.simplecore.mod.client.gui.toast.GuiToast;

@SideOnly(value=Side.CLIENT)
public interface IToast {
    public static final Object NO_TOKEN = new Object();

    public Visibility draw(GuiToast var1, long var2);

    default public Object getType() {
        return NO_TOKEN;
    }

    @SideOnly(value=Side.CLIENT)
    public static enum Visibility {
        SHOW,
        HIDE;

    }
}

