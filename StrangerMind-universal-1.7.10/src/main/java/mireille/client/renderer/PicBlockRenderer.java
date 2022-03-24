package mireille.client.renderer;

import cpw.mods.fml.client.registry.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import java.util.*;
import com.creativemd.creativecore.common.utils.*;
import net.minecraft.init.*;
import com.creativemd.creativecore.client.block.*;
import net.minecraft.world.*;
import mireille.common.tileentity.*;
import net.minecraftforge.common.util.*;
import net.minecraft.tileentity.*;
import mireille.proxy.*;

public class PicBlockRenderer implements ISimpleBlockRenderingHandler
{
    public void renderInventoryBlock(final Block block, final int metadata, final int modelId, final RenderBlocks renderer) {
        final ArrayList<CubeObject> cubes = new ArrayList<CubeObject>();
        cubes.add(new CubeObject(0.0, 0.0, 0.0, 0.05, 1.0, 1.0, Blocks.planks));
        BlockRenderHelper.renderInventoryCubes(renderer, (ArrayList)cubes, block, metadata);
    }
    
    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelId, final RenderBlocks renderer) {
        final ArrayList<CubeObject> cubes = new ArrayList<CubeObject>();
        cubes.add(new CubeObject(0.0, 0.0, 0.0, 0.05, 1.0, 1.0, Blocks.planks));
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityPicFrame && ((TileEntityPicFrame)te).visibleFrame) {
            BlockRenderHelper.renderCubes(world, (ArrayList)cubes, x, y, z, block, renderer, ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)));
        }
        return true;
    }
    
    public boolean shouldRender3DInInventory(final int modelId) {
        return true;
    }
    
    public int getRenderId() {
        return ClientProxy.modelID;
    }
}
