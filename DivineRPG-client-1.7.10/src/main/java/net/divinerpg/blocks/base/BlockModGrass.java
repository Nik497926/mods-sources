/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.items.arcana.ItemArcanaSeeds;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockModGrass
extends BlockMod {
    protected IIcon top;
    protected IIcon bottom;
    protected IIcon side;
    protected BlockMod dirt;
    protected String dirtName;

    public BlockModGrass(BlockMod dirt, String name, String dirtName, float hardness) {
        super(EnumBlockType.GRASS, name, hardness);
        this.dirt = dirt;
        this.textureName = "divinerpg:" + name;
        this.dirtName = "divinerpg:" + dirtName;
        this.setTextureName(this.textureName);
        this.setTickRandomly(true);
        this.setHarvestLevel("shovel", 3);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int intSide, int meta) {
        return intSide == 1 ? this.top : (intSide == 0 ? this.bottom : this.side);
    }

    @Override
    public Item getItemDropped(int par1, Random rand, int par3) {
        return Item.getItemFromBlock((Block)this.dirt);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.side = icon.registerIcon(this.textureName + "_side");
        this.top = icon.registerIcon(this.textureName + "_top");
        this.bottom = icon.registerIcon(this.dirtName);
    }

    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            if (world.getBlockLightOpacity(x, y + 1, z) > 2) {
                world.setBlock(x, y, z, (Block)this.dirt);
            } else {
                for (int l = 0; l < 4; ++l) {
                    int k1;
                    int j1;
                    int i1 = x + random.nextInt(3) - 1;
                    if (world.getBlock(i1, j1 = y + random.nextInt(5) - 3, k1 = z + random.nextInt(3) - 1) != this.dirt || world.getBlockLightOpacity(i1, j1 + 1, k1) > 2) continue;
                    world.setBlock(i1, j1, k1, (Block)this);
                }
            }
        }
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        return this == ArcanaBlocks.arcanaGrass && plantable instanceof ItemArcanaSeeds;
    }
}

