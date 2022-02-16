package net.divinerpg.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class ArcanaRenderer {
    private Minecraft mc = Minecraft.getMinecraft();
    public static int time;
    public static int level;
    public static float value;
    public static boolean regen;
    private int currTime;
    private float alpha1 = 0.0f;
    private float alpha2 = 0.0f;

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent event) {
        if (mc.currentScreen == null)
        this.renderArcana();
        //if (time > 0) {
            //this.textDrawRender();
            //this.onTickRender();
        //}
    }

    public void drawArcanaCircle(double xC, double yC) {
        GuiIngame gig = this.mc.ingameGUI;
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)0.2f, (float)0.2f, (float)0.2f, (float)1.0f);
        Tessellator t = Tessellator.instance;
        float currArcana = value;
        float arcana = 200.0f;
        GL11.glColor4f((float)0.2314f, (float)0.2824f, (float)0.9098f, (float)1.0f);
        t.startDrawing(6);
        GL11.glDisable((int)2884);
        t.addVertex(xC, yC, 0.0);
        int i = 0;
        while ((float)i <= currArcana) {
            double angle = 360.0f - (float)i * (360.0f / arcana);
            double x = 21.0 * Math.sin(Math.toRadians(angle)) + xC;
            double y = 21.0 * Math.cos(Math.toRadians(angle)) + yC;
            t.addVertex(x, y, 0.0);
            ++i;
        }
        t.draw();
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2884);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/gui/hcsland_arcana.png"));
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)0.0, (double)499.0);
        gig.drawTexturedModalRect((int)xC - 21, (int)yC - 21, 0, 0, 42, 42);
        GL11.glPopMatrix();
        this.renderAncient((float)xC, (float)yC, gig);
    }

    private void renderAncient(float x, float y, GuiIngame gig) {
        GL11.glPushMatrix();
        float time1 = System.currentTimeMillis() / 30L % 360L;
        this.mc.getTextureManager().bindTexture(new ResourceLocation("divinerpg:textures/gui/hcsland_arcana.png"));
        GL11.glTranslatef((float)x, (float)y, (float)500.0f);
        if (value > 10.0f) {
            if (System.currentTimeMillis() % 2L == 0L && this.alpha1 < 1.0f) {
                this.alpha1 += 0.03f;
            }
        } else if (System.currentTimeMillis() % 2L == 0L && this.alpha1 > 0.0f) {
            this.alpha1 -= 0.03f;
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)this.alpha1);
        if (this.mc.thePlayer.dimension == ConfigurationHelper.arcana) {
            GL11.glColor4f((float)0.4745f, (float)0.7882f, (float)1.0f, (float)this.alpha1);
            time1 = System.currentTimeMillis() / 10L % 360L;
            GL11.glRotatef((float)time1, (float)0.0f, (float)0.0f, (float)-1.0f);
        } else {
            GL11.glRotatef((float)time1, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        gig.drawTexturedModalRect(-14, -14, 0, 43, 28, 28);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        float time2 = System.currentTimeMillis() / 30L % 360L;
        GL11.glTranslatef((float)x, (float)y, (float)501.0f);
        if (value > 100.0f) {
            if (System.currentTimeMillis() % 2L == 0L && this.alpha2 < 1.0f) {
                this.alpha2 += 0.03f;
            }
        } else if (System.currentTimeMillis() % 2L == 0L && this.alpha2 > 0.0f) {
            this.alpha2 -= 0.03f;
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)this.alpha2);
        if (this.mc.thePlayer.dimension == ConfigurationHelper.arcana) {
            GL11.glColor4f((float)0.4745f, (float)0.7882f, (float)1.0f, (float)this.alpha2);
            time2 = System.currentTimeMillis() / 10L % 360L;
            GL11.glRotatef((float)time2, (float)0.0f, (float)0.0f, (float)1.0f);
        } else {
            GL11.glRotatef((float)time2, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        gig.drawTexturedModalRect(-11, -11, 0, 71, 22, 22);
        GL11.glPopMatrix();
    }

    private void renderArcana() {
        ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int i = scaledresolution.getScaledWidth();
        int k = scaledresolution.getScaledHeight();
        int y = k - 28;
        int x = i - 28;
        this.drawArcanaCircle(x, y);
    }

    private void onTickRender() {
        GuiIngame gig = this.mc.ingameGUI;
        ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        if (this.currTime == 0 || time > this.currTime) {
            this.currTime = time;
        }
        double percent = (double)time / (double)this.currTime * 100.0;
        int i = scaledresolution.getScaledWidth();
        int k = scaledresolution.getScaledHeight();
        int y = k - 18 - 70;
        int x = i - 111;
        this.mc.getTextureManager().bindTexture(new ResourceLocation("divinerpg", "textures/gui/arcanaBar.png"));
        gig.drawTexturedModalRect(x + 80, y - 25, 100, 18, 20, 22);
        gig.drawTexturedModalRect(x, y, 0, 23, 100, 5);
        gig.drawTexturedModalRect(x, y, 0, 18, (int)percent, 5);
        GL11.glPushMatrix();
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        gig.drawTexturedModalRect(x * 2, (y - 14) * 2, 124, 18, 20, 23);
        GL11.glPopMatrix();
    }

    private void textDrawRender() {
        if (this.mc.currentScreen == null) {
            int color = 0xFFFFFF;
            ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
            int i = scaledresolution.getScaledWidth();
            int k = scaledresolution.getScaledHeight();
            int y = k - 18 - 70;
            int x = i - 111;
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            this.mc.fontRenderer.drawStringWithShadow(this.getTime(), x + 12, y - 11, color);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
            if (level < 10) {
                this.mc.fontRenderer.drawString(String.valueOf(level), (x + 89) * 2, (y - 19) * 2, 14961920);
            } else {
                this.mc.fontRenderer.drawString(String.valueOf(level), (x + 87) * 2, (y - 19) * 2, 0xBA0000);
            }
            GL11.glPopMatrix();
        }
    }

    private String getTime() {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public ResourceLocation set(String name) {
        return new ResourceLocation("divinerpg:textures/gui/bossBar/" + name + ".png");
    }
}

