package mireille.client.renderer;

import cpw.mods.fml.client.registry.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.world.*;
import mireille.proxy.*;

public class BarrierBlockRendere implements ISimpleBlockRenderingHandler
{
    public void renderInventoryBlock(final Block block, final int metadata, final int modelId, final RenderBlocks renderer) {
    }
    
    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelId, final RenderBlocks renderer) {
        return true;
    }
    
    public boolean shouldRender3DInInventory(final int modelId) {
        return false;
    }
    
    public int getRenderId() {
        return ClientProxy.modelID2;
    }
}
