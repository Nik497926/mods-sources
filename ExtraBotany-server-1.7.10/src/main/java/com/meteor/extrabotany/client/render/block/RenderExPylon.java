/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.block;

import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.client.render.tile.RenderTileExPylon;
import com.meteor.extrabotany.common.block.tile.TileExPylon;
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
public class RenderExPylon
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.5f, (float)-0.7f, (float)-0.5f);
        RenderTileExPylon.green = metadata == 1;
        RenderTileExPylon.pink = metadata == 2;
        TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)new TileExPylon(), 0.0, 0.0, 0.0, 0.0f);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public int getRenderId() {
        return ClientProxy.renderExPylon;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}

