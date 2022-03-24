package mireille.client.gui.container;

import net.minecraft.client.gui.inventory.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import mireille.common.container.*;
import mireille.common.container.ContainerWorkbench;
import net.minecraft.inventory.*;
import mireille.*;
import org.lwjgl.opengl.*;

@SideOnly(Side.CLIENT)
public class GuiContainerWorkbench extends GuiContainer
{
    private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("mireille:textures/gui/mythical_work.png");
    private String customName;
    
    public void initGui() {
        super.initGui();
        super.xSize = 176;
        super.ySize = 184;
    }
    
    public GuiContainerWorkbench(final InventoryPlayer player, final World world, final int x, final int y, final int z) {
        super((Container)new ContainerWorkbench(player, world, x, y, z));
        this.customName = StrangerMind.resource("tile.Workbench.name");
    }
    
    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        super.fontRendererObj.drawString(this.customName, super.xSize / 2 - super.fontRendererObj.getStringWidth(this.customName) / 2, -11, 4210752);
    }
    
    protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
        GL11.glPushMatrix();
        this.mc.getTextureManager().bindTexture(GuiContainerWorkbench.craftingTableGuiTextures);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        GL11.glPopMatrix();
    }
}
