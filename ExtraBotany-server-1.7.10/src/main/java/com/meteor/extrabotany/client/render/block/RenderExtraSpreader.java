/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.block;

import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.common.block.tile.TileExtraSpreader;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderExtraSpreader
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        TileExtraSpreader.staticRedstone = metadata == 1;
        TileExtraSpreader.staticUltra = false;
        TileExtraSpreader spreader = new TileExtraSpreader();
        spreader.rotationX = -180.0f;
        TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)spreader, 0.0, 0.0, 0.0, 0.0f);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public int getRenderId() {
        return ClientProxy.renderExtraSpreader;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}

