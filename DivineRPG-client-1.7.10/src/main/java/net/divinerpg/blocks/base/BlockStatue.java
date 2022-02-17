/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.base;

import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.client.model.DivineModel;
import net.divinerpg.client.render.block.TileEntityStatue;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BlockStatue
extends BlockMod {
    protected DivineModel model;
    protected ResourceLocation texture;

    public BlockStatue(String name, DivineModel model) {
        super(EnumBlockType.ROCK, name, 6.0f);
        this.setTextureName("cobblestone");
        this.texture = new ResourceLocation("divinerpg:textures/model/" + name + ".png");
        this.model = model;
        this.setHarvestLevel("pickaxe", 0);
    }

    public DivineModel getModel() {
        return this.model;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityStatue(this.texture, this.model);
    }

    public int getRenderType() {
        return -1;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
        int l = ((MathHelper.floor_double((double)((double)(entity.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3) + 1) % 4;
        int i1 = w.getBlockMetadata(x, y, z);
        if (l == 0) {
            w.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (l == 1) {
            w.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (l == 2) {
            w.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }
        if (l == 3) {
            w.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }
    }
}

