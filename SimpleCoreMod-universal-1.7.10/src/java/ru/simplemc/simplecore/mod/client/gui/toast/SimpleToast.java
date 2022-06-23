/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui.toast;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.simplemc.simplecore.mod.client.gui.toast.GuiToast;
import ru.simplemc.simplecore.mod.client.gui.toast.IToast;
import ru.simplemc.simplecore.mod.client.gui.toast.ToastManager;

@SideOnly(value=Side.CLIENT)
public class SimpleToast
implements IToast {
    private final ResourceLocation TEXTURE_TOASTS = new ResourceLocation("simplecore", "textures/gui/toasts.png");
    private final Type type;
    private final long timeoutDelay;
    private String title;
    private String subtitle;
    private ToastType toastType;
    private long firstDrawTime;
    private boolean newDisplay;

    private SimpleToast(Type type, ToastType toastType, IChatComponent title, @Nullable IChatComponent subTitle) {
        this(type, toastType, title, subTitle, 5000);
    }

    private SimpleToast(Type type, ToastType toastType, IChatComponent title, @Nullable IChatComponent subTitle, int timeoutDelay) {
        this.type = type;
        this.toastType = toastType;
        this.title = title.getUnformattedText();
        this.subtitle = subTitle == null ? null : subTitle.getUnformattedText();
        this.timeoutDelay = timeoutDelay;
    }

    public void setDisplayedText(ToastType toastType, IChatComponent title, @Nullable IChatComponent subTitle) {
        this.toastType = toastType;
        this.title = title.getUnformattedText();
        this.subtitle = subTitle == null ? null : subTitle.getUnformattedText();
        this.newDisplay = true;
    }

    @Override
    public IToast.Visibility draw(GuiToast toastGui, long delta) {
        if (this.newDisplay) {
            this.firstDrawTime = delta;
            this.newDisplay = false;
        }
        toastGui.getMinecraft().getTextureManager().bindTexture(this.TEXTURE_TOASTS);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        toastGui.drawTexturedModalRect(0, 0, 0, this.toastType.texturePosY, 180, 32);
        toastGui.drawTexturedModalRect(6, 6, this.type.iconPosX, this.type.iconPosY, 20, 20);
        int titleColor = -256;
        int subTitleColor = 0;
        if (this.toastType == ToastType.SUCCESS) {
            titleColor = this.type.titleColor;
            subTitleColor = this.type.subTitleColor;
        }
        if (this.subtitle == null) {
            toastGui.getMinecraft().fontRenderer.drawString(this.title, 30, 12, titleColor);
        } else {
            toastGui.getMinecraft().fontRenderer.drawString(this.title, 30, 7, titleColor);
            toastGui.getMinecraft().fontRenderer.drawString(this.subtitle, 30, 18, subTitleColor);
        }
        return delta - this.firstDrawTime < this.timeoutDelay ? IToast.Visibility.SHOW : IToast.Visibility.HIDE;
    }

    @Nonnull
    public Type getType() {
        return this.type;
    }

    public static void addOrUpdate(Type type, ToastType toastType, IChatComponent title, @Nullable IChatComponent subTitle, int timeoutDelay) {
        GuiToast guiToast = ToastManager.getGuiToast();
        SimpleToast simpleToast = guiToast.getToast(SimpleToast.class, (Object)type);
        if (simpleToast == null) {
            guiToast.add(new SimpleToast(type, toastType, title, subTitle, timeoutDelay));
        } else {
            simpleToast.setDisplayedText(toastType, title, subTitle);
        }
    }

    public static void addOrUpdate(Type type, ToastType toastType, IChatComponent title, @Nullable IChatComponent subTitle) {
        SimpleToast.addOrUpdate(type, toastType, title, subTitle, 2000);
    }

    @SideOnly(value=Side.CLIENT)
    public static enum ToastType {
        ERROR(0),
        WARNING(32),
        SUCCESS(64);

        private final int texturePosY;

        private ToastType(int texturePosY) {
            this.texturePosY = texturePosY;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static enum Type {
        RADIO(0x9000FF, 0x343434, 176, 0),
        INFO(0x9000FF, 0x343434, 196, 0);

        private final int titleColor;
        private final int subTitleColor;
        private final int iconPosX;
        private final int iconPosY;

        private Type(int titleColor, int subTitleColor, int iconPosX, int iconPosY) {
            this.titleColor = titleColor;
            this.subTitleColor = subTitleColor;
            this.iconPosX = iconPosX;
            this.iconPosY = iconPosY;
        }
    }
}

