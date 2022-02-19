/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.IBlockAccess
 */
package info.jbcs.minecraft.vending.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import info.jbcs.minecraft.vending.Vending;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class BlockVendingMachineRenderer
implements ISimpleBlockRenderingHandler {
    public static int id;

    void drawBlock(Block block, int meta, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, -1.0f, 0.0f);
        renderer.renderFaceYNeg(block, 0.0, 0.0, 0.0, block.getIcon(0, meta));
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        renderer.renderFaceYPos(block, 0.0, 0.0, 0.0, block.getIcon(1, meta));
        tessellator.setNormal(0.0f, 0.0f, -1.0f);
        renderer.renderFaceZNeg(block, 0.0, 0.0, 0.0, block.getIcon(2, meta));
        tessellator.setNormal(0.0f, 0.0f, 1.0f);
        renderer.renderFaceZPos(block, 0.0, 0.0, 0.0, block.getIcon(3, meta));
        tessellator.setNormal(-1.0f, 0.0f, 0.0f);
        renderer.renderFaceXNeg(block, 0.0, 0.0, 0.0, block.getIcon(4, meta));
        tessellator.setNormal(1.0f, 0.0f, 0.0f);
        renderer.renderFaceXPos(block, 0.0, 0.0, 0.0, block.getIcon(5, meta));
        tessellator.draw();
    }

    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
        this.drawBlock(block, meta, renderer);
        renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, 0.125, 1.0);
        this.drawBlock(Vending.supports[meta], 0, renderer);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int meta = world.getBlockMetadata(x, y, z);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, 0.125, 1.0);
        renderer.renderStandardBlock(Vending.supports[meta], x, y, z);
        return false;
    }

    public boolean shouldRender3DInInventory(int var1) {
        return true;
    }

    public int getRenderId() {
        return id;
    }
}

