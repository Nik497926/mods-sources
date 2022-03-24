package mireille.client.renderer.item;

import net.minecraftforge.client.*;
import cpw.mods.fml.relauncher.*;
import mireille.client.renderer.tileentity.*;
import mireille.common.tileentity.*;
import net.minecraft.item.*;
import org.lwjgl.opengl.*;
import net.minecraft.tileentity.*;

@SideOnly(Side.CLIENT)
public class RenderItemSkinWorkbench implements IItemRenderer
{
    private RenderSkinWorkbench render;
    private TileSkinWorkbench tile;
    
    public RenderItemSkinWorkbench() {
        this.render = new RenderSkinWorkbench();
        this.tile = new TileSkinWorkbench();
    }
    
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return true;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        GL11.glPushMatrix();
        this.render.renderTileEntityAt(this.tile, 0.0, 0.0, 0.0, 0.0f);
        GL11.glPopMatrix();
    }
}
