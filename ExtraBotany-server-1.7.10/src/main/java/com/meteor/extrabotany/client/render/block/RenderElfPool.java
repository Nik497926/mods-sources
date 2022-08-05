/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.block;

import com.meteor.extrabotany.common.block.tile.TileElfPool;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.render.tile.RenderTilePool;

@SideOnly(value= Side.CLIENT)
public class RenderElfPool
implements ISimpleBlockRenderingHandler {
    public static final int ID = RenderingRegistry.getNextAvailableRenderId();

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        RenderTilePool.forceMeta = metadata;
        RenderTilePool.forceMana |= metadata == 1;
        TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)new TileElfPool(), 0.0, 0.0, 0.0, 0.0f);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public int getRenderId() {
        return ID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}

