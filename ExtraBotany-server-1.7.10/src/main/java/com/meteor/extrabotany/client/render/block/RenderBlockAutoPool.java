/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.block;

import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.common.block.tile.TileAutoPool;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(value= Side.CLIENT)
public class RenderBlockAutoPool
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.5f, (float)-0.6f, (float)-0.5f);
        GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
        TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)new TileAutoPool(), 0.0, 0.0, 0.0, 0.0f);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ClientProxy.renderAutoPool;
    }
}

