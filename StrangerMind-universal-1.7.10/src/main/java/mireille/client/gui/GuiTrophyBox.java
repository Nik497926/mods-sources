package mireille.client.gui;

import cpw.mods.fml.relauncher.*;
import net.minecraft.item.*;
import mireille.common.items.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import java.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.gui.*;

@SideOnly(Side.CLIENT)
public class GuiTrophyBox extends GuiScreen
{
    private int x_mid;
    private int y_mid;
    private int oldTime;
    private int ticks;
    private float temp_scale;
    public static ItemStack stack;
    public static int index;
    private static ItemCase Case;
    private static String rarityText;
    private static int rarityTextColor;
    private static int[] renderColor;
    
    public GuiTrophyBox() {
        this.oldTime = 0;
        this.ticks = 0;
        this.temp_scale = 0.1f;
    }
    
    public void initGui() {
        this.x_mid = super.width / 2;
        this.y_mid = super.height / 2;
        GuiTrophyBox.Case = (ItemCase)GuiTrophyBox.stack.getItem();
        final int i = (GuiTrophyBox.index != -1) ? GuiTrophyBox.index : 0;
        GuiTrophyBox.stack = GuiTrophyBox.Case.getCurrentItem(i).getItemStack();
        GuiTrophyBox.rarityText = GuiTrophyBox.Case.getCurrentItem(i).getText();
        GuiTrophyBox.rarityTextColor = GuiTrophyBox.Case.getCurrentItem(i).getTextColor();
        GuiTrophyBox.renderColor = GuiTrophyBox.Case.getCurrentItem(i).getRenderColor();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        GuiImage.bindTexture("mireille:textures/gui/gui_trophy.png");
        GuiImage.drawImage(this.x_mid - 128, this.y_mid - 128, 0, 0, 256, 256, 1.0f);
        final Tessellator tessellator = Tessellator.instance;
        final float f1 = (this.ticks + partialTicks) / 300.0f;
        final int time = (int)(Minecraft.getMinecraft().theWorld.getWorldTime() % 20L);
        if (this.oldTime != time) {
            this.oldTime = time;
            ++this.ticks;
        }
        final Random random = new Random(123141L);
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glShadeModel(7425);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glDisable(3008);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        GL11.glTranslatef((float)this.x_mid, (float)this.y_mid, 0.0f);
        GL11.glScalef(5.0f, 5.0f, 5.0f);
        for (int itemId = 0; itemId < 20; ++itemId) {
            GL11.glRotatef(random.nextFloat() * 360.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(random.nextFloat() * 360.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(random.nextFloat() * 360.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(random.nextFloat() * 360.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(random.nextFloat() * 360.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(random.nextFloat() * 360.0f + f1 * 90.0f, 0.0f, 0.0f, 1.0f);
            tessellator.startDrawing(6);
            final float var11 = random.nextFloat() * 20.0f + 5.0f + 10.0f;
            final float f2 = random.nextFloat() * 2.0f + 1.0f + 2.0f;
            tessellator.setColorRGBA_I(GuiTrophyBox.renderColor[0], 125);
            tessellator.addVertex(0.0, 0.0, 0.0);
            tessellator.setColorRGBA_I(GuiTrophyBox.renderColor[1], 0);
            tessellator.addVertex(-0.866 * f2, (double)var11, (double)(-0.5f * f2));
            tessellator.addVertex(0.866 * f2, (double)var11, (double)(-0.5f * f2));
            tessellator.addVertex(0.0, (double)var11, (double)(1.0f * f2));
            tessellator.addVertex(-0.866 * f2, (double)var11, (double)(-0.5f * f2));
            tessellator.draw();
        }
        GL11.glEnable(2929);
        GL11.glDisable(2884);
        GL11.glDisable(3042);
        GL11.glShadeModel(7424);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glPopMatrix();
        if (this.temp_scale >= 8.0f) {
            final int itemsSpeed = 25;
            final int itemId = this.ticks % (itemsSpeed * GuiTrophyBox.Case.getSize()) / itemsSpeed;
            if (GuiTrophyBox.index == -1) {
                GuiTrophyBox.stack = GuiTrophyBox.Case.getCurrentItem(itemId).getItemStack();
                GuiTrophyBox.rarityText = GuiTrophyBox.Case.getCurrentItem(itemId).getText();
                GuiTrophyBox.rarityTextColor = GuiTrophyBox.Case.getCurrentItem(itemId).getTextColor();
                GuiTrophyBox.renderColor = GuiTrophyBox.Case.getCurrentItem(itemId).getRenderColor();
            }
            final String name = GuiTrophyBox.stack.getDisplayName();
            GuiImage.drawString(name, this.x_mid, this.y_mid - 80, 16777215, true, true);
            GuiImage.drawString(GuiTrophyBox.rarityText, this.x_mid + 3, this.y_mid + 75, GuiTrophyBox.rarityTextColor, true, true);
        }
        if (this.temp_scale < 8.0f) {
            this.temp_scale += 0.05f * this.ticks;
        }
        final float var12 = (super.width - 16.0f * this.temp_scale) / 2.0f;
        final float var11 = (super.height - 16.0f * this.temp_scale) / 2.0f;
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef(var12, var11, 0.0f);
        GL11.glScalef(this.temp_scale, this.temp_scale, 1.0f);
        new RenderItem().renderItemAndEffectIntoGUI(super.fontRendererObj, Minecraft.getMinecraft().getTextureManager(), GuiTrophyBox.stack, 0, 0);
        GL11.glPopMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    protected void actionPerformed(final GuiButton button) {
    }
    
    static {
        GuiTrophyBox.renderColor = new int[2];
    }
}
