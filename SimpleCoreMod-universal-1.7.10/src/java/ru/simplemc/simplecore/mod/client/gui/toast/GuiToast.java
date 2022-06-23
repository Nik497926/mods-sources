/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui.toast;

import com.google.common.collect.Queues;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.Deque;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import ru.simplemc.simplecore.mod.client.gui.toast.IToast;

@SideOnly(value=Side.CLIENT)
public class GuiToast
extends Gui {
    private final Minecraft mc;
    private final ToastInstance<?>[] visible = new ToastInstance[5];
    private final Deque<IToast> toastsQueue = Queues.newArrayDeque();

    public GuiToast(Minecraft mcIn) {
        this.mc = mcIn;
    }

    public void drawToast() {
        if (!this.mc.gameSettings.hideGUI) {
            ScaledResolution resolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
            RenderHelper.disableStandardItemLighting();
            for (int i = 0; i < this.visible.length; ++i) {
                ToastInstance<?> toastInstance = this.visible[i];
                if (toastInstance != null && toastInstance.render(resolution.getScaledWidth(), i)) {
                    this.visible[i] = null;
                }
                if (this.visible[i] != null || this.toastsQueue.isEmpty()) continue;
                this.visible[i] = new ToastInstance(this, this.toastsQueue.removeFirst());
            }
        }
    }

    @Nullable
    public <T extends IToast> T getToast(Class<? extends T> toastClass, Object toastObject) {
        for (ToastInstance<?> toastInstance : this.visible) {
            if (toastInstance == null || !toastClass.isAssignableFrom(toastInstance.getToast().getClass()) || !toastInstance.getToast().getType().equals(toastObject)) continue;
            return (T)toastInstance.getToast();
        }
        for (IToast toast : this.toastsQueue) {
            if (!toastClass.isAssignableFrom(toast.getClass()) || !toast.getType().equals(toastObject)) continue;
            return (T)toast;
        }
        return null;
    }

    public void clear() {
        Arrays.fill(this.visible, null);
        this.toastsQueue.clear();
    }

    public void add(IToast toastIn) {
        this.toastsQueue.add(toastIn);
    }

    public Minecraft getMinecraft() {
        return this.mc;
    }

    @SideOnly(value=Side.CLIENT)
    static class ToastInstance<T extends IToast> {
        private final T toast;
        private long animationTime = -1L;
        private long visibleTime = -1L;
        private IToast.Visibility visibility = IToast.Visibility.SHOW;
        final /* synthetic */ GuiToast this$0;

        private ToastInstance(GuiToast this$0, T toastIn) {
            this.this$0 = this$0;
            this.toast = toastIn;
        }

        public T getToast() {
            return this.toast;
        }

        private float getVisibility(long flag) {
            float alpha = MathHelper.clamp_float((float)((float)(flag - this.animationTime) / 600.0f), (float)0.0f, (float)1.0f);
            alpha *= alpha;
            return this.visibility == IToast.Visibility.HIDE ? 1.0f - alpha : alpha;
        }

        public boolean render(int posX, int posY) {
            long systemTime = Minecraft.getSystemTime();
            if (this.animationTime == -1L) {
                this.animationTime = systemTime;
            }
            if (this.visibility == IToast.Visibility.SHOW && systemTime - this.animationTime <= 600L) {
                this.visibleTime = systemTime;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)posX - 160.0f * this.getVisibility(systemTime)), (float)(posY * 32), (float)(500 + posY));
            IToast.Visibility toast$visibility = this.toast.draw(this.this$0, systemTime - this.visibleTime);
            GL11.glPopMatrix();
            if (toast$visibility != this.visibility) {
                this.animationTime = systemTime - (long)((int)((1.0f - this.getVisibility(systemTime)) * 600.0f));
                this.visibility = toast$visibility;
            }
            return this.visibility == IToast.Visibility.HIDE && systemTime - this.animationTime > 600L;
        }
    }
}

